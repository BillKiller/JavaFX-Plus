package cn.edu.scau.biubiusuisui.function;

import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.function.DragWindowHandlerImpl;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @Author jack
 * @Date:2019/6/30 10:40
 */
public class FXWindowParser {

        public void parse(Stage stage, Pane fxControllerProxy, FXWindow fxWindow){

        stage.setTitle(fxWindow.title());

        if(fxWindow.resizable()){
            stage.setResizable(false);
        }

        if(fxWindow.draggable()) {
            final  int RESIZE_WIDTH = 5;// 判定是否为调整窗口状态的范围与边界距离
            EventHandler dragWindowHandler= new DragWindowHandlerImpl(stage,fxWindow.minWidth(),fxWindow.minHeight(),fxControllerProxy,fxWindow.resizable());
            fxControllerProxy.setOnMousePressed(dragWindowHandler);
            fxControllerProxy.setOnMouseDragged(dragWindowHandler);
            fxControllerProxy.setOnMouseMoved(dragWindowHandler);
        }
        stage.initStyle(fxWindow.style());
    }
}
