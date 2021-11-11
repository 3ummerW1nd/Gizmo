package component.rail;

import component.ComponentImages;
import component.ComponentType;
import javax.swing.*;
import java.util.Map;

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
