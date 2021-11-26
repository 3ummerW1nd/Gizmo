package component;

import geometry.Point;
import java.util.Map;
import javax.swing.*;
import utils.ComponentImages;
import utils.ComponentType;

/**
 * @program: Gizmo
 * @description: 吸收器
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:39
 **/

public class Absorber extends CircleObstacle {
  public Absorber() {
    super();
    setType(ComponentType.ABSORBER);
    setLabel(new JLabel(ComponentImages.getImage(getType())));
  }

  @Override
  public Point checkCollision(Ball ball) {
    Point point = super.checkCollision(ball);
    if(point != null) {
      ball.stop();
    }
    return point;
  }
}
