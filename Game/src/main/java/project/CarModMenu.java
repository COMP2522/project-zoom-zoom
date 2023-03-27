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
  private CarModMenuImages carModMenuImages;
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
  // Buffer used to adjust x or y position of button or image
  private int buffer;
  // Player objects
  Player player1;
  Player player2;

  /**
   * CarModMenu, private constructor to create a singleton of the class.
   *
   * @param window current window
   */
  private CarModMenu(GameManager window, Player p1, Player p2) {
    this.window = window;
    player1 = p1;
    player2 = p2;
    mainMenu = MainMenu.getInstance(window);
    carModMenuImages = CarModMenuImages.getInstance(window);
  }

  /**
   * getInstance, method that creates a singleton of the class.
   *
   * @param window window class
   * @return a car mod menu object
   */
  public static CarModMenu getInstance(GameManager window, Player p1, Player p2) {
    if (instance == null) {
      instance = new CarModMenu(window, p1, p2);
    }
    return instance;
  }

  /**
   * setup, setup all buttons, text, and images needed for this menu.
   */
  public void setup() {
    window.textAlign(PApplet.CENTER, PApplet.CENTER);
    buffer = 50;
    // Instantiate the engine buttons
    for (int i = 0; i < engines.length; i++) {
      engines[i] = new Button(new PVector((window.displayWidth / 8) - 100,
          (window.displayHeight / 5) + buffer), 200, 100,
          "", new Color(255, 0, 0), window);
      buffer += 125;
    }
    buffer = 50;
    // Instantiate the chassis buttons
    for (int i = 0; i < chassis.length; i++) {
      chassis[i] = new Button(new PVector((window.displayWidth / 8) + 300,
          (window.displayHeight / 5) + buffer), 200, 100,
          "", new Color(255, 0, 0), window);
      buffer += 125;
    }
    buffer = 50;
    // Instantiate the aerodynamics buttons
    for (int i = 0; i < aerodynamics.length; i++) {
      aerodynamics[i] = new Button(new PVector((window.displayWidth / 8) + 700,
          (window.displayHeight / 5) + buffer), 200, 100,
          "", new Color(255, 0, 0), window);
      buffer += 125;
    }
    buffer = 50;
    // Instantiate the gears buttons
    for (int i = 0; i < gears.length; i++) {
      gears[i] = new Button(new PVector((window.displayWidth / 8) + 1100,
          (window.displayHeight / 5) + buffer), 200, 50,
          "Gear " + (i + 1), new Color(255, 0, 0), window);
      buffer += 125;
    }

    // Instantiate other buttons
    backToMainMenu = new Button(new PVector((window.displayWidth / 2) - 100, 750), 200, 50,
        "", new Color(0, 0, 150), window);
    startRace = new Button(new PVector(window.displayWidth - 225, 750), 200, 50,
        "", new Color(0, 150, 0), window);
    carModMenuImages.setup();
  }

  @Override
  public void draw() {
    // stopwatch = Stopwatch.getInstance(window);
    // Draw background image
    carModMenuImages.drawBackground();
    window.textSize(30);

    // Draw buttons for the engine
    for (Button engine : engines) {
      engine.draw();
      engine.update();
      this.setPlayerEngine(engine);
    }

    // Draw buttons for the chassis
    for (Button chassi : chassis) {
      chassi.draw();
      chassi.update();
      this.setPlayerChassis(chassi);
    }

    // Draw buttons for the aerodynamics
    for (Button aero : aerodynamics) {
      aero.draw();
      aero.update();
      this.setPlayerAerodynamics(aero);
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
        singlePlayer.init1Player(player1);
        window.menu = 1;
      } else if (mainMenu.gameType == 2) {
        // Initialize two player game
        TwoPlayers twoPlayers = TwoPlayers.getInstance(window);
        twoPlayers.init2Player(player1, player2);
        window.menu = 2;
      }
    }
    // Draw the back to main menu button
    backToMainMenu.draw();
    backToMainMenu.update();
    if (backToMainMenu.isClicked()) {
      window.menu = 0;
    }
    // Draw all images for this class
    carModMenuImages.draw();
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
        player1.getEngine().setEngine(7000, 0.6, 3000, 1000);
        // Check for right click
      } else if (buttonClick(engine) == 2) {
        // Set player 2 engine to engine 1
        player2.getEngine().setEngine(7000, 0.6, 3000, 1000);
        System.out.println("p2 e1");
      }
    } else if (engine == engines[1]) {
      // Check for left click
      if (buttonClick(engine) == 1) {
        // Set player 1 engine to engine 2
        player1.getEngine().setEngine(9000, 0.7, 5000, 2000);
        System.out.println("p1 e2");
        // Check for right click
      } else if (buttonClick(engine) == 2) {
        // Set player 2 engine to engine 2
        player2.getEngine().setEngine(9000, 0.7, 5000, 2000);
        System.out.println("p1 e2");
      }
    } else if (engine == engines[2]) {
      // Check for left click
      if (buttonClick(engine) == 1) {
        // Set player 1 engine to engine 3
        player1.getEngine().setEngine(4000, 0.3, 2000, 500);
        System.out.println("p1 e3");
        // Check for right click
      } else if (buttonClick(engine) == 2) {
        // Set player 2 engine to engine 3
        player2.getEngine().setEngine(4000, 0.3, 2000, 500);
        System.out.println("p2 e3");
      }
    } else if (engine == engines[3]) {
      // Check for left click
      if (buttonClick(engine) == 1) {
        // Set player 1 engine to engine 4
        player1.getEngine().setEngine(12000, 1, 4000, 2500);
        System.out.println("p1 e4");
        // Check for right click
      } else if (buttonClick(engine) == 2) {
        // Set player 2 engine to engine 4
        player2.getEngine().setEngine(12000, 1, 4000, 2500);
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
    if (chassi == chassis[0]) {
      // Check for left click
      if (buttonClick(chassi) == 1) {
        // Set player 1 chassis to chassis 1
        player1.getChassis().setChassis(2000, 1,1);
        System.out.println("p1 c1");
        // Check for right click
      } else if (buttonClick(chassi) == 2) {
        // Set player 2 chassis to chassis 1
        player2.getChassis().setChassis(2000, 1,1);
        System.out.println("p2 c1");
      }
    } else if (chassi == chassis[1]) {
      // Check for left click
      if (buttonClick(chassi) == 1) {
        // Set player 1 chassis to chassis 2
        player1.getChassis().setChassis(1500, 1,1);
        System.out.println("p1 c2");
        // Check for right click
      } else if (buttonClick(chassi) == 2) {
        // Set player 2 chassis to chassis 2
        player1.getChassis().setChassis(1500, 1,1);
        System.out.println("p2 c2");
      }
    } else if (chassi == chassis[2]) {
      // Check for left click
      if (buttonClick(chassi) == 1) {
        // Set player 1 chassis to chassis 3
        player1.getChassis().setChassis(1000, 1,1);
        System.out.println("p1 c3");
        // Check for right click
      } else if (buttonClick(chassi) == 2) {
        // Set player 2 chassis to chassis 3
        player2.getChassis().setChassis(1000, 1,1);
        System.out.println("p2 c3");
      }
    } else if (chassi == chassis[3]) {
      // Check for left click
      if (buttonClick(chassi) == 1) {
        // Set player 1 chassis to chassis 4
        player1.getChassis().setChassis(500, 1,1);
        System.out.println("p1 c4");
        // Check for right click
      } else if (buttonClick(chassi) == 2) {
        // Set player 2 chassis to chassis 4
        player1.getChassis().setChassis(500, 1,1);
        System.out.println("p2 c4");
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
        player1.getAero().setAero(30, 15, 500);
        System.out.println("p1 a1");
        // Check for right click
      } else if (buttonClick(aero) == 2) {
        // Set player 2 aerodynamics to aerodynamics 1
        player2.getAero().setAero(30, 15, 500);
        System.out.println("p2 a1");
      }
    } else if (aero == aerodynamics[1]) {
      // Check for left click
      if (buttonClick(aero) == 1) {
        // Set player 1 aerodynamics to aerodynamics 2
        player1.getAero().setAero(40, 20, 300);
        System.out.println("p1 a2");
        // Check for right click
      } else if (buttonClick(aero) == 2) {
        // Set player 2 aerodynamics to aerodynamics 2
        player2.getAero().setAero(40, 20, 300);
        System.out.println("p2 a2");
      }
    } else if (aero == aerodynamics[2]) {
      // Check for left click
      if (buttonClick(aero) == 1) {
        // Set player 1 aerodynamics to aerodynamics 3
        player1.getAero().setAero(10, 5, 300);
        System.out.println("p1 a3");
        // Check for right click
      } else if (buttonClick(aero) == 2) {
        // Set player 2 aerodynamics to aerodynamics 3
        player2.getAero().setAero(10, 5, 300);
        System.out.println("p2 a3");
      }
    } else if (aero == aerodynamics[3]) {
      // Check for left click
      if (buttonClick(aero) == 1) {
        // Set player 1 aerodynamics to aerodynamics 4
        player1.getAero().setAero(0, 0, 0);
        System.out.println("p1 a4");
        // Check for right click
      } else if (buttonClick(aero) == 2) {
        // Set player 2 aerodynamics to aerodynamics 4
        player2.getAero().setAero(0, 0, 0);
        System.out.println("p2 a4");
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
