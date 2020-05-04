package cn.edu.scau.biubiusuisui.expression.data;

import cn.edu.scau.biubiusuisui.annotation.FXEntity;
import cn.edu.scau.biubiusuisui.entity.FXPlusContext;
import cn.edu.scau.biubiusuisui.utils.BeanUtil;
import com.sun.javafx.fxml.BeanAdapter;
import javafx.beans.value.ObservableValue;

/**
 * @author jack
 * @version 1.0
 * @date 2019/7/23 15:16
 * @since JavaFX2.0 JDK1.8
 */
public class MyBeanAdapter extends BeanAdapter {

    private Object object;

    /**
     * Creates a new Bean adapter.
     *
     * @param bean The Bean object to wrap.
     */
    public MyBeanAdapter(Object bean) {
        super(bean);
        this.object = bean;
    }

    @Override
    public <T> ObservableValue<T> getPropertyModel(String key) {
        if (object.getClass().getAnnotation(FXEntity.class) == null) {
            return super.getPropertyModel(key);
        } else {
            return BeanUtil.getPropertyByName(object, key);
        }
    }

    public String valueOf(String value) {
        return value;
    }
}
