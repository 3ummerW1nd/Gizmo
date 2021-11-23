package ui;

import static geometry.Geometry.sgn;

import component.*;
import component.Component;
import component.rail.CurvedRail;
import component.rail.StraightRail;
import geometry.Point;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.Timer;
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
  private final Map<Map.Entry<Integer, Integer>, Component> locations;
  private final Map<ComponentType, Class> typeComponentMap;
  private Ball ball;
  private Damper leftDamper, rightDamper;
  private final List<NormalComponent> components;
  private Component selectedComponent;
  private Timer timer;
  private String model;

  public GamePanel() {
    setLayout(null);
    setSize(630, 630);
    components = new ArrayList<>();
    typeComponentMap = new HashMap<>();
    locations = new HashMap<>();
    initTypeComponentMap();
    model = "PLAYING_MODEL";
    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (Objects.equals(model, "PLAYING_MODEL")) {
          switch (e.getKeyChar()) {
            case 'a':
              if (leftDamper != null) {
                if (leftDamper.getUpperLeft().getX() > 0) {
                  Map.Entry<Integer, Integer> box = leftDamper.getInit();
                  putComponent(
                      Map.entry(box.getKey() - 1, box.getValue()), ComponentType.LEFT_DAMPER);
                }
              }
              break;
            case 'd':
              if (leftDamper != null) {
                if (leftDamper.getUpperLeft().getX() + 60 < 600) {
                  Map.Entry<Integer, Integer> box = leftDamper.getInit();
                  putComponent(
                      Map.entry(box.getKey() + 1, box.getValue()), ComponentType.LEFT_DAMPER);
                }
              }
              break;
            case 'j':
              if (rightDamper != null) {
                if (rightDamper.getUpperLeft().getX() > 0) {
                  Map.Entry<Integer, Integer> box = rightDamper.getInit();
                  putComponent(
                      Map.entry(box.getKey() - 1, box.getValue()), ComponentType.RIGHT_DAMPER);
                }
              }
              break;
            case 'l':
              if (rightDamper != null) {
                if (rightDamper.getUpperLeft().getX() + 60 < 600) {
                  Map.Entry<Integer, Integer> box = rightDamper.getInit();
                  putComponent(
                      Map.entry(box.getKey() + 1, box.getValue()), ComponentType.RIGHT_DAMPER);
                }
              }
              break;
          }
        }
      }
    });
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
      if (locations.containsKey(tmp) && !locations.get(tmp).getType().equals(componentType)) {
        return;
      }
    }
    if (componentType == ComponentType.LEFT_DAMPER || componentType == ComponentType.RIGHT_DAMPER) {
      if (locations.containsKey(box) && !locations.get(box).getType().equals(componentType)) {
        System.out.println("冲突");
        return;
      }
    } else if (locations.containsKey(box)) {
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

  public void playGame() {
    model = "PLAYING_MODEL";
    timer = new Timer();
    timer.schedule(new GizmoGame(), 0, 10);
  }

  public void stopGame() {
    model = "SETTING_MODEL";
    timer.cancel();
  }

  public void saveGame(File file) {}

  public void readGame(File file) {}

  class GizmoGame extends TimerTask {
    int i = 0;

    private int mysgn(double x) {
      if (x > 0)
        return 1;
      else if (Math.abs(x) <= 1e-6)
        return 1;
      else
        return -1;
    }

    public void checkCollision() {
      ArrayList<Point> collisionPoints = new ArrayList<>();
      for (NormalComponent component : components) {
        if (component.checkCollision(ball) != null) {
          collisionPoints.add(component.checkCollision(ball));
        }
      }
      if (leftDamper != null) {
        if (leftDamper.checkCollision(ball) != null) {
          collisionPoints.add(leftDamper.checkCollision(ball));
        }
      }
      if (rightDamper != null) {
        if (rightDamper.checkCollision(ball) != null) {
          collisionPoints.add(rightDamper.checkCollision(ball));
        }
      }
      geometry.Point center = ball.getCircle().getCenter(), velocity = ball.getVelocity();
      double r = ball.getCircle().getRadius(), bx = center.getX(), by = center.getY();
      double vx = velocity.getX(), vy = velocity.getY();
      System.out.println(r + " " + bx);
      if (bx + r >= 600 || bx - r <= 0) {
        ball.getVelocity().setX(-vx);
      }
      if (by + r >= 600 || by - r <= 0) {
        ball.getVelocity().setY(-vy);
      }
      for (var i : collisionPoints) {
        double rx = i.getX(), ry = i.getY();
        double v = bx * bx - 2 * bx * rx + by * by - 2 * by * ry + rx * rx + ry * ry;
        double x = -(vx * bx * bx + 2 * vy * bx * by - 2 * vx * bx * rx - 2 * vy * bx * ry
                       - vx * by * by - 2 * vy * by * rx + 2 * vx * by * ry + vx * rx * rx
                       + 2 * vy * rx * ry - vx * ry * ry)
            / v;
        double y = (vy * bx * bx - 2 * vx * bx * by - 2 * vy * bx * rx + 2 * vx * bx * ry
                       - vy * by * by + 2 * vx * by * rx + 2 * vy * by * ry + vy * rx * rx
                       - 2 * vx * rx * ry - vy * ry * ry)
            / v;
        //        if (!(mysgn(x) == mysgn(vx) && mysgn(y) != mysgn(vy))) {
        //          x = (vx * bx * bx + 2 * vy * bx * by - 2 * vx * bx * rx - 2 * vy * bx * ry - vx
        //          * by * by
        //                  - 2 * vy * by * rx + 2 * vx * by * ry + vx * rx * rx + 2 * vy * rx * ry
        //                  - vx * ry * ry)
        //              / v;
        //          y = -(vy * bx * bx - 2 * vx * bx * by - 2 * vy * bx * rx + 2 * vx * bx * ry - vy
        //          * by * by
        //                  + 2 * vx * by * rx + 2 * vy * by * ry + vy * rx * rx - 2 * vx * rx * ry
        //                  - vy * ry * ry)
        //              / v;
        //        }
        //        if (!(mysgn(x) == mysgn(vx) && mysgn(y) != mysgn(vy))) {
        //          x = -vx;
        //          y = -vy;
        //        }
        ball.getVelocity().setX(x);
        ball.getVelocity().setY(y);
      }
    }

    public void run() {
      if (ball != null) {
        System.out.println(components.size());
        ball.move();
        add(ball.getLabel());
        repaint();
        checkCollision();
      } else {
        timer.cancel();
      }
    }
  }
}
