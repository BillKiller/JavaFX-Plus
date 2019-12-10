package cn.edu.scau.biubiusuisui.example.redirectDemo;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXRedirect;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author suiyu_yang
 * @description
 * @date 2019/12/3 11:53
 * @email suiyu_yang@163.com
 */
@FXController(path = "redirectDemo/login.fxml")
@FXWindow(title = "redirectDemo", mainStage = true)
public class LoginController extends FXBaseController {

    @FXML
    private TextField usernameTF;

    @FXML
    private PasswordField passwordPF;

    @FXML
    public void registerClick() {
        System.out.println("点击注册.....");
        redirectToRegister();
    }

    @FXRedirect
    public String redirectToRegister() {
        return "RegisterController";
    }

    @FXML
    @FXRedirect(close = false) //弹窗
    public String redirectToDialog() {
        return "DialogController";
    }
}
