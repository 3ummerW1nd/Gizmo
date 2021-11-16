package geometry;

public class Point {
  private double x, y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public double dist() {
    return this.x * this.x + this.y * this.y;
  }

  public Point minus(Point point) {
    return new Point(this.x - point.getX(), this.y - point.getY());
  }

  public double det(Point point) {
    return this.x * point.getY() - this.y * point.getX();
  }
}
