package cn.edu.scau.biubiusuisui.example;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author jack
 * @Date:2019/7/4 11:36
 */
@FXController(path = "Main.fxml")
@Component
@FXWindow(title = "hello",resizable = true,style = StageStyle.UNDECORATED)
public class Main2 extends FXBaseController {

    @Autowired
    Student student;


}
