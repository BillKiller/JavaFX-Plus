package cn.edu.scau.biubiusuisui.expression.data;

/**
 * @Author jack
 * @Date:2019/7/27 20:03
 */
@FunctionalInterface
public interface ExpFunction<T,U,R>{
    R apply(T t, U u);
}
