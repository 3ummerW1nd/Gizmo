package geometry;

public class Line {
  private final Point s;
  private final Point t;

  public Line(Point first, Point second) {
    this.s = first;
    this.t = second;
  }

  public Point getS() {
    return s;
  }

  public Point getT() {
    return t;
  }

}
