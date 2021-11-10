package component;

import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import point.Point;
import ui.Box;

/**
 * @program: Gizmo
 * @description: 正方形障碍物
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:51
 **/

public class SquareObstacle extends NormalComponent {
  public SquareObstacle() {
    type = SQUARE;
  }

  @Override
  public Point checkCollision(Ball ball) {
    return null;
  }

  @Override
  public void init(Box box) {}
}
