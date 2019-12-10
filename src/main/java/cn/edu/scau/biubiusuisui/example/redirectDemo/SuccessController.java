package cn.edu.scau.biubiusuisui.example.redirectDemo;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXRedirect;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author suiyu_yang
 * @description 登录成功的Controller
 * @date 2019/12/3 12:43
 * @email suiyu_yang@163.com
 */
@FXController(path = "redirectDemo/success.fxml")
@FXWindow(title = "success")
public class SuccessController extends FXBaseController implements Initializable {

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;


    @FXML
    @FXRedirect
    public String redirectToLogin() {
        return "LoginController";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
