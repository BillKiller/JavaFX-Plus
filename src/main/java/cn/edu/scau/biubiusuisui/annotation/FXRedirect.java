package cn.edu.scau.biubiusuisui.annotation;

import java.lang.annotation.*;

/**
 * @author suiyu_yang
 * @description 重定向的注解
 * @date 2019/12/3 12:53
 * @email suiyu_yang@163.com
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface FXRedirect {
    boolean close() default true;
}
