package cn.edu.scau.biubiusuisui.entity;

import cn.edu.scau.biubiusuisui.annotation.FXField;
import javafx.beans.property.Property;

/**
 * 将Controller中的JavaFX的field包装成FXFieldWrapper
 *
 * @Author jack
 * @Date:2019/6/28 10:03
 */
public class FXFieldWrapper {

    private FXField fxField;
    private Class type;

    private Property property;

    public FXFieldWrapper() {
    }

    public FXFieldWrapper(FXField fxField, Class type) {
        this.fxField = fxField;
        this.type = type;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public FXField getFxField() {
        return fxField;
    }

    public void setFxField(FXField fxField) {
        this.fxField = fxField;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

}
