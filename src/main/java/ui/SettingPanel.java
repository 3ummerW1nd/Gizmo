package ui;

import javax.swing.*;
import ui.button.IconButton;
import ui.button.TextButton;

/**
 * @program: Gizmo
 * @description: 游戏操作的场所
 * @author: 3ummerW1nd
 * @create: 2021-11-09 17:49
 **/

public class SettingPanel extends JPanel {
  private ButtonGroup allRadioButtons;
  private JButton settingModelButton;
  private JButton playingModelButton;
  private JButton readingButton;
  private JButton rotateButton;
  private JButton removeButton;
  private JButton zoomOutButton;
  private JButton zoomInButton;
  private JRadioButton ballRadioButton;
  private JRadioButton absorberRadioButton;
  private JRadioButton rectangleRadioButton;
  private JRadioButton damperRadioButton;
  private JRadioButton curvedRailRadioButton;
  private JRadioButton triangleRadioButton;
  private JRadioButton circleRadioButton;
  private JRadioButton straightRailRadioButton;
  private JLabel playingModelLabel;
  private JLabel settingModelLabel;

  public ButtonGroup getAllRadioButtons() {
    return allRadioButtons;
  }

  public JButton getSettingModelButton() {
    return settingModelButton;
  }

  public JButton getPlayingModelButton() {
    return playingModelButton;
  }

  public JButton getReadingButton() {
    return readingButton;
  }

  public JButton getRotateButton() {
    return rotateButton;
  }

  public JButton getRemoveButton() {
    return removeButton;
  }

  public JButton getZoomOutButton() {
    return zoomOutButton;
  }

  public JButton getZoomInButton() {
    return zoomInButton;
  }

  public void setModel(int model) {
    if (model == 1) {
      playingModelLabel.setVisible(true);
      settingModelLabel.setVisible(false);
      readingButton.setEnabled(false);
      zoomOutButton.setEnabled(false);
      zoomInButton.setEnabled(false);
      removeButton.setEnabled(false);
      rotateButton.setEnabled(false);
    } else {
      playingModelLabel.setVisible(false);
      settingModelLabel.setVisible(true);
      readingButton.setEnabled(true);
      zoomOutButton.setEnabled(true);
      zoomInButton.setEnabled(true);
      removeButton.setEnabled(true);
      rotateButton.setEnabled(true);
    }
  }

  public SettingPanel() {
    setLayout(null);
    initButtons();
    setSize(350, 680);
  }

  public ComponentType getSelectedComponent() {
    if (allRadioButtons.getSelection() == null)
      return ComponentType.NONE;
    return ComponentType.getComponentType(allRadioButtons.getSelection().getActionCommand());
  }

