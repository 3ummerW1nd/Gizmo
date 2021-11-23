package component;

import java.util.Map;
import javax.swing.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.ComponentImages;
import utils.ComponentSavingObject;
import utils.ComponentType;
import utils.ComponentUtil;

/**
 * @program: Gizmo
 * @description: 组件
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:19
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Component {
  private int size;
  private int angle;
  private Map.Entry<Integer, Integer> init;
  private ComponentType type; //组件的类型
  private JLabel label; //组件的图片

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

  public String save() {
    ComponentSavingObject componentSavingObject = new ComponentSavingObject();
    componentSavingObject.setSize(getSize());
    componentSavingObject.setAngle(getAngle());
    componentSavingObject.setInit(getInit());
    componentSavingObject.setType(getType());
    JSONObject jsonObject = (JSONObject) JSON.toJSON(componentSavingObject);
    return jsonObject.toJSONString() + "\r\n";
  }

  public void load(String string) {
    ComponentSavingObject componentSavingObject = JSONObject.parseObject(string, ComponentSavingObject.class);
    setSize(componentSavingObject.getSize());
    setAngle(componentSavingObject.getAngle());
    setType(componentSavingObject.getType());
    setInit(componentSavingObject.getInit());
  }

}
