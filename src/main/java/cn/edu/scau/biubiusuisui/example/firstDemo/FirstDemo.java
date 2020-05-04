package cn.edu.scau.biubiusuisui.example.firstDemo;

import cn.edu.scau.biubiusuisui.annotation.FXScan;
import cn.edu.scau.biubiusuisui.config.FXPlusApplication;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author jack
 * @author suisui
 * @version 1.0
 * @description 第一个示例
 * @date 2020/1/1 23:06
 * @since JavaFX2.0 JDK1.8
 */
@FXScan(base = {"cn.edu.scau.biubiusuisui.example.firstDemo"}) //会扫描带FXController和FXEntity的类进行初始化
public class FirstDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXPlusApplication.start(FirstDemo.class);  //其他配置和JavaFX相同，这里要调用FXPlusAppcalition的start方法，开始FX-plus加强
    }
}