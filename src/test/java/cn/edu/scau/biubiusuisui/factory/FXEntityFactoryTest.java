package cn.edu.scau.biubiusuisui.factory;

import cn.edu.scau.biubiusuisui.entity.FXPlusContext;
import cn.edu.scau.biubiusuisui.example.Student;
import javafx.beans.property.Property;
import org.junit.Test;

import java.util.Map;

/**
 * @Author jack
 * @Date:2019/6/28 1:32
 */
public class FXEntityFactoryTest {

    @Test
    public void getClassProperty() {
//        Student student = new Student();
//        student.setName("Jack");
//        try {
//            Map<String, Property> entityProperty = FXEntityFactory.processFXEntityProxy(student,null);
//            entityProperty.forEach((k,v)->{
//                System.out.println("key" + k +" v" + v);
//            });
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

    }

    @Test
    public void getInstance() {
    }

    @Test
    public void createJavaBeanProxy() {
        Student student = new Student();
        student.setName("NAME");
    }

    @Test
    public void createJavaBeanProxy2() throws InstantiationException, IllegalAccessException {
        Student student1 = (Student) FXEntityFactory.createJavaBeanProxy(Student.class);
        System.out.println(student1);
        FXPlusContext.getProryByBeanObject(student1).getStringPropertyMap().forEach((k,v)->{
            System.out.println("k " +k +"v" + v);
        });
        student1.setName("Jack");
    }

    @Test
    public void getEntityProperty() {
    }
}