package cn.edu.scau.biubiusuisui.entity;

import cn.edu.scau.biubiusuisui.proxy.classProxy.FXEntityProxy;
import javafx.beans.property.Property;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * Context is use for storing Controller
 * In addition,you can store an instance into Session to use it everywhere
 *
 * @Author jack
 * @Date:2019/6/26 12:28
 */
public class FXPlusContext {

    private FXPlusContext(){}

    private static Map<String, List<FXBaseController>> controllerContext = new ConcurrentHashMap<>(); //FXController控制器注册表

    private static Map<Object, FXEntityProxy> beanProxyMap = new ConcurrentHashMap<>(); // Object注册为FXEntityObject

    public static Property getEntityPropertyByName(Object object, String fieldName){
        FXEntityProxy fxEntityProxy = FXPlusContext.getProryByBeanObject(object);
        if(fxEntityProxy == null){
            return null;
        }
        return fxEntityProxy.getStringPropertyMap().get(fieldName);
    }

    private static Map<String, Object> session = new ConcurrentHashMap<>();

    public static void addController(FXBaseController fxBaseController){
        List<FXBaseController> controllers = controllerContext.get(fxBaseController.getName());
        if(controllers == null){
            controllers = new LinkedList<>();
        }
        controllers.add(fxBaseController);
    }

    public static List<FXBaseController> getControllers(String key){
        return controllerContext.get(key);
    }

    public static FXEntityProxy getProryByBeanObject(Object object){
        return beanProxyMap.get(object);
    }

    public static void setProxyByBeanObject(Object object,FXEntityProxy fxEntityProxy){
         beanProxyMap.put(object,fxEntityProxy);
    }
    public static Map<Object, FXEntityProxy> getBeanProxyMap() {
        return beanProxyMap;
    }

    public static void setBeanProxyMap(Map<Object, FXEntityProxy> beanProxyMap) {
        FXPlusContext.beanProxyMap = beanProxyMap;
    }

    public static Map<String, List<FXBaseController>> getControllerContext() {
        return controllerContext;
    }

    public static void setControllerContext(Map<String, List<FXBaseController>> controllerContext) {
        FXPlusContext.controllerContext = controllerContext;
    }

    public static Map<String, Object> getSession() {
        return session;
    }

    public static void setSession(Map<String, Object> session) {
        FXPlusContext.session = session;
    }
}
