package ui.button;

import javax.swing.*;

/**
 * @program: Gizmo
 * @description: 含有图片的按钮
 * @author: 3ummerW1nd
 * @create: 2021-11-09 21:30
 **/

public class IconButton extends JButton {
  public IconButton(Icon icon, int x, int y, int width, int height) {
    super(icon);
    setSize(width, height);
    setLocation(x, y);
  }
}
