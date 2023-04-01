package project;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.awt.*;


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
  MongoDB mongoDB;
  Ranking ranking;
  public static boolean audio = true;
  private int check = 1;

  static Player player1;
  static Player player2;

  /*
   * 0. Main menu
   * 1. 1 player game
   * 2. 2 player game
   * 3. Control Menu
   * 4. Car Mod Menu
   * 5. Track Menu
   */
  int menu = 0;
  // 1 = 1-Player, 2 = 2-Player
  int gameType = 0;
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
    this.fullScreen();
  }

  /**
   * Called once at the beginning of the program.
   * Initializes all objects.
   */
  public void setup() {
    mongoDB = MongoDB.getInstance();
    trackManager = new TrackManager(this);
    stopwatch = Stopwatch.getInstance(this);

    player1 = new Player(
            getStartingPosition(1, 1),
            new PVector(50, 1),
            (20),
            0.1F,
            new Color(0, 255, 0),
            this, stopwatch);
    player2 = new Player(
        getStartingPosition(2, 2),
        new PVector(50, 1),
        (20),
        0.1F,
        new Color(0, 255, 247),
        this, stopwatch);
  }
  boolean isEditing = false;
  String inputText = "";
  int inputVal;
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
      if (mongoDB != null && singlePlayer != null) {
        mongoDB.put("time", singlePlayer.stopwatch.currentTime);
      }
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
    // Scuffed handling for textbox in control menu
    if (isEditing && menu == 3) {
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
      // Scuffed handling for textbox in car mod menu
    } else if (isEditing && menu == 4) {
      if (this.key >= '0' && this.key <= '9' || this.key == this.ENTER || this.key == this.BACKSPACE) {
        if (this.key == this.BACKSPACE) {
          inputText = inputText.substring(0, this.max(0, inputText.length() - 1));
        } else if (this.key == this.ENTER) {
          inputVal = Integer.parseInt(inputText);
          // Lock input max to 1000 and min to 10
          if (inputVal > 1000) {
            inputVal = 1000;
          }
          if (inputVal < 10) {
            inputVal = 10;
          }
          System.out.println(inputVal);
          inputText = "";
          isEditing = false;
        } else if (this.textWidth(inputText + this.key) <= 195) {
          inputText += this.key;
          inputText = inputText.toUpperCase();
        }
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
    if (menu == 3) {
      controlMenu.textBox.textBoxClicked();
    }
    if (menu == 4) {
      carModMenu.gearInput.textBoxClicked();
    }
  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {
    if (audio && check == 1) {
      BGM.getBGM(true);
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
        trackManager.setTrackChoice(1);
        player1.setTrack(1);
        player2.setTrack(1);
      }
      case 5 -> {
        trackMenu = TrackMenu.getInstance(this);
        trackMenu.setUp();
        trackMenu.draw();
      }
      case 6 -> {
        ranking = Ranking.getInstance(this);
        ranking.setUp();
        ranking.draw();
      }
      case 7 -> {
        carModMenu = CarModMenu.getInstance(this);
        carModMenu.setup();
        carModMenu.draw();
        trackManager.setTrackChoice(2);
        player1.setTrack(2);
        player2.setTrack(2);
      }
      case 8 -> {
        carModMenu = CarModMenu.getInstance(this);
        carModMenu.setup();
        carModMenu.draw();
        trackManager. setTrackChoice(3);
        player1.setTrack(3);
        player2.setTrack(3);
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