package project;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

public class ControlMenu {
  private final GameManager window;
  private static ControlMenu instance;
  private TextBox textBox;
  private PImage background;
  private PImage soundOn;
  private PImage soundOff;
  private PImage p1acc, p1brake, p1left, p1right;
  private PImage p2acc, p2brake, p2left, p2right;
  private PImage setting;
  public Button soundoff;
  public Button soundon;
  public Button p1Go;
  public Button p1Stop;
  public Button p1Left;
  public Button p1Right;
  public Button Twop2Go;
  public Button Twop2Stop;
  public Button Twop2Left;
  public Button Twop2Right;
  public Button Twop1Go;
  public Button Twop1Stop;
  public Button Twop1Left;
  public Button Twop1Right;
  private boolean check = true;
  private char p1upkey;
  private char p1downkey;
  private char p1leftkey;
  private char p1rightkey;
  private char twop2upkey;
  private char twop2downkey;
  private char twop2leftkey;
  private char twop2rightkey;
//  private char twop1upkey;
//  private char twop1downkey;
//  private char twop1leftkey;
//  private char twop1rightkey;
  private ControlMenu(GameManager window) {
    this.window = window;
  }

  public static ControlMenu getInstance(GameManager window) {
    if (instance == null) {
      instance = new ControlMenu(window);
    }
    return instance;
  }

  public void setup() {
    if (window.singlePlayer != null) {
      p1upkey = window.singlePlayer.getUp();
      p1downkey = window.singlePlayer.getDown();
      p1leftkey = window.singlePlayer.getLeft();
      p1rightkey = window.singlePlayer.getRight();
    }
    if (window.twoPlayers != null) {
//      twop1upkey = window.twoPlayers.getP1Up();
//      twop1downkey = window.twoPlayers.getP1Down();
//      twop1leftkey = window.twoPlayers.getP1Left();
//      twop1rightkey = window.twoPlayers.getP1Right();
      twop2upkey = window.twoPlayers.getP2Up();
      twop2downkey = window.twoPlayers.getP2Down();
      twop2leftkey = window.twoPlayers.getP2Left();
      twop2rightkey = window.twoPlayers.getP2Right();
    }
    background = window.loadImage("Game/images/BGImage.png");
    soundOn = window.loadImage("Game/images/SoundOn.png");
    soundOff = window.loadImage("Game/images/SoundOff.png");
    p1acc = window.loadImage("Game/images/Acceleration.png");
    p1brake = window.loadImage("Game/images/Brake.png");
    p1left = window.loadImage("Game/images/Left.png");
    p1right = window.loadImage("Game/images/Right.png");
    p2acc = window.loadImage("Game/images/p2Acceleration.png");
    p2brake = window.loadImage("Game/images/p2Brake.png");
    p2left = window.loadImage("Game/images/p2Left.png");
    p2right = window.loadImage("Game/images/p2Right.png");
    setting = window.loadImage("Game/images/Setting.png");
    textBox = TextBox.getInstance(window);
    window.textAlign(PApplet.CENTER, PApplet.CENTER);
    soundoff = new Button(new PVector((float) (window.displayWidth / 20) + 100, 900), 50,
        50, "", new Color(0, 150, 0), window);
    soundon = new Button(new PVector((float) (window.displayWidth / 20), 900), 50,
        50, "", new Color(0, 150, 0), window);
    p1Go = new Button(new PVector((float) (window.displayWidth / 2) - 610, 190), 300,
        80, "", new Color(0, 150, 0), window);
    p1Stop = new Button(new PVector((float) (window.displayWidth / 2) - 610, 390), 300,
        80, "", new Color(0, 150, 0), window);
    p1Left = new Button(new PVector((float) (window.displayWidth / 2) - 610, 590), 300,
        80, "", new Color(0, 150, 0), window);
    p1Right = new Button(new PVector((float) (window.displayWidth / 2) - 610, 790), 300,
        80, "", new Color(0, 150, 0), window);
    Twop2Go = new Button(new PVector((float) (window.displayWidth / 2) + 90, 190), 300,
        80, "", new Color(0, 0, 150), window);
    Twop2Stop = new Button(new PVector((float) (window.displayWidth / 2) + 90, 390), 300,
        80, "", new Color(0, 0, 150), window);
    Twop2Left = new Button(new PVector((float) (window.displayWidth / 2) + 90, 590), 300,
        80, "", new Color(0, 0, 150), window);
    Twop2Right = new Button(new PVector((float) (window.displayWidth / 2) + 90, 790), 300,
        80, "", new Color(0, 0, 150), window);
//    Twop1Go = new Button(new PVector((float) (window.displayWidth / 2) + 400, 200), 225,
//        50, "ACELER: " + twop2upkey, new Color(200, 50, 50), window);
//    Twop1Stop = new Button(new PVector((float) (window.displayWidth / 2) + 400, 400), 225,
//        50, "BRAKE: " + twop2downkey, new Color(200, 50, 50), window);
//    Twop1Left = new Button(new PVector((float) (window.displayWidth / 2) + 400, 600), 225,
//        50, "LEFT: " + twop2leftkey, new Color(200, 50, 50), window);
//    Twop1Right = new Button(new PVector((float) (window.displayWidth / 2) + 400, 800), 225,
//        50, "RIGHT: " + twop2rightkey, new Color(200, 50, 50), window);
  }

