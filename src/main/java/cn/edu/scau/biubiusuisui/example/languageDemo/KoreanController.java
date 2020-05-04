package cn.edu.scau.biubiusuisui.example.languageDemo;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXRedirect;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.entity.FXPlusLocale;
import javafx.fxml.FXML;

/**
 * @author suisui
 * @description 法语界面
 * @date 2020/5/4 14:01
 * @since JDK1.8
 */
@FXWindow(mainStage = false, title = "languageDemo")
@FXController(path = "fxml/languageDemo/languageDemo.fxml", locale = FXPlusLocale.KOREAN)
public class KoreanController extends FXBaseController {
    @FXML
    public void clickToChinese() {
        redirect("ChineseController");
    }

    @FXML
    public void clickToEnglish() {
        redirect("EnglishController");
    }

    @FXML
    public void clickToKorean() {
        redirect("KoreanController");
    }

    @FXRedirect
    public String redirect(String name) {
        return name;
    }
}
