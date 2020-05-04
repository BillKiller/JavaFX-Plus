package cn.edu.scau.biubiusuisui.expression.data;

/**
 * @author jack
 * @version 1.0
 * @date 2019/7/27 20:03
 * @since JavaFX2.0 JDK1.8
 */
@FunctionalInterface
public interface ExpFunction<T, U, R> {
    R apply(T t, U u);
}
