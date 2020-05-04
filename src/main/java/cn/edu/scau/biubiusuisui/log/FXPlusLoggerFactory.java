package cn.edu.scau.biubiusuisui.log;

import cn.edu.scau.biubiusuisui.utils.LogUtil;
import org.apache.log4j.Logger;

/**
 * @author suisui
 * @version 1.2
 * @description 生成日志类的工厂
 * @date 2020/5/1 10:56
 * @since JavaFX2.0 JDK1.8
 */
public class FXPlusLoggerFactory {

    private FXPlusLoggerFactory() {

    }

    public static IFXPlusLogger getLogger(Class<?> clazz) {
        LogUtil.initLog4jBase();
        Logger logger = Logger.getLogger(clazz);
        return new FXPlusLogger(logger);
    }

}
