package cn.edu.scau.biubiusuisui.expression.data;

import cn.edu.scau.biubiusuisui.annotation.FXValue;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @Author jack
 * @Date:2019/7/27 3:13
 */
public class ExpressionParserTest {

    private ExpressionParser expressionParser;
    @Before
    public void init(){
        expressionParser = new ExpressionParser(null);
    }
    @Test
    public void parse() {
    }

    @Test
    public void getArgs() throws NoSuchMethodException {

    }
}