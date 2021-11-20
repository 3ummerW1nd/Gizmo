package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.*;
import utils.ComponentType;
import java.util.Timer;

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
    settingPanel.getSavingButton().addActionListener(e
        -> {
            // TODO 保存当前地图的操作
        });
    settingPanel.getReadingButton().addActionListener(e
        -> {
            // TODO 从文件系统中读取地图
        });
    settingPanel.getPlayingModelButton().addActionListener(e -> {
      model = PLAYING_MODEL;
      settingPanel.setModel(PLAYING_MODEL);
      gamePanel.playGame();
    });
    settingPanel.getSettingModelButton().addActionListener(e -> {
      model = SETTING_MODEL;
      settingPanel.setModel(SETTING_MODEL);
      gamePanel.stopGame();
    });
    settingPanel.getRemoveButton().addActionListener(e -> { gamePanel.removeSelectComponent(); });
    settingPanel.getRotateButton().addActionListener(e -> { gamePanel.rotateSelectComponent(); });
    settingPanel.getZoomInButton().addActionListener(e -> { gamePanel.zoomInSelectComponent(); });
    settingPanel.getZoomOutButton().addActionListener(e -> { gamePanel.zoomOutSelectComponent(); });
    gamePanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Map.Entry<Integer, Integer> box = gamePanel.checkBox(e.getX(), e.getY());
        if (model == SETTING_MODEL) {
          ComponentType tmp = settingPanel.getSelectedComponent();

          if (tmp == ComponentType.SELECT) {
            gamePanel.setSelectedComponent(box);
          } else if (tmp != ComponentType.NONE) {
            gamePanel.putComponent(box, tmp);
            System.out.println(box.getKey() + " " + box.getValue());
            System.out.println(settingPanel.getSelectedComponent());
          }
        }
      }
    });
  }
}
