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
 * @date 2019/12/4 00:07
 * @email suiyu_yang@163.com
 */
@FXController(path = "redirectDemo/register.fxml")
@FXWindow(title = "register")
public class RegisterController extends FXBaseController {
    @FXML
    private TextField usernameTF;

    @FXML
    private TextField emailTF;
    @FXML
    private PasswordField passwordPF;

    @FXML
    private PasswordField confirmPasswordPF;

    @FXML
    public void registerClick() {

    }

    @FXML
    public void loginClick() {
        redirectToLogin();
    }

    @FXRedirect
    public String redirectToLogin() {
        return "LoginController";
    }
}

