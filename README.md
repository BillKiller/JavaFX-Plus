# JavaFX-Plus

[TOC]

## 前言

这个框架不是UI美化框架，而是为了简化javaFX项目开发已经减少项目之间组件耦合而打造的框架。目前框架主要功能如下图所示。

![输入图片说明](https://images.gitee.com/uploads/images/2019/0629/155142_1235eb9c_2067650.png "JavaFX-Plus.png")
### 开发进程
- [x]	模块化
- [x]	信号机制
- [x]	数据绑定
- [x]	spring结合
- [x]	可拔插功能(窗口拖动等功能)
- [ ]	事件注解绑定
- [ ]	函数表达式绑定

## 特色


###  信号机制

有两个主要标签一个是FXSender，这个标签作用在方法上，标记这个方法为信号发射方法。可以通过设置name修改这个信号发射方法的名称，默认是函数名字。

发射信号会被订阅这个发射函数的所有FXReceiver接收，并且发射函数的返回值会作为参数传进这个函数之中。而且这种发送和接受关系是全局的，只要是注册了的Controller都可以进行接受，不局限于同一个Controller。

我们通过一个简单的代码来理解一下。

```java
@FXController(path = "Main.fxml")
@FXWindow(title = "demo1")
public class MainController extends FXBaseController{

    @FXML
    Button btn;

    @FXML
    Label label;
    /**
    鼠标之后，系统通过会发射信号，调用所有订阅这个发射信号函数的方法响应信号
    */
    @FXML //绑定鼠标点击事件
    @FXSender //标注为信号发射函数
    public String send(){
        System.out.println("before sending"); //输出 before sending
        return "sending msg";
    }
    /** 
        接受者必须指定要订阅的发送者类名+方法名 
        而且发送函数的返回值会注入到接受函数的参数中
   */
    @FXReceiver(name = "MainController:send")
    public void read(String msg){
        System.out.println("read " + msg); //输出 read sending msg
    }

}
```

![输入图片说明](https://images.gitee.com/uploads/images/2019/0629/022051_db8dbc7a_2067650.gif "signalshow.gif")

### JavaBean 和 JavaFxBean

 一般我们写的JavaBean都是基本类型的，但是JavaFXBean的设计哲学是这些属性都应该是JavaFX定义的Property类型，这十分不利于我们的开发，我们如何在不修改JavaBean的条件下，使用到JavaFX的Property的一些优良方法呢？答案是我们通过反射获得基本类型对应的Property（目前仅限于boolean，double，integer，long，string，float等基本类型，不支持List等封装对象。）

![输入图片说明](https://images.gitee.com/uploads/images/2019/0629/095306_9950a9af_2067650.png "微信截图_20190629095237.png")

而本次设计的过程中希望尽量避免操作界面相关的Property等方法，而是直接操作JavaBean类。例如下面代码。

```java
@FXController(path = "Main.fxml")
@FXWindow(title = "demo1")
public class MainController extends FXBaseController{

    @FXML
    Button btn;

    @FXML
    Label label;

    Student student;

    int count = 1;

    @Override
    public void initialize() {
        student = (Student) FXEntityFactory.getInstance().createJavaBeanProxy(Student.class); //工厂产生一个学生
        student.setName("Jack"); //设置学生姓名
        FXEntityProxy fxEntityProxy = FXPlusContext.getProryByBeanObject(student); //获取学生代理
        Property nameProperty = fxEntityProxy.getPropertyByFieldName("name"); //获取Bean对应的Property
        //可以通过fxEntityProxy.getPropertyByFieldName("list"); 获得List的Property
        label.textProperty().bind(nameProperty); //属性绑定
    }

    @FXML
    @FXSender
    public String send(){
        student.setName("Jack :" + count); //操作会自动反应到界面上，无需再手动操作界面元素，专心业务部分。
        count++;
        return "sending msg";
    }

}
```


```java
@FXEntity
public class Student {

    @FXField
    private String name; //标记这个类要生成property对象

    private int age;

    private  String gender;

    private  String code;

    @FXField
    private List<String> list = new ArrayList<>(); //标记这个List要生成Property对象

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


```

实现效果是:

![输入图片说明](https://images.gitee.com/uploads/images/2019/0629/022103_bc9aeb7e_2067650.gif "bindhow.gif")

直接操作JavaBean类，就会通过动态绑定修改界面，不需要讲JavaBean转换为JavaFX Bean可以减少开发中的类型转换。

###  可拖动窗口和可伸缩窗口
在Javafx中如果一个窗口隐藏了标题栏那么这个窗口也就没办法拖动和伸缩了，在JavaFX-Plus中你就不需有这种烦恼，只需要在@FXWindow中设置
```java
@FXWindow(title = "demo1",dragable = true,style = StageStyle.UNDECORATED)
```
就可以让这个没有标题的窗口可以被拖动而且能拉伸（默认打开，可以关闭）

![输入图片说明](https://images.gitee.com/uploads/images/2019/0630/135524_d0b03d5f_2067650.gif "moveable.gif")


![输入图片说明](https://images.gitee.com/uploads/images/2019/0630/135637_cb0e0a89_2067650.gif "resizeAble.gif")

###  Spring支持
可以快速支持Spring和这个框架的融合，只需要一行代码，就可将实例的生成控制转交给容器管理。
代码如下:
```java
@FXScan(base = {"cn.edu.scau.biubiusuisui.example.springDemo"})
public class SpringDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); //启动spring
        FXPlusApplication.start(SpringDemo.class, new BeanBuilder() {
            @Override
            public Object getBean(Class type) {
                return  context.getBean(type); //接管FXPlus属性的创建
            }
        });
    }
}

```

### 数据表达式绑定 
#### Bean和View绑定
在JavaFX控件的字段上面添加@FXbind可以绑定属性，类似于Vue中的界面绑定，但是不同的是，这里的绑定可以是普通Bean和View绑定，可以是View和View绑定，也可以是Bean和Bean绑定（不推荐）。
如下面代码，通过FXBind将Studen的姓名与文本框输入内容绑定，学生的密码和密码框输入框内容绑定，完全简化了数据传递操作，代码中完全没有出现界面数据传输到控制器代码。
例子:

```java
    @FXData
    @FXBind(
            {
                    "name=${usr.text}",
                    "password=${psw.text}"
            }
    )
    Student student = new Student();

    @FXML
    private PasswordField psw;
    @FXML
    private Label label;

    @FXML
    void login(ActionEvent event) {
        System.out.println("user:" + student.getName());
        System.out.println("psw:" + student.getPassword());
        if ("admin".equals(student.getName()) && "admin".equals(student.getPassword())) {
            System.out.println("Ok");
        } else {
            System.out.println("fail");
        }
    }
    

```
如图所示:
![输入图片说明](https://images.gitee.com/uploads/images/2019/0724/231705_976181ba_2067650.gif "expression.gif")

#### View和View绑定
```java
@FXBind("text=${psw.text}")
@FXML
private Label pswMsg;//任何psw中的内容都会同步到pswMsg中
```
如图所示
![输入图片说明](https://images.gitee.com/uploads/images/2019/0724/232237_7e243f15_2067650.gif "expressionV2V.gif")

### 模块化开发

#### 介绍

在Java开发过程中很多界面是相似或者重复的，如果能够将这些界面打包成为一个自定义控件，并且通过Scenebuilder拖动就能产生一个控件那将会大大提高我们的开发效率。所以我们提出将不同区域划分为不同的子模块，已达到减少耦合和加速并行开发。一般我们经常把界面分为顶部工具栏，左边导航栏，右侧的内容栏，如果全部内容都写在一个Controller那么将会导致十分臃肿，我们希望将不同的区域划分开来分而治之。

#### 如何创建模块

只要新建一个类继承自FXBaseController，而FXBaseController是继承于Pane，这就是JavaFX-Plus的设计思想之一切皆为Pane。在类上标上FXController注解，提供FXML文件的地址。如果设置为FXWindow那么将会把这个Controller以单独的Window显示，这里仅仅几句代码就实现了一个简单的窗口程序。

![输入图片说明](https://images.gitee.com/uploads/images/2019/0629/022014_83ecdbde_2067650.png "controllerConfig.png")

图2 Controller配置

![输入图片说明](https://images.gitee.com/uploads/images/2019/0629/022024_71892db3_2067650.png "demo1.png")

图3 显示结果

#### scenebuilder中导入刚刚生成的上面的控件

![输入图片说明](https://images.gitee.com/uploads/images/2019/0629/022036_e128f313_2067650.gif "modulesAction.gif")

图4 模块化操作

##  如何使用这个框架
###  内置注解

| 名字          | 作用                                                         | 参数                                    | 要求                 |
| ------------- | ------------------------------------------------------------ | --------------------------------------- | -------------------- |
| @FXData       | 表面这个普通bean要装配成javafxBean                           | fx_id                                   | 重新命名             |
| @FXScan       | 扫描@FXEntity和@FXController注解标记的类                     | 要扫描的目录                            | 默认当前目录之下所有 |
| @FXController | 标记这个类为控件                                             | fxml文件地址                            | 无                   |
| @FXWindow     | 标记这个控件要以单独窗口显示                                 | title是窗口名字，也可以设置窗口长度宽度 | 无                   |
| @FXEntity     | 标记JavaBean系统会自动识别@FXField然后包装JavaBean为JavaFXBean | 重命名                                  |                      |
| @FXField      | 代表这个属性要映射为Property属性                             |                                         |                      |
| @FXSender     | 信号发送者                                                   | name可以重命名信号                      |                      |
| @FXReceiver   | 信号接收函数                                                 | name是订阅的发射者函数名                | 不可空               |



### 两个工厂和一个context

在JavaFX-Plus中所有Controller对象和FXEnity对象都必须通过工厂创建。

```
student = (Student) FXEntityFactory.getInstance().createJavaBeanProxy(Student.class); //工厂产生一个学生 
```

通过工厂创建JavaBean，在创建同时工厂会对JavaBean代理并且包装对应的Property属性。

```
MainController mainController = (MainController)FXFactory.getFXController(MainController.class); 
```

##  创建第一个程序

```java
@FXScan(base = {"cn.edu.scau.biubiusuisui.example"}) //会扫描带FXController和FXEntity的类进行初始化
public class Demo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXPlusApplication.start(Demo.class);  //其他配置和JavaFX相同，这里要调用FXPlusAppcalition的start方法，开始FX-plus加强
    }
}

```
接下来我们生成FXML和Controller
```java
@FXController(path = "Main.fxml")
@FXWindow(title = "demo1")
public class MainController extends FXBaseController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBtn;

    @FXML
    private Button delBtn;

    @FXML
    private ListView<String> list;

    Student student;

    @FXML
    void addWord(ActionEvent event) {
        student.addList("hello" );
    }

    @FXML
    void delWord(ActionEvent event) {
        student.delList("hello");
    }

    @Override
    public void initialize() {
        student = (Student) FXEntityFactory.createJavaBeanProxy(Student.class);
        Property property = FXPlusContext.getEntityPropertyByName(student, "list");
        list.itemsProperty().bind(property);
    }
}

```
Studen类的定义如下    

```java

@FXEntity
public class Student {

    @FXField
    private String name;

    @FXField
    private List<String> list = new ArrayList<>();

    public void addList(String word){
        list.add(word);
    }
    public void delList(String word){
        list.remove(word);
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.control.Button?>
<?import javafx.scene.control.ListView?>
<?import javafx.scene.layout.Pane?>

<fx:root prefHeight="400.0" prefWidth="600.0" type="Pane" xmlns="http://javafx.com/javafx/8.0.171" xmlns:fx="http://javafx.com/fxml/1" fx:controller="cn.edu.scau.biubiusuisui.example.MainController">
   <children>
      <Button fx:id="addBtn" layoutX="432.0" layoutY="83.0" mnemonicParsing="false" onAction="#addWord" text="add" />
      <Button fx:id="delBtn" layoutX="432.0" layoutY="151.0" mnemonicParsing="false" onAction="#delWord" text="del" />
      <ListView fx:id="list" layoutX="42.0" layoutY="51.0" prefHeight="275.0" prefWidth="334.0" />
   </children>
</fx:root>

```

从我们代码可以看出，我们很少有操作界面的操作，并且我们操作的对象都是基本类型的对象，这样的操作十分有利于我们将普通的项目转换为JavaFX项目，最终运行起来将会是这样

![输入图片说明](https://images.gitee.com/uploads/images/2019/0629/160249_00f41d22_2067650.gif "helloWorldDemo.gif")


