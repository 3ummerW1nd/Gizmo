package geometry;

import pair.Pair;

public class Point {
  private Pair position;

  public Point(Pair position) {
    this.position = position;
  }

  public Pair getPosition() {
    return position;
  }

  public void setPosition(Pair position) {
    this.position = position;
  }
}
