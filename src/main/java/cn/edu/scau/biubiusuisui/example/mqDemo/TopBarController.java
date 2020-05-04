package cn.edu.scau.biubiusuisui.example.mqDemo;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXSender;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import javafx.fxml.FXML;

/**
 * @author suisui
 * @version 1.1
 * @description 导航栏示例
 * @date 2019/12/8 13:17
 * @since JavaFX2.0 JDK1.8
 */
@FXController(path = "fxml/mqDemo/topBar.fxml")
public class TopBarController extends FXBaseController {

    @FXML
    public void indexClick() {
        sendToMain("点击[首页]");
    }

    @FXML
    public void scoreClick() {
        sendToMain("点击[积分中心]");
    }

    @FXML
    public void questionClick() {
        sendToMain("点击[问答中心]");
    }

    @FXML
    public void selfClick() {
        sendToMain("点击[个人中心]");
    }

    /**
     * 系统会通过发射信号，调用所有订阅这个发射信号函数的方法,从而响应信号
     *
     * @param msg
     * @return
     */
    @FXSender   //标注为信号发射函数
    public String sendToMain(String msg) {
        return msg;
    }
}
