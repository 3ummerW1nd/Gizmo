package component;

import java.util.Map;
import javax.swing.*;
import lombok.Data;
import utils.ComponentUtil;

/**
 * @program: Gizmo
 * @description: 组件
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:19
 **/
@Data
public abstract class Component {
  private int size;
  private int angle;
  private Map.Entry<Integer, Integer> init;
  private ComponentType type; //组件的类型
  private JLabel label; //组件的图片

  Component() {}

  public void init(Map.Entry<Integer, Integer> box) {
    angle = 0;
    size = 1;
    label.setSize(30, 30);
    label.setIcon(ComponentImages.getAngleImage(type, angle, size));
    label.setLocation((box.getKey() + 1) * 30, (box.getValue() + 1) * 30);
    init = box;
  }

  public void rotate(Map<Map.Entry<Integer, Integer>, Component> locations) {
    ComponentUtil.rotateComponent(this, locations);
  }

  public void zoomIn(Map<Map.Entry<Integer, Integer>, Component> locations) {
    ComponentUtil.zoomInComponent(this, locations);
  }

  public void zoomOut(Map<Map.Entry<Integer, Integer>, Component> locations) {
    ComponentUtil.zoomOutComponent(this, locations);
  }

  public void remove(Map<Map.Entry<Integer, Integer>, Component> locations) {
    ComponentUtil.removeComponent(this, locations);
  }
}
