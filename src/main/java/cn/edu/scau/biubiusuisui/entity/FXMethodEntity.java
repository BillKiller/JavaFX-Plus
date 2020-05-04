package cn.edu.scau.biubiusuisui.entity;

import java.lang.reflect.Method;

/**
 * This class is base cn.edu.scau.biubiusuisui.entity for queue message(or signal)
 * you mush save the instance and method which means who will run this method
 *
 * @author jack
 * @version 1.0
 * @date 2019/6/26 15:39
 * @since JavaFX2.0 JDK1.8
 */
public class FXMethodEntity {

    private FXBaseController fxBaseController;

    private Method method;

    public FXMethodEntity(FXBaseController fxBaseController, Method method) {
        this.fxBaseController = fxBaseController;
        this.method = method;
    }

    public FXBaseController getFxBaseController() {
        return fxBaseController;
    }

    public void setFxBaseController(FXBaseController fxBaseController) {
        this.fxBaseController = fxBaseController;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

}
