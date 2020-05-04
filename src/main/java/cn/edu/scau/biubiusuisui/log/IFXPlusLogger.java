package cn.edu.scau.biubiusuisui.log;


import org.slf4j.Logger;

/**
 * @author suisui
 * @version 1.2
 * @description 日志接口
 * @date 2020/5/1 10:54
 * @since JavaFX2.0 JDK1.8
 */
public interface IFXPlusLogger {
    void debug(Object message);

    void debug(Object message, Throwable t);

    void info(Object message);

    void info(Object message, Throwable t);

    void warn(Object message);

    void warn(Object message, Throwable t);

    void error(Object message);

    void error(Object message, Throwable t);

}
