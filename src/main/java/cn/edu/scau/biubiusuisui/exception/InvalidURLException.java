package cn.edu.scau.biubiusuisui.exception;

/**
 * @author suiyu_yang
 * @description 不合法URL
 * @date 2020/4/6 15:59
 * @email suiyu_yang@163.com
 */
public class InvalidURLException extends Exception {
    public InvalidURLException() {
        super("the url is invalid");
    }
}
