package cn.edu.scau.biubiusuisui.messageQueue;

import cn.edu.scau.biubiusuisui.annotation.FXReceiver;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.entity.FXMethodEntity;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author jack
 * @Date:2019/6/25 12:24
 */

public class MessageQueue {


    private static Map<String, List<FXMethodEntity>> receivers = new ConcurrentHashMap<>();

    private static MessageQueue messageQueue = null;

    private MessageQueue(){ }

    public  static synchronized  MessageQueue getInstance(){
        if(messageQueue == null){
            messageQueue = new MessageQueue();
        }
        return messageQueue;
    }

    public void registerCosumer(FXBaseController fxBaseController,FXBaseController fxBaseControllerProxy){
        Class clazz = fxBaseController.getClass();
        Method  [] methods = clazz.getDeclaredMethods();
        for(Method method : methods){
            Annotation[]annotations = method.getDeclaredAnnotations();
            for(Annotation annotation : annotations){
                if(FXReceiver.class.equals(annotation.annotationType())){
                    FXReceiver receiver = (FXReceiver)annotation;
                    FXMethodEntity fxMethodEntity = new FXMethodEntity(fxBaseControllerProxy,method);
                    List<FXMethodEntity> fxMethodEntities = receivers.get(receiver.name());
                    if(fxMethodEntities == null){
                        fxMethodEntities = new ArrayList<>();
                    }
                    fxMethodEntities.add(fxMethodEntity);
                    receivers.put(receiver.name(), fxMethodEntities);
                }
            }
        }
    }

    public void sendMsg(String id,Object msg) {
        List<FXMethodEntity> lists = receivers.get(id);
        if (lists != null) {
            for (FXMethodEntity fxMethodEntity : lists) {
                Method method = fxMethodEntity.getMethod();
                method.setAccessible(true);

                FXBaseController fxBaseController = fxMethodEntity.getFxBaseController();
                if (method.getParameterCount() == 0) {
                    try {
                        method.invoke(fxBaseController);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        method.invoke(fxBaseController, msg);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