  private void initButtons() {
    allRadioButtons = new ButtonGroup();
    settingModelButton = new TextButton("布局模式", 30, 440, 290, 60);
    playingModelButton = new TextButton("游玩模式", 30, 510, 290, 60);
    readingButton = new TextButton("读取地图", 30, 230, 290, 60);
    rotateButton = new IconButton(new ImageIcon("src/main/resources/rotate.png"), 30, 370, 130, 60);
    removeButton =
        new IconButton(new ImageIcon("src/main/resources/remove.png"), 190, 370, 130, 60);
    zoomOutButton =
        new IconButton(new ImageIcon("src/main/resources/zoomOut.png"), 190, 300, 130, 60);
    zoomInButton = new IconButton(new ImageIcon("src/main/resources/zoomIn.png"), 30, 300, 130, 60);
    JLabel ballLabel = new JLabel(new ImageIcon("src/main/resources/ball.png"));
    JLabel absorberLabel = new JLabel(new ImageIcon("src/main/resources/absorber.png"));
    JLabel rectangleLabel = new JLabel(new ImageIcon("src/main/resources/rectangle.png"));
    JLabel circleLabel = new JLabel(new ImageIcon("src/main/resources/circle.png"));
    JLabel triangleLabel = new JLabel(new ImageIcon("src/main/resources/triangle.png"));
    JLabel damperLabel = new JLabel(new ImageIcon("src/main/resources/damper.png"));
    JLabel straightRailLabel = new JLabel(new ImageIcon("src/main/resources/straightRail.png"));
    JLabel curvedRailLabel = new JLabel(new ImageIcon("src/main/resources/curvedRail.png"));
    settingModelLabel = new JLabel(new ImageIcon("src/main/resources/setting.png"));
    settingModelLabel.setSize(60, 60);
    settingModelLabel.setLocation(145, 580);
    playingModelLabel = new JLabel(new ImageIcon("src/main/resources/playing.png"));
    playingModelLabel.setSize(60, 60);
    playingModelLabel.setLocation(145, 580);
    ballRadioButton = new JRadioButton();
    ballRadioButton.setActionCommand(String.valueOf(ComponentType.BALL));
    absorberRadioButton = new JRadioButton();
    absorberRadioButton.setActionCommand(String.valueOf(ComponentType.ABSORBER));
    rectangleRadioButton = new JRadioButton();
    rectangleRadioButton.setActionCommand(String.valueOf(ComponentType.RECTANGLE));
    curvedRailRadioButton = new JRadioButton();
    curvedRailRadioButton.setActionCommand(String.valueOf(ComponentType.CURVED_RAIL));
    damperRadioButton = new JRadioButton();
    damperRadioButton.setActionCommand(String.valueOf(ComponentType.DAMPER));
    triangleRadioButton = new JRadioButton();
    triangleRadioButton.setActionCommand(String.valueOf(ComponentType.TRIANGLE));
    circleRadioButton = new JRadioButton();
    circleRadioButton.setActionCommand(String.valueOf(ComponentType.CIRCLE));
    straightRailRadioButton = new JRadioButton();
    straightRailRadioButton.setActionCommand(String.valueOf(ComponentType.STRAIGHT_RAIL));
    allRadioButtons.add(ballRadioButton);
    allRadioButtons.add(absorberRadioButton);
    allRadioButtons.add(rectangleRadioButton);
    allRadioButtons.add(curvedRailRadioButton);
    allRadioButtons.add(damperRadioButton);
    allRadioButtons.add(triangleRadioButton);
    allRadioButtons.add(circleRadioButton);
    allRadioButtons.add(straightRailRadioButton);
    ballRadioButton.setSize(50, 50);
    ballRadioButton.setLocation(30, 30);
    absorberRadioButton.setSize(50, 50);
    absorberRadioButton.setLocation(190, 30);
    rectangleRadioButton.setSize(50, 50);
    rectangleRadioButton.setLocation(30, 130);
    curvedRailRadioButton.setSize(50, 50);
    curvedRailRadioButton.setLocation(190, 80);
    damperRadioButton.setSize(50, 50);
    damperRadioButton.setLocation(190, 180);
    triangleRadioButton.setSize(50, 50);
    triangleRadioButton.setLocation(30, 180);
    circleRadioButton.setSize(50, 50);
    circleRadioButton.setLocation(190, 130);
    straightRailRadioButton.setSize(50, 50);
    straightRailRadioButton.setLocation(30, 80);
    ballLabel.setSize(48, 48);
    ballLabel.setLocation(70, 30);
    absorberLabel.setSize(48, 48);
    absorberLabel.setLocation(230, 30);
    rectangleLabel.setSize(40, 40);
    rectangleLabel.setLocation(73, 130);
    circleLabel.setSize(40, 40);
    circleLabel.setLocation(233, 130);
    triangleLabel.setSize(50, 50);
    triangleLabel.setLocation(68, 180);
    damperLabel.setSize(50, 50);
    damperLabel.setLocation(230, 180);
    straightRailLabel.setSize(50, 50);
    straightRailLabel.setLocation(70, 80);
    curvedRailLabel.setSize(50, 50);
    curvedRailLabel.setLocation(230, 80);
    add(ballLabel);
    add(absorberLabel);
    add(rectangleLabel);
    add(circleLabel);
    add(triangleLabel);
    add(damperLabel);
    add(straightRailLabel);
    add(curvedRailLabel);
    add(ballRadioButton);
    add(settingModelButton);
    add(playingModelButton);
    add(rotateButton);
    add(removeButton);
    add(zoomOutButton);
    add(zoomInButton);
    add(absorberRadioButton);
    add(curvedRailRadioButton);
    add(damperRadioButton);
    add(rectangleRadioButton);
    add(triangleRadioButton);
    add(circleRadioButton);
    add(straightRailRadioButton);
    add(readingButton);
    add(settingModelLabel);
    add(playingModelLabel);
  }
}
