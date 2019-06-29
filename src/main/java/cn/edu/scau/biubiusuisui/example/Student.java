package cn.edu.scau.biubiusuisui.example;

import cn.edu.scau.biubiusuisui.annotation.FXEntity;
import cn.edu.scau.biubiusuisui.annotation.FXField;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author jack
 * @Date:2019/6/27 20:02
 */

@FXEntity
public class Student {

    @FXField
    private String name;

    private int age;

    private  String gender;

    private  String code;

    @FXField
    private List<String> list = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void addList(String word){
        list.add(word);
    }
    public void delList(String word){
        list.remove(word);
    }
}
