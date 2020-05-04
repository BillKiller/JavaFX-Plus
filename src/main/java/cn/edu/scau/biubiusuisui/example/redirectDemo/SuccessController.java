package cn.edu.scau.biubiusuisui.example.redirectDemo;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXRedirect;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * @author suisui
 * @version 1.1
 * @description 登录成功的Controller
 * @date 2019/12/3 12:43
 * @since JavaFX2.0 JDK1.8
 */
@FXController(path = "fxml/redirectDemo/success.fxml")
@FXWindow(title = "success")
public class SuccessController extends FXBaseController {

    @FXML
    private Label title;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label tokenLabel;

    @FXML
    @FXRedirect
    public String redirectToLogin() {
        return "LoginController";
    }

    @Override
    public void onShow() {
        try {
            super.onShow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.getQuery().get("showType") != null) {
            String showType = (String) this.getQuery().get("showType");
            if (showType.equals("1")) { //注册
                title.setText("注册成功");
                if (this.getParam().get("user") != null) {
                    UserEntity userEntity = (UserEntity) this.getParam().get("user");
                    usernameLabel.setText(userEntity.getUsername());
                    passwordLabel.setText(userEntity.getPassword());
                }
            } else { //登录
                title.setText("登录成功");
                // 此处为演示多种方式数据传递才进行多次赋值，实际应用时应根据数据API进行相应的数据获取操作
                if (this.getQuery().size() > 1) { //query方式传递
                    usernameLabel.setText(String.valueOf(this.getQuery().get("username")));
                    passwordLabel.setText(String.valueOf(this.getQuery().get("password")));
                    tokenLabel.setText(String.valueOf(this.getQuery().get("token")));
                }
                if (this.getParam().size() > 1) { //param方式传递
                    usernameLabel.setText(String.valueOf(this.getParam().get("username")));
                    passwordLabel.setText(String.valueOf(this.getParam().get("password")));
                }
            }
        }
    }

}
