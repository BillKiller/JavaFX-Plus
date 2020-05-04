package cn.edu.scau.biubiusuisui.proxy;

import cn.edu.scau.biubiusuisui.entity.FXFieldWrapper;
import cn.edu.scau.biubiusuisui.utils.StringUtil;
import javafx.beans.property.*;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author jack
 * @version 1.0
 * @date 2019/6/27 18:47
 * @since JavaFX2.0 JDK1.8
 */
public class FXEntityProxy implements MethodInterceptor {

    Object target;
    private Map<String, FXFieldWrapper> fxFieldWrapperMap;

    /**
     * @param target
     * @return
     * @Desciption 通过getInstance获取代理对象
     */
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
     * @param proxy       cglib生成的代理对象
     * @param method      被代理对象的方法
     * @param args        拦截的方法的入参
     * @param methodProxy 拦截方法的代理方法
     * @return
     * @throws Throwable
     * @Descripton 拦截getter, setter, del, cls, add方法
     */

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object revokeResult = methodProxy.invokeSuper(proxy, args);  //获取该方法运行后的结果
        String methodName = method.getName();
        String fieldName = null;
        if (methodName.length() >= 3) {
            fieldName = StringUtil.toInstanceName(methodName.substring(3));  // 该method有可能是getter和setter方法，进行处理
        } else {
            return revokeResult;
        }
        FXFieldWrapper fxFieldWrapper = fxFieldWrapperMap.get(fieldName);
        Property property = getPropertyByFieldName(fieldName);
        if (fxFieldWrapper == null || property == null) { //非属性的getter或setter
            return revokeResult;
        }
        Class type = fxFieldWrapper.getType();
        if (methodName.startsWith("set")) {
            if (Boolean.class.equals(type) || boolean.class.equals(type)) {
                ((SimpleBooleanProperty) property).set((Boolean) args[0]);
            } else if (Double.class.equals(type) || double.class.equals(type)) {
                ((SimpleDoubleProperty) property).set((Double) args[0]);
            } else if (Float.class.equals(type) || float.class.equals(type)) {
                ((SimpleFloatProperty) property).set((Float) args[0]);
            } else if (Integer.class.equals(type) || int.class.equals(type)) {
                ((SimpleIntegerProperty) property).set((Integer) args[0]);
            } else if (Long.class.equals(type) || long.class.equals(type)) {
                ((SimpleLongProperty) property).set((Long) args[0]);
            } else if (String.class.equals(type)) {
                ((SimpleStringProperty) property).set((String) args[0]);
            }
        } else if (methodName.startsWith("add")) {
            ((SimpleListProperty) (property)).add(args[0]);
        } else if (methodName.startsWith("del")) {
            ((SimpleListProperty) (property)).remove(args[0]);
        } else if (methodName.startsWith("cls")) {
            ((SimpleListProperty) (property)).clear();
        }
        return revokeResult;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Property getPropertyByFieldName(String name) {
        if (fxFieldWrapperMap.get(name) == null) {
            return null;
        }
        return fxFieldWrapperMap.get(name).getProperty();
    }

    public Map<String, FXFieldWrapper> getFXFieldWrapperMap() {
        return fxFieldWrapperMap;
    }

    public void setFXFieldWrapperMap(Map<String, FXFieldWrapper> fxFieldWrapperMap) {
        this.fxFieldWrapperMap = fxFieldWrapperMap;
    }
}
