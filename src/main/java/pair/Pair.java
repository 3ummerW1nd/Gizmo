package pair;

import lombok.Data;

/**
 * @program: Gizmo
 * @description: 二元组
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:23
 **/
@Data
public class Pair {
  private int x, y;

  public Pair() {
    this.x = 0;
    this.y = 0;
  }

  public Pair(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
