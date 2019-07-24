package cn.edu.scau.biubiusuisui.entity;

/**
 * @Author jack
 * @Date:2019/6/28 10:03
 */
public class FXFieldPropertyMapping {

    private boolean readOnly;
    private Class type;


    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }
}
