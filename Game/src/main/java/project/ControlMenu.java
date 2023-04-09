package project;

import java.awt.Color;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * The ControlMenu class represents the control menu for the game.
 */
public class ControlMenu {
  private final GameManager window;
  private static ControlMenu instance;
  protected TextBox textBox;
  private PImage background;
  private PImage soundOn;
  private PImage soundOff;
  private PImage p1acc;
  private PImage p1brake;
  private PImage p1left;
  private PImage p1right;
  private PImage p2acc;
  private PImage p2brake;
  private PImage p2left;
  private PImage p2right;
  private PImage setting;
  public Button soundoff;
  public Button soundon;
  public Button p1Go;
  public Button p1Stop;
  public Button p1Left;
  public Button p1Right;
  public Button twop2Go;
  public Button twop2Stop;
  public Button twop2Left;
  public Button twop2Right;
  private float halfDisplayWidth;
  private static final int BUTTON_WIDTH = 300;
  private static final int BUTTON_HEIGHT = 80;
  private static final int P1_WIDTH_ADJUSTMENT = 610;
  private static final int P2_WIDTH_ADJUSTMENT = 90;
  private static final float ACC_HEIGHT = 200;
  private static final float BRAKE_HEIGHT = 400;
  private static final float LEFT_HEIGHT = 600;
  private static final float RIGHT_HEIGHT = 800;
  private static final int KEY_AMOUNT = 4;
  private char[] p1Keys = new char[KEY_AMOUNT];
  private char[] p2Keys = new char[KEY_AMOUNT];
  private final float p1AdjustedWidth;
  private final float p2AdjustedWidth;
  private Bgm bgm;
  private boolean check = true;

  /**
   * Private constructor of ControlMenu class to keep it singleton.
   *
   * @param window the window for current game
   */
  private ControlMenu(GameManager window) {
    this.window = window;
    bgm = Bgm.getInstance();
    halfDisplayWidth = window.displayWidth / 2;
    textBox = new TextBox(new PVector(500, 500), 200, 40, window);
    p1AdjustedWidth = halfDisplayWidth - P1_WIDTH_ADJUSTMENT;
    p2AdjustedWidth = halfDisplayWidth + P2_WIDTH_ADJUSTMENT;
  }

  /**
   * Method that returns the instance of the control menu.
   *
   * @param window the window for current game
   * @return ControlMenu
   */
  public static ControlMenu getInstance(GameManager window) {
    if (instance == null) {
      instance = new ControlMenu(window);
    }
    return instance;
  }

  /**
   * Method that sets up everything for the control menu.
   */
  public void setup() {
    setPlayerKeys();
    loadImages();
    setButtons();
  }

  /**
   * Method that draws the control menu.
   */
  public void draw() {
    window.background(64, 64, 64);
    window.fill(0);
    window.image(background, 0, 0, window.displayWidth, window.displayHeight);
    updateButtons();
    updateFonts();
    updateImages();
    checkButtonClicks();
    textBox.draw();
  }

  /**
   * Sets the buttons for the control menu.
   */
  private void setPlayerKeys() {
    for (int i = 0; i < KEY_AMOUNT; i++) {
      p1Keys[i] = ControlCommandInvoker.getPlayer1Keys(i);
      p2Keys[i] = ControlCommandInvoker.getPlayer2Keys(i);
    }
  }

  /**
   * Gets the image from the path.
   *
   * @param name the name of the image
   * @return PImage the path of the image
   */
  private PImage getImage(String name) {
    return window.loadImage("Game/images/" + name);
  }

  /**
   * Load all images for the control menu.
   */
  private void loadImages() {
    background = getImage("BGImage.png");
    soundOn = getImage("SoundOn.png");
    soundOff = getImage("SoundOff.png");
    p1acc = getImage("Acceleration.png");
    p1brake = getImage("Brake.png");
    p1left = getImage("Left.png");
    p1right = getImage("Right.png");
    p2acc = getImage("p2Acceleration.png");
    p2brake = getImage("p2Brake.png");
    p2left = getImage("p2Left.png");
    p2right = getImage("p2Right.png");
    setting = getImage("Setting.png");
  }

