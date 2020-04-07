package cn.edu.scau.biubiusuisui.function;

import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @Author jack
 * @Date:2019/6/30 10:40
 * @Description 解析@FXWindow
 */
public class FXWindowParser {

    public void parse(Stage stage, Pane fxControllerProxy, FXWindow fxWindow) {

        stage.setTitle(fxWindow.title());

        // fxWindow的resizable默认为false
        if (fxWindow.resizable()) {
            stage.setResizable(true);
        }

        if (fxWindow.draggable() || fxWindow.resizable()) {
            EventHandler dragWindowHandler = new DragWindowHandlerImpl(stage, fxWindow.minWidth(), fxWindow.minHeight(), fxControllerProxy, fxWindow.draggable(), fxWindow.resizable());
            fxControllerProxy.setOnMousePressed(dragWindowHandler);
            fxControllerProxy.setOnMouseDragged(dragWindowHandler);
            fxControllerProxy.setOnMouseMoved(dragWindowHandler);
        }
        stage.initStyle(fxWindow.style());

    }
}
