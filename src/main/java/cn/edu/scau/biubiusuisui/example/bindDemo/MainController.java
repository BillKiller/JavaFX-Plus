package cn.edu.scau.biubiusuisui.example.bindDemo;

import cn.edu.scau.biubiusuisui.annotation.FXBind;
import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXData;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author jack
 * @Date:2019/7/27 1:43
 */
@FXController(path = "bindDemo/bindDemo.fxml")
@FXWindow(title = "bindDemo", mainStage = true)
public class MainController extends FXBaseController implements Initializable {
    //     View bind to View
    @FXML
    @FXBind("text=${inputTF.text}")
    private Label inputLabel;

    @FXML
    private TextField inputTF;

    //    View bind to a Java Bean
    @FXML
    private Label usernameLabel;

    @FXML
    private Label userPswLabel;

    @FXML
    private TextField usernameTF;

    @FXML
    private PasswordField pswPF;

    @FXData
    @FXBind({
            "name=${usernameTF.text}",
            "password=${pswPF.text}"
    })
    private User user = new User();

    // View bind to Expression
    @FXML
    private TextField money;

    @FXML
    @FXBind("text=${@toUs(money.text)}")
    private Label us;

    @FXML
    @FXBind("text=${@toJp(money.text)}")
    private Label jp;

    @FXML
    @FXBind("text=${@toUk(money.text)}")
    private Label uk;

    @FXML
    public void clickToShowInfo() {
        usernameLabel.setText(user.getName());
        userPswLabel.setText(user.getPassword());
    }

    public String toUs(String value) {
        double money = Double.valueOf(value);
        double percent = 0.1454;
        return String.valueOf(money * percent);
    }

    public String toJp(String value) {
        double money = Double.valueOf(value);
        double percent = 15.797;
        return String.valueOf(money * percent);
    }

    public String toUk(String value) {
        double money = Double.valueOf(value);
        double percent = 0.1174;
        return String.valueOf(money * percent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        money.setText("0");
        money.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (null == newValue || "".equals(newValue)) {
                    money.setText("0");
                } else if (!newValue.matches("^[0-9]*$")) {
                    money.setText(oldValue);
                }
            }
        });
    }
}
