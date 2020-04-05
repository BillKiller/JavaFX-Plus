package cn.edu.scau.biubiusuisui.example.resizableDemo;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import javafx.fxml.FXML;
import javafx.stage.StageStyle;

/**
 * @author suiyu_yang
 * @description 主控制器
 * @date 2020/4/5 00:05
 * @email suiyu_yang@163.com
 */
@FXController(path = "resizableDemo/resizableDemo.fxml")
@FXWindow(mainStage = true, title = "resizableDemo", draggable = true, resizable = true, style = StageStyle.UNDECORATED)
public class MainController extends FXBaseController {

    @FXML
    public void closeWindowClick() {
        this.closeStage();
    }
}
