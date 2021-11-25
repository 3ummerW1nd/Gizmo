package component.rail;

import component.Ball;
import component.Component;
import component.NormalComponent;
import geometry.Geometry;
import geometry.Line;
import geometry.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: Gizmo
 * @description: 轨道
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:40
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class Rail extends NormalComponent {
  static final byte LEFT = 0, TOP = 1, RIGHT = 2, BOTTOM = 3;
  private byte entranceX, entranceY;
  private double sideLength;
  private Point upperLeft;

  @Override
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
    this.upperLeft = new Point(box.getKey() * 30, box.getValue() * 30);
    sideLength = 30 * getSize();
  }

  @Override
  public Point checkCollision(Ball ball) {
    return null;
  }

  @Override
  public void rotate(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.rotate(locations);
    entranceX = (byte) ((entranceX + 1) % 4);
    entranceY = (byte) ((entranceY + 1) % 4);
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
}
