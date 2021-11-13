package component;

import java.util.Map;
import javax.swing.*;
import lombok.Data;
import pair.Pair;

/**
 * @program: Gizmo
 * @description: 小球
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:36
 **/

@Data
public class Ball extends Component {
  private Pair velocity;
  private Pair acceleration;
  private Pair center;
  private int radius;

  private Ball() {
    super();
    setType(ComponentType.BALL);
    setLabel(new JLabel(ComponentImages.getImage(getType())));
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
    acceleration.setY(1);
    radius = 15;
    center.setX(box.getKey() * 30 + 15);
    center.setY(box.getValue() * 30 + 15);
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
  public void remove(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.remove(locations);
  }
}
