package cn.edu.scau.biubiusuisui.example.springExpressionDemo;

import cn.edu.scau.biubiusuisui.annotation.FXScan;
import cn.edu.scau.biubiusuisui.config.FXPlusApplication;
import cn.edu.scau.biubiusuisui.example.springDemo.SpringDemo;
import cn.edu.scau.biubiusuisui.factory.BeanBuilder;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author jack
 * @Date:2019/6/25 7:05
 */
@FXScan(base = {"cn.edu.scau.biubiusuisui.example.springExpressionDemo"})
//项目目录中带有中文字符会导致无法启动
public class SpringExpressionDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        FXPlusApplication.start(SpringExpressionDemo.class, new BeanBuilder() {
            @Override
            public Object getBean(Class type) {
                return  context.getBean(type);
            }
        });
    }
}
