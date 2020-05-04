package cn.edu.scau.biubiusuisui.annotation;

import java.lang.annotation.*;

/**
 * @author suisui
 * @version 1.1
 * @description 重定向的注解
 * @date 2019/12/3 12:53
 * @since JavaFX2.0 JDK1.8
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface FXRedirect {
    boolean close() default true;
}
