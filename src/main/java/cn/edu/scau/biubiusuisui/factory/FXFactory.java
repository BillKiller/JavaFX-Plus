package cn.edu.scau.biubiusuisui.factory;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.config.FXMLLoaderPlus;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.entity.FXPlusContext;
import cn.edu.scau.biubiusuisui.example.Student;
import cn.edu.scau.biubiusuisui.function.FXExpressionParser;
import cn.edu.scau.biubiusuisui.function.FXWindowParser;
import cn.edu.scau.biubiusuisui.messageQueue.MessageQueue;
import cn.edu.scau.biubiusuisui.proxy.classProxy.FXControllerProxy;
import com.sun.javafx.fxml.BeanAdapter;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Map;

/**
 * @Author jack
 * @Date:2019/6/25 8:12
 */
public class FXFactory {


    private static final BeanBuilder BEAN_BUILDER = new FXBuilder();
    private static FXWindowParser windowAnnotationParser = new FXWindowParser();
    private static FXExpressionParser expressionParser = new FXExpressionParser();


    private FXFactory() {
    }

    public static void loadFXController(Class clazz, BeanBuilder beanBuilder) {
        if (clazz.getDeclaredAnnotation(FXWindow.class) != null) {
            getFXController(clazz, null, beanBuilder);
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

    private static void register(FXBaseController fxBaseController, FXBaseController fxBaseControllerProxy) {
        FXPlusContext.addController(fxBaseController); //保存
        MessageQueue.getInstance().registerCosumer(fxBaseController, fxBaseControllerProxy); // 添加进入消息队列 信号功能
    }

    private static FXBaseController getFxBaseController(Class clazz, String controllerName) {

        return null;
    }

    /**
     * @param clazz          instance that extends by FXBaseController
     * @param controllerName
     * @return
     */

    private static FXBaseController getFxBaseController(Class clazz, String controllerName, BeanBuilder beanBuilder) {
        Boolean isController = false;
        URL fxmlPath;
        Boolean isWindow = false;
        FXWindow fxWindow = null;
        FXController fxController = null;
        //reflect and get FXController cn.edu.scau.biubiusuisui.annotation
        fxController = (FXController) clazz.getDeclaredAnnotation(FXController.class);
        fxWindow = (FXWindow) clazz.getDeclaredAnnotation(FXWindow.class);
        Pane parent = null;

        FXBaseController fxControllerProxy = null;
        FXBaseController fxBaseController = null;

        if (fxController != null) {
            String name = fxController.path();
            fxmlPath = clazz.getClassLoader().getResource(name);
            FXMLLoaderPlus fxmlLoader = new FXMLLoaderPlus(fxmlPath);
            // create a cn.edu.scau.biubiusuisui.proxy for monitoring methods
            fxBaseController = (FXBaseController) beanBuilder.getBean(clazz);
            if (fxBaseController != null) {
                FXControllerProxy controllerProxy = new FXControllerProxy();
                fxControllerProxy = (FXBaseController) controllerProxy.getInstance(fxBaseController);

                fxmlLoader.setRoot(fxControllerProxy);
                fxmlLoader.setController(fxControllerProxy);
                try {
                    parent = fxmlLoader.load();
                    if (controllerName != null) {
                        fxControllerProxy.setName(controllerName);
                        fxBaseController.setName(controllerName);
                    } else {
                        fxBaseController.setName(parent.getId());
                    }
                    register(fxBaseController, fxControllerProxy);
                } catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
            }
        } else {
            return null;
        }
        //register

        if (fxWindow != null) {
            createWindow(fxWindow, fxControllerProxy, fxBaseController);
        }
        return fxControllerProxy;
    }

    private static void createWindow(FXWindow fxWindow, FXBaseController fxControllerProxy, FXBaseController fxBaseController) {
        Stage stage = new Stage();
        fxControllerProxy.setStage(stage);
        fxBaseController.setStage(stage);
        double preWidth = fxWindow.preWidth() == 0 ? fxControllerProxy.getPrefWidth() : fxWindow.preWidth();
        double preHeight = fxWindow.preHeight() == 0 ? fxControllerProxy.getPrefWidth() : fxWindow.preHeight();
        Scene scene = new Scene(fxControllerProxy, preWidth, preHeight);
        stage.setScene(scene);
        windowAnnotationParser.parse(stage, fxControllerProxy, fxWindow);
        stage.show();
    }

    private static void parseControllerBindExpression(FXBaseController fxBaseController, ObservableMap<String, Object> namespaces) {
        expressionParser.parse(fxBaseController, namespaces);
    }

}
