package cn.edu.scau.biubiusuisui.example.mqDemo;

import cn.edu.scau.biubiusuisui.annotation.FXScan;
import cn.edu.scau.biubiusuisui.config.FXPlusApplication;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author suiyu_yang
 * @description
 * @date 2019/12/8 13:17
 * @email suiyu_yang@163.com
 */
@FXScan(base = "cn.edu.scau.biubiusuisui.example.mqDemo")
public class MQDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXPlusApplication.start(MQDemo.class);
    }
}
