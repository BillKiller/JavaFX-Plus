package cn.edu.scau.biubiusuisui.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This cn.edu.scau.biubiusuisui.annotation is used for a method which can send a signal to all consumer
 * And FXSender has a name which is used for identifying different method
 * It is legal to use same method which has same name because JavaFX-Plus will identify a method with its full class name
 * In addition ,name is optional , default name will be the class name and method name
 * you can use this cn.edu.scau.biubiusuisui.annotation as the following cn.edu.scau.biubiusuisui.example
 * <p>
 * \@FXSender
 * public class A{
 * public void test(){
 * <p>
 * }
 * }
 * name will be A.name(It will only contain base class name and this name)
 * or you can use
 *
 * @author jack
 * @version 1.0
 * @FXSernder("testDemo") public class A{
 * public void test(){
 * <p>
 * }
 * }
 * name will be A.testDemo
 * @date 2019/6/25 13:02
 * @since JavaFX2.0 JDK1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FXSender {
    String name() default "";
}

