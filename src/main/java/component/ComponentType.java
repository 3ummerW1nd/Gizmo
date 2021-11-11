package component;

public enum ComponentType {
  BALL,
  ABSORBER,
  STRAIGHT_RAIL,
  CURVED_RAIL,
  TRIANGLE,
  RECTANGLE,
  CIRCLE,
  DAMPER,
  NONE,
  SELECT;

  public static ComponentType getComponentType(String string) {
    switch (string) {
      case "BALL":
        return BALL;
      case "ABSORBER":
        return ABSORBER;
      case "STRAIGHT_RAIL":
        return STRAIGHT_RAIL;
      case "CURVED_RAIL":
        return CURVED_RAIL;
      case "TRIANGLE":
        return TRIANGLE;
      case "RECTANGLE":
        return RECTANGLE;
      case "CIRCLE":
        return CIRCLE;
      case "DAMPER":
        return DAMPER;
      case "SELECT":
        return SELECT;
    }
    return NONE;
  }
}
