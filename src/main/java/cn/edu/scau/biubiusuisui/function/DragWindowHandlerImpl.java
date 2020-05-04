package cn.edu.scau.biubiusuisui.function;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author jack
 * @version 1.0
 * @date 2019/6/30 10:11
 * @since JavaFX2.0 JDK1.8
 */
public class DragWindowHandlerImpl implements EventHandler<MouseEvent> {
    //参考<a href="https://www.cnblogs.com/moonlightL/p/5982679.html"></a>
    private Stage stage;        //primaryStage为start方法头中的Stage
    private double oldStageX;
    private double oldStageY;
    private double oldScreenX;
    private double oldScreenY;
    final int RESIZE_WIDTH = 5;// 判定是否为调整窗口状态的范围与边界距离
    private double MIN_WIDTH = 300;// 窗口最小宽度
    private double MIN_HEIGHT = 250;// 窗口最小高度
    boolean isRight;// 是否处于右边界调整窗口状态
    boolean isBottomRight;// 是否处于右下角调整窗口状态
    boolean isBottom;// 是否处于下边界调整窗口状态
    private Pane pane;
    private boolean resizable; //是否拉伸
    private boolean draggable; //是否拖拽


    public DragWindowHandlerImpl(Stage primaryStage, Pane pane, boolean draggable, boolean resizable) {        //构造器
        this.stage = primaryStage;
        this.pane = pane;
        this.draggable = draggable;
        this.resizable = resizable;
    }

    public DragWindowHandlerImpl(Stage stage, double MIN_WIDTH, double MIN_HEIGHT, Pane pane, boolean draggable, boolean resizable) {
        this.stage = stage;
        this.MIN_WIDTH = MIN_WIDTH;
        this.MIN_HEIGHT = MIN_HEIGHT;
        this.pane = pane;
        this.draggable = draggable;
        this.resizable = resizable;
    }

    @Override
    public void handle(MouseEvent e) {

        if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {    //鼠标按下的事件
            //鼠标按下时记录坐标
            this.oldStageX = this.stage.getX();
            this.oldStageY = this.stage.getY();
            this.oldScreenX = e.getScreenX();
            this.oldScreenY = e.getScreenY();
        } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {  //鼠标拖动的事件
            double nextX = stage.getX();
            double nextY = stage.getY();
            double nextWidth = stage.getWidth();
            double nextHeight = stage.getHeight();

            double x = e.getSceneX();
            double y = e.getSceneY();
            // 保存窗口改变后的x、y坐标和宽度、高度，用于预判是否会小于最小宽度、最小高度
            if (isRight || isBottomRight) {// 所有右边调整窗口状态
                nextWidth = x;
            }
            if (isBottomRight || isBottom) {// 所有下边调整窗口状态
                nextHeight = y;
            }
            if (nextWidth <= MIN_WIDTH) {// 如果窗口改变后的宽度小于最小宽度，则宽度调整到最小宽度
                nextWidth = MIN_WIDTH;
            }
            if (nextHeight <= MIN_HEIGHT) {// 如果窗口改变后的高度小于最小高度，则高度调整到最小高度
                nextHeight = MIN_HEIGHT;
            }

            // 最后统一改变窗口的x、y坐标和宽度、高度，可以防止刷新频繁出现的屏闪情况
            if (draggable) {
                if (isBottom || isBottomRight || isRight) {
                    stage.setX(nextX);
                    stage.setY(nextY);
                } else {
                    this.stage.setX(e.getScreenX() - this.oldScreenX + this.oldStageX);
                    this.stage.setY(e.getScreenY() - this.oldScreenY + this.oldStageY);
                }
            }
            if (resizable) {
                stage.setWidth(nextWidth);
                stage.setHeight(nextHeight);
            }
        } else if (e.getEventType() == MouseEvent.MOUSE_MOVED) {  //鼠标移动
            e.consume();
            double x = e.getSceneX();
            double y = e.getSceneY();
            double width = stage.getWidth();
            double height = stage.getHeight();
            Cursor cursorType = Cursor.DEFAULT;// 鼠标光标初始为默认类型，若未进入调整窗口状态，保持默认类型
            // 先将所有调整窗口状态重置
            isRight = isBottomRight = isBottom = false;

            if (resizable) {
                if (y >= height - RESIZE_WIDTH) {
                    if (x <= RESIZE_WIDTH) {// 左下角调整窗口状态

                    } else if (x >= width - RESIZE_WIDTH) {// 右下角调整窗口状态
                        isBottomRight = true;
                        cursorType = Cursor.SE_RESIZE;
                    } else {// 下边界调整窗口状态
                        isBottom = true;
                        cursorType = Cursor.S_RESIZE;
                    }
                } else if (x >= width - RESIZE_WIDTH) {// 右边界调整窗口状态
                    isRight = true;
                    cursorType = Cursor.E_RESIZE;
                }
                // 最后改变鼠标光标
                pane.setCursor(cursorType);
            }
        }
    }
}
