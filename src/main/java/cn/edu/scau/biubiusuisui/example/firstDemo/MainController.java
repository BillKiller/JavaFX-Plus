package cn.edu.scau.biubiusuisui.example.firstDemo;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.entity.FXPlusContext;
import cn.edu.scau.biubiusuisui.factory.FXEntityFactory;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author suiyu_yang
 * @description
 * @date 2020/1/1 23:06
 * @email suiyu_yang@163.com
 */
@FXController(path = "firstDemo/firstDemo.fxml")
@FXWindow(title = "firstDemo", mainStage = true)
public class MainController extends FXBaseController implements Initializable {

    @FXML
    private Button addHelloButton;
    @FXML
    private Button deleteHelloButton;

    @FXML
    private ListView<String> list;

    private Student student;

    @FXML
    void addWord(ActionEvent event) {
        student.addList("hello");
    }

    @FXML
    void delWord(ActionEvent event) {
        student.delList("hello");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        student = (Student) FXEntityFactory.wrapFxBean(Student.class);  // 从工厂中拿到将JavaBean转换得到的JavaFXBean
        Property listProperty = FXPlusContext.getEntityPropertyByName(student, "list");
        list.itemsProperty().bind(listProperty);
    }
}

