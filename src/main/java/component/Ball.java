package component;

import geometry.Circle;
import geometry.Point;
import java.util.Map;
import javax.swing.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import utils.ComponentImages;
import utils.ComponentType;

/**
 * @program: Gizmo
 * @description: 小球
 * @author: 3ummerW1nd
 * @create: 2021-10-31 19:36
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class Ball extends Component {
  private Point velocity;
  private Point acceleration;
  private Circle circle;

  private Ball() {
    super();
    circle = new Circle();
    setType(ComponentType.BALL);
    setLabel(new JLabel(ComponentImages.getImage(getType())));
  }

  public void move() {
    double velocityX = velocity.getX(), velocityY = velocity.getY();
    double accelerationX = acceleration.getX(), accelerationY = acceleration.getY();
    double x = velocityX * 10 + 50 * accelerationX;
    double y = velocityY * 10 + 50 * accelerationY;
    Point center = circle.getCenter();
    double centerX = center.getX(), centerY = center.getY();
    circle.getCenter().setX(centerX + x);
    circle.getCenter().setY(centerY + y);
    getLabel().setLocation((int) (circle.getCenter().getX() - circle.getRadius()) + 30,
        (int) ((circle.getCenter().getY() - circle.getRadius())) + 30);
    velocity.setX(velocityX + 10 * accelerationX);
    velocity.setY(velocityY + 10 * accelerationY);
  }

  @Override
  public void init(Map.Entry<Integer, Integer> box) {
    super.init(box);
    velocity = new Point(0, 0);
    acceleration = new Point();
    acceleration.setY(0.00125);
    circle.setCenter(new Point(box.getKey() * 30 + 15, box.getValue() * 30 + 15));
    circle.setRadius(15);
  }

  @Override
  public void zoomIn(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.zoomIn(locations);
    circle.zoomIn();
  }

  @Override
  public void zoomOut(Map<Map.Entry<Integer, Integer>, Component> locations) {
    super.zoomOut(locations);
    circle.zoomOut();
  }

  public void stop() {
    setVelocity(new Point(0, 0));
    setAcceleration(new Point(0, 0));
  }
}
