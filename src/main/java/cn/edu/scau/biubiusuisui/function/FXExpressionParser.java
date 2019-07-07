package cn.edu.scau.biubiusuisui.function;

import cn.edu.scau.biubiusuisui.annotation.FXBind;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.entity.FXPlusContext;
import com.sun.javafx.fxml.BeanAdapter;
import com.sun.javafx.fxml.expression.Expression;
import com.sun.javafx.fxml.expression.ExpressionValue;
import javafx.beans.property.Property;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Author jack
 * @Date:2019/7/4 13:55
 */
public class FXExpressionParser {

    private static final String BIND_EXPRESSION_PREFIX = "${";
    private static final String BIND_EXPRESSION_SUFIX = "}";

    public static void parse(FXBaseController fxBaseController, ObservableMap<String, Object> namespaces) {
//        Class clazz = fxBaseController.getClass();
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            FXBind fxBind = field.getDeclaredAnnotation(FXBind.class);
//            if (fxBind != null) {
//                String[] names = fxBind.bind();
//                for (String propertyName : names) {
//                    String args[] = propertyName.split("=");
//                    String name = args[0];
//                    String value = args[1];
//                    if (value.startsWith(BIND_EXPRESSION_PREFIX) && value.endsWith(BIND_EXPRESSION_SUFIX)) {
//                        value = value.substring(BIND_EXPRESSION_PREFIX.length(), value.length() - 1);
//                        Object object = null;
//                        try {
//                            field.setAccessible(true);
//                            object = field.get(fxBaseController);
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        }
//
//                        BeanAdapter targetAdapter = new BeanAdapter(object);
//                        ObservableValue<Object> propertyModel = targetAdapter.getPropertyModel(name);
//                        Class<?> type = targetAdapter.getType(name);
//                        if (propertyModel instanceof Property<?>) {
//                            ((Property<Object>) propertyModel).bind(FXPlusContext.getEntityPropertyByName(fxBaseController,));
//                        }
//                    }
//                }
//            }
    }
}
