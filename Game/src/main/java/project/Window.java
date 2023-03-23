package project;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Zoom zoom window class.
 * Example window class from lab 03 to test
 * other classes.
 *
 */
public class Window extends PApplet {
  MainMenu mainMenu;
  ControlMenu controlMenu;
  SinglePlayer singlePlayer;
  TwoPlayers twoPlayers;
  public static boolean audio = true;
  private int check = 1;

  /*
   * 0. Main menu
   * 1. 1 player game
   * 2. 2 player game
   * more values will come when menus are implemented.
   * TODO
   *  car mod menu
   *  track menu
   *  control menu
   * car mod and track menus should happen before game starts
   * control menu button in main menu
   */
  int menu = 0;

  /**
   * Called once at the beginning of the program.
   */
  public void settings() {
    size(displayWidth, displayHeight);
  }

  /**
   * Called once at the beginning of the program.
   * Initializes all objects.
   */
  public void setup() {
  }

  /**
   * keyPressed, take key event input and update the controls boolean variable to true.
   *
   * @param event key event
   */
  @Override
  public void keyPressed(KeyEvent event) {
    int keyCode = event.getKeyCode();
    if (keyCode == TAB) {
      menu = 0;
    }
    if (menu == 1 || menu == 2)
      Controls.setMovementTrue(keyCode);
  }

  /**
   * keyReleased, set controls boolean variable to false when an input key is released.
   */
  @Override
  public void keyReleased() {
    if (menu == 1 || menu == 2)
      Controls.setMovementFalse(keyCode);
  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {
    if (audio && check == 1) {
//      BGM.getBGM(true);
      audio = false;
      check++;
    }
    background(0);
    switch (menu) {
      case 0 -> { // Main menu
        mainMenu = MainMenu.getInstance(this);
        mainMenu.setup();
        mainMenu.draw();
        break;
      }
      case 1 -> { // 1 Player game
        background(64, 64, 64);
        singlePlayer = SinglePlayer.getInstance(this);
        singlePlayer.draw();
      }
      case 2 -> { // 2 Player game
        background(64, 64, 64);
        twoPlayers = TwoPlayers.getInstance(this);
        twoPlayers.draw();
      }
      case 3 -> {
        background(64, 64, 64);
        controlMenu = ControlMenu.getInstance(this);
        controlMenu.setup();
        controlMenu.draw();
        break;
      }
      default -> {
        break;
      }
    }
  }

  /**
   * Main function.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"eatBubbles"};
    Window eatBubbles = new Window();
    PApplet.runSketch(appletArgs, eatBubbles);
    // Run background music
  }
}