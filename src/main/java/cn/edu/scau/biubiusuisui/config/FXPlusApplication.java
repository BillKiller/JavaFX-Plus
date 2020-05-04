package cn.edu.scau.biubiusuisui.config;

import cn.edu.scau.biubiusuisui.annotation.FXScan;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.factory.BeanBuilder;
import cn.edu.scau.biubiusuisui.factory.FXBuilder;
import cn.edu.scau.biubiusuisui.factory.FXControllerFactory;
import cn.edu.scau.biubiusuisui.function.FXWindowParser;
import cn.edu.scau.biubiusuisui.log.FXPlusLoggerFactory;
import cn.edu.scau.biubiusuisui.log.IFXPlusLogger;
import cn.edu.scau.biubiusuisui.utils.ClassUtil;
import cn.edu.scau.biubiusuisui.utils.FileUtil;
import cn.edu.scau.biubiusuisui.utils.LogUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author jack
 * @version 1.0
 * @date 2019/6/25 2:54
 * @since JavaFX2.0 JDK1.8
 */
public class FXPlusApplication {
    private static final IFXPlusLogger logger = FXPlusLoggerFactory.getLogger(FXPlusApplication.class);
//    Application

    private static FXWindowParser windowAnnotationParser = new FXWindowParser();

    private static BeanBuilder DEFAULT_BEAN_FACTORY = new FXBuilder();

    private static BeanBuilder beanBuilder;

    public static boolean IS_SCENE_BUILDER = true;

    public static void start(Class clazz, BeanBuilder beanBuilder) {
        logger.info("starting JavaFX-Plus Application");
        logger.info("\n" + FileUtil.readFileFromResources("banner.txt"));
        // 初始化日志路径
        LogUtil.initLog4jBase();

        IS_SCENE_BUILDER = false;
        FXPlusApplication.beanBuilder = beanBuilder;
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        logger.info("starting to scanning and registering controllers");
        for (Annotation annotation : annotations) {
            if (FXScan.class.equals(annotation.annotationType())) {
                String[] dirs = ((FXScan) annotation).base();
                Set<String> sets = new HashSet<>();
                for (String dir : dirs) {
                    sets.add(dir);
                }
                Set<String> classNames = new HashSet<>();
                for (String dir : sets) {
                    logger.info("scanning directory: " + dir);
                    ClassUtil classUtil = new ClassUtil();
                    List<String> temps = classUtil.scanAllClassName(dir);
                    for (String className : temps) {
                        try {
                            logger.info("loading class: " + className);
                            loadFXPlusClass(className, beanBuilder);
                        } catch (ClassNotFoundException e) {
                            logger.error(e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void start(Class clazz) {
        start(clazz, DEFAULT_BEAN_FACTORY);
    }

    private static void loadFXPlusClass(String className, BeanBuilder beanBuilder) throws ClassNotFoundException {
        Class clazz = Class.forName(className);
        // 是窗口，需要初始化Stage
        if (clazz.getAnnotation(FXWindow.class) != null) {
            logger.info("loading stage of class: " + className);
            FXControllerFactory.loadStage(clazz, beanBuilder);
        }
    }
}
