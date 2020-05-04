package cn.edu.scau.biubiusuisui.example.lifeDemo;

import cn.edu.scau.biubiusuisui.annotation.FXScan;
import cn.edu.scau.biubiusuisui.config.FXPlusApplication;
import cn.edu.scau.biubiusuisui.log.FXPlusLoggerFactory;
import cn.edu.scau.biubiusuisui.log.IFXPlusLogger;
import cn.edu.scau.biubiusuisui.utils.LogUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;


/**
 * @author suisui
 * @version 1.2
 * @description 测试生命周期的Demo
 * @date 2020/5/1 11:50
 * @since JavaFX2.0 JDK1.8
 */
@FXScan(base = "cn.edu.scau.biubiusuisui.example.lifeDemo")
public class LifeDemo extends Application {
    private static IFXPlusLogger logger = FXPlusLoggerFactory.getLogger(LifeDemo.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.info("LifeDemo---start");
//        Platform.setImplicitExit(false); //设置当关闭最后一个Stage时，JavaFX应用程序不会自动退出
        FXPlusApplication.start(getClass());
    }

    @Override
    public void init() throws Exception {
        logger.info("LifeDemo---init");
    }

    @Override
    public void stop() throws Exception {
        logger.info("LifeDemo---stop");
    }

}

