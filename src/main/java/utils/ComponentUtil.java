package utils;

import component.*;
import component.rail.CurvedRail;
import component.rail.StraightRail;
import java.util.*;

/**
 * @program: Gizmo
 * @description: 布局模式下操作组件的工具
 * @author: 3ummerW1nd
 * @create: 2021-11-01 11:23
 **/

public class ComponentUtil {
  private static final Map<ComponentType, Class> typeComponentMap = new HashMap<>();

  static {
    typeComponentMap.put(ComponentType.TRIANGLE, TriangleObstacle.class);
    typeComponentMap.put(ComponentType.CIRCLE, CircleObstacle.class);
    typeComponentMap.put(ComponentType.RECTANGLE, SquareObstacle.class);
    typeComponentMap.put(ComponentType.STRAIGHT_RAIL, StraightRail.class);
    typeComponentMap.put(ComponentType.CURVED_RAIL, CurvedRail.class);
    typeComponentMap.put(ComponentType.ABSORBER, Absorber.class);
  }

  public static Class getComponentClass(ComponentType componentType) {
    return typeComponentMap.get(componentType);
  }

  public static void rotateComponent(
      Component component, Map<Map.Entry<Integer, Integer>, Component> locations) {
    if (component.getType() == ComponentType.LEFT_DAMPER
        || component.getType() == ComponentType.RIGHT_DAMPER)
      return;
    if (component.getType() == ComponentType.TRIANGLE) {
      int size = component.getSize();
      int initX = component.getInit().getKey();
      int initY = component.getInit().getValue();
      List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
      switch (component.getAngle()) {
        case 0:
          for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i; j++) {
              if (locations.containsKey(Map.entry(initX + i, initY + j))
                  && !locations.get(Map.entry(initX + i, initY + j)).equals(component)) {
                return;
              }
              list.add(Map.entry(initX + i, initY + j));
            }
          }
          break;
        case 1:
          for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
              if (locations.containsKey(Map.entry(initX + i, initY + j))
                  && !locations.get(Map.entry(initX + i, initY + j)).equals(component)) {
                return;
              }
              list.add(Map.entry(initX + i, initY + j));
            }
          }
          break;
        case 2:
          for (int i = 0; i < size; i++) {
            for (int j = size - 1; j >= size - 1 - i; j--) {
              if (locations.containsKey(Map.entry(initX + i, initY + j))
                  && !locations.get(Map.entry(initX + i, initY + j)).equals(component)) {
                return;
              }
              list.add(Map.entry(initX + i, initY + j));
            }
          }
          break;
        case 3:
          for (int i = 0; i < size; i++) {
            for (int j = size - 1; j >= i; j--) {
              if (locations.containsKey(Map.entry(initX + i, initY + j))
                  && !locations.get(Map.entry(initX + i, initY + j)).equals(component)) {
                return;
              }
              list.add(Map.entry(initX + i, initY + j));
            }
          }
          break;
      }
      removeComponent(component, locations);
      for (Map.Entry<Integer, Integer> it : list) {
        locations.put(it, component);
      }
    }
    component.setAngle((component.getAngle() + 1) % 4);
    component.getLabel().setIcon(ComponentImages.getAngleImage(
        component.getType(), component.getAngle(), component.getSize()));
  }

  public static void zoomInComponent(
      Component component, Map<Map.Entry<Integer, Integer>, Component> locations) {
    int initX = component.getInit().getKey();
    int initY = component.getInit().getValue();
    int size = component.getSize();
    if (initX + size >= 20 || initY + size >= 20)
      return;
    switch (component.getType()) {
      case BALL:
      case CIRCLE:
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
        List<Map.Entry<Integer, Integer>> delete = new ArrayList<>();
        int centerX = initX * 30 + (size + 1) * 15, centerY = initY * 30 + (size + 1) * 15,
            r = (size + 1) * 15;
        if ((initX + size < 20 || initY + size < 20)) {
          int tmp = (size + 1) / 2;
          for (int i = 0; i < size + 1; i++) {
            for (int j = 0; j < size + 1; j++) {
              int tempX = initX + i, tempY = initY + j;
              int diX, diY;
              if (i < tmp) {
                diX = (tempX + 1) * 30 - centerX;
                if (j < tmp) {
                  diY = (tempY + 1) * 30 - centerY;
                  if (diX * diX + diY * diY >= r * r) {
                    if (locations.containsKey(Map.entry(tempX, tempY))
                        && locations.get(Map.entry(tempX, tempY)).equals(component)) {
                      delete.add(Map.entry(tempX, tempY));
                    }
                  }
                } else {
                  diY = (tempY) *30 - centerY;
                }
              } else {
                diX = (tempX) *30 - centerX;
                if (j < tmp) {
                  diY = (tempY + 1) * 30 - centerY;
                } else {
                  diY = (tempY) *30 - centerY;
                }
              }
              if (diX * diX + diY * diY < r * r) {
                list.add(Map.entry(tempX, tempY));
                if (locations.containsKey(Map.entry(tempX, tempY))
                    && !locations.get(Map.entry(tempX, tempY)).equals(component)) {
                  return;
                }
              }
            }
          }
          for (Map.Entry<Integer, Integer> it : list) {
            locations.put(it, component);
          }
          for (Map.Entry<Integer, Integer> it : delete) {
            locations.remove(it);
          }
        }
        break;
      case ABSORBER:
      case RECTANGLE:
      case CURVED_RAIL:
      case STRAIGHT_RAIL:
        if ((initX + size < 20 || initY + size < 20)
            && !locations.containsKey(Map.entry(initX + size, initY + size))) {
          for (int i = 0; i < size; i++) {
            if (locations.containsKey(Map.entry(initX + size, initY + i))
                || locations.containsKey(Map.entry(initX + i, initY + size))) {
              return;
            }
          }
          locations.put(Map.entry(initX + size, initY + size), component);
          for (int i = 0; i < size; i++) {
            locations.put(Map.entry(initX + size, initY + i), component);
            locations.put(Map.entry(initX + i, initY + size), component);
          }
        }
        break;
      case TRIANGLE:
        if (initX + size >= 20 || initY + size >= 20)
          return;
        List<Map.Entry<Integer, Integer>> puts = new ArrayList<>();
        switch (component.getAngle()) {
          case 0:
            for (int i = 0; i < size + 1; i++) {
              if (locations.containsKey(Map.entry(initX + i, initY + size))) {
                return;
              }
              puts.add(Map.entry(initX + i, initY + size));
            }
            break;
          case 1:
            for (int i = 0; i < size + 1; i++) {
              if (locations.containsKey(Map.entry(initX + size - i, initY + i))) {
                return;
              }
              puts.add(Map.entry(initX + size - i, initY + i));
            }
            break;
          case 2:
            for (int i = 0; i < size + 1; i++) {
              if (locations.containsKey(Map.entry(initX + size, initY + i))) {
                return;
              }
              puts.add(Map.entry(initX + size, initY + i));
            }
            break;
          case 3:
            if (locations.containsKey(Map.entry(initX + size, initY + size)))
              return;
            for (int i = 0; i < size; i++) {
              if (locations.containsKey(Map.entry(initX + size, initY + i))
                  || locations.containsKey(Map.entry(initX + i, initY + size))) {
                return;
              }
            }
            List<Map.Entry<Integer, Integer>> all = new ArrayList<>();
            for (Map.Entry<Map.Entry<Integer, Integer>, Component> it : locations.entrySet()) {
              if (it.getValue().equals(component)) {
                Map.Entry<Integer, Integer> tmp = it.getKey();
                all.add(tmp);
              }
            }
            for (Map.Entry<Integer, Integer> it : all) {
              locations.remove(it);
              puts.add(Map.entry(it.getKey() + 1, it.getValue()));
            }
            for (int i = 0; i < size + 1; i++) {
              puts.add(Map.entry(initX + i, initY + size));
            }
            break;
        }
        for (Map.Entry<Integer, Integer> it : puts) {
          locations.put(it, component);
        }
        break;
      case RIGHT_DAMPER:
      case LEFT_DAMPER:
        // TODO 挡板放大
        return;
    }
    component.setSize(size + 1);
    size = component.getSize();
    component.getLabel().setSize(30 * size, 30 * size);
    component.getLabel().setIcon(
        ComponentImages.getAngleImage(component.getType(), component.getAngle(), size));
  }

  public static void zoomOutComponent(
      Component component, Map<Map.Entry<Integer, Integer>, Component> locations) {
    int initX = component.getInit().getKey();
    int initY = component.getInit().getValue();
    int size = component.getSize();
    if (size <= 1)
      return;
    switch (component.getType()) {
      case BALL:
      case CIRCLE:
        Set<Map.Entry<Integer, Integer>> stay = new HashSet<>();
        int centerX = initX * 30 + (size - 1) * 15, centerY = initY * 30 + (size - 1) * 15,
            r = (size - 1) * 15;
        int tmp = (size - 1) / 2;
        for (int i = 0; i < size - 1; i++) {
          for (int j = 0; j < size - 1; j++) {
            int tempX = initX + i, tempY = initY + j;
            int diX, diY;
            if (i < tmp) {
              diX = (tempX + 1) * 30 - centerX;
              if (j < tmp) {
                diY = (tempY + 1) * 30 - centerY;
                if (diX * diX + diY * diY < r * r) {
                  if (locations.containsKey(Map.entry(tempX, tempY))
                      && !locations.get(Map.entry(tempX, tempY)).equals(component)) {
                    System.out.println("111");
                    return;
                  }
                }
              } else {
                diY = (tempY) *30 - centerY;
              }
            } else {
              diX = (tempX) *30 - centerX;
              if (j < tmp) {
                diY = (tempY + 1) * 30 - centerY;
              } else {
                diY = (tempY) *30 - centerY;
              }
            }
            if (diX * diX + diY * diY < r * r) {
              stay.add(Map.entry(tempX, tempY));
            }
          }
        }
        Collection<Component> values = locations.values();
        while (values.contains(component)) values.remove(component);
        for (Map.Entry<Integer, Integer> it : stay) {
          locations.put(it, component);
        }
        break;
      case ABSORBER:
      case RECTANGLE:
      case CURVED_RAIL:
      case STRAIGHT_RAIL:
        locations.remove(Map.entry(initX + size - 1, initY + size - 1));
        for (int i = 0; i < size; i++) {
          locations.remove(Map.entry(initX + size - 1, initY + i));
          locations.remove(Map.entry(initX + i, initY + size - 1));
        }
        break;
      case TRIANGLE:
        switch (component.getAngle()) {
          case 0:
            for (int i = 0; i < size; i++) {
              locations.remove(Map.entry(initX + i, initY + size - 1), component);
            }
            break;
          case 1:
            for (int i = 0; i < size; i++) {
              locations.remove(Map.entry(initX + size - 1 - i, initY + i), component);
            }
            break;
          case 2:
            for (int i = 0; i < size; i++) {
              locations.remove(Map.entry(initX + size - 1, initY + i), component);
            }
            break;
          case 3:
            List<Map.Entry<Integer, Integer>> still = new ArrayList<>();
            for (Map.Entry<Map.Entry<Integer, Integer>, Component> it : locations.entrySet()) {
              if (it.getValue().equals(component)) {
                if (it.getKey().getValue() - initY < size - 1) {
                  Map.Entry<Integer, Integer> temp =
                      Map.entry(it.getKey().getKey() - 1, it.getKey().getValue());
                  still.add(temp);
                  if (locations.containsKey(temp) && !locations.get(temp).equals(component)) {
                    return;
                  }
                }
              }
            }
            removeComponent(component, locations);
            for (Map.Entry<Integer, Integer> it : still) {
              locations.put(it, component);
            }
            break;
        }
        break;
      case RIGHT_DAMPER:
      case LEFT_DAMPER:
        return;
    }
    component.setSize(size - 1);
    size = component.getSize();
    System.out.println(size);
    component.getLabel().setSize(30 * size, 30 * size);
    component.getLabel().setIcon(
        ComponentImages.getAngleImage(component.getType(), component.getAngle(), size));
  }

  public static void removeComponent(
      Component component, Map<Map.Entry<Integer, Integer>, Component> locations) {
    Collection<Component> values = locations.values();
    while (values.contains(component)) {
      values.remove(component);
    }
  }
}
