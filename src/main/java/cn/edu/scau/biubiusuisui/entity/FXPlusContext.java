package cn.edu.scau.biubiusuisui.entity;

import cn.edu.scau.biubiusuisui.proxy.FXEntityProxy;
import javafx.beans.property.Property;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Context is use for storing Controller
 * In addition,you can store an instance into Session to use it everywhere
 *
 * @Author jack
 * @Date:2019/6/26 12:28
 */
public class FXPlusContext {

    private FXPlusContext() {
    }

    private static Map<String, List<FXBaseController>> controllerContext = new ConcurrentHashMap<>(); //FXController控制器注册表

    private static Map<Object, FXEntityProxy> beanMap = new ConcurrentHashMap<>(); // Object注册为FXEntityObject


    public static void addController(FXBaseController fxBaseController) {
        List<FXBaseController> controllers = controllerContext.get(fxBaseController.getName());
        if (controllers == null) {
            controllers = new LinkedList<>();
        }
        controllers.add(fxBaseController);
    }

    public static List<FXBaseController> getControllers(String key) {
        return controllerContext.get(key);
    }

    public static FXEntityProxy getProxyByBeanObject(Object object) {
        return beanMap.get(object);
    }

    public static void setProxyByBeanObject(Object object, FXEntityProxy fxEntityProxy) {
        beanMap.put(object, fxEntityProxy);
    }

    public static Property getEntityPropertyByName(Object object, String fieldName) {
        return getProxyByBeanObject(object).getPropertyByFieldName(fieldName);
    }
}
