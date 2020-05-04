package cn.edu.scau.biubiusuisui.annotation;

import java.lang.annotation.*;

/**
 * @author jack
 * @version 1.0
 * @date 2019/6/25 1:36
 * @since JavaFX2.0 JDK1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface FXData {
    String fx_id() default "";
}
