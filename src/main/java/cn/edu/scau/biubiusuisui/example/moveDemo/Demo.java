package cn.edu.scau.biubiusuisui.example.moveDemo;

import cn.edu.scau.biubiusuisui.annotation.FXScan;
import cn.edu.scau.biubiusuisui.config.FXPlusApplication;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @Author jack
 * @Date:2019/6/25 7:05
 */
@FXScan(base = {"cn.edu.scau.biubiusuisui.example.moveDemo"})
//项目目录中带有中文字符会导致无法启动
public class Demo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXPlusApplication.start(Demo.class);
    }
}
