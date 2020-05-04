package cn.edu.scau.biubiusuisui.example.lifeDemo;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.entity.FXPlusLocale;
import cn.edu.scau.biubiusuisui.log.FXPlusLogger;
import cn.edu.scau.biubiusuisui.log.FXPlusLoggerFactory;
import cn.edu.scau.biubiusuisui.log.IFXPlusLogger;

/**
 * @author suisui
 * @version 1.2
 * @description 弹窗
 * @date 2020/5/1 13:49
 * @since JavaFX2.0 JDK1.8
 */
@FXWindow(title = "Dialog")
@FXController(path = "fxml/lifeDemo/dialog.fxml", locale = FXPlusLocale.SIMPLIFIED_CHINESE)

public class DialogController extends FXBaseController {
    private static IFXPlusLogger logger = FXPlusLoggerFactory.getLogger(DialogController.class);

    @Override
    public void initialize() throws Exception {
        logger.info("DialogController----initialize");
    }

    @Override
    public void onLoad() throws Exception {
        logger.info("DialogController----onLoad");
    }

    @Override
    public void onShow() throws Exception {
        logger.info("DialogController----onShow");
    }

    @Override
    public void onClose() throws Exception {
        logger.info("DialogController----onClose");
    }

    @Override
    public void onHide() throws Exception {
        logger.info("DialogController----onHide");
    }
}
