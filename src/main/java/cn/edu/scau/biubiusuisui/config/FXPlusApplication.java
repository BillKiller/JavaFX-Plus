package cn.edu.scau.biubiusuisui.config;

import cn.edu.scau.biubiusuisui.annotation.FXScan;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.factory.BeanBuilder;
import cn.edu.scau.biubiusuisui.factory.FXBuilder;
import cn.edu.scau.biubiusuisui.factory.FXControllerFactory;
import cn.edu.scau.biubiusuisui.function.FXWindowParser;
import cn.edu.scau.biubiusuisui.utils.ClassUtils;
import javafx.application.Application;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author jack
 * @Date:2019/6/25 2:54
 */
public class FXPlusApplication {

    private static FXWindowParser windowAnnotationParser = new FXWindowParser();

    private static BeanBuilder DEFALUT_BEAN_FACTORY = new FXBuilder();

    private static BeanBuilder beanBuilder;

    public static boolean IS_SCENE_BUILDER = true;

    public static void start(Class clazz, BeanBuilder beanBuilder) {
        IS_SCENE_BUILDER = false;
        FXPlusApplication.beanBuilder = beanBuilder;
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (FXScan.class.equals(annotation.annotationType())) {
                String[] dirs = ((FXScan) annotation).base();
                Set<String> sets = new HashSet<>();
                for (String dir : dirs) {
                    sets.add(dir);
                }
                Set<String> classNames = new HashSet<>();
                for (String dir : sets) {
                    ClassUtils classUtils = new ClassUtils();
                    List<String> temps = classUtils.scanAllClassName(dir);
                    for (String className : temps) {
                        try {
                            loadFXPlusClass(className, beanBuilder);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void start(Class clazz) {
        start(clazz, DEFALUT_BEAN_FACTORY);
    }

    private static void loadFXPlusClass(String className, BeanBuilder beanBuilder) throws ClassNotFoundException {
        Class clazz = Class.forName(className);
        if (clazz.getAnnotation(FXWindow.class) != null) {
            FXControllerFactory.loadStage(clazz, beanBuilder);
        }
    }
}
