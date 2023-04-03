package project;

import processing.core.PVector;

/**
 * CarModMenuButtons, handles all buttons needed for CarModMenu.
 */
public class CarModMenuButtons implements Drawable {
  private Button backToMainMenu;
  private Button startRace;
  private Button saveCarBuild;
  // Other data
  private GameManager window;
  private static CarModMenuButtons instance;
  // Player objects
  private Player player1 = GameManager.player1;
  private Player player2 = GameManager.player2;
  private PartEngine engine;
  private PartChassis partChassis;
  private PartAero partAero;

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
    int x = (window.displayWidth / 8) - 100;
    int y = 750;
    int width = 200;
    int height = 50;
    saveCarBuild = new Button(new PVector(x, y), width, height, Button.PURPLE, window);
    x += 400;
    backToMainMenu = new Button(new PVector(x, y), width, height, Button.BLUE, window);
    x += 400;
    startRace = new Button(new PVector(x, y), width, height, Button.GREEN, window);
  }

  /**
   * draw, draws all buttons needed for CarModMenu.
   */
  @Override
  public void draw() {
    EngineButton.drawEngines();
    ChassisButton.drawChassis();
    AerodynamicsButton.drawAerodynamics();
    GearButton.drawGears();
    // Draw start race button
    startRace.draw();
    startRace.click();
    startRace();
    // Draw the back to main menu button
    backToMainMenu.draw();
    backToMainMenu.click();
    if (backToMainMenu.isLeftClicked()) {
      window.menu = 0;
    }
    saveCarBuild.draw();
    saveCarBuild.click();
    saveCarBuild();
  }

  /**
   * startRace, helper function that starts the race once the button is clicked.
   */
  private void startRace() {
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
  }

  /**
   * saveCarBuild, saves the player's car build to the database once
   * the button is left or right clicked.
   */
  private void saveCarBuild() {
    if (saveCarBuild.isLeftClicked()) {
      // Save player1's car parts to database
      System.out.println("Player 1 build saved");
      putStatsInMongoDB(player1);
    } else if (saveCarBuild.isRightClicked()) {
      // Save player2's car parts to database
      System.out.println("Player 2 build saved");
      putStatsInMongoDB(player2);
    }
  }

  /**
   * This method puts stats of each parts of car (Engine, Chassis, Aero) by calling
   * methods in MongoDB Class.
   *
   * @param player either player1 or player2
   */
  public void putStatsInMongoDB(Player player) {
    String whichPlayer;
    if (player == player1) {
      whichPlayer = "P1 ";
    } else {
      whichPlayer = "P2 ";
    }
    engine = player.getEngine();
    partChassis = player.getChassis();
    partAero = player.getAero();
    window.mongoDB.putEngine(whichPlayer + "Power", engine.getPower(),
        whichPlayer + "DropOff", engine.getDropoff(),
        whichPlayer + "OptimalRevs", engine.getOpRevs(),
        whichPlayer + "EngineWeight", engine.getWeight());
    window.mongoDB.putChasis(whichPlayer + "WheelBaseX", partChassis.getWheelBaseX(),
        whichPlayer + "WheelBaseY", partChassis.wheelBaseY,
        whichPlayer + "ChasisWeight", partChassis.getWeight());
    window.mongoDB.putAero(whichPlayer + "DownForce", partAero.getDownForce(),
        whichPlayer + "Drag", partAero.getDrag(),
        whichPlayer + "AeroWeight,", partAero.getWeight());
  }
}

