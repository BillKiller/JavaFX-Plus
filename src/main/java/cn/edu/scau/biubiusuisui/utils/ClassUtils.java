package cn.edu.scau.biubiusuisui.utils;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Author jack
 * @Date:2019/6/25 5:20
 */
public class ClassUtils {
    private ClassLoader classLoader;

    public ClassUtils() {
        classLoader = getClass().getClassLoader();
    }

    private List<String> getAllFXControllerClassName(String base, List<String> nameList) {
        String splashPath = StringUtils.dotToSplash(base);
        URL url = classLoader.getResource(splashPath);
        String filePath = StringUtils.getRootPath(url);
        List<String> names = null;
        if(filePath.endsWith("jar")){
            nameList = readFromJarDirectory(filePath,base);
        }else {
            names = readFromDirectory(filePath);
            for (String name : names) {
                if (isClassFile(name)) {
                    nameList.add(toFullyQualifiedName(name, base));
                } else if (isDirectory(name)) {
                    nameList = getAllFXControllerClassName(base + "." + name, nameList);
                }
            }
        }
        return nameList;
    }

    public List<String> scanAllClassName(String base) {
        return getAllFXControllerClassName(base, new LinkedList<>());
    }

    private static String toFullyQualifiedName(String shortName, String basePackage) {
        StringBuilder sb = new StringBuilder(basePackage);
        sb.append('.');
        sb.append(StringUtils.trimExtension(shortName));
        return sb.toString();
    }

    private static boolean isClassFile(String name) {
        return name.endsWith(".class");
    }

    private static boolean isDirectory(String name) {
        return !name.contains(".");
    }

    private static List<String> readFromDirectory(String path) {
        if (path == null) return null;
        return readFromFileDirectory(path);
    }

    private static List<String> readFromJarDirectory(String path,String packageName) {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Enumeration<JarEntry> entrys = jarFile.entries();
        List<String> classNames = new ArrayList<>();
        while (entrys.hasMoreElements()) {
            JarEntry jarEntry = entrys.nextElement();
            if(!jarEntry.getName().endsWith(".class"))continue;
            int packageNameIndex = jarEntry.getName().indexOf("/");
            if("".equals(packageName)){
                classNames.add(jarEntry.getName());
            }else {
                if(packageNameIndex == -1) continue;
                String baseName = jarEntry.getName().substring(0, packageNameIndex);
                if (baseName.equals(packageName)) {
                    classNames.add(StringUtils.trimExtension(jarEntry.getName()).replaceAll("/","."));
                }
            }
        }
        return classNames;
    }

    private static List<String> readFromFileDirectory(String path) {
        File file = new File(path);
        String[] names = file.list();
        if (null == names) {
            return null;
        } else {
            return Arrays.asList(names);
        }
    }


    public static boolean hasDeclaredAnnotation(Class clazz, Class annotation) {
        if (annotation == null) {
            return false;
        }
        if (hasAnnotationInList(annotation, clazz.getDeclaredAnnotations())) return true;
        return false;
    }

    public static boolean hasAnnotation(Class clazz, Class annotation) {
        if (annotation == null) {
            return false;
        }
        if (hasAnnotationInList(annotation, clazz.getAnnotations())) return true;
        return false;
    }

    public static boolean hasAnnotationInList(Class annotation, Annotation[] annotations2) {
        if (getAnnotationInList(annotation, annotations2) == null) {
            return false;
        } else {
            return true;
        }
    }

    public static Annotation getAnnotationInList(Class annotation, Annotation[] annotations) {
        if (annotations == null || annotation == null) {
            return null;
        }
        for (Annotation annotation1 : annotations) {
            if (annotation1.annotationType().equals(annotation)) {
                return annotation1;
            }
        }
        return null;
    }

    public static void copyField(Object target, Object base) {
        Class clazz = base.getClass();
        Class targetClass = target.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }
    }
}
