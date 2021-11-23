package geometry;

import java.util.Objects;

public class Point {
  private double x, y;

  public Point() {
    this.x = 0;
    this.y = 0;
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Point point = (Point) o;
    return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  public double dist() {
    return Math.sqrt(this.x * this.x + this.y * this.y);
  }

  public Point add(Point point) {
    return new Point(this.x + point.getX(), this.y + point.getY());
  }

  public Point minus(Point point) {
    return new Point(this.x - point.getX(), this.y - point.getY());
  }

  public double dot(Point point) {
    return this.x * point.getX() + this.y * point.getY();
  }

  public double det(Point point) {
    return this.x * point.getY() - this.y * point.getX();
  }
}
