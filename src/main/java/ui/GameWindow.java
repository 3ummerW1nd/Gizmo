package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import utils.ComponentType;

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
    settingPanel.getSavingButton().addActionListener(
        e -> { gamePanel.saveGame(showFileOpenDialog(false)); });
    settingPanel.getReadingButton().addActionListener(
        e -> { gamePanel.loadGame(showFileOpenDialog(true)); });
    settingPanel.getPlayingModelButton().addActionListener(e -> {
      model = PLAYING_MODEL;
      settingPanel.setModel(PLAYING_MODEL);
      gamePanel.playGame();
      gamePanel.requestFocus();
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

  private File showFileOpenDialog(boolean read) {
    File file = null;
    int result = 0;
    JFileChooser fileChooser = new JFileChooser();
    // 设置默认显示的文件夹为当前文件夹
    fileChooser.setCurrentDirectory(new File("."));
    // 设置文件选择的模式（只选文件、只选文件夹、文件和文件均可选）
    if (read) {
      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("gizmo(*.gizmo)", "gizmo"));
      fileChooser.setFileFilter(new FileNameExtensionFilter("gizmo(*.gizmo)", "gizmo"));
      fileChooser.setMultiSelectionEnabled(false);
      result = fileChooser.showOpenDialog(this);
    } else {
      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      fileChooser.setSelectedFile(new File("test.gizmo"));
      result = fileChooser.showSaveDialog(this);
    }
    if (result == JFileChooser.APPROVE_OPTION) {
      file = fileChooser.getSelectedFile();
    } else {
      return null;
    }
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return file;
  }
}
