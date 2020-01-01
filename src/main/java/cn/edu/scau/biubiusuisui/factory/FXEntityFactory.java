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

    public static Object wrapFxBean(Class clazz) {
        return wrapFxBean(clazz, new FXBuilder());
    }

    public static Object wrapFxBean(Class clazz, BeanBuilder beanBuilder) {
        Object object = null;
        object = beanBuilder.getBean(clazz);
        if (object != null) {
            return wrapFxBean(object);
        } else {
            return null;
        }
    }

    public static Object wrapFxBean(Object object) {
        FXEntityProxy fxEntityProxy = new FXEntityProxy();
        Object objectProxy = null;
        try {
            objectProxy = fxEntityProxy.getInstance(object);  // 初始化代理类
            processFXEntityProxy(object, objectProxy, fxEntityProxy);
            FXPlusContext.setProxyByBeanObject(objectProxy, fxEntityProxy);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return objectProxy;
    }

    private static void processFXEntityProxy(Object entity, Object proxy, FXEntityProxy fxEntityProxy) throws IllegalAccessException {
        Map<String, FXFieldWrapper> fxFieldWrapperMap = new HashMap<>();
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            Annotation annotation = ClassUtils.getAnnotationInList(FXField.class, field.getDeclaredAnnotations());
            if (annotation != null) {
                Property property = null;
                field.setAccessible(true);
                FXField fxField = (FXField) annotation;
                FXFieldWrapper fieldWrapper = new FXFieldWrapper();
                fieldWrapper.setFxField(fxField);
                fieldWrapper.setType(field.getType());
                if (field.get(entity) == null) {
                    property = getFieldDefaultProperty(field);
                } else {
                    property = getFieldProperty(entity, field);
                }
                if (property != null) {
                    property.addListener((object, oldVal, newVal) -> {
                        if (!fxField.readOnly()) {
                            if (!List.class.isAssignableFrom(field.getType())) {
                                try {
                                    field.set(proxy, newVal);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
                fieldWrapper.setProperty(property);
                fxFieldWrapperMap.put(field.getName(), fieldWrapper);
            }
        }
        fxEntityProxy.setFxFieldWrapperMap(fxFieldWrapperMap);
    }


    private static Property getFieldProperty(Object object, Field field) throws IllegalAccessException {
        Class type = field.getType();
        Object value = field.get(object);

        Property property = null;

        if (Boolean.class.equals(type) || boolean.class.equals(type)) {
            property = new SimpleBooleanProperty((Boolean) value);
        } else if (Double.class.equals(type) || double.class.equals(type)) {
            property = new SimpleDoubleProperty((Double) value);
        } else if (Float.class.equals(type) || float.class.equals(type)) {
            property = new SimpleFloatProperty((Float) value);
        } else if (Integer.class.equals(type) || int.class.equals(type)) {
            property = new SimpleIntegerProperty((Integer) value);
        } else if (Long.class.equals(type) || long.class.equals(property)) {
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
        } else if (Long.class.equals(type) || long.class.equals(property)) {
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
