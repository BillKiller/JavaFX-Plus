package cn.edu.scau.biubiusuisui.proxy.classProxy;

import cn.edu.scau.biubiusuisui.annotation.FXSender;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.messageQueue.MessageQueue;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 *
 * This proxy class intercept Methods that has special annotation such as
 * FXSender which is a mark for message queue
 *
 * @Author jack
 * @Date:2019/6/25 2:03
 */
public class FXControllerProxy implements MethodInterceptor {


    FXBaseController target;

    public Object getInstance(FXBaseController target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object o1 = methodProxy.invokeSuper(o, objects);
        Annotation []annotations = method.getDeclaredAnnotations();
        for(Annotation annotation : annotations){
            if(FXSender.class.equals(annotation.annotationType())){
                FXSender fxSender = (FXSender)annotation;
                String name = target.getName() +":";
                if("".equals(fxSender.name())){
                     name += method.getName();
                }else{
                     name += fxSender.name();
                }
                MessageQueue.getInstance().sendMsg(name,o1);
            }
        }
        return o1;
    }

}
