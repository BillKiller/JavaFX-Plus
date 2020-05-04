package cn.edu.scau.biubiusuisui.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jack
 * @date 2019/6/25 2:55
 * @since JavaFX2.0 JDK1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FXScan {
    String[] base() default ".";
}
