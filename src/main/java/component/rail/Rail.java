package component.rail;

import component.Ball;
import component.NormalComponent;
import java.util.Map;
import lombok.Data;
import pair.Pair;

/**
 * @program: Gizmo
 * @description: 轨道
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:40
 **/
@Data
public class Rail extends NormalComponent {
  static final byte LEFT = 0, TOP = 1, RIGHT = 2, BOTTOM = 3;
  private byte entranceX, entranceY;

  @Override
  public Pair checkCollision(Ball ball) {
    return null;
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
  }

  @Override
  public void rotate() {
    super.rotate();
    entranceX = (byte) ((entranceX + 1) % 4);
    entranceY = (byte) ((entranceY + 1) % 4);
  }
}
