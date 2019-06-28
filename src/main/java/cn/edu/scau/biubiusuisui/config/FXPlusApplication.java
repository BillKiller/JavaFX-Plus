package cn.edu.scau.biubiusuisui.config;

import cn.edu.scau.biubiusuisui.annotation.FXScan;
import cn.edu.scau.biubiusuisui.factory.FXFactory;
import cn.edu.scau.biubiusuisui.utils.ClassUtils;

import java.util.List;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author jack
 * @Date:2019/6/25 2:54
 */

public  class FXPlusApplication {

    public static void start(Class clazz){
        Annotation []annotations = clazz.getDeclaredAnnotations();
        for(Annotation annotation : annotations){
            if(FXScan.class.equals(annotation.annotationType())){
                String []dirs = ((FXScan)annotation).base();
                Set<String> sets = new HashSet<>();
                for(String dir : dirs){
                    sets.add(dir);
                }
                Set<String> classNames = new HashSet<>();
                for(String dir:sets){
                    ClassUtils classUtils = new ClassUtils();
                    List<String> temps = classUtils.scanAllClassName(dir);
                    for(String className:temps){
                        try {
                            loadFXPlusClass(className);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private static  void loadFXPlusClass(String className) throws ClassNotFoundException {
        Class clazz = Class.forName(className);
        FXFactory.loadFXController(clazz);
    }
}
