package cn.edu.scau.biubiusuisui.annotation;

import java.lang.annotation.*;

/**
 * @Author jack
 * @Date:2019/7/4 13:58
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface FXBind {
    String  [] bind();
}
