package cn.edu.scau.biubiusuisui.annotation;

import java.lang.annotation.*;

/**
 * @Author jack
 * @Date:2019/6/25 1:36
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface FXWindow {
    double preWidth() default 0.0;
    double preHeight()default 0.0;
    String title () ;
}
