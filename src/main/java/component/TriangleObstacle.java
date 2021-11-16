package component;

import java.util.Map;
import javax.swing.*;
import pair.Pair;
import utils.ComponentImages;
import utils.ComponentType;

/**
 * @program: Gizmo
 * @description: 直角三角形障碍物
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:37
 **/

public class TriangleObstacle extends NormalComponent {
  public TriangleObstacle() {
    setType(ComponentType.TRIANGLE);
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

  @Override
  public void zoomIn(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.zoomIn(locations);
  }

  @Override
  public void zoomOut(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.zoomOut(locations);
  }
}
