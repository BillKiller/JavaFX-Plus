package cn.edu.scau.biubiusuisui.annotation;

import java.lang.annotation.*;

/**
 * @version 1.0
 * @since JavaFX2.0 JDK1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface FXBind {
    String[] value();
}
