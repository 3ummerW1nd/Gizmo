package utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

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
  private Map.Entry<Integer, Integer> init;
  private int angle;
}
