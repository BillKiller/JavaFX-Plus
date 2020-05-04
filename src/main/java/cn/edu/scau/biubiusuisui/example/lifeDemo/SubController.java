package cn.edu.scau.biubiusuisui.example.lifeDemo;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.entity.FXPlusLocale;
import cn.edu.scau.biubiusuisui.log.FXPlusLoggerFactory;
import cn.edu.scau.biubiusuisui.log.IFXPlusLogger;

/**
 * @author suisui
 * @version 1.2
 * @description 子组件
 * @date 2020/5/1 13:48
 * @since JavaFX2.0 JDK1.8
 */
@FXController(path = "fxml/lifeDemo/subBar.fxml", locale = FXPlusLocale.SIMPLIFIED_CHINESE)
public class SubController extends FXBaseController {
    private static IFXPlusLogger logger = FXPlusLoggerFactory.getLogger(SubController.class);

    @Override
    public void initialize() throws Exception {
        logger.info("SubController----initialize");
    }

    @Override
    public void onShow() throws Exception {
        logger.info("SubController----onShow");
    }

    @Override
    public void onLoad() throws Exception {
        logger.info("SubController----onLoad");
    }

    @Override
    public void onClose() throws Exception {
        logger.info("SubController----onClose");
    }

    @Override
    public void onHide() throws Exception {
        logger.info("SubController----onHide");
    }
}
