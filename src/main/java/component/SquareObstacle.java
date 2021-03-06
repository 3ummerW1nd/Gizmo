package component;

import geometry.Geometry;
import geometry.Line;
import geometry.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import utils.ComponentImages;
import utils.ComponentType;

/**
 * @program: Gizmo
 * @description: 正方形障碍物
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:51
 **/

public class SquareObstacle extends NormalComponent {
  private double sideLength;
  private Point upperLeft;

  public SquareObstacle() {
    setType(ComponentType.RECTANGLE);
    setLabel(new JLabel(ComponentImages.getImage(getType())));
  }

  @Override
  public Point checkCollision(Ball ball) {
    double radius = ball.getCircle().getRadius();
    Point center = ball.getCircle().getCenter();
    List<Line> lines = new ArrayList<>();
    lines.add(new Line(upperLeft, upperLeft.add(new Point(sideLength, 0))));
    lines.add(new Line(upperLeft, upperLeft.add(new Point(0, sideLength))));
    lines.add(new Line(
        upperLeft.add(new Point(sideLength, 0)), upperLeft.add(new Point(sideLength, sideLength))));
    lines.add(new Line(
        upperLeft.add(new Point(0, sideLength)), upperLeft.add(new Point(sideLength, sideLength))));
    Line bestLine = null;
    for (var line : lines) {
      if (radius >= Geometry.pointToSegmentDistance(center, line)) {
        radius = Geometry.pointToSegmentDistance(center, line);
        bestLine = line;
      }
    }
    if (bestLine == null) {
      return null;
    } else {
      double dis = Geometry.pointToSegmentDistance(center, bestLine);
      if (dis == Geometry.pointToPointDistance(center, bestLine.getS())) {
        return bestLine.getS();
      } else if (dis == Geometry.pointToPointDistance(center, bestLine.getT())) {
        return bestLine.getT();
      } else {
        double ax = center.getX(), ay = center.getY(), sx = bestLine.getS().getX(),
               sy = bestLine.getS().getY(), tx = bestLine.getT().getX(),
               ty = bestLine.getT().getY();
        double tmp = sx * sx - 2 * sx * tx + sy * sy - 2 * sy * ty + tx * tx + ty * ty;
        return new Point((ax * sx * sx - sx * sy * ty + ay * sx * sy - 2 * ax * sx * tx
                             + sx * ty * ty - ay * sx * ty + sy * sy * tx - sy * tx * ty
                             - ay * sy * tx + ax * tx * tx + ay * tx * ty)
                / tmp,
            (sx * sx * ty - sx * sy * tx + ax * sx * sy - sx * tx * ty - ax * sx * ty + ay * sy * sy
                + sy * tx * tx - ax * sy * tx - 2 * ay * sy * ty + ax * tx * ty + ay * ty * ty)
                / tmp);
      }
    }
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
    sideLength = 30 * getSize();
    upperLeft = new Point(box.getKey() * 30, box.getValue() * 30);
  }

  @Override
  public void rotate(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.rotate(locations);
  }

  @Override
  public void zoomIn(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.zoomIn(locations);
    sideLength = 30 * getSize();
  }

  @Override
  public void zoomOut(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.zoomOut(locations);
    sideLength = 30 * getSize();
  }

  @Override
  public void remove(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.remove(locations);
  }
}
