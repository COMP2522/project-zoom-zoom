package project;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.Arrays;

/**
 * CarModMenuButtons, handles all buttons needed for CarModMenu.
 */
public class CarModMenuButtons implements Drawable {
  private Button backToMainMenu;
  private Button startRace;
  private Button saveCarBuild;
  private AerodynamicsButton[] aerodynamics = new AerodynamicsButton[4];
  private GearButton[] gears = new GearButton[4];
  // Other data
  private GameManager window;
  private static CarModMenuButtons instance;
  // Buffer used to adjust x or y position of button
  private int buffer;
  private PVector position;
  // Player objects
  private Player player1 = GameManager.player1;
  private Player player2 = GameManager.player2;

  /**
   * CarModMenuButtons, private constructor for class using singleton design.
   *
   * @param window of screen
   */
  private CarModMenuButtons(GameManager window) {
    this.window = window;
  }

  /**
   * getInstance, gets one object of class.
   *
   * @param window of screen
   * @return singleton object of class
   */
  public static CarModMenuButtons getInstance(GameManager window) {
    if (instance == null) {
      instance = new CarModMenuButtons(window);
    }
    return instance;
  }

  /**
   * setup, instantiates all buttons needed for CarModMenu class.
   */
  public void setup() {
    EngineButton.setupEngineButtons();
    ChassisButton.setupChassisButtons();
    AerodynamicsButton.setupAerodynamicsButtons();
    GearButton.setupGearButtons();
    setupOtherButtons();
  }

  /**
   * setUpOtherButtons, sets up all other buttons needed for CarModMenu.
   */
  public void setupOtherButtons() {
    saveCarBuild = new Button(new PVector((window.displayWidth / 8) - 100, 750), 200, 50,
        "Save build", new Color(104, 52, 235), window);
    backToMainMenu = new Button(new PVector((window.displayWidth / 8) + 300, 750), 200, 50,
        "", new Color(0, 0, 150), window);
    startRace = new Button(new PVector((window.displayWidth / 8) + 700, 750), 200, 50,
        "", new Color(0, 150, 0), window);
  }

  /**
   * draw, draws all buttons needed for CarModMenu.
   */
  @Override
  public void draw() {
    window.textSize(30);
    EngineButton.drawEngines();
    ChassisButton.drawChassis();
    AerodynamicsButton.drawAerodynamics();
    GearButton.drawGears();

    // Draw start race button
    startRace.draw();
    startRace.click();
    if (startRace.isLeftClicked()) {
      if (window.gameType == 1) {
        // Initialize one player game
        SinglePlayer singlePlayer = SinglePlayer.getInstance(window);
        singlePlayer.init1Player(player1);
        window.menu = 1;
      } else if (window.gameType == 2) {
        // Initialize two player game
        TwoPlayers twoPlayers = TwoPlayers.getInstance(window);
        twoPlayers.init2Player(player1, player2);
        window.menu = 2;
      }
    }
    // Draw the back to main menu button
    backToMainMenu.draw();
    backToMainMenu.click();
    if (backToMainMenu.isLeftClicked()) {
      window.menu = 0;
    }
    saveCarBuild.draw();
    saveCarBuild.click();
    if (saveCarBuild.isLeftClicked()) {
      // Save player1's car parts to database
      System.out.println("Player 1 build saved");
    } else if (saveCarBuild.isRightClicked()) {
      // Save player2's car parts to database
      System.out.println("Player 2 build saved");
    }
  }
}

