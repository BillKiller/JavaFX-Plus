package cn.edu.scau.biubiusuisui.example.logDemo;

import cn.edu.scau.biubiusuisui.example.lifeDemo.LifeDemo;
import cn.edu.scau.biubiusuisui.log.FXPlusLoggerFactory;
import cn.edu.scau.biubiusuisui.log.IFXPlusLogger;
import cn.edu.scau.biubiusuisui.utils.LogUtil;

/**
 * @author suisui
 * @description 测试日志的Demo
 * @date 2020/5/2 19:58
 * @since JDK1.8
 */
public class LogDemo {
    private static IFXPlusLogger logger = FXPlusLoggerFactory.getLogger(LogDemo.class);

    public void testLogger() {
        logger.info("info");
        logger.error("error");
        logger.debug("debug");
        logger.warn("warn");
    }

    public void testLogUtil() {
        LogUtil.info("info");
        LogUtil.error("error");
        LogUtil.debug("debug");
        LogUtil.warn("warn");
    }

    public static void main(String[] args) {
        LogDemo demo = new LogDemo();
        demo.testLogger();
        demo.testLogUtil();
    }
}
