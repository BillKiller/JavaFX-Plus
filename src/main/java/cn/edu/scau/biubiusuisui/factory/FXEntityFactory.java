package cn.edu.scau.biubiusuisui.factory;

import cn.edu.scau.biubiusuisui.annotation.FXField;
import cn.edu.scau.biubiusuisui.entity.FXFieldWrapper;
import cn.edu.scau.biubiusuisui.entity.FXPlusContext;
import cn.edu.scau.biubiusuisui.proxy.FXEntityProxy;
import cn.edu.scau.biubiusuisui.utils.ClassUtils;
import javafx.beans.property.*;
import javafx.collections.FXCollections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author jack
 * @Date:2019/6/28 1:12
 */
public class FXEntityFactory {

    private FXEntityFactory() {
    }

    public static Object wrapFXBean(Class clazz) {
        return wrapFXBean(clazz, new FXBuilder());
    }

    public static Object wrapFXBean(Class clazz, BeanBuilder beanBuilder) {
        Object object = null;
        object = beanBuilder.getBean(clazz);
        if (object != null) {
            return wrapFXBean(object);
        } else {
            return null;
        }
    }

    /**
     * @param object 被转换的对象
     * @return
     */
    public static Object wrapFXBean(Object object) {
        FXEntityProxy fxEntityProxy = new FXEntityProxy();
        Object proxyObject = null;
        try {
            proxyObject = fxEntityProxy.getInstance(object);  // 初始化代理类
            processFXEntityProxyFields(object, proxyObject, fxEntityProxy); //处理FXEntity上的@FXField
            FXPlusContext.setProxyByBeanObject(proxyObject, fxEntityProxy);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return proxyObject;
    }

    /**
     * @param entityObject  被转换的原Entity对象
     * @param proxyObject   被转换对象的FXEntityProxy对象
     * @param fxEntityProxy 被转换对象的FXEntityProxy类
     * @throws IllegalAccessException
     * @Description 处理FXEntity中的FXField注解，1. 添加监听 2.赋值FXEntityProxy中的fxFieldWrapperMap
     */
    private static void processFXEntityProxyFields(Object entityObject, Object proxyObject, FXEntityProxy fxEntityProxy) throws IllegalAccessException {
        Map<String, FXFieldWrapper> fxFieldWrapperMap = new HashMap<>();
        Field[] fields = entityObject.getClass().getDeclaredFields();
        for (Field field : fields) {
            Annotation annotation = ClassUtils.getAnnotationInList(FXField.class, field.getDeclaredAnnotations());
            if (annotation != null) {
                Property property = null;
                field.setAccessible(true);
                FXField fxField = (FXField) annotation;
                FXFieldWrapper fieldWrapper = new FXFieldWrapper(fxField, field.getType());
                if (field.get(entityObject) == null) { //没有初始值
                    property = getFieldDefaultProperty(field);
                } else { //有初始值
                    property = getFieldProperty(entityObject, field);
                }
                if (property != null) {
                    // 监听
                    property.addListener((object, oldVal, newVal) -> {
                        if (!fxField.readOnly()) {
                            // 判断field.getType()是否为List类型
                            if (!List.class.isAssignableFrom(field.getType())) {
                                try {
                                    field.set(proxyObject, newVal);//赋值
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
                // 设置属性
                fieldWrapper.setProperty(property);
                fxFieldWrapperMap.put(field.getName(), fieldWrapper);
            }
        }
        fxEntityProxy.setFXFieldWrapperMap(fxFieldWrapperMap);
    }

    /**
     * @param entityObject
     * @param field
     * @return
     * @throws IllegalAccessException
     * @Description 某一属性中有初始值时
     */
    private static Property getFieldProperty(Object entityObject, Field field) throws IllegalAccessException {
        Class type = field.getType();
        Object value = field.get(entityObject);
        Property property = null;

        if (Boolean.class.equals(type) || boolean.class.equals(type)) {
            property = new SimpleBooleanProperty((Boolean) value);
        } else if (Double.class.equals(type) || double.class.equals(type)) {
            property = new SimpleDoubleProperty((Double) value);
        } else if (Float.class.equals(type) || float.class.equals(type)) {
            property = new SimpleFloatProperty((Float) value);
        } else if (Integer.class.equals(type) || int.class.equals(type)) {
            property = new SimpleIntegerProperty((Integer) value);
        } else if (Long.class.equals(type) || long.class.equals(type)) {
            property = new SimpleLongProperty((Long) value);
        } else if (String.class.equals(type)) {
            property = new SimpleStringProperty((String) value);
        } else if (List.class.isAssignableFrom(type)) {
            property = new SimpleListProperty(FXCollections.observableList((List) value));
        } else if (Object.class.isAssignableFrom(type)) {
            property = new SimpleObjectProperty(value);
        }
        return property;
    }

    /**
     * @param field
     * @return
     * @throws IllegalAccessException
     * @Description 某一属性中无初始值
     */
    private static Property getFieldDefaultProperty(Field field) throws IllegalAccessException {
        Class type = field.getType();
        Property property = null;

        if (Boolean.class.equals(type) || boolean.class.equals(type)) {
            property = new SimpleBooleanProperty();
        } else if (Double.class.equals(type) || double.class.equals(type)) {
            property = new SimpleDoubleProperty();
        } else if (Float.class.equals(type) || float.class.equals(type)) {
            property = new SimpleFloatProperty();
        } else if (Integer.class.equals(type) || int.class.equals(type)) {
            property = new SimpleIntegerProperty();
        } else if (Long.class.equals(type) || long.class.equals(type)) {
            property = new SimpleLongProperty();
        } else if (String.class.equals(type)) {
            property = new SimpleStringProperty();
        } else if (List.class.isAssignableFrom(type)) {
            property = new SimpleListProperty();
        } else if (Object.class.isAssignableFrom(type)) {
            property = new SimpleObjectProperty();
        }
        return property;
    }
}
