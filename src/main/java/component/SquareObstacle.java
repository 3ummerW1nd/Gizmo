package component;

import java.util.Map;
import javax.swing.*;
import point.Point;

/**
 * @program: Gizmo
 * @description: 正方形障碍物
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:51
 **/

public class SquareObstacle extends NormalComponent {
  private int sideLength;
  private int upperLeftX;
  private int upperLeftY;

  public SquareObstacle() {
    setType(ComponentType.RECTANGLE);
    setLabel(new JLabel(ComponentImages.getImage(getType())));
  }

  @Override
  public Point checkCollision(Ball ball) {
    return null;
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
    sideLength = 30;
    upperLeftX = box.getKey() * 30;
    upperLeftY = box.getValue() * 30;
    System.out.println(upperLeftX + " " + upperLeftY);
  }
}
