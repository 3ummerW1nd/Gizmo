package component;

import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import lombok.Data;
import ui.Box;

/**
 * @program: Gizmo
 * @description: 小球
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:36
 **/

@Data
public class Ball extends Component {
  private int velocityX, velocityY;
  private int accelerationX, accelerationY;
  private Ball() {
    type = CIRCLE;
  }

  @Override
  public void init(Box box) {}

  @Override
  public void rotate() {}

  @Override
  public void zoomIn() {}

  @Override
  public void zoomOut() {}
}
