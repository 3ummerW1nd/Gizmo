package component;

import java.util.Map;
import javax.swing.*;
import lombok.Data;
import pair.Pair;
import utils.ComponentImages;
import utils.ComponentType;

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
}
