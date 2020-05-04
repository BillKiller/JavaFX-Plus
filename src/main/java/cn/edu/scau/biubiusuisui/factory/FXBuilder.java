package cn.edu.scau.biubiusuisui.factory;

import cn.edu.scau.biubiusuisui.log.FXPlusLoggerFactory;
import cn.edu.scau.biubiusuisui.log.IFXPlusLogger;

/**
 * @author jack
 * @version 1.0
 * @date 2019/7/4 11:13
 * @since JavaFX2.0 JDK1.8
 */
public class FXBuilder implements BeanBuilder {
    private IFXPlusLogger logger = FXPlusLoggerFactory.getLogger(FXBuilder.class);

    @Override
    public Object getBean(Class type) {
        Object object = null;
        try {
            object = type.newInstance();
        } catch (InstantiationException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return object;
    }
}
