package cn.edu.scau.biubiusuisui.log;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author suisui
 * @version 1.2
 * @description JavaPlus的日志类
 * @date 2020/5/1 10:55
 * @since JavaFX2.0 JDK1.8
 */
public class FXPlusLogger implements IFXPlusLogger {
    private Logger logger;
    private String FQCN;

    public FXPlusLogger(Logger logger) {
        this.FQCN = FXPlusLogger.class.getName();
        this.logger = logger;
    }

    public FXPlusLogger(String fqcn, Logger logger) {
        this.FQCN = fqcn;
        this.logger = logger;
    }

    @Override
    public void debug(Object message) {
        logger.log(FQCN, Level.DEBUG, message, null);
    }

    @Override
    public void debug(Object message, Throwable t) {
        logger.log(FQCN, Level.DEBUG, message, t);
    }

    @Override
    public void info(Object message) {
        logger.log(FQCN, Level.INFO, message, null);
    }

    @Override
    public void info(Object message, Throwable t) {
        logger.log(FQCN, Level.INFO, message, t);
    }

    @Override
    public void warn(Object message) {
        logger.log(FQCN, Level.WARN, message, null);
    }

    @Override
    public void warn(Object message, Throwable t) {
        logger.log(FQCN, Level.WARN, message, t);
    }

    @Override
    public void error(Object message) {
        logger.log(FQCN, Level.ERROR, message, null);
    }

    @Override
    public void error(Object message, Throwable t) {
        logger.log(FQCN, Level.ERROR, message, t);
    }
}
