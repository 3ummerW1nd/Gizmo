package component.rail;

import ui.Box;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

/**
 * @program: Gizmo
 * @description: 直轨道
 * @author: 3ummerW1nd
 * @create: 2021-11-01 11:31
 **/

public class StraightRail extends Rail {
  public StraightRail() {
    super();
  }

  @Override
  public void init(Box box) {
    setEntranceX(TOP);
    setEntranceX(BOTTOM);
  }
}
