package cn.edu.scau.biubiusuisui.messageQueue;

import cn.edu.scau.biubiusuisui.annotation.FXReceiver;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.entity.FXMethodEntity;
import cn.edu.scau.biubiusuisui.log.FXPlusLoggerFactory;
import cn.edu.scau.biubiusuisui.log.IFXPlusLogger;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jack
 * @version 1.0
 * @date 2019/6/25 12:24
 * @since JavaFX2.0 JDK1.8
 */

public class MessageQueue {
    private IFXPlusLogger logger = FXPlusLoggerFactory.getLogger(MessageQueue.class);

    private static Map<String, List<FXMethodEntity>> receivers = new ConcurrentHashMap<>();  //Map<主题，订阅了主题的所有方法>

    private static MessageQueue messageQueue = null;

    private MessageQueue() {
    }

    public static synchronized MessageQueue getInstance() {
        if (messageQueue == null) {
            messageQueue = new MessageQueue();
        }
        return messageQueue;
    }

    /**
     * @param fxBaseController
     * @param fxBaseControllerProxy
     * @Description 注册消费者，即FXReceiver注解的method
     */
    public void registerConsumer(FXBaseController fxBaseController, FXBaseController fxBaseControllerProxy) {
        Class clazz = fxBaseController.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (FXReceiver.class.equals(annotation.annotationType())) {
                    logger.info("registering consumer: " + fxBaseControllerProxy.getName());
//                    System.out.println("FXReceiver");
                    FXReceiver receiver = (FXReceiver) annotation;
                    FXMethodEntity fxMethodEntity = new FXMethodEntity(fxBaseControllerProxy, method);
                    List<FXMethodEntity> fxMethodEntities = receivers.get(receiver.name());
                    if (fxMethodEntities == null) {
                        fxMethodEntities = new ArrayList<>();
                    }
                    fxMethodEntities.add(fxMethodEntity);
                    receivers.put(receiver.name(), fxMethodEntities);
                }
            }
        }
    }

    /**
     * @param id
     * @param msg
     * @Description 处理消息发送，id为topic,msg为消息
     */
    public void sendMsg(String id, Object msg) {
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
                        logger.error(e.getMessage());
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        logger.error(e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    try {
                        // obj the object the underlying method is invoked from
                        method.invoke(fxBaseController, msg);
                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage());
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        logger.error(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
