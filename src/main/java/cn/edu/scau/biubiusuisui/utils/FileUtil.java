package cn.edu.scau.biubiusuisui.utils;

import cn.edu.scau.biubiusuisui.exception.ProtocolNotSupport;
import cn.edu.scau.biubiusuisui.log.FXPlusLoggerFactory;
import cn.edu.scau.biubiusuisui.log.IFXPlusLogger;

import java.io.*;
import java.net.URL;

/**
 * @author jack
 * @version 1.0
 * @date 2019/6/25 7:01
 * @since JavaFX2.0 JDK1.8
 */
public class FileUtil {
    private static IFXPlusLogger logger = FXPlusLoggerFactory.getLogger(FileUtil.class);

    /**
     * @param filePath
     * @return
     * @throws ProtocolNotSupport
     * @decription 从resources文件夹中读取File
     * 输出如：    file:/Users/suisui/workspace/Idea/JavaFX-Plus/target/classes/image/icon.png
     * @version 1.0
     */
    public URL getFilePathFromResources(String filePath) throws ProtocolNotSupport {
        return FileUtil.class.getClassLoader().getResource(filePath);
    }

    /**
     * @param filePath
     * @return
     * @description 读取resources文件夹下的file，相对于resources的文件路径，如 resources/config.conf 则只需 config.conf
     */
    public static String readFileFromResources(String filePath) {
        String path = StringUtil.getRootPath(FileUtil.class.getClassLoader().getResource(filePath));
        return readFile(path);
    }

    /**
     * @param filePath 绝对路径或相对路径
     * @return
     * @description 读取文件
     */
    public static String readFile(String filePath) {
        StringBuffer content = new StringBuffer();
        try (FileReader reader = new FileReader(filePath);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String temp;
            while ((temp = br.readLine()) != null) {
                // 一次读入一行数据
                content.append(temp + "\r\n");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return content.toString();
    }

    /**
     * @param filePath
     * @param content
     * @description 写文件
     */
    public static void writeFile(String filePath, String content) {
        try {
            File writeName = new File(filePath); // 相对路径，如果没有则要建立一个新的output.txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write(content);
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
