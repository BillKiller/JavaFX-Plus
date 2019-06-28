package cn.edu.scau.biubiusuisui.utils;

import cn.edu.scau.biubiusuisui.exception.ProtocolNotSupport;

import java.net.URL;

/**
 * @Author jack
 * @Date:2019/6/25 7:01
 */
public class FileUtils {


    public URL getFXMLFile(String fxmlFilePath) throws ProtocolNotSupport {
        return this.getClass().getClassLoader().getResource(fxmlFilePath);
    }
}
