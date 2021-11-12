package component;

import java.util.Map;
import javax.swing.*;
import lombok.Data;
import point.Point;

/**
 * @program: Gizmo
 * @description: 挡板
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:42
 **/
@Data
public class Damper extends Component {
  private Point Center;
  private int length;

  private Damper() {
    setType(ComponentType.DAMPER);
    setLabel(new JLabel(ComponentImages.getImage(getType())));
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {}

  @Override
  public void rotate() {}

  @Override
  public void zoomIn() {}

  @Override
  public void zoomOut() {}
}
