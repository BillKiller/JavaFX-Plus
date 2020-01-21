package cn.edu.scau.biubiusuisui.stage;

import cn.edu.scau.biubiusuisui.entity.FXBaseController;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author suiyu_yang
 * @description 舞台管理器
 * @date 2019/12/3 15:43
 * @email suiyu_yang@163.com
 */
public class StageManager {
    private static StageManager stageManager = null;
    private static Map<String, FXBaseController> windows = new ConcurrentHashMap<>();  //

    /**
     * @author yangsuiyu
     * @description 1.2新增属性
     */
    private static ArrayDeque<FXBaseController> windowsStack = new ArrayDeque<>();

    private StageManager() {

    }

    /**
     * 单例
     *
     * @return
     */
    public static synchronized StageManager getInstance() {
        if (stageManager == null) {
            stageManager = new StageManager();
        }
        return stageManager;
    }

    public void registerWindow(FXBaseController fxBaseControllerProxy) {
        if (fxBaseControllerProxy.isWindow()) {
            windows.put(fxBaseControllerProxy.getName(), fxBaseControllerProxy);
        }
    }

    public void closeStage(String controllerName) {
        windows.get(controllerName).closeStage();
    }

    /**
     * 无参数跳转
     *
     * @param controller
     */
    public void redirectTo(Object controller) {
        windows.get(controller).showStage();
    }
}
