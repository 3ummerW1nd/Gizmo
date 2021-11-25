package utils;

import component.Component;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: Gizmo
 * @description: 保存地图时的对象
 * @author: 3ummerW1nd
 * @create: 2021-11-23 09:03
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentSavingObject {
  private ComponentType type;
  private int size;
  private int initX;
  private int initY;
  private int angle;

  public Component load(Map<Map.Entry<Integer, Integer>, Component> locations) {
    Component component = null;
    if (type == ComponentType.BALL) {
      component = ComponentFactory.getBall();
    } else if (type == ComponentType.LEFT_DAMPER) {
      component = ComponentFactory.getLeftDamper();
    } else if (type == ComponentType.RIGHT_DAMPER) {
      component = ComponentFactory.getRightDamper();
    } else {
      component = ComponentFactory.createNormalComponent(ComponentUtil.getComponentClass(type));
    }
    locations.put(Map.entry(initX, initY), component);
    component.init(Map.entry(initX, initY));
    for (int i = 1; i < size; i++) {
      component.zoomIn(locations);
    }
    for (int i = 0; i < size; i++) {
      component.rotate(locations);
    }
    return component;
  }
}
