package point;

import component.Ball;
import lombok.Data;

/**
 * @program: Gizmo
 * @description: 地图上的一个点
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:23
 **/
@Data
public class Point {
  private int x, y;

  public Point() {}

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void rebound(Ball ball) {
    // TODO:反弹都是点和球的交互
  }

  public void absorb(Ball ball) {}

  public void transfer(Ball ball) {}
}
