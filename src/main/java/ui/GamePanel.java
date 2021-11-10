package ui;

import component.Component;
import component.NormalComponent;
import component.Ball;
import component.CircleObstacle;
import component.Damper;
import component.Absorber;
import component.SquareObstacle;
import component.rail.CurvedRail;
import component.rail.StraightRail;
import component.TriangleObstacle;
import factory.ComponentFactory;
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

/**
 * @program: Gizmo
 * @description: 游戏中的棋盘，各种组件摆放以及游戏进行的场所
 * @author: 3ummerW1nd
 * @create: 2021-11-09 17:48
 **/

public class GamePanel extends JPanel {
  private List<NormalComponent> components;
  private Set<Map.Entry<Integer, Integer>> full;
  private Map<ComponentType, Class> typeComponentMap;
  private Ball ball;
  private Damper leftDamper, rightDamper;
  private Graphics2D g2;
  private Map<ComponentType, Image> images;
  public Box checkBox(int x, int y) {
    return new Box(x / 30 - 1, y / 30 - 1);
  }

  public GamePanel() {
    setSize(630, 630);
    setBackground(Color.WHITE);
    components = new ArrayList<>();
    full = new HashSet<>();
    typeComponentMap = new HashMap<>();
    images = new HashMap<>();
    initImages();
    initTypeComponentMap();
  }

  private void initImages() {
    Toolkit toolkit = getToolkit();
    Image ballImage = toolkit.getImage("src/main/resources/ball.png");
    Image absorberImage = toolkit.getImage("src/main/resources/absorber.png");
    Image triangleImage = toolkit.getImage("src/main/resources/triangle.png");
    Image squareImage = toolkit.getImage("src/main/resources/rectangle.png");
    Image curvedRailImage = toolkit.getImage("src/main/resources/curvedRail.png");
    Image straightRailImage = toolkit.getImage("src/main/resources/straightRail.png");
    Image damperImage = toolkit.getImage("src/main/resources/damper.png");
    Image circleImage = toolkit.getImage("src/main/resources/circle.png");
    images.put(ComponentType.BALL, ballImage);
    images.put(ComponentType.ABSORBER, absorberImage);
    images.put(ComponentType.TRIANGLE, triangleImage);
    images.put(ComponentType.RECTANGLE, squareImage);
    images.put(ComponentType.CURVED_RAIL, curvedRailImage);
    images.put(ComponentType.STRAIGHT_RAIL, straightRailImage);
    images.put(ComponentType.CIRCLE, circleImage);
    images.put(ComponentType.DAMPER, damperImage);
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
    g2 = (Graphics2D) g;
    g2.setColor(Color.WHITE);
    g2.fill3DRect(30, 30, 600, 600, true);
    g2.setColor(Color.black);
    g2.setStroke(new BasicStroke(1));
    for (int i = 1; i < 22; i++) {
      g2.drawLine(30, 30 * i, 630, 30 * i);
    }
    for (int i = 1; i < 22; i++) {
      g2.drawLine(30 * i, 30, 30 * i, 630);
    }
  }

  public void putComponent(Box box, ComponentType componentType) {
    if (full.contains(Map.entry(box.getX(), box.getY())))
      return;
    Component component = null;
    if (componentType == ComponentType.BALL) {
      ball = ComponentFactory.getBall();
      component = ball;
      ball.init(box);
    } else if (componentType == ComponentType.DAMPER) {
      leftDamper = ComponentFactory.getDamper();
      component = leftDamper;
    } else {
      component = (Component) ComponentFactory.createNormalComponent(typeComponentMap.get(componentType));
      components.add((NormalComponent) component);
    }
    full.add(Map.entry(box.getX(), box.getY()));
    System.out.println(components);
    drawComponent(componentType, box);
  }

  private void drawComponent(ComponentType componentType, Box box) {
    g2 = (Graphics2D) getGraphics();
    if(componentType == ComponentType.DAMPER) {
      g2.drawImage(images.get(componentType), (box.getX() + 1) * 30, (box.getY() + 1) * 30, 60, 30, this);
      return;
    }
    g2.drawImage(images.get(componentType), (box.getX() + 1) * 30, (box.getY() + 1) * 30, 30, 30, this);
  }

}
