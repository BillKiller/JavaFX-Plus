package cn.edu.scau.biubiusuisui.example.moveDemo;

import cn.edu.scau.biubiusuisui.annotation.FXEntity;
import cn.edu.scau.biubiusuisui.annotation.FXField;

/**
 * @Author jack
 * @Date:2019/7/25 9:24
 */
@FXEntity
public class CircleBean {

    @FXField
    double x = 0;

    @FXField
    double y = 0;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "CircleBean{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
