package cn.edu.scau.biubiusuisui.example.resizableDemo;

import cn.edu.scau.biubiusuisui.annotation.FXScan;
import cn.edu.scau.biubiusuisui.config.FXPlusApplication;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author suiyu_yang
 * @description 缩放和拖拽的示例
 * @date 2020/4/5 00:04
 * @email suiyu_yang@163.com
 */
@FXScan(base = "cn.edu.scau.biubiusuisui.example.resizableDemo")
public class Demo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXPlusApplication.start(Demo.class);
    }
}
