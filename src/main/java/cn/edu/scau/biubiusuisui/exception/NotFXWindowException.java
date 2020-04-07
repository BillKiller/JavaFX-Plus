package cn.edu.scau.biubiusuisui.exception;

/**
 * @author suiyu_yang
 * @description 某Controller不是窗口
 * @date 2020/4/6 17:10
 * @email suiyu_yang@163.com
 */
public class NotFXWindowException extends Exception {
    public NotFXWindowException() {
        super("the controller is not a window");
    }
}
