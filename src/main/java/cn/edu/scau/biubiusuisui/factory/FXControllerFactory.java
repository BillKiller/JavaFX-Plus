package cn.edu.scau.biubiusuisui.factory;

import cn.edu.scau.biubiusuisui.annotation.FXBind;
import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXData;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.config.FXMLLoaderPlus;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.entity.FXPlusContext;
import cn.edu.scau.biubiusuisui.exception.NoSuchChangeMethod;
import cn.edu.scau.biubiusuisui.expression.data.ExpressionParser;
import cn.edu.scau.biubiusuisui.function.FXWindowParser;
import cn.edu.scau.biubiusuisui.messageQueue.MessageQueue;
import cn.edu.scau.biubiusuisui.proxy.FXControllerProxy;
import cn.edu.scau.biubiusuisui.stage.StageManager;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;

/**
 * @Author jack
 * @Date:2019/6/25 8:12
 */
public class FXControllerFactory {

    private static final BeanBuilder BEAN_BUILDER = new FXBuilder();
    private static FXWindowParser fxWindowAnnotationParser = new FXWindowParser();


    /**
     * 控制类的注入流程
     * 这是一个即将被创建的控制类
     * <pre>
     *   <code>
     *    class MainController{
     *          @Autowired
     *          Student stu; //普通属性
     *          @FXML
     *          Button btn; //FX属性
     *    }
     *   </code>
     *   </pre>
     * 1. 实现对普通属性的注入
     * 如果使用了Spring那么这类会自动注入那些@Autowired的属性，请不要将这个方法用在控制器属性中
     * <pre>
     *   <code>
     *    class MainController{
     *          @Autowired
     *          Student stu ;  //初始化完成
     *          @FXML
     *          Button btn;  // null
     *    }
     *   </code>
     *   </pre>
     * 2. 通过loadFXML实现对FX属性的注入
     * <pre>
     *   <code>
     *    class MainController{
     *          @Autowired
     *          Student stu ;  //初始化完成
     *          @FXML
     *          Button btn;  // 初始化完成
     *    }
     *   </code>
     *   </pre>
     * <p>
     * 3.  完成对FXBind的注解的解析
     * <p>
     * <p>
     * <p>
     * 4.  完成注册
     *
     * @param clazz          instance that extends by FXBaseController
     * @param controllerName
     * @return
     */
    private static FXBaseController getFxBaseController(Class clazz, String controllerName, BeanBuilder beanBuilder) {
        return getFxBaseController0(clazz, controllerName, beanBuilder);
    }

    private static FXBaseController getFxBaseController0(Class clazz, String controllerName, BeanBuilder beanBuilder) {
        URL fxmlPath;
        FXController fxController = null;        //reflect and get FXController cn.edu.scau.biubiusuisui.annotation
        fxController = (FXController) clazz.getDeclaredAnnotation(FXController.class);
        Pane parent = null;
        FXBaseController fxBaseController = null;
        FXBaseController fxControllerProxy = null;
        if (fxController != null) {
            String fxmlPathName = fxController.path();
            fxmlPath = clazz.getClassLoader().getResource(fxmlPathName);
            FXMLLoaderPlus fxmlLoader = new FXMLLoaderPlus(fxmlPath);
            // create a cn.edu.scau.biubiusuisui.proxy for monitoring methods

            fxBaseController = (FXBaseController) beanBuilder.getBean(clazz); //获取controller实例
            parseData(fxBaseController);

            if (fxBaseController != null) {
                FXControllerProxy controllerProxy = new FXControllerProxy();
                fxControllerProxy = (FXBaseController) controllerProxy.getInstance(fxBaseController); //产生代理从而实现赋能
                fxmlLoader.setRoot(fxControllerProxy);
                fxmlLoader.setController(fxControllerProxy);
                fxmlLoader.setBaseController(fxBaseController);
                try {
                    parent = fxmlLoader.load();
                    if (controllerName != null) {
                        fxControllerProxy.setName(controllerName);
                        fxBaseController.setName(controllerName);
                    } else {
                        fxBaseController.setName(parent.getId());
                    }
                    ObservableMap namespace = fxmlLoader.getNamespace();
                    addDataInNameSpace(namespace, fxBaseController); //处理fxBaseController里面的@FXData
                    scanBind(namespace, fxBaseController); //处理@FXBind
                    register(fxBaseController, fxControllerProxy);
                } catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
            }
        } else {
            return null;
        }
        return fxControllerProxy;
    }


    /**
     * 将代理对象和目标对象注册
     *
     * @param fxBaseController      目标对象
     * @param fxBaseControllerProxy 代理对象
     */
    private static void register(FXBaseController fxBaseController, FXBaseController fxBaseControllerProxy) {
        FXPlusContext.registerController(fxBaseController); //保存
        MessageQueue.getInstance().registerConsumer(fxBaseController, fxBaseControllerProxy); // 添加进入消息队列 信号功能
    }

