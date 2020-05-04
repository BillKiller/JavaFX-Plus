package cn.edu.scau.biubiusuisui.example.mqDemo;

import cn.edu.scau.biubiusuisui.annotation.FXScan;
import cn.edu.scau.biubiusuisui.config.FXPlusApplication;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author suisui
 * @version 1.1
 * @description
 * @date 2019/12/8 13:17
 * @since JavaFX2.0 JDK1.8
 */
@FXScan(base = "cn.edu.scau.biubiusuisui.example.mqDemo")
public class MQDemo extends Application {

    @Override
    public void init() throws Exception {
        System.out.println("application init");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("application start");
        FXPlusApplication.start(MQDemo.class);
    }

    @Override
    public void stop() throws Exception {
        System.out.println("application stop");
    }
}
