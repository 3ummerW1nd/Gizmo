package component;

import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import point.Point;
import ui.Box;

/**
 * @program: Gizmo
 * @description: 吸收器
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:39
 **/

public class Absorber extends NormalComponent {
  public Absorber() {
    type = CIRCLE;
  }

  @Override
  public Point checkCollision(Ball ball) {
    return null;
  }
}
