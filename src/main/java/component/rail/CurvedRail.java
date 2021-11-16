package component.rail;

import java.util.Map;
import javax.swing.*;
import utils.ComponentImages;
import utils.ComponentType;

/**
 * @program: Gizmo
 * @description: 弯轨道
 * @author: 3ummerW1nd
 * @create: 2021-11-01 11:32
 **/
public class CurvedRail extends Rail {
  public CurvedRail() {
    super();
    setType(ComponentType.CURVED_RAIL);
    setLabel(new JLabel(ComponentImages.getImage(getType())));
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
    setEntranceX(TOP);
    setEntranceX(LEFT);
  }
}
