package cn.edu.scau.biubiusuisui.factory;

import cn.edu.scau.biubiusuisui.annotation.FXController;
import cn.edu.scau.biubiusuisui.annotation.FXWindow;
import cn.edu.scau.biubiusuisui.config.FXMLLoaderPlus;
import cn.edu.scau.biubiusuisui.entity.FXBaseController;
import cn.edu.scau.biubiusuisui.entity.FXPlusContext;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import cn.edu.scau.biubiusuisui.messageQueue.MessageQueue;
import cn.edu.scau.biubiusuisui.proxy.classProxy.FXControllerProxy;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;

/**
 * @Author jack
 * @Date:2019/6/25 8:12
 */
public class FXFactory {


    private FXFactory() {
    }


    public static void loadFXController(Class clazz) {
        getFXController(clazz, "");
    }

    public static FXBaseController getFXController(Class clazz) {
        FXBaseController fxBaseController = getFXController(clazz, "");
        return fxBaseController;
    }

    public static FXBaseController getFXController(Class clazz, String controllerName) {
        FXBaseController fxBaseController = getFxBaseController(clazz, controllerName);
        return fxBaseController;
    }

    private static void register(FXBaseController fxBaseController, FXBaseController fxBaseControllerProxy) {
        FXPlusContext.addController(fxBaseController);
        MessageQueue.getInstance().registerCosumer(fxBaseController, fxBaseControllerProxy);
    }

    /**
     *
     * @param clazz instance that extends by FXBaseController
     * @param controllerName
     * @return
     */
    private static FXBaseController getFxBaseController(Class clazz, String controllerName) {
        Annotation[] annotations = clazz.getAnnotations();
        Boolean isController = false;
        URL fxmlPath;
        Boolean isWindow = false;
        FXWindow fxWindow = null;
        FXController fxController = null;
        //reflect and get FXController cn.edu.scau.biubiusuisui.annotation
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(FXController.class)) {
                fxController = (FXController) annotation;
                isController = true;
            } else if (annotation.annotationType().equals(FXWindow.class)) {
                fxWindow = (FXWindow) annotation;
                isWindow = true;
            }
        }

        Pane parent = null;

        FXBaseController fxControllerProxy = null;
        FXBaseController fxBaseController = null;

        if (isController) {
            String name = fxController.path();
            fxmlPath = clazz.getClassLoader().getResource(name);
            FXMLLoaderPlus fxmlLoader = new FXMLLoaderPlus(fxmlPath);
            try {
                // create a cn.edu.scau.biubiusuisui.proxy for monitoring methods
                fxBaseController = (FXBaseController) clazz.newInstance();
                FXControllerProxy controllerProxy = new FXControllerProxy();
                fxControllerProxy = (FXBaseController) controllerProxy.getInstance(fxBaseController);
                fxControllerProxy.setName(controllerName);
                fxBaseController.setName(controllerName);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            fxmlLoader.setRoot(fxControllerProxy);
            fxmlLoader.setController(fxControllerProxy);
            try {
                parent = fxmlLoader.load();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }else{
            return null;
        }
        //register
        register(fxBaseController, fxControllerProxy);
        if (isWindow) {
            Stage stage = new Stage();
            double preWidth = fxWindow.preWidth() == 0 ? parent.getPrefWidth() : fxWindow.preWidth();
            double preHeight = fxWindow.preHeight() == 0 ? parent.getPrefWidth() : fxWindow.preHeight();
            Scene scene = new Scene(fxControllerProxy, preWidth, preHeight);
            stage.setScene(scene);
            stage.setTitle(fxWindow.title());
            stage.show();
        }
        return fxControllerProxy;
    }

}
