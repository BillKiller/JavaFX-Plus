package cn.edu.scau.biubiusuisui.factory;

import cn.edu.scau.biubiusuisui.annotation.FXField;
import cn.edu.scau.biubiusuisui.entity.FXFieldPropertyMapping;
import cn.edu.scau.biubiusuisui.entity.FXPlusContext;
import cn.edu.scau.biubiusuisui.proxy.classProxy.FXEntityProxy;
import cn.edu.scau.biubiusuisui.utils.ClassUtils;
import javafx.beans.property.*;
import javafx.collections.FXCollections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * @Author jack
 * @Date:2019/6/28 1:12
 */
public class FXEntityFactory {

    private FXEntityFactory(){}

    public  static  Object createJavaBeanProxy(Class clazz){
        return createJavaBeanProxy(clazz, new FXBuilder());
    }
    public  static  Object createJavaBeanProxy(Class clazz,BeanBuilder beanBuilder)  {
        Object object = null;
        object = beanBuilder.getBean(clazz);
        if(object !=null){
            return createJavaBeanProxy(object);
        }else {
            return null;
        }
    }

    public static Object createJavaBeanProxy(Object object){
        FXEntityProxy fxEntityProxy = new FXEntityProxy();
        Object objectProxy = null;
        try {
            objectProxy = fxEntityProxy.getInstance(object);
            processFXEntityProxy(object,objectProxy,fxEntityProxy);
            FXPlusContext.setProxyByBeanObject(objectProxy, fxEntityProxy);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return objectProxy;
    }


    public static void processFXEntityProxy(Object entity, Object proxy,FXEntityProxy fxEntityProxy) throws IllegalAccessException {
        Map<String, Property> stringPropertyMap = new HashMap<>();
        Map<String, FXFieldPropertyMapping> fxFieldPropertyMappingHashMap = new HashMap<>();
        Field []fields = entity.getClass().getDeclaredFields();
        for(Field field:fields){
            Annotation annotation = ClassUtils.getAnnotationInList( FXField.class,field.getDeclaredAnnotations());
            if(annotation != null){
                Property property = null;
                field.setAccessible(true);
                FXField fxField = (FXField)annotation;

                FXFieldPropertyMapping fieldPropertyMapping = new FXFieldPropertyMapping();
                fieldPropertyMapping.setReadOnly(fxField.readOnly());
                fieldPropertyMapping.setType(field.getType());

                fxFieldPropertyMappingHashMap.put(field.getName(), fieldPropertyMapping);

                if(field.get(entity) == null){
                        property = getFieldDefalutProperty(field);

                }else{
                        property = getFieldProperty(entity, field);
                }
                if(property !=null) {
                    //添加时间;
                    property.addListener((object,oldVal,newVal)->{
                        if(!fxField.readOnly()) {
                            if(!field.getType().equals(List.class)) {
                                try {
                                    field.set(proxy, newVal);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    stringPropertyMap.put(field.getName(), property);
                }
            }
        }
        fxEntityProxy.setStringPropertyMap(stringPropertyMap);
        fxEntityProxy.setFxFieldPropertyMappingMap(fxFieldPropertyMappingHashMap);
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
        }else if(List.class.equals(type)){
            property = new SimpleListProperty(FXCollections.observableList((List)value));
        }else if(Object.class.equals(type)){
            property = new SimpleObjectProperty(value);
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
        }else if(List.class.equals(type)){
            property = new SimpleListProperty();
        }else if(Object.class.equals(type)){
            property = new SimpleObjectProperty();
        }
        return property;
    }

}
