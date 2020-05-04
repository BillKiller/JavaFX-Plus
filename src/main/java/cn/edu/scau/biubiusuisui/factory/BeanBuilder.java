package cn.edu.scau.biubiusuisui.factory;

/**
 * @author jack
 * @version 1.0
 * @date 2019/7/4 11:16
 * @since JavaFX2.0 JDK1.8
 */
public interface BeanBuilder {
    /**
     * 万能工厂方法
     *
     * @param type 类型
     * @return 实例对象
     */
    Object getBean(Class type);
}
