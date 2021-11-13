package component;

import java.util.Map;
import javax.swing.*;
import lombok.Data;
import pair.Pair;
import utils.ComponentImages;
import utils.ComponentType;

/**
 * @program: Gizmo
 * @description: 挡板
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:42
 **/
@Data
public class Damper extends Component {
  private Pair left;
  private int length;

  private Damper() {
    setLabel(new JLabel(ComponentImages.getImage(ComponentType.LEFT_DAMPER)));
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
    getLabel().setSize(60, 30);
    left = new Pair(getInit().getKey() * 30, getInit().getValue() * 30 + 15);
  }
}
