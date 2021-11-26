package component.rail;

import component.Ball;
import component.Component;
import geometry.Circle;
import geometry.Geometry;
import geometry.Point;
import java.util.Map;
import javax.swing.*;
import utils.ComponentImages;
import utils.ComponentType;

/**
 * @program: Gizmo
 * @description: 弯轨道
 * @author: 3ummerW1nd
 * @create: 2021-11-01 11:32
 **/
public class CurvedRail extends Rail {
  private final Circle circle;

  public CurvedRail() {
    super();
    this.circle = new Circle();
    setType(ComponentType.CURVED_RAIL);
    setLabel(new JLabel(ComponentImages.getImage(getType())));
  }

  @Override
  public Point checkCollision(Ball ball) {
    double bx = ball.getCircle().getCenter().getX(), by = ball.getCircle().getCenter().getY(),
           br = ball.getCircle().getRadius(), cx = circle.getCenter().getX(),
           cy = circle.getCenter().getY(), cr = circle.getRadius();
    double v = bx * bx - 2 * bx * cx + by * by - 2 * by * cy + cx * cx + cy * cy;
    double v2 = by * cr * Math.sqrt(1 / v);
    double v3 = cr * cy * Math.sqrt(1 / v);
    double v1 = cy * cy * cy / v + v2 + (bx * bx * cy) / v - (2 * by * cy * cy) / v
        + (by * by * cy) / v - v3 + (cx * cx * cy) / v - (2 * bx * cx * cy) / v;
    double x = -(bx * cy - by * cx - bx * v1 + cx * v1) / (by - cy);
    double y = v1;
    if (checkPointOnCircle(ball, br, cx, cy, x, y))
      return new Point(x, y);
    double v4 = cy * cy * cy / v - v2 + (bx * bx * cy) / v - (2 * by * cy * cy) / v
        + (by * by * cy) / v + v3 + (cx * cx * cy) / v - (2 * bx * cx * cy) / v;
    x = -(bx * cy - by * cx - bx * v4 + cx * v4) / (by - cy);
    y = v4;
    if (checkPointOnCircle(ball, br, cx, cy, x, y))
      return new Point(x, y);
    return null;
  }

  private boolean checkPointOnCircle(
      Ball ball, double br, double cx, double cy, double x, double y) {
    if (Geometry.pointToPointDistance(new Point(x, y), ball.getCircle().getCenter()) <= br) {
      int tmpX = Geometry.sgn(x - cx), tmpY = Geometry.sgn(y - cy);
      switch (getAngle()) {
        case 0:
          if (tmpX == 1 && tmpY == 1) {
            return true;
          }
          break;
        case 1:
          if (tmpX == -1 && tmpY == 1) {
            return true;
          }
          break;
        case 2:
          if (tmpX == -1 && tmpY == -1) {
            return true;
          }
          break;
        default:
          if (tmpX == 1 && tmpY == -1) {
            return true;
          }
      }
    }
    return false;
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
    setEntranceX(TOP);
    setEntranceY(LEFT);
    circle.setRadius(getSideLength() * 0.875);
    switch (getAngle()) {
      case 0:
        circle.setCenter(getUpperLeft());
        break;
      case 1:
        circle.setCenter(getUpperLeft().add(new Point(getSideLength(), 0)));
        break;
      case 2:
        circle.setCenter(getUpperLeft().add(new Point(getSideLength(), getSideLength())));
        break;
      default:
        circle.setCenter(getUpperLeft().add(new Point(0, getSideLength())));
    }
  }

  @Override
  public void rotate(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.rotate(locations);
    switch (getAngle()) {
      case 0:
        circle.setCenter(getUpperLeft());
        break;
      case 1:
        circle.setCenter(getUpperLeft().add(new Point(getSideLength(), 0)));
        break;
      case 2:
        circle.setCenter(getUpperLeft().add(new Point(getSideLength(), getSideLength())));
        break;
      default:
        circle.setCenter(getUpperLeft().add(new Point(0, getSideLength())));
    }
  }

  @Override
  public void zoomIn(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.zoomIn(locations);
    circle.setRadius(getSideLength() * 0.875);
  }

  @Override
  public void zoomOut(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.zoomOut(locations);
    circle.setRadius(getSideLength() * 0.975);
  }
}
