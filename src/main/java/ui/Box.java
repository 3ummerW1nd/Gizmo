package ui;

/**
 * @program: Gizmo
 * @description: 格子
 * @author: 3ummerW1nd
 * @create: 2021-11-10 10:33
 **/

public class Box {
  private int x, y;

  public Box(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }
}
