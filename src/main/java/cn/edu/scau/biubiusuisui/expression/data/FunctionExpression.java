package cn.edu.scau.biubiusuisui.expression.data;

import com.sun.javafx.fxml.expression.Expression;
import com.sun.javafx.fxml.expression.KeyPath;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author jack
 * @Date:2019/7/27 20:00
 */
public class FunctionExpression extends Expression {

    private Method method;
    private Object target;
    private Expression[] args;

    public FunctionExpression(Method method, Object target, Expression[] expressions) {
        this.method = method;
        this.target = target;
        this.args = expressions;
    }

    @Override
    public List<KeyPath> getArguments() {
        List<KeyPath> list = new ArrayList<>();
        if (args != null) {
            for (Expression expression : args) {
                list.addAll(expression.getArguments());
            }
        }
        return list;
    }

    @Override
    public Object evaluate(Object namespace) {
        Object result = null;
        if (args != null) {
            Object[] values = new Object[args.length];
            for (int i = 0; i < args.length; i++) {
                values[i] = args[i].evaluate(namespace);
            }
            try {
                result = method.invoke(target, values);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return result;
        } else {
            try {
                result = method.invoke(target);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void update(Object namespace, Object value) {

    }

    @Override
    public boolean isDefined(Object namespace) {
        return false;
    }

    @Override
    public boolean isLValue() {
        return false;
    }

    @Override
    protected void getArguments(List arguments) {

    }
}
