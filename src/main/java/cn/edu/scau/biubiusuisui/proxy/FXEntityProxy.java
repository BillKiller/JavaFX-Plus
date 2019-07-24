package cn.edu.scau.biubiusuisui.proxy;

import cn.edu.scau.biubiusuisui.entity.FXFieldPropertyMapping;
import cn.edu.scau.biubiusuisui.utils.StringUtils;
import javafx.beans.property.*;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author jack
 * @Date:2019/6/27 18:47
 */
public class FXEntityProxy implements MethodInterceptor {

    Object target;

    private Map<String, Property> stringPropertyMap;
    private Map<String, FXFieldPropertyMapping> fxFieldPropertyMappingMap;

    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * intercept get and set method and
     *
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        Object o1 = methodProxy.invokeSuper(o, objects);
        String methodName = method.getName();
        String fieldName = null;
        if (methodName.length() >= 3) {
            fieldName = StringUtils.toInstanceName(methodName.substring(3));
        } else {
            return o1;
        }
        FXFieldPropertyMapping fxFieldPropertyMapping = fxFieldPropertyMappingMap.get(fieldName);
        Property property = stringPropertyMap.get(fieldName);
        if(fxFieldPropertyMapping == null || property == null){
            return o1;
        }
        Class type = fxFieldPropertyMapping.getType();
        if (methodName.startsWith("set")) {
            if(Boolean.class.equals(type)){
                ((SimpleBooleanProperty)property).set((Boolean)objects[0]);
            }else if(Double.class.equals(type)){
                ((SimpleDoubleProperty)property).set((Double)objects[0]);
            }else if (Float.class.equals(type)){
                ((SimpleFloatProperty)property).set((Float) objects[0]);
            }else if(Integer.class.equals(type)){
                ((SimpleIntegerProperty)property).set((Integer) objects[0]);
            }else if(Long.class.equals(type)){
                ((SimpleLongProperty)property).set((Long)objects[0]);
            }else if(String.class.equals(type)){
                ((SimpleStringProperty)property).set((String)objects[0]);
            }
        }else if (methodName.startsWith("add")){
            ((SimpleListProperty)(property)).add(objects[0]);
        }else if(methodName.startsWith("del")){
            ((SimpleListProperty)(property)).remove(objects[0]);
        }else if(methodName.startsWith("cls")){
            ((SimpleListProperty)(property)).clear();
        }

        //修改
        return o1;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Property getPropertyByFieldName(String name) {
        return stringPropertyMap.get(name);
    }

    public Map<String, Property> getStringPropertyMap() {
        return stringPropertyMap;
    }

    public void setStringPropertyMap(Map<String, Property> stringPropertyMap) {
        this.stringPropertyMap = stringPropertyMap;
    }

    public Map<String, FXFieldPropertyMapping> getFxFieldPropertyMappingMap() {
        return fxFieldPropertyMappingMap;
    }

    public void setFxFieldPropertyMappingMap(Map<String, FXFieldPropertyMapping> fxFieldPropertyMappingMap) {
        this.fxFieldPropertyMappingMap = fxFieldPropertyMappingMap;
    }
}
