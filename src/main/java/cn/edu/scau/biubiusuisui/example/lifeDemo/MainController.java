package cn.edu.scau.biubiusuisui.example.lifeDemo;

import cn.edu.scau.biubiusuisui.annotation.FXRedirect;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.entity.FXPlusLocale;
import cn.edu.scau.biubiusuisui.log.FXPlusLoggerFactory;
import cn.edu.scau.biubiusuisui.log.IFXPlusLogger;
import javafx.fxml.FXML;
import javafx.scene.input.Clipboard;

/**
 * @author suisui
 * @version 1.2
 * @description 主窗口
 * @date 2020/5/1 11:53
 * @since JavaFX2.0 JDK1.8
 */
@FXWindow(mainStage = true, title = "lifeDemo", icon = "image/icon.png")
@FXController(path = "fxml/lifeDemo/lifeMain.fxml", locale = FXPlusLocale.SIMPLIFIED_CHINESE)
public class MainController extends FXBaseController {
    private static IFXPlusLogger logger = FXPlusLoggerFactory.getLogger(FXBaseController.class);

    @Override
    public void initialize() throws Exception {
        logger.info("MainController----initialize");
    }

    @Override
    public void onShow() throws Exception {
        logger.info("MainController----onShow");
    }

    @Override
    public void onLoad() throws Exception {
        logger.info("MainController----onLoad");
    }

    @Override
    public void onClose() throws Exception {
        logger.info("MainController----onClose");
    }

    @Override
    public void onHide() throws Exception {
        logger.info("MainController----onHide");
    }

    @FXML
    public void go() {
        redirectToDialog();
    }

    @FXML
    public void goAndClose() {
        redirectToDialogAndClose();
    }

    /**
     * 弹窗不关闭窗口
     */
    @FXRedirect(close = false)
    public String redirectToDialog() {
        return "DialogController";
    }

    /**
     * 弹窗并关闭本窗口
     */
    @FXRedirect()
    public String redirectToDialogAndClose() {
        return "DialogController";
    }
}
