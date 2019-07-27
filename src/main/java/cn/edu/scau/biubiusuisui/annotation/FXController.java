package cn.edu.scau.biubiusuisui.annotation;

import java.lang.annotation.*;

/**
 * This is use for marking A controller as FX-Plus Controller
 @Author jack
 @Date:2019/6/25 1:34
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface FXController {
    String path();
    double preWidth() default 0.0;
    double preHeight() default 0.0;
}
