package component;

import java.util.Map;
import javax.swing.*;
import lombok.Data;
import pair.Pair;

/**
 * @program: Gizmo
 * @description: 挡板
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:42
 **/
@Data
public class Damper extends Component {
  private Pair Center;
  private int length;

  private Damper() {
    setType(ComponentType.DAMPER);
    setLabel(new JLabel(ComponentImages.getImage(getType())));
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {}

  @Override
  public void rotate(Map<Map.Entry<Integer, Integer>, Component> locations) {}

  @Override
  public void zoomIn(Map<Map.Entry<Integer, Integer>, Component> locations) {}

  @Override
  public void zoomOut(Map<Map.Entry<Integer, Integer>, Component> locations) {}
}
