package cn.edu.scau.biubiusuisui.annotation;

import java.lang.annotation.*;

/**
 * @Author jack
 * @Date:2019/7/27 3:06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Inherited
public @interface FXValue {
    String value();
}
