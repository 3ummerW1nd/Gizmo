package component.rail;

import component.Ball;
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
 * @description: 直轨道
 * @author: 3ummerW1nd
 * @create: 2021-11-01 11:31
 **/

public class StraightRail extends Rail {
  public StraightRail() {
    super();
    setType(ComponentType.STRAIGHT_RAIL);
    setLabel(new JLabel(ComponentImages.getImage(getType())));
  }

  @Override
  public Point checkCollision(Ball ball) {
    double radius = ball.getCircle().getRadius();
    Point center = ball.getCircle().getCenter();
    List<Line> lines = new ArrayList<>();
    if (getEntranceX() == LEFT || getEntranceY() == LEFT) {
      lines.add(new Line(getUpperLeft(), getUpperLeft().add(new Point(0, getSideLength()))));
    }
    if (getEntranceX() == TOP || getEntranceY() == TOP) {
      lines.add(new Line(getUpperLeft(), getUpperLeft().add(new Point(getSideLength(), 0))));
    }
    if (getEntranceX() == RIGHT || getEntranceY() == RIGHT) {
      lines.add(new Line(getUpperLeft().add(new Point(getSideLength(), getSideLength())),
          getUpperLeft().add(new Point(getSideLength(), 0))));
    }
    if (getEntranceX() == BOTTOM || getEntranceY() == BOTTOM) {
      lines.add(new Line(getUpperLeft().add(new Point(getSideLength(), getSideLength())),
          getUpperLeft().add(new Point(0, getSideLength()))));
    }
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
    setEntranceX(LEFT);
    setEntranceY(RIGHT);
  }
}
