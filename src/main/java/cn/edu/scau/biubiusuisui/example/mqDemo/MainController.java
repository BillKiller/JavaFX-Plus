package cn.edu.scau.biubiusuisui.example.mqDemo;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXReceiver;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * @author suisui
 * @version 1.2
 * @description 主界面
 * @date 2019/12/8 13:17
 * @since JavaFX2.0 JDK1.8
 */
@FXController(path = "fxml/mqDemo/main.fxml")
@FXWindow(mainStage = true, title = "MQDemo")
public class MainController extends FXBaseController {

    @FXML
    private TextArea outTA;

    /**
     * 接收者必须指定要订阅的[发送者类名:方法名]
     * 发送函数的返回值会注入到接收函数的参数中
     *
     * @param msg
     */
    @FXReceiver(name = "TopBarController:sendToMain")
    public void handleTopBar(String msg) {
        // 处理导航栏的点击事件
        outTA.appendText(msg + "\n");
    }
}
