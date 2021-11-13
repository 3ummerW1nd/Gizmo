package ui;

import utils.ComponentImages;
import utils.ComponentType;
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
  private JButton savingButton;
  private JButton readingButton;
  private JButton rotateButton;
  private JButton removeButton;
  private JButton zoomOutButton;
  private JButton zoomInButton;
  private JRadioButton ballRadioButton;
  private JRadioButton absorberRadioButton;
  private JRadioButton rectangleRadioButton;
  private JRadioButton leftDamperRadioButton;
  private JRadioButton curvedRailRadioButton;
  private JRadioButton triangleRadioButton;
  private JRadioButton circleRadioButton;
  private JRadioButton straightRailRadioButton;
  private JRadioButton selectRadioButton;
  private JRadioButton rightDamperRadioButton;
  private JLabel playingModelLabel;
  private JLabel settingModelLabel;

  public SettingPanel() {
    setLayout(null);
    initButtons();
    initRadioButtons();
    initLabels();
    add(settingModelLabel);
    add(playingModelLabel);
    setSize(350, 680);
  }

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

  public JButton getSavingButton() {
    return savingButton;
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
      savingButton.setEnabled(false);
    } else {
      playingModelLabel.setVisible(false);
      settingModelLabel.setVisible(true);
      readingButton.setEnabled(true);
      zoomOutButton.setEnabled(true);
      zoomInButton.setEnabled(true);
      removeButton.setEnabled(true);
      rotateButton.setEnabled(true);
      savingButton.setEnabled(true);
    }
  }

  private void initLabels() {
    JLabel ballLabel = new JLabel(ComponentImages.getImage(ComponentType.BALL));
    JLabel absorberLabel = new JLabel(ComponentImages.getImage(ComponentType.ABSORBER));
    JLabel rectangleLabel = new JLabel(ComponentImages.getImage(ComponentType.RECTANGLE));
    JLabel circleLabel = new JLabel(ComponentImages.getImage(ComponentType.CIRCLE));
    JLabel triangleLabel = new JLabel(ComponentImages.getImage(ComponentType.TRIANGLE));
    JLabel leftDamperLabel = new JLabel(ComponentImages.getImage(ComponentType.LEFT_DAMPER));
    JLabel straightRailLabel = new JLabel(ComponentImages.getImage(ComponentType.STRAIGHT_RAIL));
    JLabel curvedRailLabel = new JLabel(ComponentImages.getImage(ComponentType.CURVED_RAIL));
    JLabel rightDamperLabel = new JLabel(ComponentImages.getImage(ComponentType.LEFT_DAMPER));
    JLabel selectLabel = new JLabel(new ImageIcon("src/main/resources/select.png"));
    settingModelLabel = new JLabel(new ImageIcon("src/main/resources/setting.png"));
    settingModelLabel.setSize(60, 60);
    settingModelLabel.setLocation(145, 580);
    playingModelLabel = new JLabel(new ImageIcon("src/main/resources/playing.png"));
    playingModelLabel.setSize(60, 60);
    playingModelLabel.setLocation(145, 580);
    ballLabel.setSize(48, 48);
    ballLabel.setLocation(70, 20);
    absorberLabel.setSize(48, 48);
    absorberLabel.setLocation(230, 20);
    rectangleLabel.setSize(40, 40);
    rectangleLabel.setLocation(73, 122);
    circleLabel.setSize(40, 40);
    circleLabel.setLocation(233, 120);
    triangleLabel.setSize(45, 45);
    triangleLabel.setLocation(68, 170);
    selectLabel.setSize(50, 50);
    selectLabel.setLocation(230, 170);
    leftDamperLabel.setSize(50, 50);
    leftDamperLabel.setLocation(70, 220);
    rightDamperLabel.setSize(50, 50);
    rightDamperLabel.setLocation(230, 220);
    straightRailLabel.setSize(50, 50);
    straightRailLabel.setLocation(70, 70);
    curvedRailLabel.setSize(50, 50);
    curvedRailLabel.setLocation(230, 70);
    add(ballLabel);
    add(absorberLabel);
    add(rectangleLabel);
    add(circleLabel);
    add(triangleLabel);
    add(leftDamperLabel);
    add(straightRailLabel);
    add(curvedRailLabel);
    add(rightDamperLabel);
    add(selectLabel);
  }

  public ComponentType getSelectedComponent() {
    if (allRadioButtons.getSelection() == null)
      return ComponentType.NONE;
    return ComponentType.getComponentType(allRadioButtons.getSelection().getActionCommand());
  }

  private void initRadioButtons() {
    allRadioButtons = new ButtonGroup();
    ballRadioButton = new JRadioButton();
    ballRadioButton.setActionCommand(String.valueOf(ComponentType.BALL));
    absorberRadioButton = new JRadioButton();
    absorberRadioButton.setActionCommand(String.valueOf(ComponentType.ABSORBER));
    rectangleRadioButton = new JRadioButton();
    rectangleRadioButton.setActionCommand(String.valueOf(ComponentType.RECTANGLE));
    curvedRailRadioButton = new JRadioButton();
    curvedRailRadioButton.setActionCommand(String.valueOf(ComponentType.CURVED_RAIL));
    leftDamperRadioButton = new JRadioButton();
    leftDamperRadioButton.setActionCommand(String.valueOf(ComponentType.LEFT_DAMPER));
    rightDamperRadioButton = new JRadioButton();
    rightDamperRadioButton.setActionCommand(String.valueOf(ComponentType.RIGHT_DAMPER));
    triangleRadioButton = new JRadioButton();
    triangleRadioButton.setActionCommand(String.valueOf(ComponentType.TRIANGLE));
    circleRadioButton = new JRadioButton();
    circleRadioButton.setActionCommand(String.valueOf(ComponentType.CIRCLE));
    straightRailRadioButton = new JRadioButton();
    straightRailRadioButton.setActionCommand(String.valueOf(ComponentType.STRAIGHT_RAIL));
    selectRadioButton = new JRadioButton();
    selectRadioButton.setActionCommand(String.valueOf(ComponentType.SELECT));
    allRadioButtons.add(ballRadioButton);
    allRadioButtons.add(absorberRadioButton);
    allRadioButtons.add(rectangleRadioButton);
    allRadioButtons.add(curvedRailRadioButton);
    allRadioButtons.add(leftDamperRadioButton);
    allRadioButtons.add(rightDamperRadioButton);
    allRadioButtons.add(triangleRadioButton);
    allRadioButtons.add(circleRadioButton);
    allRadioButtons.add(straightRailRadioButton);
    allRadioButtons.add(selectRadioButton);
    ballRadioButton.setSize(50, 50);
    ballRadioButton.setLocation(30, 20);
    absorberRadioButton.setSize(50, 50);
    absorberRadioButton.setLocation(190, 20);
    rectangleRadioButton.setSize(50, 50);
    rectangleRadioButton.setLocation(30, 120);
    curvedRailRadioButton.setSize(50, 50);
    curvedRailRadioButton.setLocation(190, 70);
    leftDamperRadioButton.setSize(50, 50);
    leftDamperRadioButton.setLocation(30, 220);
    rightDamperRadioButton.setSize(50, 50);
    rightDamperRadioButton.setLocation(190, 220);
    triangleRadioButton.setSize(50, 50);
    triangleRadioButton.setLocation(30, 170);
    selectRadioButton.setSize(50, 50);
    selectRadioButton.setLocation(190, 170);
    circleRadioButton.setSize(50, 50);
    circleRadioButton.setLocation(190, 120);
    straightRailRadioButton.setSize(50, 50);
    straightRailRadioButton.setLocation(30, 70);
    add(ballRadioButton);
    add(absorberRadioButton);
    add(curvedRailRadioButton);
    add(leftDamperRadioButton);
    add(rightDamperRadioButton);
    add(rectangleRadioButton);
    add(triangleRadioButton);
    add(circleRadioButton);
    add(straightRailRadioButton);
    add(selectRadioButton);
  }

  private void initButtons() {
    settingModelButton = new TextButton("布局模式", 30, 450, 290, 60);
    playingModelButton = new TextButton("游玩模式", 30, 510, 290, 60);
    readingButton = new TextButton("读取地图", 30, 270, 140, 60);
    savingButton = new TextButton("保存地图", 180, 270, 140, 60);
    rotateButton = new IconButton(new ImageIcon("src/main/resources/rotate.png"), 30, 390, 140, 60);
    removeButton =
        new IconButton(new ImageIcon("src/main/resources/remove.png"), 180, 390, 140, 60);
    zoomOutButton =
        new IconButton(new ImageIcon("src/main/resources/zoomOut.png"), 180, 330, 140, 60);
    zoomInButton = new IconButton(new ImageIcon("src/main/resources/zoomIn.png"), 30, 330, 140, 60);
    add(settingModelButton);
    add(playingModelButton);
    add(rotateButton);
    add(removeButton);
    add(zoomOutButton);
    add(zoomInButton);
    add(readingButton);
    add(savingButton);
  }
}
