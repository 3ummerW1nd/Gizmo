import javax.swing.*;

import ui.GameWindow;

/**
 * @program: Gizmo
 * @description: 程序入口
 * @author: 3ummerW1nd
 * @create: 2021-10-31 20:00
 **/

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        GameWindow gameWindow = new GameWindow();
        gameWindow.initGameWindow();
      }
    });
  }
}
