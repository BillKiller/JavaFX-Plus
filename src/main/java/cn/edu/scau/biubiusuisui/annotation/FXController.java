package cn.edu.scau.biubiusuisui.annotation;

import cn.edu.scau.biubiusuisui.entity.FXPlusLocale;

import java.lang.annotation.*;

/**
 * This is use for marking A controller as FX-Plus Controller
 *
 * @author jack
 * @author suisui
 * @version 1.0
 * @date 2019/6/25 1:34
 * @since JavaFX2.0 JDK1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface FXController {
    String path();

    double preWidth() default 0.0;

    double preHeight() default 0.0;

    /**
     * @return
     * @description 程序语言，默认不设置
     * @version 1.2
     */
    FXPlusLocale locale() default FXPlusLocale.NONE;
}
