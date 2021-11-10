package ui.button;

import javax.swing.*;

/**
 * @program: Gizmo
 * @description: 含有文字的图片
 * @author: 3ummerW1nd
 * @create: 2021-11-09 21:36
 **/

public class TextButton extends JButton {
  public TextButton(String text, int x, int y, int width, int height) {
    super(text);
    setSize(width, height);
    setLocation(x, y);
  }
}
