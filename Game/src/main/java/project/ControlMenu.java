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
  private static final Player player1 = Controls.player1;
  private static final Player player2 = Controls.player2;
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
  private char p1upkey;
  private char p1downkey;
  private char p1leftkey;
  private char p1rightkey;
  private char twop2upkey;
  private char twop2downkey;
  private char twop2leftkey;
  private char twop2rightkey;
  private float halfDisplayWidth;
  private static final int BUTTON_WIDTH = 300;
  private static final int BUTTON_HEIGHT = 80;
  private static final int P1_WIDTH_ADJUSTMENT = 610;
  private static final int P2_WIDTH_ADJUSTMENT = 90;
  private static final float ACC_HEIGHT = 200;
  private static final float BRAKE_HEIGHT = 400;
  private static final float LEFT_HEIGHT = 600;
  private static final float RIGHT_HEIGHT = 800;
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
    final SinglePlayer singlePlayer = window.singlePlayer;
    final TwoPlayers twoPlayers = window.twoPlayers;
    if (window.singlePlayer != null) {
      p1upkey = singlePlayer.getUp();
      p1downkey = singlePlayer.getDown();
      p1leftkey = singlePlayer.getLeft();
      p1rightkey = singlePlayer.getRight();
    }
    if (window.twoPlayers != null) {
      twop2upkey = twoPlayers.getP2Up();
      twop2downkey = twoPlayers.getP2Down();
      twop2leftkey = twoPlayers.getP2Left();
      twop2rightkey = twoPlayers.getP2Right();
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
    final float p1fontWidth = halfDisplayWidth - 350;
    final float p2fontWidth = halfDisplayWidth + 350;
    window.textSize(50);
    window.fill(117, 204, 32);
    window.text(p1upkey, p1fontWidth, ACC_HEIGHT);
    window.text(p1downkey, p1fontWidth, BRAKE_HEIGHT);
    window.text(p1leftkey, p1fontWidth, LEFT_HEIGHT);
    window.text(p1rightkey, p1fontWidth, RIGHT_HEIGHT);
    window.fill(72, 194, 249);
    window.text(twop2upkey, p2fontWidth, ACC_HEIGHT);
    window.text(twop2downkey, p2fontWidth, BRAKE_HEIGHT);
    window.text(twop2leftkey, p2fontWidth, LEFT_HEIGHT);
    window.text(twop2rightkey, p2fontWidth, RIGHT_HEIGHT);
  }

  /**
   * Check if the buttons are clicked.
   */
  private void checkButtonClicks() {
    if (soundoff.isLeftClicked()) {
      bgm.stopBgm(false);
      check = true;
      GameManager.audio = true;
    } else if (soundon.isLeftClicked() && check && GameManager.audio) {
      bgm.getBgm(true);
      check = false;
    } else if (p1Go.isLeftClicked()) {
      Controls.setUp(player1, window.inputChar);
    } else if (p1Stop.isLeftClicked()) {
      Controls.setDown(player1, window.inputChar);
    } else if (p1Left.isLeftClicked()) {
      Controls.setLeft(player1, window.inputChar);
    } else if (p1Right.isLeftClicked()) {
      Controls.setRight(player1, window.inputChar);
    } else if (twop2Go.isLeftClicked()) {
      Controls.setUp(player2, window.inputChar);
    } else if (twop2Stop.isLeftClicked()) {
      Controls.setDown(player2, window.inputChar);
    } else if (twop2Left.isLeftClicked()) {
      Controls.setLeft(player2, window.inputChar);
    } else if (twop2Right.isLeftClicked()) {
      Controls.setRight(player2, window.inputChar);
    }
  }

}