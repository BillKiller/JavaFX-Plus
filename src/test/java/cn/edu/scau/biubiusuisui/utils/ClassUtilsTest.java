package cn.edu.scau.biubiusuisui.utils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtilsTest {

    public static void main(String[] args) {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile("/Users/Jack/test.jar");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Enumeration<JarEntry> entrys = jarFile.entries();
        List<String> classNames = new ArrayList<>();
        while (entrys.hasMoreElements()) {
            JarEntry jarEntry = entrys.nextElement();
            classNames.add(jarEntry.getName());
        }
        classNames.forEach(System.out::println);
    }

}
