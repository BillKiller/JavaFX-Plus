package cn.edu.scau.biubiusuisui.entity;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.config.FXMLLoaderPlus;
import cn.edu.scau.biubiusuisui.config.FXPlusApplication;
import cn.edu.scau.biubiusuisui.exception.NotFXWindowException;
import cn.edu.scau.biubiusuisui.utils.StringUtils;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author jack
 * @Date:2019/6/25 5:51
 */

/**
 * In JavaFX-Plus Framework Controller
 * We use MVC model
 * V means view which stand for fxml
 * C means controller which stand for FXBaseController instance
 * M means model  which is base cn.edu.scau.biubiusuisui.entity in your program
 * Every BaseController has a name which is used for identifying different  <strong>instance</strong>
 */
public class FXBaseController extends Pane {

    protected String name = "";
    private Stage stage;
    private boolean isController = false;
    private boolean isWindow = false;


    /**
     * @Author: yangsuiyu
     * @Descriptions: 用于携带信息数据
     */
    private Map<String, Object> query = new HashMap<>();
    private Map<String, Object> param = new HashMap<>();

    public FXBaseController(String name) {
        this.name = name;
    }

    public FXBaseController() {
        FXController fxController = null;
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
                isWindow = true;
            }
        }
        //load fxml file to show panel in scene builder
        if (isController && FXPlusApplication.IS_SCENE_BUILDER == true) {
            FXMLLoaderPlus fxmlLoader = new FXMLLoaderPlus(getClass().getClassLoader().getResource(fxController.path()));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.setShow(true);
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initialize() {

    }

    /**
     * 在显示Stage之前的操作
     */
    public void beforeShowStage() {

    }

    /**
     * 唤起舞台
     */
    public void showStage() {
        this.beforeShowStage();
        if (isWindow) {
            this.stage.show();

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

    public void showAndWait() {
        this.beforeShowStage();
        if (isWindow) {
            this.stage.showAndWait();
        }
    }

    public String getName() {
        if ("".equals(name) || name == null) { // 原本无“name == null”判断条件，会出错
            return StringUtils.getBaseClassName(getClass().getSimpleName());
        } else {
            return StringUtils.getBaseClassName(getClass().getSimpleName()) + "#" + name;
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
