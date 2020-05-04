package cn.edu.scau.biubiusuisui.example.bindDemo;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

/**
 * @author suisui
 * @version 1.2
 * @description User的JavaFXBean
 * @date 2020/4/6 14:30
 * @since JavaFX2.0 JDK1.8
 */
public class UserPropertyEntity {
    private SimpleStringProperty name;
    private SimpleStringProperty password;
    private SimpleIntegerProperty age;
    private SimpleListProperty<Double> scores;
    private SimpleDoubleProperty gpa;//平均分
    private SimpleObjectProperty<Profile> profile;
    private SimpleBooleanProperty enable;

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public int getAge() {
        return age.get();
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public ObservableList<Double> getScores() {
        return scores.get();
    }

    public SimpleListProperty<Double> scoresProperty() {
        return scores;
    }

    public void setScores(ObservableList<Double> scores) {
        this.scores.set(scores);
    }

    public double getGpa() {
        return gpa.get();
    }

    public SimpleDoubleProperty gpaProperty() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa.set(gpa);
    }

    public Profile getProfile() {
        return profile.get();
    }

    public SimpleObjectProperty<Profile> profileProperty() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile.set(profile);
    }

    public boolean getEnable() {
        return enable.get();
    }

    public SimpleBooleanProperty enableProperty() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable.set(enable);
    }
}
