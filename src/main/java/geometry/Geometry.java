package geometry;

public class Geometry {
  public double pointToPointDistance(Point a, Point b) {
    return Math.sqrt(
        Math.pow(Math.abs(a.getX() - b.getX()), 2) + Math.pow(Math.abs(a.getY() - b.getY()), 2));
  }

  private double cross(Point s, Point t, Point o) {
    return s.minus(o).det(t.minus(o));
  }

  public double pointToLineDistance(Point point, Line line) {
    return Math.abs(cross(line.getS(), line.getT(), point)) / line.getT().minus(line.getS()).dist();
  }

  private int sgn(double x) {
    return Math.abs(x) < 1e-10 ? 0 : (x > 0 ? 1 : -1);
  }

  public double pointToSegmentDistance(Point point, Line line) {
    if (line.getS() == line.getT()) {
      return point.minus(line.getS()).dist();
    }
    Point ps = point.minus(line.getS()), pt = point.minus(line.getT()),
          l = line.getT().minus(line.getS());
    if (sgn(l.dot(ps)) < 0) {
      return ps.dist();
    } else if (sgn(l.dot(pt)) > 0) {
      return pt.dist();
    } else {
      return pointToLineDistance(point, line);
    }
  }
}
