package utils;

import component.Ball;
import component.Damper;
import component.NormalComponent;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: Gizmo
 * @description: 生产组件的工厂
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:55
 **/

public class ComponentFactory {
  private static final int maxNumOfDamper = 2;
  private static Ball ball;
  private static List<Damper> damperList = new ArrayList<>();
  private static int countOfDamper = 0;
  static {
    try {
      Class<?> ballClass = Class.forName(Ball.class.getName());
      Class<?> damperClass = Class.forName(Damper.class.getName());
      Constructor<?> ballConstructor = ballClass.getDeclaredConstructor();
      Constructor<?> damperConstructor = damperClass.getDeclaredConstructor();
      ballConstructor.setAccessible(true);
      damperConstructor.setAccessible(true);
      ball = (Ball) ballConstructor.newInstance();
      for (int i = 0; i < maxNumOfDamper; i++) {
        damperList.add((Damper) damperConstructor.newInstance());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static <T extends NormalComponent> T createNormalComponent(Class<T> tClass) {
    NormalComponent component = null;
    try {
      component =
          (NormalComponent) Class.forName(tClass.getName()).getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return (T) component;
  }

  public static Ball getBall() {
    return ball;
  }

  public static Damper getDamper() {
    int tmp = countOfDamper % 2;
    countOfDamper++;
    return damperList.get(tmp);
  }
}
