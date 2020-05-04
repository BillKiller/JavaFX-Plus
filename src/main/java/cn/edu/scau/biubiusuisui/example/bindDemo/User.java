package cn.edu.scau.biubiusuisui.example.bindDemo;

import cn.edu.scau.biubiusuisui.annotation.FXEntity;
import cn.edu.scau.biubiusuisui.annotation.FXField;

import java.util.List;

/**
 * @author suisui
 * @version 1.2
 * @date 2020/4/5 12:19
 * @since JavaFX2.0 JDK1.8
 */
@FXEntity
public class User {
    @FXField
    private String name;
    @FXField
    private String password;
    @FXField
    private Integer age;
    @FXField
    private List<Double> scores;
    @FXField
    private Double gpa;//平均分
    @FXField
    private Profile profile;
    @FXField
    private boolean isEnable;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Double> getScores() {
        return scores;
    }

    public void addScore(double score) {
        this.scores.add(score);
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public double getGpa() {
        double sum = 0;
        for (double score : scores) {
            sum += score;
        }
        gpa = sum / scores.size();
        return gpa;
    }
}

