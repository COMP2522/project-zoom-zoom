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
  private PImage bgImage;
  private PImage menuTitleImage;
  private PImage startRaceImage;
  private PImage mainMenuImage;
  private PImage[] engineImages = new PImage[4];
  private PImage[] chassisImages = new PImage[4];
  private PImage[] aerodynamicImages = new PImage[4];
  private PImage background;
  private PImage[] partTitleImages = new PImage[4];
  // Buttons
  private Button backToMainMenu;
  private Button startRace;
  private Button[] engines = new Button[4];
  private Button[] chassis = new Button[4];
  private Button[] aerodynamics = new Button[4];
  private Button[] gears = new Button[4];
  // Image file names
  private String[] engineImageNames;
  private String[] chassisImageNames;
  private String[] aeroImageNames;
  private String[] titleImageNames;
  // Other data
  private final GameManager window;
  private static CarModMenu instance;
  private MainMenu mainMenu;
  private Stopwatch stopwatch;
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
          (window.displayHeight / 5) + buffer), 200, 100,
          "Gear " + (i + 1), new Color(255, 0, 0), window);
      buffer += 125;
    }

    background = window.loadImage("Game/images/BGImage.png");
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
    // Instantiate other buttons
    backToMainMenu = new Button(new PVector((window.displayWidth / 2) - 100, 750), 200, 50,
        "", new Color(0, 0, 150), window);
    startRace = new Button(new PVector(window.displayWidth - 225, 750), 200, 50,
        "", new Color(0, 150, 0), window);
    // Get image file names
    FileReader.readFiles("Game/images/");
    // Instantiate images for engine buttons
    engineImageNames = FileReader.engineImages();
    for (int i = 0; i < engineImages.length; i++) {
      engineImages[i] = window.loadImage("Game/images/" + engineImageNames[i]);
    }
    // Instantiate images for chassis buttons
    chassisImageNames = FileReader.chassisImages();
    for (int i = 0; i < chassisImages.length; i++) {
      chassisImages[i] = window.loadImage("Game/images/" + chassisImageNames[i]);
    }
    // Instantiate images for aero buttons
    aeroImageNames = FileReader.aerodynamicsImages();
    for (int i = 0; i < aerodynamicImages.length; i++) {
      aerodynamicImages[i] = window.loadImage("Game/images/" + aeroImageNames[i]);
    }
    /* Instantiate a slightly different background image
     if one player or two player game was selected. */
    if (mainMenu.gameType == 1) {
      bgImage = window.loadImage("Game/images/BGImage2.png");
    } else if (mainMenu.gameType == 2) {
      bgImage = window.loadImage("Game/images/BGImage.png");
    }
    // Instantiate part title images
    titleImageNames = FileReader.carModTitles();
    for (int i = 0; i < partTitleImages.length; i++) {
      partTitleImages[i] = window.loadImage("Game/images/" + titleImageNames[i]);
    }
    // Instantiate other images
    menuTitleImage = window.loadImage("Game/images/CarModTitle.png");
    startRaceImage = window.loadImage("Game/images/StartRace.png");
    mainMenuImage = window.loadImage("Game/images/MainMenu.png");
  }

  @Override
  public void draw() {
    // stopwatch = Stopwatch.getInstance(window);
    window.background(64, 64, 64);
    window.fill(0);
    window.image(background, 0, 0, window.displayWidth, window.displayHeight);
    window.text("Car Modification", window.displayWidth / 2 + 10, window.displayHeight / 10);
    // Create text for each car part
    // Draw background image
    window.textSize(30);
    window.image(bgImage, 0, 0, window.displayWidth, window.displayHeight);
    buffer = -100;
    for (int i = 0; i < partTitleImages.length; i ++) {
      window.image(partTitleImages[i], (window.displayWidth / 8) + buffer, window.displayHeight / 5);
      if (i == 1) {
        // update aero x position
        buffer = 1125;
      } else if (i == 2) {
        // update gear x position
        buffer = 650;
      } else {
        buffer += 400;
      }
    }

    // Draw buttons for the engine
    for (Button engine : engines) {
      engine.draw();
      engine.update();
      this.setPlayerEngine(engine);
    }
    buffer = 50;
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
    // Draw text images
    window.image(menuTitleImage, window.displayWidth / 4 + 75, window.displayHeight / 10);
    window.image(startRaceImage, window.displayWidth - 220, 760);
    window.image(mainMenuImage, (window.displayWidth / 2) - 90, 760);
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
