package cn.edu.scau.biubiusuisui.function;

import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.exception.ProtocolNotSupport;
import cn.edu.scau.biubiusuisui.log.FXPlusLoggerFactory;
import cn.edu.scau.biubiusuisui.log.IFXPlusLogger;
import cn.edu.scau.biubiusuisui.utils.FileUtil;
import cn.edu.scau.biubiusuisui.utils.StringUtil;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

/**
 * @author jack
 * @version 1.0
 * @date 2019/6/30 10:40
 * @description 解析@FXWindow
 * @since JavaFX2.0 JDK1.8
 */
public class FXWindowParser {
    private static IFXPlusLogger logger = FXPlusLoggerFactory.getLogger(FXWindowParser.class);

    public void parse(Stage stage, FXBaseController fxControllerProxy, FXWindow fxWindow) {
        logger.info("parsing @FXWindow of class: " + fxControllerProxy.getName());
        // 处理 title
        stage.setTitle(fxWindow.title());

        // 处理 icon
        if (!"".equals(fxWindow.icon())) {
            try {
                URL iconUrl = new FileUtil().getFilePathFromResources(fxWindow.icon());
                if (iconUrl != null) {
                    if (new File(StringUtil.getRootPath(iconUrl)).exists()) {
                        stage.getIcons().add(new Image(fxWindow.icon()));
                    } else {
                        logger.warn("the icon file has not existed");
                    }
                } else {
                    logger.warn("the icon file has not existed");
                }
            } catch (ProtocolNotSupport protocolNotSupport) {
                logger.error(protocolNotSupport.getMessage(), protocolNotSupport);
                protocolNotSupport.printStackTrace();
            }
        }

        // fxWindow的resizable默认为false
        if (fxWindow.resizable()) {
            stage.setResizable(true);
        }

        // 处理draggable和resizable
        if (fxWindow.draggable() || fxWindow.resizable()) {
            EventHandler dragWindowHandler = new DragWindowHandlerImpl(stage, fxWindow.minWidth(), fxWindow.minHeight(), fxControllerProxy, fxWindow.draggable(), fxWindow.resizable());
            fxControllerProxy.setOnMousePressed(dragWindowHandler);
            fxControllerProxy.setOnMouseDragged(dragWindowHandler);
            fxControllerProxy.setOnMouseMoved(dragWindowHandler);
        }
        // 处理style
        stage.initStyle(fxWindow.style());
    }
}
