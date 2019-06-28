package cn.edu.scau.biubiusuisui.factory;

import cn.edu.scau.biubiusuisui.annotation.FXField;
import cn.edu.scau.biubiusuisui.entity.FXPlusContext;
import cn.edu.scau.biubiusuisui.function.DefaultEventFunction;
import cn.edu.scau.biubiusuisui.proxy.classProxy.FXEntityProxy;
import cn.edu.scau.biubiusuisui.utils.ClassUtils;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author jack
 * @Date:2019/6/28 1:12
 */
public class FXEntityFactory {

    private FXEntityFactory(){}

    private  static FXEntityFactory instance = null;

    ChangeListener propertyChangeEvent = new DefaultEventFunction();

    public synchronized static FXEntityFactory getInstance() {
        if(instance == null){
            instance = new FXEntityFactory();
        }
        return instance;
    }

    public Object createJavaBeanProxy(Class clazz)  {
        Object object = null;
        try {
            object = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return createJavaBeanProxy(object);
    }

    public Object createJavaBeanProxy(Object object){
        FXEntityProxy fxEntityProxy = new FXEntityProxy();
        Object objectProxy = null;
        try {

            objectProxy = fxEntityProxy.getInstance(object);
            Map<String, Property> stringPropertyMap = FXEntityFactory.getInstance().getEntityProperty(object,objectProxy);
            fxEntityProxy.setStringPropertyMap(stringPropertyMap);

            FXPlusContext.setProxyByBeanObject(objectProxy, fxEntityProxy);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return objectProxy;
    }


    public Map<String, Property> getEntityProperty(Object entity,Object proxy) throws IllegalAccessException {
        Map<String, Property> stringPropertyMap = new HashMap<>();
        Field []fields = entity.getClass().getDeclaredFields();
        for(Field field:fields){
            Annotation annotation = ClassUtils.getAnnotationInList( FXField.class,field.getDeclaredAnnotations());
            if(annotation != null){
                Property property;
                field.setAccessible(true);
                FXField fxField = (FXField)annotation;
                if(field.get(entity) == null){
                    if(fxField.readOnly()){
                        property = getReadOnlyFieldDefalutProperty(field);
                    }else{
                        property = getFieldDefalutProperty(field);
                    }

                }else{

                    if(fxField.readOnly()){
                        property = getReadOnlyProperty(entity,field);
                    }else{
                        property = getFieldProperty(entity, field);
                    }
                }
                if(property !=null) {
                    //添加时间;
                    property.addListener((object,oldVal,newVal)->{

                        try {
                            field.set(proxy, newVal);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });
                    stringPropertyMap.put(field.getName(), property);
                }
            }
        }
        return stringPropertyMap;
    }

    private static Property getFieldProperty(Object object,Field field) throws IllegalAccessException {
        Class type = field.getType();
        Object value = field.get(object);
        Property property = null;
        if(Boolean.class.equals(type)){
            property = new SimpleBooleanProperty((Boolean) value);
        }else if(Double.class.equals(type)){
            property = new SimpleDoubleProperty((Double) value);
        }else if (Float.class.equals(type)){
            property = new SimpleFloatProperty((Float) value);
        }else if(Integer.class.equals(type)){
            property = new SimpleIntegerProperty((Integer) value);
        }else if(Long.class.equals(type)){
            property = new SimpleLongProperty((Long) value);
        }else if(String.class.equals(type)){
            property = new SimpleStringProperty((String) value);
        }
        return property;
    }
    private static Property getFieldDefalutProperty(Field field) throws  IllegalAccessException{
        Class type = field.getType();
        Property property = null;
        if(Boolean.class.equals(type)){
            property = new SimpleBooleanProperty();
        }else if(Double.class.equals(type)){
            property = new SimpleDoubleProperty();
        }else if (Float.class.equals(type)){
            property = new SimpleFloatProperty();
        }else if(Integer.class.equals(type)){
            property = new SimpleIntegerProperty();
        }else if(Long.class.equals(type)){
            property = new SimpleLongProperty();
        }else if(String.class.equals(type)){
            property = new SimpleStringProperty();
        }
        return property;
    }
    private static Property getReadOnlyFieldDefalutProperty(Field field) throws  IllegalAccessException{
        Class type = field.getType();
        Property property = null;
        if(Boolean.class.equals(type)){
            property = new ReadOnlyBooleanWrapper();
        }else if(Double.class.equals(type)){
            property = new ReadOnlyDoubleWrapper();
        }else if (Float.class.equals(type)){
            property = new ReadOnlyFloatWrapper();
        }else if(Integer.class.equals(type)){
            property = new ReadOnlyIntegerWrapper();
        }else if(Long.class.equals(type)){
            property = new ReadOnlyLongWrapper();
        }else if(String.class.equals(type)){
            property = new ReadOnlyStringWrapper();
        }
        return property;
    }
    private static Property getReadOnlyProperty(Object object,Field field) throws IllegalAccessException {
        Class type = field.getType();
        Object value = field.get(object);
        Property property = null;
        if(Boolean.class.equals(type)){
            property = new ReadOnlyBooleanWrapper((Boolean) value);
        }else if(Double.class.equals(type)){
            property = new ReadOnlyDoubleWrapper((Double) value);
        }else if (Float.class.equals(type)){
            property = new ReadOnlyFloatWrapper((Float) value);
        }else if(Integer.class.equals(type)){
            property = new ReadOnlyIntegerWrapper((Integer) value);
        }else if(Long.class.equals(type)){
            property = new ReadOnlyLongWrapper((Long) value);
        }else if(String.class.equals(type)){
            property = new ReadOnlyStringWrapper((String) value);
        }
        return property;
    }

    private static Method getFieldMethodMapping(Object object ,Field field){
        Class clazz = object.getClass();
        return null;
    }

}
