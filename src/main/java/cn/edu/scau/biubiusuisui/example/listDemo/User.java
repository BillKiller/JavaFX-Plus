package cn.edu.scau.biubiusuisui.example.listDemo;

import cn.edu.scau.biubiusuisui.annotation.FXEntity;
import cn.edu.scau.biubiusuisui.annotation.FXField;

import java.util.ArrayList;

/**
 * @Author jack
 * @Date:2019/7/27 12:19
 */
@FXEntity
public class User {
    @FXField
    private String name;

    @FXField
    private String password;

    public User() {
        names.add("hello ");
        names.add("test");
    }




    public void addNames(String name){
        names.add(name);
    }
    @FXField
    private ArrayList<String> names = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }
}