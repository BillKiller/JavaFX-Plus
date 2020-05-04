package cn.edu.scau.biubiusuisui.example.languageDemo;

import cn.edu.scau.biubiusuisui.annotation.FXScan;
import cn.edu.scau.biubiusuisui.config.FXPlusApplication;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author suisui
 * @description 测试语言国际化的Demo
 * @date 2020/5/3 09:57
 * @since JDK1.8
 */
@FXScan(base = "cn.edu.scau.biubiusuisui.example.languageDemo")
public class LanguageDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXPlusApplication.start(getClass());
    }
}
