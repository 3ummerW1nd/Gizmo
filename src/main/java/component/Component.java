package component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import lombok.Data;

/**
 * @program: Gizmo
 * @description: 组件
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:19
 **/
@Data
public abstract class Component {
  private int angle;
  private List<Map.Entry<Integer, Integer>> own;
  private ComponentType type; //组件的类型
  private JLabel label; //组件的图片

  Component() {
    own = new ArrayList<>();
    angle = 0;
  }

  public void init(Map.Entry<Integer, Integer> box) {
    label.setSize(30, 30);
    label.setLocation((box.getKey() + 1) * 30, (box.getValue() + 1) * 30);
    own.add(box);
  }
  public void rotate() {
    angle = (angle + 1) % 4;
    label.setIcon(ComponentImages.getAngleImage(type, angle));
  }
  public void zoomIn() {
    // TODO:实现组件的放大
  }
  public void zoomOut() {
    // TODO:实现组件的缩小
  }
  public void remove() {
    // TODO:实现组件的删除
  }
}
