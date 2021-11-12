package component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

/**
 * @program: Gizmo
 * @description: 初始化每个组件对应的图片
 * @author: 3ummerW1nd
 * @create: 2021-11-11 10:13
 **/

public class ComponentImages {
  private static Map<ComponentType, List<ImageIcon>> images;

  static {
    images = new HashMap<>();
    List<ImageIcon> ballImages = new ArrayList<>();
    List<ImageIcon> absorberImages = new ArrayList<>();
    List<ImageIcon> triangleImages = new ArrayList<>();
    List<ImageIcon> squareImages = new ArrayList<>();
    List<ImageIcon> curvedRailImages = new ArrayList<>();
    List<ImageIcon> straightRailImages = new ArrayList<>();
    List<ImageIcon> damperImages = new ArrayList<>();
    new ImageIcon("src/main/resources/damper.png");
    List<ImageIcon> circleImages = new ArrayList<>();
    new ImageIcon("src/main/resources/circle0.png");
    for (int i = 0; i < 4; i++) {
      ballImages.add(new ImageIcon("src/main/resources/ball" + i + ".png"));
      absorberImages.add(new ImageIcon("src/main/resources/absorber" + i + ".png"));
      triangleImages.add(new ImageIcon("src/main/resources/triangle" + i + ".png"));
      squareImages.add(new ImageIcon("src/main/resources/rectangle" + i + ".png"));
      curvedRailImages.add(new ImageIcon("src/main/resources/curvedRail" + i + ".png"));
      straightRailImages.add(new ImageIcon("src/main/resources/straightRail" + i + ".png"));
      circleImages.add(new ImageIcon("src/main/resources/circle" + i + ".png"));
    }
    damperImages.add(new ImageIcon("src/main/resources/damper.png"));
    images.put(ComponentType.BALL, ballImages);
    images.put(ComponentType.ABSORBER, absorberImages);
    images.put(ComponentType.TRIANGLE, triangleImages);
    images.put(ComponentType.RECTANGLE, squareImages);
    images.put(ComponentType.CURVED_RAIL, curvedRailImages);
    images.put(ComponentType.STRAIGHT_RAIL, straightRailImages);
    images.put(ComponentType.CIRCLE, circleImages);
    images.put(ComponentType.DAMPER, damperImages);
  }

  public static ImageIcon getAngleImage(ComponentType componentType, int angle) {
    return images.get(componentType).get(angle);
  }

  public static ImageIcon getImage(ComponentType componentType) {
    return images.get(componentType).get(0);
  }
}
