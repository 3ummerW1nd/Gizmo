package utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @program: Gizmo
 * @description: 初始化每个组件对应的图片
 * @author: 3ummerW1nd
 * @create: 2021-11-11 10:13
 **/

public class ComponentImages {
  private static Map<ComponentType, List<Image>> images;
  static {
    images = new HashMap<>();
    List<Image> ballImages = new ArrayList<>();
    List<Image> absorberImages = new ArrayList<>();
    List<Image> triangleImages = new ArrayList<>();
    List<Image> squareImages = new ArrayList<>();
    List<Image> curvedRailImages = new ArrayList<>();
    List<Image> straightRailImages = new ArrayList<>();
    List<Image> damperImages = new ArrayList<>();
    List<Image> circleImages = new ArrayList<>();
    try {
      for (int i = 0; i < 4; i++) {
        ballImages.add(ImageIO.read(new File("src/main/resources/ball" + i + ".png")));
        absorberImages.add(ImageIO.read(new File("src/main/resources/absorber" + i + ".png")));
        triangleImages.add(ImageIO.read(new File("src/main/resources/triangle" + i + ".png")));
        squareImages.add(ImageIO.read(new File("src/main/resources/rectangle" + i + ".png")));
        curvedRailImages.add(ImageIO.read(new File("src/main/resources/curvedRail" + i + ".png")));
        straightRailImages.add(
            ImageIO.read(new File("src/main/resources/straightRail" + i + ".png")));
        circleImages.add(ImageIO.read(new File("src/main/resources/circle" + i + ".png")));
      }
      damperImages.add(ImageIO.read(new File("src/main/resources/damper.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
    images.put(ComponentType.BALL, ballImages);
    images.put(ComponentType.ABSORBER, absorberImages);
    images.put(ComponentType.TRIANGLE, triangleImages);
    images.put(ComponentType.RECTANGLE, squareImages);
    images.put(ComponentType.CURVED_RAIL, curvedRailImages);
    images.put(ComponentType.STRAIGHT_RAIL, straightRailImages);
    images.put(ComponentType.CIRCLE, circleImages);
    images.put(ComponentType.LEFT_DAMPER, damperImages);
  }

  public static ImageIcon getAngleImage(ComponentType componentType, int angle, int size) {
    if(componentType == ComponentType.RIGHT_DAMPER || componentType == ComponentType.LEFT_DAMPER) {
      Image image = images.get(ComponentType.LEFT_DAMPER)
              .get(angle)
              .getScaledInstance(size * 80, size * 30, Image.SCALE_SMOOTH);
      return new ImageIcon(image);
    }
    Image image = images.get(componentType)
                      .get(angle)
                      .getScaledInstance(size * 30, size * 30, Image.SCALE_SMOOTH);
    return new ImageIcon(image);
  }

  public static ImageIcon getImage(ComponentType componentType) {
    if(componentType == ComponentType.RIGHT_DAMPER || componentType == ComponentType.LEFT_DAMPER) {
      Image image = images.get(ComponentType.LEFT_DAMPER)
              .get(0)
              .getScaledInstance(80, 30, Image.SCALE_SMOOTH);
      return new ImageIcon(image);
    }
    Image image = images.get(componentType).get(0).getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    return new ImageIcon(image);
  }
}