  /**
   * Update the buttons for the control menu.
   */
  private void setButtons() {
    final float soundWidth = (float) (window.displayWidth / 20);
    final float soundHeight = 900;
    final float soundbuttonwidthandheight = 50;
    final float accbuttonpvectory = 190;
    final float brakebuttonpvectory = 390;
    final float leftbuttonpvectory = 590;
    final float rightbuttonpvectory = 790;
    window.textAlign(PApplet.CENTER, PApplet.CENTER);
    soundoff = new Button(new PVector(soundWidth + 100, soundHeight), soundbuttonwidthandheight,
        soundbuttonwidthandheight, "", Color.GREEN, window);
    soundon = new Button(new PVector(soundWidth, soundHeight), soundbuttonwidthandheight,
        soundbuttonwidthandheight, "", Color.GREEN, window);
    p1Go = new Button(new PVector(p1AdjustedWidth, accbuttonpvectory), BUTTON_WIDTH,
        BUTTON_HEIGHT, "", Color.GREEN, window);
    p1Stop = new Button(new PVector(p1AdjustedWidth, brakebuttonpvectory), BUTTON_WIDTH,
        BUTTON_HEIGHT, "", Color.GREEN, window);
    p1Left = new Button(new PVector(p1AdjustedWidth, leftbuttonpvectory), BUTTON_WIDTH,
        BUTTON_HEIGHT, "", Color.GREEN, window);
    p1Right = new Button(new PVector(p1AdjustedWidth, rightbuttonpvectory), BUTTON_WIDTH,
        BUTTON_HEIGHT, "", Color.GREEN, window);
    twop2Go = new Button(new PVector(p2AdjustedWidth, accbuttonpvectory), BUTTON_WIDTH,
        BUTTON_HEIGHT, "", Color.BLUE, window);
    twop2Stop = new Button(new PVector(p2AdjustedWidth, brakebuttonpvectory), BUTTON_WIDTH,
        BUTTON_HEIGHT, "", Color.BLUE, window);
    twop2Left = new Button(new PVector(p2AdjustedWidth, leftbuttonpvectory), BUTTON_WIDTH,
        BUTTON_HEIGHT, "", Color.BLUE, window);
    twop2Right = new Button(new PVector(p2AdjustedWidth, rightbuttonpvectory), BUTTON_WIDTH,
        BUTTON_HEIGHT, "", Color.BLUE, window);
  }


  /**
   * Update the buttons for the control menu.
   */
  private void updateButtons() {
    Button[] buttons = {soundoff, soundon, p1Go, p1Stop, p1Left,
        p1Right, twop2Go, twop2Stop, twop2Left, twop2Right};
    for (Button button : buttons) {
      button.click();
      button.draw();
    }
  }

  /**
   * Update the images for the control menu.
   */
  private void updateImages() {
    final float p1imageWidth = halfDisplayWidth - 600;
    final float p2imageWidth = halfDisplayWidth + 100;
    final float soundWidth = (float) (window.displayWidth / 20);
    final float soundHeight = 900;
    window.image(setting, halfDisplayWidth - 150, (float) window.displayHeight / 20);
    window.image(soundOn, soundWidth, soundHeight);
    window.image(soundOff, soundWidth + 100, soundHeight);
    window.image(p1acc, p1imageWidth, ACC_HEIGHT);
    window.image(p1brake, p1imageWidth, BRAKE_HEIGHT);
    window.image(p1left, p1imageWidth, LEFT_HEIGHT);
    window.image(p1right, p1imageWidth, RIGHT_HEIGHT);
    window.image(p2acc, p2imageWidth, ACC_HEIGHT);
    window.image(p2brake, p2imageWidth, BRAKE_HEIGHT);
    window.image(p2left, p2imageWidth, LEFT_HEIGHT);
    window.image(p2right, p2imageWidth, RIGHT_HEIGHT);
  }

  /**
   * Update the fonts for the control menu.
   */
  private void updateFonts() {
    float x = halfDisplayWidth - 350;
    float y = 200;
    window.textSize(50);
    window.fill(117, 204, 32);
    for (int i = 0; i < KEY_AMOUNT; i++) {
      window.text(p1Keys[i], x, y);
      y += 200;
    }
    x = halfDisplayWidth + 350;
    y = 200;
    window.fill(72, 194, 249);
    for (int i = 0; i < KEY_AMOUNT; i++) {
      window.text(p2Keys[i], x, y);
      y += 200;
    }
  }

  /**
   * Check if the buttons are clicked.
   */
  private void checkButtonClicks() {
    if (soundoff.isLeftClicked()) {
      bgm.stopBgm(false);
      check = true;
      GameManager.audio = true;
    }
    if (soundon.isLeftClicked() && check && GameManager.audio) {
      bgm.getBgm(true);
      check = false;
    }
    if (p1Go.isLeftClicked()) {
      ControlCommandInvoker.setPlayer1Keys(0, window.inputChar);
    }
    if (p1Stop.isLeftClicked()) {
      ControlCommandInvoker.setPlayer1Keys(1, window.inputChar);
    }
    if (p1Left.isLeftClicked()) {
      ControlCommandInvoker.setPlayer1Keys(2, window.inputChar);
    }
    if (p1Right.isLeftClicked()) {
      ControlCommandInvoker.setPlayer1Keys(3, window.inputChar);
    }
    if (twop2Go.isLeftClicked()) {
      ControlCommandInvoker.setPlayer2Keys(0, window.inputChar);
    }
    if (twop2Stop.isLeftClicked()) {
      ControlCommandInvoker.setPlayer2Keys(1, window.inputChar);
    }
    if (twop2Left.isLeftClicked()) {
      ControlCommandInvoker.setPlayer2Keys(2, window.inputChar);
    }
    if (twop2Right.isLeftClicked()) {
      ControlCommandInvoker.setPlayer2Keys(3, window.inputChar);
    }
  }

}