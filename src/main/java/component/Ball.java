package component;

import geometry.Circle;
import geometry.Point;
import java.util.Map;
import javax.swing.*;
import lombok.Data;
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
  private Point velocity;
  private Point acceleration;
  private Circle circle;

  private Ball() {
    super();
    setType(ComponentType.BALL);
    setLabel(new JLabel(ComponentImages.getImage(getType())));
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
    velocity = new Point();
    acceleration = new Point();
    acceleration.setY(1);
    circle.setCenter(new Point(box.getKey() * 30 + 15, box.getValue() * 30 + 15));
    circle.setRadius(15);
  }
}
