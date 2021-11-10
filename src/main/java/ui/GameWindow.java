package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import static java.awt.event.MouseEvent.BUTTON3;

/**
 * @program: Gizmo
 * @description: 游戏主页面
 * @author: 3ummerW1nd
 * @create: 2021-11-09 17:43
 **/

public class GameWindow extends JFrame {
  private static final int PLAYING_MODEL = 1;
  private static final int SETTING_MODEL = -1;
  private int model = SETTING_MODEL;
  private GamePanel gamePanel;
  private SettingPanel settingPanel;
  public void initGameWindow() {
    setLayout(null);
    setTitle("弹球游戏");
    gamePanel = new GamePanel();
    settingPanel = new SettingPanel();
    add(gamePanel);
    add(settingPanel);
    settingPanel.setLocation(650, 0);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(0, 0, 1000, 680);
    setVisible(true);
    addListeners();
    settingPanel.setModel(SETTING_MODEL);
  }

  private void addListeners() {
    settingPanel.getReadingButton().addActionListener(e
        -> {

        });
    settingPanel.getPlayingModelButton().addActionListener(e -> {
      model = PLAYING_MODEL;
      settingPanel.setModel(PLAYING_MODEL);
    });
    settingPanel.getSettingModelButton().addActionListener(e -> {
      model = SETTING_MODEL;
      settingPanel.setModel(SETTING_MODEL);
    });
    settingPanel.getRemoveButton().addActionListener(e
        -> {

        });
    settingPanel.getRotateButton().addActionListener(e
        -> {

        });
    settingPanel.getZoomInButton().addActionListener(e
        -> {

        });
    settingPanel.getZoomOutButton().addActionListener(e
        -> {

        });
    gamePanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if(model == SETTING_MODEL) {
          if(e.getButton() == BUTTON3) {
            System.out.println("右击");
          } else {
            Box box = gamePanel.checkBox(e.getX(), e.getY());
            gamePanel.putComponent(box, settingPanel.getSelectedComponent());
            System.out.println(box.getX() + " " + box.getY());
            System.out.println(settingPanel.getSelectedComponent());
          }
        }
      }
    });
  }
}