    /**
     * @param fxWindow
     * @param clazz
     * @param fxBaseControllerProxy
     * @return
     * @Description 为有FXWindow注解的类创建Stage
     */
    private static Stage createWindow(FXWindow fxWindow, Class clazz, FXBaseController fxBaseControllerProxy) {
        Stage stage = new Stage();
        fxBaseControllerProxy.setStage(stage);
        double preWidth = fxWindow.preWidth() == 0 ? fxBaseControllerProxy.getPrefWidth() : fxWindow.preWidth();
        double preHeight = fxWindow.preHeight() == 0 ? fxBaseControllerProxy.getPrefHeight() : fxWindow.preHeight();
        Scene scene = new Scene(fxBaseControllerProxy, preWidth, preHeight);
        stage.setScene(scene);
        fxWindowAnnotationParser.parse(stage, fxBaseControllerProxy, fxWindow);

        StageManager.getInstance().registerWindow(clazz, fxBaseControllerProxy);  //注册舞台
        if (fxWindow.mainStage() == true) {  //当是主舞台时，先show为敬
            fxBaseControllerProxy.showStage();
        }
        return stage;
    }

    private FXControllerFactory() {

    }

    /**
     * 加载舞台
     * 原函数名为loadMainStage(Class clazz, BeanBuilder beanBuilder)
     *
     * @param clazz
     * @param beanBuilder
     */
    public static void loadStage(Class clazz, BeanBuilder beanBuilder) {
        FXWindow declaredAnnotation = (FXWindow) clazz.getDeclaredAnnotation(FXWindow.class);
        //只有当用了FXWindow注解，才会注册Stage
        if (declaredAnnotation != null) {
            getFXWindow(clazz, null, beanBuilder);
        }
    }

    public static FXBaseController getFXController(Class clazz) {
        return getFXController(clazz, BEAN_BUILDER);
    }

    public static FXBaseController getFXController(Class clazz, BeanBuilder beanBuilder) {
        FXBaseController fxBaseController = getFXController(clazz, null, beanBuilder);
        return fxBaseController;
    }

    public static FXBaseController getFXController(Class clazz, String controllerName) {
        return getFXController(clazz, controllerName, BEAN_BUILDER);
    }

    public static FXBaseController getFXController(Class clazz, String controllerName, BeanBuilder beanBuilder) {
        FXBaseController fxBaseController = getFxBaseController(clazz, controllerName, beanBuilder);
        return fxBaseController;
    }


    public static Stage getFXWindow(Class clazz) {
        FXWindow fxWindow = (FXWindow) clazz.getDeclaredAnnotation(FXWindow.class);
        if (fxWindow != null) {
            FXBaseController fxController = getFXController(clazz, null, BEAN_BUILDER);
            return createWindow(fxWindow, clazz, fxController);
        } else {
            return null;
        }
    }

    public static Stage getFXWindow(Class clazz, BeanBuilder beanBuilder) {
        FXWindow fxWindow = (FXWindow) clazz.getDeclaredAnnotation(FXWindow.class);
        if (fxWindow != null) {
            FXBaseController fxController = getFXController(clazz, null, beanBuilder);
            return createWindow(fxWindow, clazz, fxController);
        } else {
            return null;
        }
    }

    public static Stage getFXWindow(Class clazz, String controllerName) {
        FXWindow fxWindow = (FXWindow) clazz.getDeclaredAnnotation(FXWindow.class);
        if (fxWindow != null) {
            FXBaseController fxController = getFXController(clazz, controllerName, BEAN_BUILDER);
            return createWindow(fxWindow, clazz, fxController);
        } else {
            return null;
        }
    }

    public static Stage getFXWindow(Class clazz, String controllerName, BeanBuilder beanBuilder) {
        FXWindow fxWindow = (FXWindow) clazz.getDeclaredAnnotation(FXWindow.class);
        if (fxWindow != null) {
            FXBaseController fxController = getFXController(clazz, controllerName, beanBuilder);
            return createWindow(fxWindow, clazz, fxController);
        } else {
            return null;
        }
    }

    private static void parseData(Object fxControllerObject) {
        Class clazz = fxControllerObject.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            FXData annotation = field.getAnnotation(FXData.class);
            if (annotation != null) {
                field.setAccessible(true);
                //建立代理
                try {
                    Object fieldValue = field.get(fxControllerObject);
                    Object fieldValueProxy = FXEntityFactory.wrapFXBean(fieldValue);
                    field.set(fxControllerObject, fieldValueProxy);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void addDataInNameSpace(ObservableMap namespace, Object object) {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            FXData annotation = field.getAnnotation(FXData.class);
            if (annotation != null) {
                field.setAccessible(true);
                try {
                    String fx_id;
                    field.setAccessible(true);
                    if ("".equals(annotation.fx_id())) {
                        fx_id = field.getName();
                    } else {
                        fx_id = annotation.fx_id();
                    }
                    Object fieldValue = field.get(object);
                    namespace.put(fx_id, fieldValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void scanBind(ObservableMap namespace, Object object) {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            parseBind(namespace, object, field);
        }
    }

    private static void parseBind(ObservableMap namespace, Object object, Field field) {
        FXBind fxBind = field.getAnnotation(FXBind.class);
        field.setAccessible(true);
        ExpressionParser expressionParser = new ExpressionParser(namespace, object);
        if (fxBind != null) {
            String[] expressions = fxBind.value();
            try {
                Object objectValue = field.get(object);
                for (String e : expressions) {
                    expressionParser.parse(objectValue, e);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchChangeMethod noSuchChangeMethod) {
                noSuchChangeMethod.printStackTrace();
            }
        }
    }

    private static boolean isFXWindow(Class clazz) {
        if (clazz.getDeclaredAnnotation(FXWindow.class) != null) {
            return true;
        } else {
            return false;
        }
    }
}
