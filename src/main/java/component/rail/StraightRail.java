package component.rail;

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
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
    setEntranceX(LEFT);
    setEntranceX(RIGHT);
  }
}
