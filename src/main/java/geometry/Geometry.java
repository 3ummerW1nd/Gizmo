package geometry;

public class Geometry {
  public double pointToPoint(Point a, Point b) {
    return Math.sqrt(
        Math.pow(Math.abs(a.getX() - b.getX()), 2) + Math.pow(Math.abs(a.getY() - b.getY()), 2));
  }

  private double cross(Point s, Point t, Point o) {
    return s.minus(o).det(t.minus(o));
  }

  public double pointToLine(Point point, Line line) {
    return Math.abs(cross(line.getS(), line.getT(), point)) / line.getT().minus(line.getS()).dist();
  }
}
