package cn.edu.scau.biubiusuisui.example.redirectDemo;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXRedirect;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.entity.FXRedirectParam;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @FXML
    @FXRedirect //登录成功 Query方式
    public String redirectToSuccessWithQuery() {
        return "SuccessController?showType=0&username=" + usernameTF.getText() + "&password=" + passwordPF.getText();
    }

    @FXML
    @FXRedirect //登录成功 Param方式
    public FXRedirectParam redirectToSuccessWithParam() {
        FXRedirectParam params = new FXRedirectParam("SuccessController");
        params.addParam("username", usernameTF.getText());
        params.addParam("password", passwordPF.getText());
        params.addQuery("showType", "0");
        return params;
    }

    @FXML
    @FXRedirect
    public FXRedirectParam redirectToSuccessWithAll() {
        FXRedirectParam params = new FXRedirectParam("SuccessController");
        params.addParam("username", usernameTF.getText());
        params.addParam("password", passwordPF.getText());

        params.addQuery("token", new Date().toString());
        params.addQuery("showType", "0");
        return params;
    }
}
