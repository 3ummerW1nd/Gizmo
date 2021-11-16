package ui;

import component.*;
import component.Component;
import component.rail.CurvedRail;
import component.rail.StraightRail;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import utils.ComponentFactory;
import utils.ComponentType;

/**
 * @program: Gizmo
 * @description: 游戏中的棋盘，各种组件摆放以及游戏进行的场所
 * @author: 3ummerW1nd
 * @create: 2021-11-09 17:48
 **/

public class GamePanel extends JPanel {
  private Map<Map.Entry<Integer, Integer>, Component> locations;
  private Map<ComponentType, Class> typeComponentMap;
  private Ball ball;
  private Damper leftDamper, rightDamper;
  private List<NormalComponent> components;
  private Component selectedComponent;

  public GamePanel() {
    setLayout(null);
    setSize(630, 630);
    components = new ArrayList<>();
    typeComponentMap = new HashMap<>();
    locations = new HashMap<>();
    initTypeComponentMap();
  }

  public Component getSelectedComponent() {
    return selectedComponent;
  }

  public void setSelectedComponent(Map.Entry<Integer, Integer> box) {
    if (!locations.containsKey(box))
      return;
    Component component = locations.get(box);
    this.selectedComponent = component;
    System.out.println(component);
  }

  public Map.Entry<Integer, Integer> checkBox(int x, int y) {
    return Map.entry(x / 30 - 1, y / 30 - 1);
  }

  private void initTypeComponentMap() {
    typeComponentMap.put(ComponentType.TRIANGLE, TriangleObstacle.class);
    typeComponentMap.put(ComponentType.CIRCLE, CircleObstacle.class);
    typeComponentMap.put(ComponentType.RECTANGLE, SquareObstacle.class);
    typeComponentMap.put(ComponentType.STRAIGHT_RAIL, StraightRail.class);
    typeComponentMap.put(ComponentType.CURVED_RAIL, CurvedRail.class);
    typeComponentMap.put(ComponentType.ABSORBER, Absorber.class);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.black);
    g2.setStroke(new BasicStroke(1));
    for (int i = 1; i < 22; i++) {
      g2.drawLine(30, 30 * i, 630, 30 * i);
    }
    for (int i = 1; i < 22; i++) {
      g2.drawLine(30 * i, 30, 30 * i, 630);
    }
  }

  public void putComponent(Map.Entry<Integer, Integer> box, ComponentType componentType) {
    if (box.getValue() < 0 || box.getKey() < 0)
      return;
    if (componentType == ComponentType.LEFT_DAMPER || componentType == ComponentType.RIGHT_DAMPER) {
      if (box.getKey() >= 19)
        return;
      Map.Entry<Integer, Integer> tmp = Map.entry(box.getKey() + 1, box.getValue());
      if (locations.containsKey(tmp) && !locations.get(tmp).getType().equals(componentType))
        return;
    }
    if (locations.containsKey(box)) {
      System.out.println("chongtu");
      return;
    }
    Component component;
    if (componentType == ComponentType.BALL) {
      if (ball == null)
        ball = ComponentFactory.getBall();
      else
        deleteComponent(ball);
      component = ball;
    } else if (componentType == ComponentType.LEFT_DAMPER) {
      if (leftDamper == null)
        leftDamper = ComponentFactory.getLeftDamper();
      else
        deleteComponent(leftDamper);
      component = leftDamper;
    } else if (componentType == ComponentType.RIGHT_DAMPER) {
      if (rightDamper == null)
        rightDamper = ComponentFactory.getRightDamper();
      else
        deleteComponent(rightDamper);
      component = rightDamper;
    } else {
      component = ComponentFactory.createNormalComponent(typeComponentMap.get(componentType));
      components.add((NormalComponent) component);
    }
    component.init(box);
    locations.put(box, component);
    Map.Entry<Integer, Integer> tmp = Map.entry(box.getKey() + 1, box.getValue());
    if (componentType == ComponentType.RIGHT_DAMPER || componentType == ComponentType.LEFT_DAMPER) {
      locations.put(tmp, component);
    }
    JLabel jLabel = component.getLabel();
    add(jLabel);
    repaint();
    System.out.println(locations);
  }

  private void deleteComponent(Component component) {
    component.remove(locations);
    remove(component.getLabel());
    repaint();
  }

  public void removeSelectComponent() {
    if (selectedComponent == null)
      return;
    deleteComponent(selectedComponent);
    selectedComponent = null;
  }

  public void rotateSelectComponent() {
    if (selectedComponent == null)
      return;
    selectedComponent.rotate(locations);
    repaint();
  }

  public void zoomInSelectComponent() {
    if (selectedComponent == null)
      return;
    selectedComponent.zoomIn(locations);
    repaint();
  }

  public void zoomOutSelectComponent() {
    if (selectedComponent == null)
      return;
    selectedComponent.zoomOut(locations);
    repaint();
  }
}
