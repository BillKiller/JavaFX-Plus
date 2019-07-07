package cn.edu.scau.biubiusuisui.example;

import cn.edu.scau.biubiusuisui.annotation.*;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.entity.FXPlusContext;
import cn.edu.scau.biubiusuisui.factory.FXEntityFactory;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author jack
 * @Date:2019/6/25 1:47
 */
@FXController(path = "Main.fxml")
//@FXWindow(title = "demo1", draggable = true, resizable = true,style = StageStyle.UNDECORATED)
public class MainController extends FXBaseController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBtn;

    @FXML
    private Button delBtn;

    @FXML
    private ListView<String> list;

    @FXML
    @FXBind(bind = {"text=${student.name}"})
    Label label;

    Student student;

    @FXML
    @FXSender
    void addWord(ActionEvent event) {
        student.addList("hello" );
    }

    @FXML
    void delWord(ActionEvent event) {
        student.delList("hello");
    }


    @FXReceiver(name = "MainController#haha:addWord")
    void test2(){
        System.out.println("?--?");
    }

    @Override
    public void initialize() {
        student = (Student) FXEntityFactory.createJavaBeanProxy(Student.class);
        Property property = FXPlusContext.getEntityPropertyByName(student, "list");
        list.itemsProperty().bind(property);
    }
}
