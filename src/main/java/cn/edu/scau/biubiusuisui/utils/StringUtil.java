package cn.edu.scau.biubiusuisui.utils;

import cn.edu.scau.biubiusuisui.log.FXPlusLoggerFactory;
import cn.edu.scau.biubiusuisui.log.IFXPlusLogger;

import java.net.URL;

/**
 * @author jack
 * @author suisui
 * @version 1.0
 * @date 2019/6/25 3:46
 * @since JavaFX2.0 JDK1.8
 */
public class StringUtil {
    private static IFXPlusLogger logger = FXPlusLoggerFactory.getLogger(StringUtil.class);

    private StringUtil() {

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
     * "cn/fh/lightning" -> "cn.fh.lightning"
     *
     * @param name
     * @return
     */
    public static String splashToDot(String name) {

        return name.replaceAll("/", "\\.");
    }

    /**
     * "Apple.class" -> "Apple"
     */
    public static String trimExtension(String name) {
        int pos = name.lastIndexOf('.');
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

    /**
     * MainController$receive -> MainController
     *
     * @param name
     * @return
     */
    public static String getBaseClassName(String name) {
        int index = name.indexOf("$");
        if (index == -1) {
            return name;
        }
//        System.out.println(name.substring(0, index));
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

    /**
     * cn/edu/scau/biubiusuisui/resources/fxml/languageDemo/languageDemo.fxml -> fxml/languageDemo/languageDemo.fxml
     *
     * @param name
     * @return
     * @description 获取相对于resources目录下的路径
     */
    public static String getFilePathInResources(String name) {
        String resources = "resources";
        int resIdx = name.indexOf(resources);
        if (resIdx == -1) {
            return name;
        }
        return name.substring(resIdx + resources.length() + 1);
    }

    /**
     * cn/edu/scau/biubiusuisui/resources/fxml/languageDemo/languageDemo.fxml -> languageDemo
     *
     * @param name 文件名
     * @return
     * @version 1.2
     */
    public static String getFileBaseName(String name) {
        String result = "";
        String[] tempStrs = name.split("/");
        if (1 == tempStrs.length) { //只有文件名，即name: languageDemo.fxml
            result = StringUtil.trimExtension(name);
        } else {
            result = StringUtil.trimExtension(tempStrs[tempStrs.length - 1]);
        }
        return result;
    }

}