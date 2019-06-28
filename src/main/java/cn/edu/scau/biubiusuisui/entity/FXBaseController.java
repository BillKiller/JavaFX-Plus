package cn.edu.scau.biubiusuisui.entity;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.config.FXMLLoaderPlus;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import cn.edu.scau.biubiusuisui.utils.StringUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author jack
 * @Date:2019/6/25 5:51
 */

/**
 *  In JavaFX-Plus Framework Controller
 *  We use MVC model
 *  V means view which stand for fxml
 *  C means controller which stand for FXBaseController instance
 *  M means model  which is base cn.edu.scau.biubiusuisui.entity in your program
 *  Every BaseController has a name which is used for identifying different  <strong>instance</strong>
 *
 */
public  class FXBaseController extends Pane  {

    protected String name = "";

    private boolean isController = false;

    private boolean isWindows = false;

    public FXBaseController(String name){
        this.name = name;
    }

    public FXBaseController(){
        FXController fxController = null;
        Annotation[] annotations = getClass().getAnnotations();
        // Find FXController cn.edu.scau.biubiusuisui.annotation
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(FXController.class)) {
                fxController = (FXController) annotation;
                isController = true;
            }
        }
        //load fxml file to show panel in scene builder
        if(isController) {
            FXMLLoaderPlus fxmlLoader = new FXMLLoaderPlus(getClass().getClassLoader().getResource(fxController.path()));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void initialize() {

    }

    public String getName() {
        if("".equals(name)){
            return StringUtils.getBaseClassName(getClass().getSimpleName());
        }else{
            return StringUtils.getBaseClassName(getClass().getSimpleName()) +"#"+name;
        }
    }
    public void doInit(){}

    public void setName(String name) {
        this.name = name;
    }

    public boolean isController() {
        return isController;
    }

    public void setController(boolean controller) {
        isController = controller;
    }

    public boolean isWindows() {
        return isWindows;
    }

    public void setWindows(boolean windows) {
        isWindows = windows;
    }
}
