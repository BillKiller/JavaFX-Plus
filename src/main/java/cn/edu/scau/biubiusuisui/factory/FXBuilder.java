package cn.edu.scau.biubiusuisui.factory;

/**
 * @Author jack
 * @Date:2019/7/4 11:13
 */
public class FXBuilder  implements  BeanBuilder{
    @Override
    public Object getBean(Class type) {
        Object object = null;
        try {
            object = type.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }
}
