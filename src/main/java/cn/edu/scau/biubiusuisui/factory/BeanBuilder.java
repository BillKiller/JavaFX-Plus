package cn.edu.scau.biubiusuisui.factory;

/**
 * @Author jack
 * @Date:2019/7/4 11:16
 */
public interface BeanBuilder {
    /**
     *  万能工厂方法
     * @param type 类型
     * @return 实例对象
     */
    Object getBean(Class type);
}
