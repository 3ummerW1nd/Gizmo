package component;

import java.util.Map;
import javax.swing.*;
import pair.Pair;

/**
 * @program: Gizmo
 * @description: 圆形障碍物
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:37
 **/

public class CircleObstacle extends NormalComponent {
  public CircleObstacle() {
    setType(ComponentType.CIRCLE);
    setLabel(new JLabel(ComponentImages.getImage(getType())));
  }

  @Override
  public Pair checkCollision(Ball ball) {
    return null;
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
  }
}
