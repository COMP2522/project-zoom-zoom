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
  protected Button[] engines = new Button[4];
  protected Button[] chassis = new Button[4];
  protected Button[] aerodynamics = new Button[4];
  protected Button[] gears = new Button[4];
  // Other data
  private MainMenu mainMenu;
  private GameManager window;
  private static CarModMenuButtons instance;
  // Buffer used to adjust x or y position of button
  private int buffer;

  /**
   * CarModMenuButtons, private constructor for class using singleton design.
   *
   * @param window of screen
   */
  private CarModMenuButtons(GameManager window) {
    this.window = window;
    this.mainMenu = MainMenu.getInstance(window);
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
    setUpEngineButtons();
    setUpChassisButtons();
    setUpAerodynamicsButtons();
    setUpGearButtons();
    setUpOtherButtons();
  }

  /**
   * setUpEngineButtons, sets up the engine buttons needed for CarModMenu.
   */
  public void setUpEngineButtons() {
    buffer = 50;
    for (int i = 0; i < engines.length; i++) {
      engines[i] = new Button(new PVector((window.displayWidth / 8) - 100,
          (window.displayHeight / 5) + buffer), 200, 100,
          "", new Color(255, 0, 0), window);
      buffer += 125;
    }
  }

  /**
   * setUpChassisButtons, sets up the chassis buttons needed for CarModMenu.
   */
  public void setUpChassisButtons() {
    buffer = 50;
    for (int i = 0; i < chassis.length; i++) {
      chassis[i] = new Button(new PVector((window.displayWidth / 8) + 300,
          (window.displayHeight / 5) + buffer), 200, 100,
          "", new Color(255, 0, 0), window);
      buffer += 125;
    }
  }

  /**
   * setUpAerodynamicsButtons, sets up all aero buttons needed
   * for CarModMenu.
   */
  public void setUpAerodynamicsButtons() {
    buffer = 50;
    for (int i = 0; i < aerodynamics.length; i++) {
      aerodynamics[i] = new Button(new PVector((window.displayWidth / 8) + 700,
          (window.displayHeight / 5) + buffer), 200, 100,
          "", new Color(255, 0, 0), window);
      buffer += 125;
    }
  }

  /**
   * setUpGearButtons, sets up all gear buttons needed for CarModMenu.
   */
  public void setUpGearButtons() {
    window.textAlign(PApplet.CENTER, PApplet.CENTER);
    buffer = 50;
    for (int i = 0; i < gears.length; i++) {
      gears[i] = new Button(new PVector((window.displayWidth / 8) + 1100,
          (window.displayHeight / 5) + buffer), 200, 100,
          "Gear " + (i + 1), new Color(255, 0, 0), window);
      buffer += 125;
    }
  }

  /**
   * setUpOtherButtons, sets up all other buttons needed for CarModMenu.
   */
  public void setUpOtherButtons() {
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
      engine.update();
      setPlayerEngine(engine);
    }

    // Draw buttons for the chassis
    for (Button chassi : chassis) {
      chassi.draw();
      chassi.update();
      setPlayerChassis(chassi);
    }

    // Draw buttons for the aerodynamics
    for (Button aero : aerodynamics) {
      aero.draw();
      aero.update();
      setPlayerAerodynamics(aero);
    }

    // Draw buttons for the tires
    for (Button gear : gears) {
      gear.draw();
      gear.update();
      setPlayerGears(gear);
    }

    // Draw start race button
    startRace.draw();
    startRace.update();
    if (startRace.isClicked()) {
      if (mainMenu.gameType == 1) {
        // Initialize one player game
        SinglePlayer singlePlayer = SinglePlayer.getInstance(window);
        singlePlayer.init1Player(GameManager.player1);
        window.menu = 1;
      } else if (mainMenu.gameType == 2) {
        // Initialize two player game
        TwoPlayers twoPlayers = TwoPlayers.getInstance(window);
        twoPlayers.init2Player(GameManager.player1, GameManager.player2);
        window.menu = 2;
      }
    }
    // Draw the back to main menu button
    backToMainMenu.draw();
    backToMainMenu.update();
    if (backToMainMenu.isClicked()) {
      window.menu = 0;
    }
  }

  /**
   * setPlayerEngine, sets the player's engine part depending on which
   * engine button was clicked.
   *
   * @param engine button
   */
  private void setPlayerEngine(Button engine) {
    if (engine == engines[0]) {
      // Check for left click
      if (buttonClick(engine) == 1) {
        // Set player 1 engine to engine 1
        System.out.println("p1 e1");
        GameManager.player1.getEngine().setEngine(7000, 0.6, 3000, 1000);
        // Check for right click
      } else if (buttonClick(engine) == 2) {
        // Set player 2 engine to engine 1
        GameManager.player2.getEngine().setEngine(7000, 0.6, 3000, 1000);
        System.out.println("p2 e1");
      }
    } else if (engine == engines[1]) {
      // Check for left click
      if (buttonClick(engine) == 1) {
        // Set player 1 engine to engine 2
        GameManager.player1.getEngine().setEngine(9000, 0.7, 5000, 2000);
        System.out.println("p1 e2");
        // Check for right click
      } else if (buttonClick(engine) == 2) {
        // Set player 2 engine to engine 2
        GameManager.player2.getEngine().setEngine(9000, 0.7, 5000, 2000);
        System.out.println("p1 e2");
      }
    } else if (engine == engines[2]) {
      // Check for left click
      if (buttonClick(engine) == 1) {
        // Set player 1 engine to engine 3
        GameManager.player1.getEngine().setEngine(4000, 0.3, 2000, 500);
        System.out.println("p1 e3");
        // Check for right click
      } else if (buttonClick(engine) == 2) {
        // Set player 2 engine to engine 3
        GameManager.player2.getEngine().setEngine(4000, 0.3, 2000, 500);
        System.out.println("p2 e3");
      }
    } else if (engine == engines[3]) {
      // Check for left click
      if (buttonClick(engine) == 1) {
        // Set player 1 engine to engine 4
        GameManager.player1.getEngine().setEngine(12000, 1, 4000, 2500);
        System.out.println("p1 e4");
        // Check for right click
      } else if (buttonClick(engine) == 2) {
        // Set player 2 engine to engine 4
        GameManager.player2.getEngine().setEngine(12000, 1, 4000, 2500);
        System.out.println("p2 e4");
      }
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
    int clickType = buttonClick(chassi);

    if (clickType == 1) {
      GameManager.player1.getChassis().setChassis(2500 - 500 * chassisIndex, 1, 1);
      System.out.println("p1 c" + (chassisIndex + 1));
    } else if (clickType == 2) {
      GameManager.player2.getChassis().setChassis(2500 - 500 * chassisIndex, 1, 1);
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
    if (aero == aerodynamics[0]) {
      // Check for left click
      if (buttonClick(aero) == 1) {
        // Set player 1 aerodynamics to aerodynamics 1
        GameManager.player1.getAero().setAero(30, 15, 500);
        System.out.println("p1 a1");
        // Check for right click
      } else if (buttonClick(aero) == 2) {
        // Set player 2 aerodynamics to aerodynamics 1
        GameManager.player2.getAero().setAero(30, 15, 500);
        System.out.println("p2 a1");
      }
    } else if (aero == aerodynamics[1]) {
      // Check for left click
      if (buttonClick(aero) == 1) {
        // Set player 1 aerodynamics to aerodynamics 2
        GameManager.player1.getAero().setAero(40, 20, 300);
        System.out.println("p1 a2");
        // Check for right click
      } else if (buttonClick(aero) == 2) {
        // Set player 2 aerodynamics to aerodynamics 2
        GameManager.player2.getAero().setAero(40, 20, 300);
        System.out.println("p2 a2");
      }
    } else if (aero == aerodynamics[2]) {
      // Check for left click
      if (buttonClick(aero) == 1) {
        // Set player 1 aerodynamics to aerodynamics 3
        GameManager.player1.getAero().setAero(10, 5, 300);
        System.out.println("p1 a3");
        // Check for right click
      } else if (buttonClick(aero) == 2) {
        // Set player 2 aerodynamics to aerodynamics 3
        GameManager.player2.getAero().setAero(10, 5, 300);
        System.out.println("p2 a3");
      }
    } else if (aero == aerodynamics[3]) {
      // Check for left click
      if (buttonClick(aero) == 1) {
        // Set player 1 aerodynamics to aerodynamics 4
        GameManager.player1.getAero().setAero(0, 0, 0);
        System.out.println("p1 a4");
        // Check for right click
      } else if (buttonClick(aero) == 2) {
        // Set player 2 aerodynamics to aerodynamics 4
        GameManager.player2.getAero().setAero(0, 0, 0);
        System.out.println("p2 a4");
      }
    }
  }

  /**
   * setPlayerGears, sets the players gears based on the text box
   * and which button was clicked.
   * @param gear button
   */
  public void setPlayerGears(Button gear) {
    int gearsIndex = Arrays.asList(gears).indexOf(gear);
    int clickType = buttonClick(gear);

    if (clickType == 1) {
      // Set player1 gear1
      System.out.println("p1 g" + (gearsIndex + 1) + ": " + window.inputVal);
    } else if (clickType == 2) {
      // Set player2 gear1
      System.out.println("p2 g" + (gearsIndex + 1) + ": " + window.inputVal);
    }
  }



  /**
   * mouseClick, helper method that checks if a button was left or right clicked.
   *
   * @param part Button that was clicked
   * @return 1 if left click, 2 if right click, else 0
   */
  private int buttonClick(Button part) {
    if (part.isClicked() && window.mouseButton == PApplet.LEFT) {
      return 1;
      /* Checks if right mouse button was clicked and two player button
         in main menu was clicked. */
    } else if (part.isClicked() && window.mouseButton == PApplet.RIGHT
        && mainMenu.gameType == 2) {
      return 2;
    }
    return 0;
  }
}

