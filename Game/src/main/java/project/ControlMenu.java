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
    window.textAlign(PApplet.CENTER, PApplet.CENTER);

    soundoff = new Button(new PVector((float) (window.displayWidth / 20) + 100, 900), 50,
        50, "", Color.GREEN, window);
    soundon = new Button(new PVector((float) (window.displayWidth / 20), 900), 50,
        50, "", Color.GREEN, window);
    p1Go = new Button(new PVector(p1AdjustedWidth, 190), BUTTON_WIDTH,
        BUTTON_HEIGHT, "", Color.GREEN, window);
    p1Stop = new Button(new PVector(p1AdjustedWidth, 390), BUTTON_WIDTH,
        BUTTON_HEIGHT, "", Color.GREEN, window);
    p1Left = new Button(new PVector(p1AdjustedWidth, 590), BUTTON_WIDTH,
        BUTTON_HEIGHT, "", Color.GREEN, window);
    p1Right = new Button(new PVector(p1AdjustedWidth, 790), BUTTON_WIDTH,
        BUTTON_HEIGHT, "", Color.GREEN, window);
    twop2Go = new Button(new PVector(p2AdjustedWidth, 190), BUTTON_WIDTH,
        BUTTON_HEIGHT, "", Color.BLUE, window);
    twop2Stop = new Button(new PVector(p2AdjustedWidth, 390), BUTTON_WIDTH,
        BUTTON_HEIGHT, "", Color.BLUE, window);
    twop2Left = new Button(new PVector(p2AdjustedWidth, 590), BUTTON_WIDTH,
        BUTTON_HEIGHT, "", Color.BLUE, window);
    twop2Right = new Button(new PVector(p2AdjustedWidth, 790), BUTTON_WIDTH,
        BUTTON_HEIGHT, "", Color.BLUE, window);
  }


  /**
   * Update the buttons for the control menu.
   */
  private void updateButtons() {
    soundoff.click();
    soundoff.draw();
    soundon.draw();
    soundon.click();
    p1Go.click();
    p1Go.draw();
    p1Stop.click();
    p1Stop.draw();
    p1Left.click();
    p1Left.draw();
    p1Right.click();
    p1Right.draw();
    twop2Go.click();
    twop2Go.draw();
    twop2Stop.click();
    twop2Stop.draw();
    twop2Left.click();
    twop2Left.draw();
    twop2Right.click();
    twop2Right.draw();
  }

  /**
   * Update the images for the control menu.
   */
  private void updateImages() {
    window.image(setting, halfDisplayWidth - 150, (float) window.displayHeight / 20);
    window.image(soundOn, (float) (window.displayWidth / 20), 900);
    window.image(soundOff, (float) (window.displayWidth / 20) + 100, 900);
    window.image(p1acc, halfDisplayWidth - 600, 200);
    window.image(p1brake, halfDisplayWidth - 600, 400);
    window.image(p1left, halfDisplayWidth - 600, 600);
    window.image(p1right, halfDisplayWidth - 600, 800);
    window.image(p2acc, halfDisplayWidth + 100, 200);
    window.image(p2brake, halfDisplayWidth + 100, 400);
    window.image(p2left, halfDisplayWidth + 100, 600);
    window.image(p2right, halfDisplayWidth + 100, 800);
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
      bgm.stopBGM(false);
      check = true;
      GameManager.audio = true;
    }
    if (soundon.isLeftClicked() && check && GameManager.audio) {
      bgm.getBGM(true);
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
      ControlCommandInvoker.setPlayer1Keys(0, window.inputChar);
    }
    if (twop2Stop.isLeftClicked()) {
      ControlCommandInvoker.setPlayer1Keys(1, window.inputChar);
    }
    if (twop2Left.isLeftClicked()) {
      ControlCommandInvoker.setPlayer1Keys(2, window.inputChar);
    }
    if (twop2Right.isLeftClicked()) {
      ControlCommandInvoker.setPlayer1Keys(3, window.inputChar);
    }
  }
}