package component;

import geometry.Point;
import java.util.Map;
import javax.swing.*;
import lombok.Data;
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
  private Point left;
  private int length;

  public void moveLeft() {
    double x = left.getX();
    left.setX(x - 30);
    int labelX = getLabel().getX(), labelY = getLabel().getY();
    getLabel().setLocation(labelX - 30, labelY);
  }

  public void moveRight() {
    double x = left.getX();
    left.setX(x + 30);
    int labelX = getLabel().getX(), labelY = getLabel().getY();
    getLabel().setLocation(labelX  + 30, labelY);
  }

  public Point checkCollision(Ball ball){
    return new Point();
  }


  private Damper() {
    setLabel(new JLabel(ComponentImages.getImage(ComponentType.LEFT_DAMPER)));
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
    getLabel().setSize(60, 30);
    left = new Point(getInit().getKey() * 30, getInit().getValue() * 30 + 15);
  }
}
