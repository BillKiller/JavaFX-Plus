package cn.edu.scau.biubiusuisui.utils;

/**
 * @author suisui
 * @version 1.2
 * @description 路径工具类
 * @date 2020/5/2 14:43
 * @since JDK1.8
 */
public class PathUtil {
    public static String getCurrentPath() {
        return System.getProperty("user.dir");
    }
}
