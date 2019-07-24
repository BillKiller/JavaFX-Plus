package cn.edu.scau.biubiusuisui.example.listViewExpressionDemo;

import cn.edu.scau.biubiusuisui.annotation.FXScan;
import cn.edu.scau.biubiusuisui.config.FXPlusApplication;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @Author jack
 * @Date:2019/7/24 22:56
 */
@FXScan(base = {"cn.edu.scau.biubiusuisui.example.expressionDemo"})
//项目目录中带有中文字符会导致无法启动
public class Demo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXPlusApplication.start(Demo.class);
    }
}
