package cn.edu.scau.biubiusuisui.example.moveDemo;

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
import javafx.scene.shape.Circle;

/**
 * @Author jack
 * @Date:2019/7/4 11:36
 */
@FXController(path = "moveDemo.fxml")
@FXWindow(title = "hello", resizable = true, draggable = true)
public class Main2 extends FXBaseController {

    @FXML
    @FXBind({"layoutX=${bean.x}","layoutY=${bean.y}"})
    private Circle circle;

    @FXData
    CircleBean bean = new CircleBean();

    @FXML
    @FXBind("text=${bean.x}")
    private TextField xinput;

    @FXML
    @FXBind("text=${bean.y}")
    private TextField yinput;

    @FXML
    private Button xSub;

    @FXML
    private Button xAdd;

    @FXML
    private Button ySub;

    @FXML
    private Button yAdd;
    @FXML
    public void addX(){
        double x = bean.getX() + 5 ;
        bean.setX(x);
        System.out.println("? ? " + bean);
    }
    @FXML
    public void subX(){
        double x = bean.getX() - 5 ;
        bean.setX(x);
    }
    @FXML
    public void addY(){
        double y = bean.getY() + 5 ;
        bean.setY(y);
    }
    @FXML
    public void subY(){
        double y = bean.getY() - 5 ;
        bean.setY(y);
    }
}
