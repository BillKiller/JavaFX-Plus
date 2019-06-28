package cn.edu.scau.biubiusuisui.example;

import cn.edu.scau.biubiusuisui.annotation.*;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.entity.FXPlusContext;
import cn.edu.scau.biubiusuisui.factory.FXEntityFactory;
import cn.edu.scau.biubiusuisui.factory.FXFactory;
import cn.edu.scau.biubiusuisui.proxy.classProxy.FXEntityProxy;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author jack
 * @Date:2019/6/25 1:47
 */
@FXController(path = "Main.fxml")
@FXWindow(title = "demo1")
public class MainController extends FXBaseController{

    @FXML
    Button btn;

    @FXML
    Label label;

    Student student;

    int count = 1;

    @Override
    public void initialize() {

        student = (Student) FXEntityFactory.getInstance().createJavaBeanProxy(Student.class); //工厂产生一个学生
        student.setName("Jack"); //设置学生姓名
        FXEntityProxy fxEntityProxy = FXPlusContext.getProryByBeanObject(student); //获取学生代理
        Property nameProperty = fxEntityProxy.getPropertyByFieldName("name"); //获取Bean对应的Property
        label.textProperty().bind(nameProperty); //属性绑定
    }

    @FXML
    @FXSender
    public String send(){
        student.setName("Jack :" + count);
        count++;
        return "sending msg";
    }

}
