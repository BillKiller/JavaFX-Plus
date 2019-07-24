package cn.edu.scau.biubiusuisui.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author jack
 * @Date:2019/6/27 20:10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FXField {
    boolean readOnly() default false;

    String setter() default "";

    String add() default "";

    String delete() default "";

    String edit() default "";
}
