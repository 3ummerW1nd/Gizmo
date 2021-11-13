package component;

import java.util.Map;
import javax.swing.*;
import point.Point;

/**
 * @program: Gizmo
 * @description: 吸收器
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:39
 **/

public class Absorber extends NormalComponent {
  public Absorber() {
    setType(ComponentType.ABSORBER);
    setLabel(new JLabel(ComponentImages.getImage(getType())));
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

  @Override
  public Point checkCollision(Ball ball) {
    return null;
  }
}
