package cn.edu.scau.biubiusuisui.entity;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.config.FXMLLoaderPlus;
import cn.edu.scau.biubiusuisui.config.FXPlusApplication;
import cn.edu.scau.biubiusuisui.log.FXPlusLoggerFactory;
import cn.edu.scau.biubiusuisui.log.IFXPlusLogger;
import cn.edu.scau.biubiusuisui.utils.ResourceBundleUtil;
import cn.edu.scau.biubiusuisui.utils.StringUtil;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * In JavaFX-Plus Framework Controller
 * We use MVC model
 * V means view which stand for fxml
 * C means controller which stand for FXBaseController instance
 * M means model  which is base cn.edu.scau.biubiusuisui.entity in your program
 * Every BaseController has a name which is used for identifying different  <strong>instance</strong>
 */

/**
 * @author jack
 * @author suisui
 * @version 1.0
 * @date 2019/6/25 5:51
 * @since JavaFX2.0 JDK1.8
 */
public class FXBaseController extends Pane {
    private static IFXPlusLogger logger = FXPlusLoggerFactory.getLogger(FXBaseController.class);

    protected String name = "";
    private Stage stage;
    private boolean isController = false;
    private boolean isWindow = false;


    /**
     * @description 用于携带信息数据
     * @version 1.2
     */
    private Map<String, Object> query = new HashMap<>();
    private Map<String, Object> param = new HashMap<>();

    public FXBaseController(String name) {
        this.name = name;
    }

    public FXBaseController() {
        FXController fxController = null;
        FXWindow fxWindow = null;
        Annotation[] annotations = getClass().getAnnotations();
        // Find FXController cn.edu.scau.biubiusuisui.annotation
        for (Annotation annotation : annotations) {
            // 是否Controller
            if (annotation.annotationType().equals(FXController.class)) {
                fxController = (FXController) annotation;
                isController = true;
            }
            // 添加赋予是否为窗口的逻辑
            if (annotation.annotationType().equals(FXWindow.class)) {
                fxWindow = (FXWindow) annotation;
                isWindow = true;
            }
        }
        //load fxml file to show panel in scene builder
        if (isController && FXPlusApplication.IS_SCENE_BUILDER == true) {
            logger.info("loading the FXML file of " + this.getName());
            URL location = getClass().getClassLoader().getResource(fxController.path());
            String fxmlBaseName = StringUtil.getFilePathInResources(fxController.path());
            ResourceBundle resourceBundle = ResourceBundleUtil.getResourceBundle(fxmlBaseName, fxController.locale());
            FXMLLoaderPlus fxmlLoader = new FXMLLoaderPlus(location);
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.setShow(true);
            fxmlLoader.setResources(resourceBundle);
            try {
                // 加载前
                onLoad();
                fxmlLoader.load();
            } catch (IOException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * @description 相当于onReady, 页面渲染完后的操作
     * @version 1.2
     */
    public void initialize() throws Exception {
    }

    /**
     * @description 初始化onShow, onHide, onClose的生命周期
     * @version 1.2
     */
    public final void initLifeCycle() {
        logger.info("init the life cycle of " + this.getName());
        this.stage.setOnShowing(event -> {
            try {
                onShow();
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        });
        this.stage.setOnCloseRequest(event -> {
            try {
                onClose();
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        });
        // 监听最小化窗口
        this.stage.iconifiedProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (newValue) { //最小化
                    onHide();
                } else {
                    onShow(); //取消最小化
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        });
    }

    /**
     * @description 在加载页面之前的操作
     * @version 1.2
     */
    public void onLoad() throws Exception {
    }

    /**
     * @description 在显示页面之前的操作
     * @version 1.2
     */
    public void onShow() throws Exception {
    }

    /**
     * @description 在关闭窗口之前的操作
     * @version 1.2
     */
    public void onClose() throws Exception {
    }

    /**
     * @description 在隐藏窗口之前的操作
     * @version 1.2
     */
    public void onHide() throws Exception {
    }

    /**
     * 唤起舞台
     */
    public void showStage() {
        if (isWindow) {
            this.stage.show();
        }
    }

    public void showAndWait() {
        if (isWindow) {
            this.stage.showAndWait();
        }
    }

    /**
     * 关闭舞台
     */
    public void closeStage() {
        if (isWindow) {
            this.stage.close();
        }
    }

    /**
     * @description 最小化
     * @version 1.2
     */
    public void hideStage() {
        if (isWindow) {
            this.stage.setIconified(true);
        }
    }

    public String getName() {
        if ("".equals(name) || name == null) { // 原本无“name == null”判断条件，会出错
            return StringUtil.getBaseClassName(getClass().getSimpleName());
        } else {
            return StringUtil.getBaseClassName(getClass().getSimpleName()) + "#" + name;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isController() {
        return isController;
    }

    public void setController(boolean controller) {
        isController = controller;
    }

    public boolean isWindow() {
        return isWindow;
    }

    public void setWindow(boolean window) {
        isWindow = window;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Map<String, Object> getQuery() {
        return query;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setQuery(Map<String, Object> query) {
        this.query = query;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }
}
