package component;

import geometry.Geometry;
import geometry.Line;
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

  private Damper() {
    setLabel(new JLabel(ComponentImages.getImage(ComponentType.LEFT_DAMPER)));
  }

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
    getLabel().setLocation(labelX + 30, labelY);
  }

  public Point checkCollision(Ball ball) {
    Line line = new Line(left, left.add(new Point(length, 0)));
    Point center = ball.getCircle().getCenter();
    double dis = Geometry.pointToSegmentDistance(center, line);
    if (dis == Geometry.pointToPointDistance(center, left)) {
      return left;
    } else if (dis == Geometry.pointToPointDistance(center, line.getT())) {
      return line.getT();
    } else if (dis <= ball.getCircle().getRadius()) {
      double ax = center.getX(), ay = center.getY(), sx = left.getX(), sy = left.getY(),
             tx = sx + length;
      double tmp = sx * sx - 2 * sx * tx + sy * sy - 2 * sy * sy + tx * tx + sy * sy;
      return new Point((ax * sx * sx - sx * sy * sy + ay * sx * sy - 2 * ax * sx * tx + sx * sy * sy
                           - ay * sx * sy + sy * sy * tx - sy * tx * sy - ay * sy * tx
                           + ax * tx * tx + ay * tx * sy)
              / tmp,
          (sx * sx * sy - sx * sy * tx + ax * sx * sy - sx * tx * sy - ax * sx * sy + ay * sy * sy
              + sy * tx * tx - ax * sy * tx - 2 * ay * sy * sy + ax * tx * sy + ay * sy * sy)
              / tmp);
    } else {
      return null;
    }
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
    getLabel().setSize(60, 30);
    left = new Point(getInit().getKey() * 30, getInit().getValue() * 30 + 15);
  }
}
