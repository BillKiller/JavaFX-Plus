

**1.1 前言**

**1.1.1 为什么要出这个框架**

 记得从刚开始学习Java就开始接触JavaFX，从一开始的代码编写图形到后来通过FXML编写界面，一步步的学习之中逐渐领悟JavaFX的强大与灵活，我对JavaFX这门生不逢时的技术有了独特的感情，可以说JavaFX的强大不被许多人了解。

 随着不断深入，我也渐渐发现JavaFx的设计思想在很多时候是无法满足当代程序开发思想的，并且一些功能并不是特别容易被使用，所以特定开发了一套简化开发JavaFx开发过程的框架供大家使用，希望能够简化大家的操作将精力专注于主要业务。

 下面是我在开发过程中遇到的一些问题，我也针对这些问题做了简化操作。

**1.1.2 FX缺点1 : 单一控制器臃肿**

JavaFX中似乎都是一个Controller把所有的操作和控件囊括在里面，一个Controller有几百行甚至几千行，程序虽然不用考虑模块之间调用问题了，但是这几千行的代码却很难被管理。

![img](C:/Users/zxh/Documents/zhu747441355@163.com/3dd44382997542f884b56cb7d4a7b219/clipboard.png)

图1 臃肿的controller

**1.1.3 FX缺点2 : 控制类控制能力弱**

JavaFX启动的Stage和Controller之间总是隔着远远的距离，并且由于Controller是由JavaFX注入生成的，所以很多非Controller的东西与Controller交流，导致了不得不得使用静态方法或者静态成员这类小技巧来实现交流，导致代码变"丑"

**1.1.4 FX缺点3 : JavaBean无法使用Property**

JavaFX的设计哲学是所有的JavaBean的属性都是property类型的，可是很多时候我们的JavaBean都是String，Integer这类基本类型，要重新修改类属性所带来的问题就足以让人让而却步了。

![img](C:/Users/zxh/Documents/zhu747441355@163.com/6b38cfbb150b454baa275f995af878b4/clipboard.png)

图2 常见的Bean对象

![img](C:/Users/zxh/Documents/zhu747441355@163.com/ef6ab5afa4744e9db757c01b2010a9ee/clipboard.png)

图3 JavaFX Bean



**1.1.5 总结**

为了解决上述问题，我开发了一套增强JavaFX功能的框架，来起到简化JavaFX开发过程的问题。

**1.2 特色一:模块化开发**

1.2.1 介绍

 在Java开发过程中很多界面是相似或者重复的，如果能够将这些界面打包成为一个自定义控件，并且通过Scenebuilder拖动就能产生一个控件那将会大大提高我们的开发效率。所以我们提出将不同区域划分为不同的子模块，已达到减少耦合和加速并行开发。一般我们经常把界面分为顶部工具栏，左边导航栏，右侧的内容栏，如果全部内容都写在一个Controller那么将会导致十分臃肿，我们希望将不同的区域划分开来分而治之。

1.2.2 如何创建模块

 只要新建一个类继承自FXBaseController，而FXBaseController是继承于Pane，这就是JavaFX-Plus的设计思想之一切皆为Pane。在类上标上FXController注解，提供FXML文件的地址。如果设置为FXWindow那么将会把这个Controller以单独的Window显示，这里仅仅几句代码就实现了一个简单的窗口程序。

![img](C:/Users/zxh/Documents/zhu747441355@163.com/68a9d088ed294b12b19cbcdfa8331a8e/clipboard.png)

图4 Controller配置

![img](C:/Users/zxh/Documents/zhu747441355@163.com/615e65189fc449e48247d1a90765ea84/clipboard.png)

图5 显示结果

1.2.3 scenebuilder中导入刚刚生成的上面的控件

![img](C:/Users/zxh/Documents/zhu747441355@163.com/29fe5348100e41f9b94c43375393072c/test1.gif)

图6 模块化操作

**1.3 特色2 :信号机制**

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

![img](C:/Users/zxh/Documents/zhu747441355@163.com/f1e4203089b0427e83f3dfad8e8b7e49/signal.gif)

**1.4 特色3 :JavaBean 和 JavaFxBean**

 一般我们写的JavaBean都是基本类型的，但是JavaFXBean的设计哲学是这些属性都应该是JavaFX定义的Property类型，这十分不利于我们的开发，我们如何在不修改JavaBean的条件下，使用到JavaFX的Property的一些优良方法呢？答案是我们通过反射获得基本类型对应的Property（目前仅限于boolean，double，integer，long，string，float等基本类型，不支持List等封装对象。

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
        label.textProperty().bind(nameProperty); //属性绑定
    }

    @FXML
    @FXSender
    public String send(){
        student.setName("Jack :" + count);
        count++;
        return "sending msg";
    }

}
```



实现效果是:

![img](C:/Users/zxh/Documents/zhu747441355@163.com/c5b641e8bb89451c9856a5bc06b2f44f/javabean.gif)

直接操作JavaBean类，就会通过动态绑定修改界面，不需要讲JavaBean转换为JavaFX Bean可以减少开发中的类型转换。



**2 如何使用这个框架**

2.1 了解内置注解

| 名字          | 作用                                                         | 参数                                    | 要求                 |
| ------------- | ------------------------------------------------------------ | --------------------------------------- | -------------------- |
| @FXScan       | 扫描@FXEntity和@FXController注解标记的类                     | 要扫描的目录                            | 默认当前目录之下所有 |
| @FXController | 标记这个类为控件                                             | fxml文件地址                            | 无                   |
| @FXWindow     | 标记这个控件要以单独窗口显示                                 | title是窗口名字，也可以设置窗口长度宽度 | 无                   |
| @FXEntity     | 标记JavaBean系统会自动识别@FXField然后包装JavaBean为JavaFXBean | 重命名                                  |                      |
| @FXField      | 代表这个属性要映射为Property属性                             |                                         |                      |
| @FXSender     | 信号发送者                                                   | name可以重命名信号                      |                      |
| @FXReceiver   | 信号接收函数                                                 | name是订阅的发射者函数名                | 不可空               |

2.2  两个工厂和一个context

在JavaFX-Plus中所有Controller对象和FXEnity对象都必须通过工厂创建。

```
student = (Student) FXEntityFactory.getInstance().createJavaBeanProxy(Student.class); //工厂产生一个学生 
```

通过工厂创建JavaBean，在创建同时工厂会对JavaBean代理并且包装对应的Property属性。

```
MainController mainController = (MainController)FXFactory.getFXController(MainController.class); 
```



