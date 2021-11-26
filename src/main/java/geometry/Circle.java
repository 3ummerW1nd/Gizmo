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

  public void zoomIn() {
    this.setRadius(this.getRadius() + 15.0);
    Point center = this.getCenter();
    double x = center.getX(), y = center.getY();
    this.getCenter().setX(x + 15.0);
    this.getCenter().setY(y + 15.0);
  }

  public void zoomOut() {
    this.setRadius(this.getRadius() - 15.0);
    Point center = this.getCenter();
    double x = center.getX(), y = center.getY();
    this.getCenter().setX(x - 15.0);
    this.getCenter().setY(y - 15.0);
  }
}
