package geometry;

public class Line {
  private Point s, t;

  public Line(Point first, Point second) {
    this.s = first;
    this.t = second;
  }

  public Point getS() {
    return s;
  }

  public void setS(Point s) {
    this.s = s;
  }

  public Point getT() {
    return t;
  }

  public void setT(Point t) {
    this.t = t;
  }
}
