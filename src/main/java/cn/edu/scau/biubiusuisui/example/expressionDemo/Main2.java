package cn.edu.scau.biubiusuisui.example.expressionDemo;

import cn.edu.scau.biubiusuisui.annotation.FXBind;
import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXData;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.example.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

/**
 * @Author jack
 * @Date:2019/7/4 11:36
 */
@FXController(path = "Main.fxml")
@FXWindow(title = "hello", resizable = true, style = StageStyle.UNDECORATED)
public class Main2 extends FXBaseController {

    @FXData
    @FXBind(
            {
                    "name=${usr.text}",
                    "password=${psw.text}"
            }
    )
    Student student = new Student();


    @FXML
    private Label label;

    @FXML
    private TextField usr;

    @FXML
    private Button resetBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private Label usrMsg;

    @FXML
    private PasswordField psw;

    @FXML
    private Label pswMsg;

    @FXML
    void login(ActionEvent event) {
        System.out.println("user:" + student.getName());
        System.out.println("psw:"+student.getPassword());
        if("admin".equals(student.getName())&&"admin".equals(student.getPassword())){
            System.out.println("Ok");
        }else{
            System.out.println("fail");
        }
    }

    @FXML
    void reset(ActionEvent event) {
        psw.setText("");
        usr.setText("");
    }


}