  public void draw() {
    window.background(64, 64, 64);
    window.fill(0);
    window.image(background, 0, 0, window.displayWidth, window.displayHeight);
    window.fill(0);
    window.image(setting, (float) window.displayWidth / 2 - 150, (float) window.displayHeight / 20);

//    window.text("KEY SETTING", (float) window.displayWidth / 2 + 10, (float) window.displayHeight / 20);
    soundoff.update();
    soundoff.draw();
    soundon.draw();
    soundon.update();
    p1Go.update();
    p1Go.draw();
    p1Stop.update();
    p1Stop.draw();
    p1Left.update();
    p1Left.draw();
    p1Right.update();
    p1Right.draw();
    Twop2Go.update();
    Twop2Go.draw();
    Twop2Stop.update();
    Twop2Stop.draw();
    Twop2Left.update();
    Twop2Left.draw();
    Twop2Right.update();
    Twop2Right.draw();
    textBox.draw();
    window.image(soundOn, (float) (window.displayWidth / 20), 900);
    window.image(soundOff, (float) (window.displayWidth / 20) + 100, 900);
    window.image(p1acc, (float) (window.displayWidth / 2) - 600, 200);
    window.image(p1brake, (float) (window.displayWidth / 2) - 600, 400);
    window.image(p1left, (float) (window.displayWidth / 2) - 600, 600);
    window.image(p1right, (float) (window.displayWidth / 2) - 600, 800);

    window.image(p2acc, (float) (window.displayWidth / 2) + 100, 200);
    window.image(p2brake, (float) (window.displayWidth / 2) + 100, 400);
    window.image(p2left, (float) (window.displayWidth / 2) + 100, 600);
    window.image(p2right, (float) (window.displayWidth / 2) + 100, 800);

    window.textSize(50);
    window.fill(117, 204, 32);
    window.text(p1upkey, (float) (window.displayWidth / 2) - 350, 200);
    window.text(p1downkey, (float) (window.displayWidth / 2) - 350, 400);
    window.text(p1leftkey, (float) (window.displayWidth / 2) - 350, 600);
    window.text(p1rightkey, (float) (window.displayWidth / 2) - 350, 800);
    window.fill(72, 194, 249);
    window.text(twop2upkey, (float) (window.displayWidth / 2) + 350, 200);
    window.text(twop2downkey, (float) (window.displayWidth / 2) + 350, 400);
    window.text(twop2leftkey, (float) (window.displayWidth / 2) + 350, 600);
    window.text(twop2rightkey, (float) (window.displayWidth / 2) + 350, 800);
//    Twop1Go.update();
//    Twop1Go.draw();
//    Twop1Stop.update();
//    Twop1Stop.draw();
//    Twop1Left.update();
//    Twop1Left.draw();
//    Twop1Right.update();
//    Twop1Right.draw();
//    textBox.draw();
    if (soundoff.isClicked()) {
      BGM.stopBGM(false);
      check = true;
      GameManager.audio = true;
    }
    if (soundon.isClicked() && check && GameManager.audio) {
      BGM.getBGM(true);
      check = false;
    }
    if (p1Go.isClicked()) {
      Controls.setUp(Controls.player1, window.inputChar);
    }
    if (p1Stop.isClicked()) {
      Controls.setDown(Controls.player1, window.inputChar);
    }
    if (p1Left.isClicked()) {
      Controls.setLeft(Controls.player1, window.inputChar);
    }
    if (p1Right.isClicked()) {
      Controls.setRight(Controls.player1, window.inputChar);
    }
    if (Twop2Go.isClicked()) {
      Controls.setUp(Controls.player2, window.inputChar);
    }
    if (Twop2Stop.isClicked()) {
      Controls.setDown(Controls.player2, window.inputChar);
    }
    if (Twop2Left.isClicked()) {
      Controls.setLeft(Controls.player2, window.inputChar);
    }
    if (Twop2Right.isClicked()) {
      Controls.setRight(Controls.player2, window.inputChar);
    }
//    if (Twop1Go.isClicked()) {
//      Controls.setUp(Controls.player2, window.inputChar);
//    }
//    if (Twop1Stop.isClicked()) {
//      Controls.setDown(Controls.player2, window.inputChar);
//    }
//    if (Twop1Left.isClicked()) {
//      Controls.setLeft(Controls.player2, window.inputChar);
//    }
//    if (Twop1Right.isClicked()) {
//      Controls.setRight(Controls.player2, window.inputChar);
//    }
  }
}