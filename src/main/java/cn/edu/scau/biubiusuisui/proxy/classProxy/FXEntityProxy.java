package cn.edu.scau.biubiusuisui.proxy.classProxy;

import cn.edu.scau.biubiusuisui.entity.FXFieldMethodMapping;
import cn.edu.scau.biubiusuisui.utils.StringUtils;
import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author jack
 * @Date:2019/6/27 18:47
 */
public class FXEntityProxy implements MethodInterceptor {

    Object target;

    private Map<String, Property> stringPropertyMap;
    private Map<String, FXFieldMethodMapping> stringFXFieldMethodMappingMap;

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


        SimpleStringProperty property = (SimpleStringProperty) stringPropertyMap.get(fieldName);

        if (methodName.startsWith("set")) {
            property.set((String) objects[0]);
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
}
