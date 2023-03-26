package project;

import java.awt.*;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * CarModMenu, menu that allows users to change the parts to their cars.
 *
 * @author James Langille
 */
public class CarModMenu implements Drawable {
  // Images
  private PImage[] engineImages = new PImage[4];
  private PImage[] chassisImages = new PImage[4];
  private PImage[] aerodynamicImages = new PImage[4];
  // Buttons
  private Button backToMainMenu;
  private Button startRace;
  private Button[] engines = new Button[4];
  private Button[] chassis = new Button[4];
  private Button[] aerodynamics = new Button[4];
  private Button[] gears = new Button[4];
  // Other data
  private final GameManager window;
  private static CarModMenu instance;
  private MainMenu mainMenu;
  private Stopwatch stopwatch;

  /**
   * CarModMenu, private constructor to create a singleton of the class.
   *
   * @param window current window
   */
  private CarModMenu(GameManager window) {
    this.window = window;
    mainMenu = MainMenu.getInstance(window);
  }

  /**
   * getInstance, method that creates a singleton of the class.
   *
   * @param window window class
   * @return a car mod menu object
   */
  public static CarModMenu getInstance(GameManager window) {
    if (instance == null) {
      instance = new CarModMenu(window);
    }
    return instance;
  }

  /**
   * setup, setup all buttons, text, and images needed for this menu.
   */
  public void setup() {
    window.textAlign(PApplet.CENTER, PApplet.CENTER);
    window.textSize(40);

    backToMainMenu = new Button(new PVector((float) (window.displayWidth / 2) - 100, 750), 200, 50,
        "Main Menu", new Color(52, 152, 235), window);
    startRace = new Button(new PVector(window.displayWidth - 225, 750), 200, 50,
        "Start Race!", new Color(0, 255, 0), window);
    // Buffer used to adjust y position of button
    int buffer = 50;
    // Instantiate the engine buttons
    for (int i = 0; i < engines.length; i++) {
      engines[i] = new Button(new PVector((window.displayWidth / 8) - 100,
          (window.displayHeight / 5) + buffer), 200, 100,
          "Engine " + (i + 1), new Color(255, 0, 0), window);
      buffer += 125;
    }
    buffer = 50;
    // Instantiate the chassis buttons
    for (int i = 0; i < chassis.length; i++) {
      chassis[i] = new Button(new PVector((window.displayWidth / 8) + 300,
          (window.displayHeight / 5) + buffer), 200, 100,
          "Chassis " + (i + 1), new Color(255, 0, 0), window);
      buffer += 125;
    }
    buffer = 50;
    // Instantiate the aerodynamics buttons
    for (int i = 0; i < aerodynamics.length; i++) {
      aerodynamics[i] = new Button(new PVector((window.displayWidth / 8) + 700,
          (window.displayHeight / 5) + buffer), 200, 100,
          "Aero " + (i + 1), new Color(255, 0, 0), window);
      buffer += 125;
    }
    buffer = 50;
    // Instantiate the gears buttons
    for (int i = 0; i < gears.length; i++) {
      gears[i] = new Button(new PVector((window.displayWidth / 8) + 1100,
          (window.displayHeight / 5) + buffer), 200, 100,
          "Gears " + (i + 1), new Color(255, 0, 0), window);
      buffer += 125;
    }

    // Set up images for engine buttons
    engineImages[0] = window.loadImage("Game/images/engine1.png");
    engineImages[1] = window.loadImage("Game/images/engine2.png");
    engineImages[2] = window.loadImage("Game/images/engine3.png");
    engineImages[3] = window.loadImage("Game/images/engine4.png");
    // Set up images for chassis buttons
    chassisImages[0] = window.loadImage("Game/images/chassis1.png");
    chassisImages[1] = window.loadImage("Game/images/chassis2.png");
    chassisImages[2] = window.loadImage("Game/images/chassis3.png");
    chassisImages[3] = window.loadImage("Game/images/chassis4.png");
    // Set up images for aero buttons
    aerodynamicImages[0] = window.loadImage("Game/images/aero1.png");
    aerodynamicImages[1] = window.loadImage("Game/images/aero2.png");
    aerodynamicImages[2] = window.loadImage("Game/images/aero3.png");
    aerodynamicImages[3] = window.loadImage("Game/images/aero4.png");
  }

