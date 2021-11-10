package component;

import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import lombok.Data;
import point.Point;
import ui.Box;

/**
 * @program: Gizmo
 * @description: 挡板
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:42
 **/
@Data
public class Damper extends Component {
  private Point Center;
  private int length;
  private Damper() {
    type = SPECIAL;
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
