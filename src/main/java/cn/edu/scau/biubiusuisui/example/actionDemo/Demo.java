package cn.edu.scau.biubiusuisui.example.actionDemo;

import cn.edu.scau.biubiusuisui.annotation.FXScan;
import cn.edu.scau.biubiusuisui.config.FXPlusApplication;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @Author jack
 * @Date:2019/7/27 1:43
 */
@FXScan(base = "cn.edu.scau.biubiusuisui.example.actionDemo")
public class Demo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXPlusApplication.start(Demo.class);
    }
}
