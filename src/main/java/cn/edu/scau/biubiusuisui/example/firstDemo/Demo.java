package cn.edu.scau.biubiusuisui.example.firstDemo;

import cn.edu.scau.biubiusuisui.annotation.FXScan;
import cn.edu.scau.biubiusuisui.config.FXPlusApplication;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author suiyu_yang
 * @description 第一个示例
 * @date 2020/1/1 23:06
 * @email suiyu_yang@163.com
 */
@FXScan(base = {"cn.edu.scau.biubiusuisui.example.firstDemo"}) //会扫描带FXController和FXEntity的类进行初始化
public class Demo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXPlusApplication.start(Demo.class);  //其他配置和JavaFX相同，这里要调用FXPlusAppcalition的start方法，开始FX-plus加强
    }
}