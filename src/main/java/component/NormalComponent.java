package component;

import point.Point;

public abstract class NormalComponent extends Component {
  public abstract Point checkCollision(Ball ball);
}
