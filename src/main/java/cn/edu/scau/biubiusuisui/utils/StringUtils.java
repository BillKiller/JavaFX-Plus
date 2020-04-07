package cn.edu.scau.biubiusuisui.utils;

/**
 * @Author jack
 * @Date:2019/6/25 3:46
 */

import cn.edu.scau.biubiusuisui.exception.InvalidURLException;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class StringUtils {

    private StringUtils() {

    }

    /**
     * "file:/home/whf/cn/fh" -> "/home/whf/cn/fh"
     * "jar:file:/home/whf/foo.jar!cn/fh" -> "/home/whf/foo.jar"
     */
    public static String getRootPath(URL url) {
        String fileUrl = url.getFile();
        int pos = fileUrl.indexOf('!');

        if (-1 == pos) {
            return fileUrl;
        }

        return fileUrl.substring(5, pos);
    }

    /**
     * "cn.fh.lightning" -> "cn/fh/lightning"
     *
     * @param name
     * @return
     */
    public static String dotToSplash(String name) {
        return name.replaceAll("\\.", "/");
    }

    /**
     * "Apple.class" -> "Apple"
     */
    public static String trimExtension(String name) {
        int pos = name.indexOf('.');
        if (-1 != pos) {
            return name.substring(0, pos);
        }

        return name;
    }

    /**
     * /application/home -> /home
     *
     * @param uri
     * @return
     */
    public static String trimURI(String uri) {
        String trimmed = uri.substring(1);
        int splashIndex = trimmed.indexOf('/');

        return trimmed.substring(splashIndex);
    }

    public static String getBaseClassName(String name) {
        int index = name.indexOf("$");
        if (index == -1) {
            return name;
        }
//        System.out.println(name.substring(0,index));
        return name.substring(0, index);
    }


    /**
     * Object -> object ; Student -> student
     *
     * @param name
     * @return
     */
    public static String toInstanceName(String name) {
        String result = name.substring(0, 1).toLowerCase().concat(name.substring(1));
        return result;
    }

    /**
     * object -> Object ; student -> Student
     *
     * @param name
     * @return
     */
    public static String toClassName(String name) {
        String result = name.substring(0, 1).toUpperCase().concat(name.substring(1));
        return result;
    }

}