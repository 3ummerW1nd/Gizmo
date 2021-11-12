package component;

import pair.Pair;

public abstract class NormalComponent extends Component {
  public abstract Pair checkCollision(Ball ball);
}
