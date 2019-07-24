package cn.edu.scau.biubiusuisui.example.springDemo2;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.example.springDemo.SpringController;
import javafx.fxml.FXML;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Component;

/**
 * @Author jack
 * @Date:2019/7/4 11:36
 */
@FXController(path = "springDemo2.fxml")
@Component
//@FXWindow(title = "hello",resizable = true,style = StageStyle.UNDECORATED)
public class SpringController2 extends FXBaseController {


    @FXML
    SpringController  springController;

    int count = 1;

    @Override
    public void initialize() {

    }

    @FXML
    public void test(){
        System.out.println("student"+springController.getStudent());
    }
}
