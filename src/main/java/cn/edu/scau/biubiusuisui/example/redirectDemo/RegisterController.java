package cn.edu.scau.biubiusuisui.example.redirectDemo;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXRedirect;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        if (validate()) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(usernameTF.getText());
            userEntity.setPassword(passwordPF.getText());
            userEntity.setEmail(emailTF.getText());
        }

    }

    @FXML
    public void loginClick() {
        redirectToLogin();
    }

    @FXRedirect
    public String redirectToLogin() {
        return "LoginController";
    }


    // 校验
    private boolean validate() {
        boolean retCode = false;
        if (null != passwordPF.getText() && null != confirmPasswordPF.getText()) {
            if (!passwordPF.getText().equals(confirmPasswordPF.getText())) {
                retCode = false;
                new Alert(Alert.AlertType.ERROR, "两次密码不一致");
            } else {
                retCode = true;
            }
        } else if (null == usernameTF.getText()) {
            retCode = false;
            new Alert(Alert.AlertType.ERROR, "用户名不能为空");
        } else if (null == emailTF.getText()) {
            retCode = false;
            new Alert(Alert.AlertType.ERROR, "邮箱不能为空");
        }
        return retCode;
    }

    private void clearAllInput() {
        usernameTF.setText("");
        emailTF.setText("");
        passwordPF.setText("");
        confirmPasswordPF.setText("");
    }
}

