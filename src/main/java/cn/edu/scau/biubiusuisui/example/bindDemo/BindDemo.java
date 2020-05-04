package cn.edu.scau.biubiusuisui.example.bindDemo;

import cn.edu.scau.biubiusuisui.annotation.FXScan;
import cn.edu.scau.biubiusuisui.config.FXPlusApplication;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author jack
 * @author suisui
 * @version 1.2
 * @date 2020/5/1 1:43
 * @since JavaFX2.0 JDK1.8
 */
@FXScan(base = "cn.edu.scau.biubiusuisui.example.bindDemo")
public class BindDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXPlusApplication.start(BindDemo.class);
    }
}
