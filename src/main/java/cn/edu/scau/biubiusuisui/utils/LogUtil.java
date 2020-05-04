package cn.edu.scau.biubiusuisui.utils;


import cn.edu.scau.biubiusuisui.log.FXPlusLogger;
import cn.edu.scau.biubiusuisui.log.IFXPlusLogger;
import org.apache.log4j.Logger;

/**
 * @author suisui
 * @version 1.2
 * @description 日志工具类
 * @date 2020/5/1 10:54
 * @since JavaFX2.0 JDK1.8
 */
public class LogUtil {
    private static final String FQCN = LogUtil.class.getName();
    private static IFXPlusLogger logger;

    static {
        initLog4jBase();
        logger = new FXPlusLogger(FQCN, Logger.getLogger(LogUtil.class));
    }

    public static void initLog4jBase() {
        if (System.getProperty("log.base") == null) {
            // 默认是当前目录下
            String projectPath = PathUtil.getCurrentPath();
            initLog4jBase(projectPath);
        }
    }

    public static void initLog4jBase(String base) {
        System.setProperty("log.base", base);
    }

    public static void debug(Object message) {
        logger.debug(message);
    }

    public static void debug(Object message, Throwable t) {
        logger.debug(message, t);
    }

    public static void info(Object message) {
        logger.info(message);
    }

    public static void info(Object message, Throwable t) {
        logger.debug(message, t);
    }

    public static void warn(Object message) {
        logger.warn(message);
    }

    public static void warn(Object message, Throwable t) {
        logger.warn(message, t);
    }

    public static void error(Object message) {
        logger.error(message);
    }

    public static void error(Object message, Throwable t) {
        logger.error(message, t);
    }
}
