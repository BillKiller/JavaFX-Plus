package cn.edu.scau.biubiusuisui.example.redirectDemo;

import cn.edu.scau.biubiusuisui.annotation.FXScan;
import cn.edu.scau.biubiusuisui.config.FXPlusApplication;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author suiyu_yang
 * @description 测试重定向功能的Application
 * @date 2019/12/3 11:52
 * @email suiyu_yang@163.com
 */
@FXScan(base = "cn.edu.scau.biubiusuisui.example.redirectDemo")
public class RedirectDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXPlusApplication.start(RedirectDemo.class);
    }
}
