package component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import point.Point;
import ui.Box;

/**
 * @program: Gizmo
 * @description: 直角三角形障碍物
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:37
 **/

public class TriangleObstacle extends NormalComponent {
  public TriangleObstacle() {
    type = TRIANGLE;
  }

  @Override
  public Point checkCollision(Ball ball) {
    return null;
  }

  @Override
  public void init(Box box) {}
}
