package component;

import java.util.Map;
import point.Point;

public abstract class NormalComponent extends Component {
  public abstract Point checkCollision(Ball ball);
}
