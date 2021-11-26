package ui;

import com.alibaba.fastjson.JSON;
import component.Ball;
import component.Component;
import component.Damper;
import component.NormalComponent;
import geometry.Point;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;
import java.util.Timer;
import javax.swing.*;
import utils.ComponentFactory;
import utils.ComponentSavingObject;
import utils.ComponentType;
import utils.ComponentUtil;

/**
 * @program: Gizmo
 * @description: 游戏中的棋盘，各种组件摆放以及游戏进行的场所
 * @author: 3ummerW1nd
 * @create: 2021-11-09 17:48
 **/

public class GamePanel extends JPanel {
  private final Map<Map.Entry<Integer, Integer>, Component> locations;
  private final List<NormalComponent> components;
  private Ball ball;
  private Damper leftDamper, rightDamper;
  private Component selectedComponent;
  private Timer timer;
  private String model;

  public GamePanel() {
    setLayout(null);
    setSize(630, 630);
    components = new ArrayList<>();
    locations = new HashMap<>();
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
      component =
          ComponentFactory.createNormalComponent(ComponentUtil.getComponentClass(componentType));
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
    selectedComponent = component;
    System.out.println(locations);
  }

  private void deleteComponent(Component component) {
    component.remove(locations);
    components.remove(component);
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

  public void saveGame(File file) {
    if (file == null)
      return;
    try {
      OutputStreamWriter osw =
          new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
      if (ball != null) {
        osw.write(ball.save(locations));
        osw.flush();
      }
      if (leftDamper != null) {
        osw.write(leftDamper.save(locations));
        osw.flush();
      }
      if (rightDamper != null) {
        osw.write(rightDamper.save(locations));
        osw.flush();
      }
      for (Component component : components) {
        osw.write(component.save(locations));
        osw.flush();
      }
      osw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loadGame(File file) {
    if (file == null)
      return;
    try {
      InputStreamReader inputStreamReader =
          new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      String line;
      for (Component component : components) {
        remove(component.getLabel());
      }
      components.clear();
      locations.clear();
      if (ball != null) {
        remove(ball.getLabel());
        ball = null;
      }
      if (leftDamper != null) {
        remove(leftDamper.getLabel());
        leftDamper = null;
      }
      if (rightDamper != null) {
        remove(rightDamper.getLabel());
        rightDamper = null;
      }
      while ((line = bufferedReader.readLine()) != null) {
        ComponentSavingObject componentSavingObject =
            JSON.parseObject(line, ComponentSavingObject.class);
        Component component = componentSavingObject.load(locations);
        ComponentType type = component.getType();
        if (type.equals(ComponentType.BALL)) {
          ball = (Ball) component;
        } else if (type.equals(ComponentType.LEFT_DAMPER)) {
          leftDamper = (Damper) component;
        } else if (type.equals(ComponentType.RIGHT_DAMPER)) {
          rightDamper = (Damper) component;
        } else {
          components.add((NormalComponent) component);
        }
        add(component.getLabel());
        repaint();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  class GizmoGame extends TimerTask {
    public void checkCollision() {
      ArrayList<Point> collisionPoints = new ArrayList<>();
      for (NormalComponent component : components) {
        if (component.checkCollision(ball) != null) {
          if (component.getType() == ComponentType.ABSORBER) {
            remove(ball.getLabel());
            repaint();
            timer.cancel();
          }
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
