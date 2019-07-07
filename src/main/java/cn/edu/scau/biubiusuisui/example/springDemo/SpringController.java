package cn.edu.scau.biubiusuisui.example.springDemo;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.entity.FXPlusContext;
import cn.edu.scau.biubiusuisui.example.Student;
import cn.edu.scau.biubiusuisui.factory.FXEntityFactory;
import cn.edu.scau.biubiusuisui.factory.FXFactory;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author jack
 * @Date:2019/7/4 11:36
 */
@FXController(path = "springDemo.fxml")
@Component
@FXWindow(title = "hello",resizable = true,style = StageStyle.UNDECORATED)
public class SpringController extends FXBaseController {

    @Autowired
    Student student;


    Student studentProxy;

    @FXML
    Label label;

    int count = 1;

    @Override
    public void initialize() {
        studentProxy = (Student) FXEntityFactory.createJavaBeanProxy(student);
        Property property = FXPlusContext.getEntityPropertyByName(studentProxy, "name");
        label.textProperty().bind(property);
    }

    @FXML
    public void add(){
        studentProxy.setName("Jack : " + count);
        count++;
    }

}
