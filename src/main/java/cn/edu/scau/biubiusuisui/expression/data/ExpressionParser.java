package cn.edu.scau.biubiusuisui.expression.data;


import cn.edu.scau.biubiusuisui.annotation.FXValue;
import cn.edu.scau.biubiusuisui.exception.NoSuchChangeMethod;
import com.sun.istack.internal.NotNull;
import com.sun.javafx.fxml.expression.Expression;
import com.sun.javafx.fxml.expression.VariableExpression;
import javafx.beans.property.Property;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.NoSuchElementException;

/**
 * 将FXBind中表达式建立绑定
 * 格式如下
 * 语法如下:
 * FXBind("S")
 * S -> left=right
 * left -> property
 * right -> ${expression}
 * expression -> bean.field
 * <p>
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
    private Object targetController;
    private static final String BIND_PREFIX = "${";
    private static final String BIND_SUFIX = "}";
    private static final String PROEPRTY = "Property";

    public enum ExpressionType {
        DataExpression,
        ActionExpression
    }

    public ExpressionParser(Object namespace, Object targetController) {
        this.namespace = namespace;
        this.targetController = targetController;
    }

    public ExpressionParser(Object namespace) {
        this.namespace = namespace;
    }

    private Property getLeftProperty(MyBeanAdapter myBeanAdapter, String key) {
        return (Property) myBeanAdapter.getPropertyModel(key);
    }

    private static final String FUNCTION_PREFIX = "@";

    private MyExpressionValue getRightExpreesion(MyBeanAdapter myBeanAdapter, String key, String rightExpression) {
        Expression expression = null;
        if (rightExpression.startsWith(FUNCTION_PREFIX)) {
            expression = getFunctionExpression(rightExpression);
        } else {
            expression = Expression.valueOf(rightExpression);
        }
        Class clazz = myBeanAdapter.getType(key);
        MyExpressionValue myExpressionValue = new MyExpressionValue(namespace, expression, clazz);
        return myExpressionValue;
    }

    private Expression getFunctionExpression(String rightExpression) {
        Expression expression = null;
        int indexLeft = rightExpression.indexOf("(");
        String methodName = rightExpression.substring(1,indexLeft);
        int indexRight = rightExpression.indexOf(")");
        String argString = rightExpression.substring(indexLeft + 1, indexRight);
        String[] args = null;
        if(!"".equals(argString.trim())) {
            args = argString.split(",");
        }
        Class clazz = targetController.getClass();
        Method[] methods = clazz.getMethods();
        Expression[] expressions = null;
        if(args!=null) {
            expressions = new Expression[args.length];
            for (int i = 0; i < args.length; i++) {
                if (!"".equals(args[i].trim())) {
                    expressions[i] = Expression.valueOf(args[i]);
                }
            }
        }
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                expression = new FunctionExpression(method, targetController, expressions);
            }
        }
        return expression;
    }

    public void parse(Object object, @NotNull String expression) throws NoSuchChangeMethod {
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
        ExpressionType expressionType;
        if (right.startsWith(BIND_PREFIX) && right.endsWith(BIND_SUFIX)) {
            int length = right.length();
            right = right.substring(2, length - 1);
            expressionType = ExpressionType.DataExpression;
        } else {
            right = right.substring(1); //#changeMethod -> changeMethod
            expressionType = ExpressionType.ActionExpression;
        }
        MyBeanAdapter myBeanAdapter = new MyBeanAdapter(object);
        Property leftProperty = getLeftProperty(myBeanAdapter, left);
        switch (expressionType) {
            case DataExpression:
                bindDataExpression(left, right, myBeanAdapter, leftProperty);
                break;
            case ActionExpression:
                //
                bindActionExpression(right, leftProperty);
        }

    }

    private void bindActionExpression(String right, Property leftProperty) throws NoSuchChangeMethod {
        Class clazz = targetController.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(right)) {
                leftProperty.addListener((observable, oldValue, newValue) -> {
                    try {
                        Object[] objects = getArgs(method, observable, newValue, oldValue);
                        method.invoke(targetController, objects);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    private void bindDataExpression(String left, String right, MyBeanAdapter myBeanAdapter, Property leftProperty) {
        MyExpressionValue rightProperty = getRightExpreesion(myBeanAdapter, left, right);
        leftProperty.bind(rightProperty);
    }

    public Object[] getArgs(Method method, Object... args) {
        Parameter[] parameters = method.getParameters();
        Object[] objects = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            FXValue annotation = parameters[i].getAnnotation(FXValue.class);
            switch (annotation.value()) {
                case "target":
                    objects[i] = args[0];
                    break;
                case "newValue":
                    objects[i] = args[1];
                    break;
                case "oldValue":
                    objects[i] = args[2];
                    break;
            }
        }
        return objects;
    }
}
