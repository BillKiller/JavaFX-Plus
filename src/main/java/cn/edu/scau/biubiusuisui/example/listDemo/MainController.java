package cn.edu.scau.biubiusuisui.example.listDemo;

import cn.edu.scau.biubiusuisui.annotation.FXBind;
import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXData;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

/**
 * @Author jack
 * @Date:2019/7/27 1:43
 */
@FXController(path = "listDemo/listDemo.fxml")
@FXWindow(title = "listDemo", mainStage = true)
public class MainController extends FXBaseController {

    private static int count = 0;

    @FXData
    User user = new User();

    @FXML
    @FXBind("items=${@toList(user.names)}")
    private ListView<String> userNameListView;


    @FXML
    public void addUserName() {
        user.addNames("Jack\t" + (count++));
    }

    public ObservableList toList(ArrayList list) {
        if (list == null) {
            return null;
        }
        return FXCollections.observableList(list);
    }
}
