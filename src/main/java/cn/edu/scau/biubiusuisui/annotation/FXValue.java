package cn.edu.scau.biubiusuisui.annotation;

import java.lang.annotation.*;

/**
 * @author jack
 * @version 1.0
 * @date 2019/7/27 3:06
 * @since JavaFX2.0 JDK1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Inherited
public @interface FXValue {
    String value();
}
