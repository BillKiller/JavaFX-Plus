package cn.edu.scau.biubiusuisui.annotation;

import java.lang.annotation.*;

/**
 * @Author jack
 * @Date:2019/6/25 13:06
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.METHOD)
public @interface FXReceiver {
    String name();
}
