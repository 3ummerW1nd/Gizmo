package component;

import component.Ball;
import component.NormalComponent;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import point.Point;
import ui.Box;

/**
 * @program: Gizmo
 * @description: 圆形障碍物
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:37
 **/

public class CircleObstacle extends NormalComponent {
  public CircleObstacle() {
    type = CIRCLE;
  }

  @Override
  public Point checkCollision(Ball ball) {
    return null;
  }
}
