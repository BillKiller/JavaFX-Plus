package cn.edu.scau.biubiusuisui.annotation;

import cn.edu.scau.biubiusuisui.entity.FXPlusLocale;
import javafx.stage.StageStyle;

import java.lang.annotation.*;
import java.util.Locale;

/**
 * @author jack
 * @author suisui
 * @version 1.0
 * @date 2019/6/25 1:36
 * @since JavaFX2.0 JDK1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface FXWindow {
    double preWidth() default 0.0;

    double preHeight() default 0.0;

    double minWidth() default 0.0;

    double minHeight() default 0.0;

    boolean resizable() default false;

    boolean draggable() default false;

    boolean mainStage() default false;

    StageStyle style() default StageStyle.DECORATED;

    String title();

    /**
     * @description 图标URL
     * @version 1.2
     */
    String icon() default "";

}
