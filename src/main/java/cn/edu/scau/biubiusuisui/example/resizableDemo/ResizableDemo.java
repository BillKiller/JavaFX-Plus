package cn.edu.scau.biubiusuisui.example.resizableDemo;

import cn.edu.scau.biubiusuisui.annotation.FXScan;
import cn.edu.scau.biubiusuisui.config.FXPlusApplication;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author suisui
 * @version 1.0
 * @description 缩放和拖拽的示例
 * @date 2020/4/5 00:04
 * @since JavaFX2.0 JDK1.8
 */
@FXScan(base = "cn.edu.scau.biubiusuisui.example.resizableDemo")
public class ResizableDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXPlusApplication.start(ResizableDemo.class);
    }
}
