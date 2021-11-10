package component;

import java.awt.image.BufferedImage;
import javax.swing.*;
import ui.Box;

/**
 * @program: Gizmo
 * @description: 组件
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:19
 **/

public abstract class Component {
  public static final int CIRCLE = 1;
  public static final int TRIANGLE = 2;
  public static final int SQUARE = 3;
  public static final int SPECIAL = 4;
  public int type;

  public abstract void init(Box box);
  public abstract void rotate();
  public abstract void zoomIn();
  public abstract void zoomOut();

  public void remove() {
    // TODO:实现组件的删除
  }
}
