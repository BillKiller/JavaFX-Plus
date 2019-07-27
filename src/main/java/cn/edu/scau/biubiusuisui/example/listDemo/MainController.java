package cn.edu.scau.biubiusuisui.example.listDemo;

import cn.edu.scau.biubiusuisui.annotation.FXBind;
import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXData;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

/**
 * @Author jack
 * @Date:2019/7/27 1:43
 */
@FXController(path = "listDemo.fxml")
@FXWindow(title = "actionDemo",mainStage = true)
public class MainController extends FXBaseController {

    @FXData
    User user = new User();


    @FXML
    @FXBind("items=${@toList(user.names)}")
    private ListView<String> list;


    public ObservableList hello(){
        return FXCollections.observableList(new ArrayList<String>(){{add("hello");}});
    }

    public ObservableList toList(ArrayList list){
        return FXCollections.observableList(list);
    }

    @FXML
    public void addName(){
        user.addNames("hello");
    }
}
