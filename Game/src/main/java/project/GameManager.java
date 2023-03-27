package project;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import javax.swing.*;


/**
 * Game manager class to indicate whether the game is running or not
 *
 * @author Rohil Patel
 */

public class GameManager extends PApplet {
  MainMenu mainMenu;
  TrackManager trackManager;
  ControlMenu controlMenu;
  CarModMenu carModMenu;
  SinglePlayer singlePlayer;
  TwoPlayers twoPlayers;
  Stopwatch stopwatch;
  TrackMenu trackMenu;
  public static boolean audio = true;
  private int check = 1;

  /*
   * 0. Main menu
   * 1. 1 player game
   * 2. 2 player game
   * 3. Control Menu
   * 4. Car Mod Menu
   * more values will come when menus are implemented.
   * TODO
   *  track menu
   * car mod and track menus should happen before game starts
   */
  int menu = 0;
  private static GameManager instance;

  private boolean gameRunning;

  // Private constructor to prevent instantiation
  private GameManager() {
  }

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
    trackManager = new TrackManager(this);
    trackManager.initTrack();
  }
  boolean isEditing = false;
  String inputText = "";
  char inputChar;

  /**
   * keyPressed, take key event input and update the controls boolean variable to true.
   *
   * @param event key event
   */
  @Override
  public void keyPressed(KeyEvent event) {
    int keyCode = event.getKeyCode();
    if (keyCode == TAB) {
      if (singlePlayer != null) {
        singlePlayer.stopwatch.stopTimer();
        singlePlayer.setTimerCheck(true);
      }
      if (twoPlayers != null) {
        twoPlayers.stopwatch.stopTimer();
        twoPlayers.setTimerCheck(true);
      }
      menu = 0;
    }
    if (menu == 1 || menu == 2) {
      Controls.setMovementTrue(keyCode);
      Controls.shiftGears(keyCode);
    }
    if (isEditing) {
      if (this.key == this.BACKSPACE) {
        inputText = inputText.substring(0, this.max(0, inputText.length() - 1));
      } else if (this.key == this.ENTER) {
        inputChar = inputText.charAt(0);
        inputText = "";
        isEditing = false;
      } else if (this.textWidth(inputText + this.key) <= 195) {
        inputText += this.key;
        inputText = inputText.toUpperCase();
      }
    }
  }

  /**
   * keyReleased, set controls boolean variable to false when an input key is released.
   */
  @Override
  public void keyReleased() {
    if (menu == 1 || menu == 2) {
      Controls.setMovementFalse(keyCode);
    }
  }
  @Override
  public void mousePressed() {
    if (mouseX >= this.displayWidth - 380 && mouseX <= this.displayWidth - 180
        && mouseY >= 500 && mouseY <= 540) {
      isEditing = true;
    } else {
      isEditing = false;
    }
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
        singlePlayer = SinglePlayer.getInstance(this);
        trackManager.draw();
        singlePlayer.draw();
      }
      case 2 -> { // 2 Player game
        twoPlayers = TwoPlayers.getInstance(this);
        trackManager.draw();
        twoPlayers.draw();
      }
      case 3 -> { // Control menu
        background(64, 64, 64);
        controlMenu = ControlMenu.getInstance(this);
        controlMenu.setup();
        controlMenu.draw();
        break;
      }
      case 4 -> { // Car modification menu
        carModMenu = CarModMenu.getInstance(this);
        carModMenu.setup();
        carModMenu.draw();
      }
      case 5 -> {
        trackMenu = TrackMenu.getInstance(this);
        trackMenu.setUp();
        trackMenu.draw();
      }
      default -> {
        break;
      }
    }
  }

  // Get instance method to make sure that there's only ever one instance of the game
  // Uses singleton design
  public static GameManager getInstance() {
    if (instance == null) {
      instance = new GameManager();
    }
    return instance;
  }

  // Code to initialize game objects and start the game loop
  public void startGame() {
    gameRunning = true;
  }

  // Code to clean up game objects and stop the game loop
  public void stopGame() {
    gameRunning = false;
  }

  public boolean isGameRunning() {
    return gameRunning;
  }

  public PVector getStartingPosition(int numberOfPlayers, int playerNumber) {
    return trackManager.getStartCords(numberOfPlayers, playerNumber);
  }

  /**
   * Main function.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"Zoom Zoom"};
    GameManager zoomZoom = new GameManager();
    PApplet.runSketch(appletArgs, zoomZoom);
  }
}