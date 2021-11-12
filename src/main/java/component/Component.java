package component;

import java.awt.*;
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
  public void rotate() {
    angle = (angle + 1) % 4;
    label.setIcon(ComponentImages.getAngleImage(type, angle, size));
  }
  public void zoomIn() {
    // TODO:实现组件的放大
    int initX = init.getKey();
    int initY = init.getValue();
    size ++;
    int limit = size - 1;
//    for(int i = 0; i < limit; i ++) {
//      own.add(Map.entry(initX + limit, initY + i));
//      own.add(Map.entry(initX + i, initY + limit));
//    }
//    own.add(Map.entry(initX + limit, initY + limit));
    label.setSize(30*size, 30*size);
    label.setIcon(ComponentImages.getAngleImage(type,angle,size));
    System.out.println(label.getIcon().getIconHeight());
  }
  public void zoomOut() {
    // TODO:实现组件的缩小
    size --;
    System.out.println(size);
//    int tmp = own.size();
//    for(int i = tmp - 1; i >= tmp - size * 2 - 1; i --) {
//      own.remove(i);
//    }
    label.setSize(30*size, 30*size);
    label.setIcon(ComponentImages.getAngleImage(type, angle, size));
  }
  public void remove() {
    // TODO:实现组件的删除
  }
}
