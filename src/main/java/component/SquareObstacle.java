package component;

import geometry.Line;
import geometry.Point;
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
  private int sideLength;
  private Point upperLeft;

  public SquareObstacle() {
    setType(ComponentType.RECTANGLE);
    setLabel(new JLabel(ComponentImages.getImage(getType())));
  }

  @Override
  public Point checkCollision(Ball ball) {
    Line line = new Line(upperLeft, upperLeft.add(new Point(sideLength, 0)));
    return null;
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
    sideLength = 30;
    upperLeft = new Point(box.getKey() * 30, box.getValue() * 30);
  }

  @Override
  public void rotate(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.rotate(locations);
  }

  @Override
  public void zoomIn(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.zoomIn(locations);
  }

  @Override
  public void zoomOut(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.zoomOut(locations);
  }

  @Override
  public void remove(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.remove(locations);
  }
}
