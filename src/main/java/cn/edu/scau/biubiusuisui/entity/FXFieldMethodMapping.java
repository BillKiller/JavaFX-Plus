package cn.edu.scau.biubiusuisui.entity;

import java.lang.reflect.Method;

/**
 * @Author jack
 * @Date:2019/6/28 10:03
 */
public class FXFieldMethodMapping {

    private Method  setMethod ;
    private Method  addMethod ;
    private Method  delMethod;

    public Method getSetMethod() {
        return setMethod;
    }

    public void setSetMethod(Method setMethod) {
        this.setMethod = setMethod;
    }

    public Method getAddMethod() {
        return addMethod;
    }

    public void setAddMethod(Method addMethod) {
        this.addMethod = addMethod;
    }

    public Method getDelMethod() {
        return delMethod;
    }

    public void setDelMethod(Method delMethod) {
        this.delMethod = delMethod;
    }
}
