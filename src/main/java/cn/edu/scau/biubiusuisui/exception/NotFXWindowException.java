package cn.edu.scau.biubiusuisui.exception;

/**
 * @author suisui
 * @version 1.2
 * @description 某Controller不是窗口的错误
 * @date 2020/4/6 17:10
 * @since JavaFX2.0 JDK1.8
 */
public class NotFXWindowException extends Exception {
    public NotFXWindowException() {
        super("the controller is not a window");
    }
}
