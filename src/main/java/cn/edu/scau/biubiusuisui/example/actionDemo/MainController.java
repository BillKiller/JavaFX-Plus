package cn.edu.scau.biubiusuisui.example.actionDemo;

import cn.edu.scau.biubiusuisui.annotation.FXBind;
import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXData;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;

/**
 * @Author jack
 * @Date:2019/7/27 1:43
 */
@FXController(path = "actionDemo.fxml")
@FXWindow(title = "actionDemo")
public class MainController extends FXBaseController {



    @FXML
    @FXBind("text=${@toUs(time.text)}")
    private Label us;

    @FXML
    @FXBind("text=${@toJp(time.text)}")
    private Label jp;

    @FXML
    private TextField time;

    @FXML
    @FXBind("text=${@toUk(time.text)}")
    private Label uk;

    public String toUs(String value){
        double money = Double.valueOf(value);
        double percent = 0.1454;
        return String.valueOf(money * percent);
    }

    public String toJp(String value){
        double money = Double.valueOf(value);
        double percent = 15.797;
        return String.valueOf(money * percent);
    }

    public String toUk(String value){
        double money = Double.valueOf(value);
        double percent = 0.1174;
        return String.valueOf(money * percent);
    }
}
