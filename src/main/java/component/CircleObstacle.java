package component;

import geometry.Point;
import java.util.Map;
import javax.swing.*;
import utils.ComponentImages;
import utils.ComponentType;

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
  public Point checkCollision(Ball ball) {
    return null;
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
  }

  @Override
  public void zoomIn(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.zoomIn(locations);
  }

  @Override
  public void zoomOut(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.zoomOut(locations);
  }
}
