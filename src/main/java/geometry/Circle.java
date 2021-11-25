package geometry;

public class Circle {
  private Point center;
  private double radius;

  public Circle() {
    center = new Point();
  }

  public Point getCenter() {
    return center;
  }

  public void setCenter(Point center) {
    this.center = center;
  }

  public double getRadius() {
    return radius;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }
}
