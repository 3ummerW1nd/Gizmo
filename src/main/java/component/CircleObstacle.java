package component;

import geometry.Circle;
import geometry.Geometry;
import geometry.Point;
import java.util.Map;
import javax.swing.*;
import utils.ComponentImages;
import utils.ComponentType;

/**
 * @program: Gizmo
 * @description: 圆形障碍物
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:37
 **/

public class CircleObstacle extends NormalComponent {
  private Circle circle;

  public CircleObstacle() {
    setType(ComponentType.CIRCLE);
    setLabel(new JLabel(ComponentImages.getImage(getType())));
  }

  @Override
  public Point checkCollision(Ball ball) {
    double br = ball.getCircle().getRadius(), r = this.circle.getRadius(),
           cx = this.circle.getCenter().getX(), cy = this.circle.getCenter().getY(),
           px = ball.getCircle().getCenter().getX(), py = ball.getCircle().getCenter().getY();
    if (Geometry.pointToPointDistance(this.circle.getCenter(), ball.getCircle().getCenter())
        > br + r) {
      return null;
    } else {
      double v2 = cx * cx - 2 * cx * px + cy * cy - 2 * cy * py + px * px + py * py;
      double v1 =
          Math.pow(cy, 3) / v2 + (cx * cx * cy) / v2 + (cy * px * px) / v2 + (cy * py * py) / v2;
      double v3 = cy * r * Math.sqrt(1 / v2);
      double v4 = py * r * Math.sqrt(1 / v2);
      double v = v1 - (2 * cy * cy * py) / v2 + v3 - v4 - (2 * cx * cy * px) / v2;
      Point res = new Point(-(cx * py - cy * px - cx * v + px * v) / (cy - py), v);
      if (Geometry.pointToPointDistance(res, ball.getCircle().getCenter()) <= br) {
        return res;
      } else {
        double v5 = (v1) - (2 * cy * cy * py) / v2 - v3 + v4 - (2 * cx * cy * px) / v2;
        res.setX(-(cx * py - cy * px - cx * v5 + px * v5) / (cy - py));
        res.setY(v5);
        if (Geometry.pointToPointDistance(res, ball.getCircle().getCenter()) <= br) {
          return res;
        } else {
          return null;
        }
      }
    }
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
    circle.setRadius(15 * getSize());
  }

  @Override
  public void zoomIn(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.zoomIn(locations);
    circle.setRadius(15 * getSize());
  }

  @Override
  public void zoomOut(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.zoomOut(locations);
    circle.setRadius(15 * getSize());
  }
}
