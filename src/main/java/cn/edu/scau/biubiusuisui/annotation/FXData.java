package cn.edu.scau.biubiusuisui.annotation;

import java.lang.annotation.*;

/**
 * @Author jack
 * @Date:2019/6/25 1:36
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface FXData {
    String fx_id() default "";
}
