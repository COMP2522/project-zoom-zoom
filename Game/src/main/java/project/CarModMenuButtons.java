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
  private EngineButton[] engines = new EngineButton[4];
  private ChassisButton[] chassis = new ChassisButton[4];
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
    setupEngineButtons();
    setupChassisButtons();
    setupAerodynamicsButtons();
    setupGearButtons();
    setupOtherButtons();
  }

  /**
   * setUpEngineButtons, sets up the engine buttons needed for CarModMenu.
   */
  public void setupEngineButtons() {
    buffer = 50;
    for (int i = 0; i < engines.length; i++) {
      position = new PVector((window.displayWidth / 8) - 100,
          (window.displayHeight / 5) + buffer);
      engines[i] = new EngineButton(position, "", window);
      buffer += 125;
    }
  }

  /**
   * setUpChassisButtons, sets up the chassis buttons needed for CarModMenu.
   */
  public void setupChassisButtons() {
    buffer = 50;
    for (int i = 0; i < chassis.length; i++) {
      position = new PVector((window.displayWidth / 8) + 300,
          (window.displayHeight / 5) + buffer);
      chassis[i] = new ChassisButton(position, "", window);
      buffer += 125;
    }
  }

  /**
   * setUpAerodynamicsButtons, sets up all aero buttons needed
   * for CarModMenu.
   */
  public void setupAerodynamicsButtons() {
    buffer = 50;
    for (int i = 0; i < aerodynamics.length; i++) {
      position = new PVector((window.displayWidth / 8) + 700,
          (window.displayHeight / 5) + buffer);
      aerodynamics[i] = new AerodynamicsButton(position, "", window);
      buffer += 125;
    }
  }

  /**
   * setUpGearButtons, sets up all gear buttons needed for CarModMenu.
   */
  public void setupGearButtons() {
    window.textAlign(PApplet.CENTER, PApplet.CENTER);
    buffer = 50;
    for (int i = 0; i < gears.length; i++) {
      position = new PVector((window.displayWidth / 8) + 1100,
          (window.displayHeight / 5) + buffer);
      gears[i] = new GearButton(position, "Gear " + (i + 1), window);
      buffer += 125;
    }
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
    // Draw buttons for the engine
    for (Button engine : engines) {
      engine.draw();
      engine.click();
      setPlayerEngine(engine);
    }

    // Draw buttons for the chassis
    for (Button chassi : chassis) {
      chassi.draw();
      chassi.click();
      setPlayerChassis(chassi);
    }

    // Draw buttons for the aerodynamics
    for (Button aero : aerodynamics) {
      aero.draw();
      aero.click();
      setPlayerAerodynamics(aero);
    }

    // Draw buttons for the tires
    for (Button gear : gears) {
      gear.draw();
      gear.click();
      setPlayerGears(gear);
    }

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

  /**
   * setPlayerEngine, sets the player's engine part depending on which
   * engine button was clicked.
   *
   * @param engine button
   */
  private void setPlayerEngine(Button engine) {
    int engineIndex = Arrays.asList(engines).indexOf(engine);

    if (engine.isLeftClicked()) {
      player1.getEngine().setEngine(PartEngine.engineParts[engineIndex]);
      System.out.println("p1 e" + engineIndex);
    } else if (engine.isRightClicked()) {
      player2.getEngine().setEngine(PartEngine.engineParts[engineIndex]);
      System.out.println("p2 e" + engineIndex);
    }
  }

  /**
   * setPlayerChassis, sets the player's chassis part depending on which
   * chassis button was clicked.
   *
   * @param chassi button
   */
  private void setPlayerChassis(Button chassi) {
    int chassisIndex = Arrays.asList(chassis).indexOf(chassi);

    if (chassi.isLeftClicked()) {
      player1.getChassis().setChassis(PartChassis.chassisParts[chassisIndex]);
      System.out.println("p1 c" + (chassisIndex + 1));
    } else if (chassi.isRightClicked()) {
      player2.getChassis().setChassis(PartChassis.chassisParts[chassisIndex]);
      System.out.println("p2 c" + (chassisIndex + 1));
    }
  }

  /**
   * sets the player's aerodynamics part depending on which
   * aerodynamics button was clicked.
   *
   * @param aero button
   */
  private void setPlayerAerodynamics(Button aero) {
    int aeroIndex = Arrays.asList(aerodynamics).indexOf(aero);

    if (aero.isLeftClicked()) {
      player1.getAero().setAero(PartAero.aeroParts[aeroIndex]);
      System.out.println("p1 a" + (aeroIndex + 1));
    } else if (aero.isRightClicked()) {
      player2.getAero().setAero(PartAero.aeroParts[aeroIndex]);
      System.out.println("p2 a" + (aeroIndex + 1));
    }
  }

  /**
   * setPlayerGears, sets the players gears based on the text box
   * and which button was clicked.
   * @param gear button
   */
  public void setPlayerGears(Button gear) {
    int gearsIndex = Arrays.asList(gears).indexOf(gear);

    if (gear.isLeftClicked()) {
      // Set player1 gear1
      System.out.println("p1 g" + (gearsIndex + 1) + ": " + window.inputVal);
    } else if (gear.isRightClicked()) {
      // Set player2 gear1
      System.out.println("p2 g" + (gearsIndex + 1) + ": " + window.inputVal);
    }
  }
}

