package cn.edu.scau.biubiusuisui.expression;


import com.sun.istack.internal.NotNull;
import com.sun.javafx.fxml.expression.Expression;
import com.sun.javafx.fxml.expression.ExpressionValue;
import javafx.beans.property.Property;

/**
 * 将FXBind中表达式建立绑定
 * 格式如下
 * 语法如下:
 * FXBind("S")
 * S -> left=right
 * left -> property
 * right -> ${expression}
 * expression -> bean.field
 *
 * FXBind("text=${bean.field})
 * textProperty 通过 adapter.getModelProeprty --> textProperty实例
 * bean 通过namespace 获取，因为bean有FXEntity标签，所以返回包装过后的bean的property
 * 最后
 * left.bind(right)
 *
 * @Author jack
 * @Date:2019/7/23 15:05
 */
public class ExpressionParser {

    private Object namespace;
    private static final String BIND_PREFIX = "${";
    private static final String BIND_SUFIX = "}";
    private static final String PROEPRTY = "Property";



    public ExpressionParser(Object namespace) {
        this.namespace = namespace;
    }

    private Property getLeftProperty(MyBeanAdapter myBeanAdapter, String key) {
        return (Property) myBeanAdapter.getPropertyModel(key);
    }

    private MyExpressionValue getRightExpreesion(MyBeanAdapter myBeanAdapter,String key ,String rightExpression) {
        Expression expression = Expression.valueOf(rightExpression);
        Class clazz = myBeanAdapter.getType(key);
        MyExpressionValue myExpressionValue = new MyExpressionValue(namespace, expression, clazz);
        return myExpressionValue;
    }

    public void parse(Object object, @NotNull String expression) {
        //check expression
        int index = expression.indexOf("=");
        if (index == -1) {
            return;
        }
        String[] items = expression.split("=");
        if (items.length != 2) {
            return;
        }
        String left = items[0];
        String right = items[1];
        if (left == null || right == null) {
            return;
        }
        right = right.trim();

        if (right.startsWith(BIND_PREFIX) && right.endsWith(BIND_SUFIX)){
            int length = right.length();
            right = right.substring(2,length-1);
        }
        MyBeanAdapter myBeanAdapter = new MyBeanAdapter(object);
        Property leftProperty = getLeftProperty(myBeanAdapter, left);
        MyExpressionValue rightProperty = getRightExpreesion(myBeanAdapter,left,right);
        leftProperty.bind(rightProperty);
    }
}
