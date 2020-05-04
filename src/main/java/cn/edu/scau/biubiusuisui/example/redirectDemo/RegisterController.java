package cn.edu.scau.biubiusuisui.example.redirectDemo;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXRedirect;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.entity.FXRedirectParam;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author suisui
 * @version 1.1
 * @description
 * @date 2019/12/4 00:07
 * @since JavaFX2.0 JDK1.8
 */
@FXController(path = "fxml/redirectDemo/register.fxml")
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
            redirectToRegisterSuccess(userEntity);
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

    @FXRedirect
    public FXRedirectParam redirectToRegisterSuccess(UserEntity userEntity) {
        FXRedirectParam fxRedirectParam = new FXRedirectParam("SuccessController");
        fxRedirectParam.addQuery("showType", "1");
        fxRedirectParam.addParam("user", userEntity);
        return fxRedirectParam;
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

