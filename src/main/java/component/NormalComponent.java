package component;

import point.Point;
import ui.Box;

public abstract class NormalComponent extends Component {
  public abstract Point checkCollision(Ball ball);

  @Override
  public void init(Box box) {}

  @Override
  public void rotate() {}

  @Override
  public void zoomIn() {}

  @Override
  public void zoomOut() {}

  @Override
  public void remove() {
    super.remove();
  }
}
