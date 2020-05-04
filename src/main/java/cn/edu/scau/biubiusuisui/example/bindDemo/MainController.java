package cn.edu.scau.biubiusuisui.example.bindDemo;

import cn.edu.scau.biubiusuisui.annotation.FXBind;
import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author jack
 * @author suisui
 * @version 1.2
 * @date 2020/5/1 1:43
 * @since JavaFX2.0 JDK1.8
 */
@FXController(path = "fxml/bindDemo/bindDemo.fxml")
@FXWindow(title = "bindDemo", mainStage = true)
// TODO  待完善
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
    private Label ageLabel;

    @FXML
    private Label enableLabel;

    @FXML
    private TextField usernameTF;

    @FXML
    private PasswordField pswPF;

    @FXML
    private TextField ageTF;

    @FXML
    private ToggleGroup enableButtons;

    //
//    @FXData
//    @FXBind({
//            "name=${usernameTF.text}",
//            "password=${pswPF.text}",
//            "age=${ageTF.text}",
//            "isEnable=${enableButtons.getSelectedToggle().getUserData()}"
//    })
//    private User user = new User();
    private UserPropertyEntity user = new UserPropertyEntity();

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

    @FXML
    public void clickToShowInfo() {
        RadioButton button = (RadioButton) enableButtons.getSelectedToggle();
        System.out.println(button.getText());
        usernameLabel.setText(user.getName());
        userPswLabel.setText(user.getPassword());
        ageLabel.setText(Integer.toString(user.getAge()));
        enableLabel.setText(Boolean.toString(user.getEnable()));
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

}