  @Override
  public void draw() {
    // stopwatch = Stopwatch.getInstance(window);
    window.background(64, 64, 64);
    window.fill(0);
    window.text("Car Modification", window.displayWidth / 2 + 10, window.displayHeight / 10);
    // Create text for each car part
    window.textSize(30);
    window.text("Engine", (window.displayWidth / 8), window.displayHeight / 5);
    window.text("Chassis", (window.displayWidth / 8) + 400, window.displayHeight / 5);
    window.text("Aerodynamics", (window.displayWidth / 8) + 800, window.displayHeight / 5);
    window.text("Gears", (window.displayWidth / 8) + 1200, window.displayHeight / 5);

    // Draw buttons for the engine
    for (Button engine : engines) {
      engine.draw();
      engine.update();
      this.setPlayerEngine(engine);
    }
    int buffer = 50;
    // Draw images for each engine
    for (PImage engine : engineImages) {
      window.image(engine, (window.displayWidth / 8) - 100, (window.displayHeight / 5) + buffer);
      buffer += 125;
    }

    // Draw buttons for the chassis
    for (Button chassi : chassis) {
      chassi.draw();
      chassi.update();
      this.setPlayerChassis(chassi);
    }
    buffer = 50;
    // Draw images for each chassis
    for (PImage chassis : chassisImages) {
      window.image(chassis, (window.displayWidth / 8) + 300, (window.displayHeight / 5) + buffer);
      buffer += 125;
    }

    // Draw buttons for the aerodynamics
    for (Button aero : aerodynamics) {
      aero.draw();
      aero.update();
      this.setPlayerAerodynamics(aero);
    }
    buffer = 50;
    // Draw images for each aerodynamics
    for (PImage aero : aerodynamicImages) {
      window.image(aero, (window.displayWidth / 8) + 700, (window.displayHeight / 5) + buffer);
      buffer += 125;
    }

    // Draw buttons for the tires
    for (Button gear : gears) {
      gear.draw();
      gear.update();
      // If a tire button is clicked, update the player's part to that tire.
      if (gear.isClicked()) {

      }
    }

    // Draw start race button
    startRace.draw();
    startRace.update();
    if (startRace.isClicked()) {
      if (mainMenu.gameType == 1) {
        // Initialize one player game
        SinglePlayer singlePlayer = SinglePlayer.getInstance(window);
        singlePlayer.init1Player();
        window.menu = 1;
      } else if (mainMenu.gameType == 2) {
        // Initialize two player game
        TwoPlayers twoPlayers = TwoPlayers.getInstance(window);
        twoPlayers.init2Player();
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
        System.out.println("test");
        // Check for right click
      } else if (buttonClick(engine) == 2) {
        // Set player 2 engine to engine 1
        System.out.println("right test");
      }
    } else if (engine == engines[1]) {
      // Check for left click
      if (buttonClick(engine) == 1) {
        // Set player 1 engine to engine 2
        System.out.println("test 2");
        // Check for right click
      } else if (buttonClick(engine) == 2) {
        // Set player 2 engine to engine 2
        System.out.println("right test 2");
      }
    } else if (engine == engines[2]) {
      // Check for left click
      if (buttonClick(engine) == 1) {
        // Set player 1 engine to engine 3
        System.out.println("test 3");
        // Check for right click
      } else if (buttonClick(engine) == 2) {
        // Set player 2 engine to engine 3
        System.out.println("right test 3");
      }
    } else if (engine == engines[3]) {
      // Check for left click
      if (buttonClick(engine) == 1) {
        // Set player 1 engine to engine 4
        System.out.println("test 4");
        // Check for right click
      } else if (buttonClick(engine) == 2) {
        // Set player 2 engine to engine 4
        System.out.println("right test 4");
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
    if (chassi == chassis[0]) {
      // Check for left click
      if (buttonClick(chassi) == 1) {
        // Set player 1 chassis to chassis 1
        System.out.println("test chassis 1");
        // Check for right click
      } else if (buttonClick(chassi) == 2) {
        // Set player 2 chassis to chassis 1
        System.out.println("right test chassis 1");
      }
    } else if (chassi == chassis[1]) {
      // Check for left click
      if (buttonClick(chassi) == 1) {
        // Set player 1 chassis to chassis 2
        System.out.println("test chassis 2");
        // Check for right click
      } else if (buttonClick(chassi) == 2) {
        // Set player 2 chassis to chassis 2
        System.out.println("right test chassis 2");
      }
    } else if (chassi == chassis[2]) {
      // Check for left click
      if (buttonClick(chassi) == 1) {
        // Set player 1 chassis to chassis 3
        System.out.println("test chassis 3");
        // Check for right click
      } else if (buttonClick(chassi) == 2) {
        // Set player 2 chassis to chassis 3
        System.out.println("right test chassis 3");
      }
    } else if (chassi == chassis[3]) {
      // Check for left click
      if (buttonClick(chassi) == 1) {
        // Set player 1 chassis to chassis 4
        System.out.println("test chassis 4");
        // Check for right click
      } else if (buttonClick(chassi) == 2) {
        // Set player 2 chassis to chassis 4
        System.out.println("right test chassis 4");
      }
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
        System.out.println("test aero 1");
        // Check for right click
      } else if (buttonClick(aero) == 2) {
        // Set player 2 aerodynamics to aerodynamics 1
        System.out.println("right test aero 1");
      }
    } else if (aero == aerodynamics[1]) {
      // Check for left click
      if (buttonClick(aero) == 1) {
        // Set player 1 aerodynamics to aerodynamics 2
        System.out.println("test aero 2");
        // Check for right click
      } else if (buttonClick(aero) == 2) {
        // Set player 2 aerodynamics to aerodynamics 2
        System.out.println("right test aero 2");
      }
    } else if (aero == aerodynamics[2]) {
      // Check for left click
      if (buttonClick(aero) == 1) {
        // Set player 1 aerodynamics to aerodynamics 3
        System.out.println("test aero 3");
        // Check for right click
      } else if (buttonClick(aero) == 2) {
        // Set player 2 aerodynamics to aerodynamics 3
        System.out.println("right test aero 3");
      }
    } else if (aero == aerodynamics[3]) {
      // Check for left click
      if (buttonClick(aero) == 1) {
        // Set player 1 aerodynamics to aerodynamics 4
        System.out.println("test aero 4");
        // Check for right click
      } else if (buttonClick(aero) == 2) {
        // Set player 2 aerodynamics to aerodynamics 4
        System.out.println("right test aero 4");
      }
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
