package component;

import java.util.Map;
import javax.swing.*;
import pair.Pair;

/**
 * @program: Gizmo
 * @description: 正方形障碍物
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:51
 **/

public class SquareObstacle extends NormalComponent {
  private int sideLength;
  private Pair upperLeft;

  public SquareObstacle() {
    setType(ComponentType.RECTANGLE);
    setLabel(new JLabel(ComponentImages.getImage(getType())));
  }

  @Override
  public Pair checkCollision(Ball ball) {
    return null;
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
    sideLength = 30;
    upperLeft.setX(box.getKey() * 30);
    upperLeft.setY(box.getValue() * 30);
  }
}
