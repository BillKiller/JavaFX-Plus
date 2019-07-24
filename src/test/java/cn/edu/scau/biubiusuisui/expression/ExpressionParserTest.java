package cn.edu.scau.biubiusuisui.expression;

import cn.edu.scau.biubiusuisui.annotation.FXEntity;
import cn.edu.scau.biubiusuisui.example.Student;
import cn.edu.scau.biubiusuisui.factory.FXEntityFactory;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author jack
 * @Date:2019/7/24 11:55
 */
public class ExpressionParserTest extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        // test  - ok
        Label label = new Label("text");
        ObservableMap<String, Object> namespace = FXCollections.observableHashMap();
        Student student = new Student();
        Student studentProxy = (Student) FXEntityFactory.createJavaBeanProxy(student);
        studentProxy.setName("jack");
        namespace.put("student", studentProxy);
        ExpressionParser expressionParser = new ExpressionParser(namespace);
        expressionParser.parse(label,"text=${student.name}");
        System.out.println(label.textProperty());
        studentProxy.setName("jack-modified");
        System.out.println(label.textProperty());
        System.out.println(label.textProperty().getValue());
    }

    public void testValueFactory(){
        new PropertyValueFactory("password");
    }
}