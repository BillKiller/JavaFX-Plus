package cn.edu.scau.biubiusuisui.example.firstDemo;

import cn.edu.scau.biubiusuisui.annotation.FXEntity;
import cn.edu.scau.biubiusuisui.annotation.FXField;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suisui
 * @version 1.0
 * @description
 * @date 2020/1/1 23:07
 * @since JavaFX2.0 JDK1.8
 */

@FXEntity
public class Student {

    @FXField    //标记该属性是否被生成对应的Property
    private int age = 0;

    @FXField
    private List<String> list = new ArrayList<>();

    public void addList(String word) {
        list.add(word);
    }

    public void delList(String word) {
        list.remove(word);
    }

}