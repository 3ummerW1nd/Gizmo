package component;

import java.util.Map;
import javax.swing.*;
import lombok.Data;

/**
 * @program: Gizmo
 * @description: 小球
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:36
 **/

@Data
public class Ball extends Component {
  private int velocityX, velocityY;
  private int accelerationX, accelerationY;
  private int centerX, centerY, radius;
  private Ball() {
    super();
    setType(ComponentType.BALL);
    setLabel(new JLabel(ComponentImages.getImage(getType())));
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
    accelerationY = 1; // TODO: 选取一个合理的加速度
    radius = 15;
    centerX = box.getKey() * 30 + 15;
    centerY = box.getValue() * 30 + 15;
    System.out.println(centerX + " " + centerY);
  }
}
