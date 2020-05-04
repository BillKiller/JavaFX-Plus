package cn.edu.scau.biubiusuisui.exception;

/**
 * @author suisui
 * @version 1.2
 * @description 不合法URL
 * @date 2020/4/6 15:59
 * @since JavaFX2.0 JDK1.8
 */
public class InvalidURLException extends Exception {
    public InvalidURLException() {
        super("the url is invalid");
    }
}
