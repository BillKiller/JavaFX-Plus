package cn.edu.scau.biubiusuisui.example.redirectDemo;

import cn.edu.scau.biubiusuisui.annotation.FXScan;
import cn.edu.scau.biubiusuisui.config.FXPlusApplication;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author suisui
 * @version 1.1
 * @description 测试重定向功能的Application
 * @date 2019/12/3 11:52
 * @since JavaFX2.0 JDK1.8
 */
@FXScan(base = "cn.edu.scau.biubiusuisui.example.redirectDemo")
public class RedirectDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXPlusApplication.start(RedirectDemo.class);
    }
}
