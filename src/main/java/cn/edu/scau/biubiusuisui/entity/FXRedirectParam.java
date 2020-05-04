package cn.edu.scau.biubiusuisui.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author suisui
 * @version 1.2
 * @description 跳转窗口携带的参数
 * @date 2020/4/6 18:06
 * @since JavaFX2.0 JDK1.8
 */
public class FXRedirectParam {
    private String toController;
    private Map<String, Object> query = new HashMap<>();
    private Map<String, Object> params = new HashMap<>();

    public FXRedirectParam(String toController) {
        this.toController = toController;
    }

    public String getToController() {
        return toController;
    }

    public void setToController(String toController) {
        this.toController = toController;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public Map<String, Object> getQueryMap() {
        return query;
    }


    public void addParam(String key, Object param) {
        this.params.put(key, param);
    }

    public Object getParam(String key) {
        return this.params.get(key);
    }

    public void addQuery(String key, Object param) {
        this.query.put(key, param);
    }

    public Object getOneQuery(String key) {
        return this.query.get(key);
    }
}
