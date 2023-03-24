package project;

import java.awt.*;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * CarModMenu, menu that allows users to change the parts to their cars.
 *
 * @author James Langille
 */
public class CarModMenu {
  private PImage engine1image;
  private PImage engine2image;
  private PImage engine3image;
  private PImage engine4image;
  private PImage chassis1image;
  private PImage chassis2image;
  private PImage chassis3image;
  private PImage chassis4image;
  private PImage aero1image;
  private PImage aero2image;
  private PImage aero3image;
  private PImage aero4image;
  private final GameManager window;
  Stopwatch stopwatch;
  private static CarModMenu instance;
  private MainMenu mainMenu;
  private Button backToMainMenu;
  private Button startRace;
  private Button engine1;
  private Button engine2;
  private Button engine3;
  private Button engine4;
  private ArrayList<Button> engines = new ArrayList<Button>();
  private Button chassis1;
  private Button chassis2;
  private Button chassis3;
  private Button chassis4;
  private ArrayList<Button> chassis = new ArrayList<Button>();
  private Button aero1;
  private Button aero2;
  private Button aero3;
  private Button aero4;
  private ArrayList<Button> aerodynamics = new ArrayList<Button>();
  private Button gears1;
  private Button gears2;
  private Button gears3;
  private Button gears4;
  private ArrayList<Button> gears = new ArrayList<Button>();

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
   * setup, setup all buttons and text needed for this
   */
  public void setup() {
    window.textAlign(PApplet.CENTER, PApplet.CENTER);
    window.textSize(40);

    backToMainMenu = new Button(new PVector((float) (window.displayWidth / 2) - 100, 750), 200, 50,
        "Main Menu", new Color(52, 152, 235), window);
    startRace = new Button(new PVector(window.displayWidth - 225, 750), 200, 50,
        "Start Race!", new Color(0, 255, 0), window);
    // Instantiate the engine buttons
    engine1 = new Button(new PVector((window.displayWidth / 8) - 100, (window.displayHeight / 5) + 50),
        200, 100, "Engine 1", new Color(255, 0, 0), window);
    engine2 = new Button(new PVector((window.displayWidth / 8) - 100, (window.displayHeight / 5) + 175),
        200, 100, "Engine 2", new Color(255, 0, 0), window);
    engine3 = new Button(new PVector((window.displayWidth / 8) - 100, (window.displayHeight / 5) + 300),
        200, 100, "Engine 3", new Color(255, 0, 0), window);
    engine4 = new Button(new PVector((window.displayWidth / 8) - 100, (window.displayHeight / 5) + 425),
        200, 100, "Engine 4", new Color(255, 0, 0), window);
    // Add each engine to the engines arraylist
    engines.add(engine1);
    engines.add(engine2);
    engines.add(engine3);
    engines.add(engine4);
    // Instantiate the chassis buttons
    chassis1 = new Button(new PVector((window.displayWidth / 8) + 300, (window.displayHeight / 5) + 50),
        200, 100, "Chassis 1", new Color(255, 0, 0), window);
    chassis2 = new Button(new PVector((window.displayWidth / 8) + 300, (window.displayHeight / 5) + 175),
        200, 100, "Chassis 2", new Color(255, 0, 0), window);
    chassis3 = new Button(new PVector((window.displayWidth / 8) + 300, (window.displayHeight / 5) + 300),
        200, 100, "Chassis 3", new Color(255, 0, 0), window);
    chassis4 = new Button(new PVector((window.displayWidth / 8) + 300, (window.displayHeight / 5) + 425),
        200, 100, "Chassis 4", new Color(255, 0, 0), window);
    // Add each chassis into the chassis arraylist
    chassis.add(chassis1);
    chassis.add(chassis2);
    chassis.add(chassis3);
    chassis.add(chassis4);
    // Instantiate the aerodynamics buttons
    aero1 = new Button(new PVector((window.displayWidth / 8) + 700, (window.displayHeight / 5) + 50),
        200, 100, "Aero 1", new Color(255, 0, 0), window);
    aero2 = new Button(new PVector((window.displayWidth / 8) + 700, (window.displayHeight / 5) + 175),
        200, 100, "Aero 2", new Color(255, 0, 0), window);
    aero3 = new Button(new PVector((window.displayWidth / 8) + 700, (window.displayHeight / 5) + 300),
        200, 100, "Aero 3", new Color(255, 0, 0), window);
    aero4 = new Button(new PVector((window.displayWidth / 8) + 700, (window.displayHeight / 5) + 425),
        200, 100, "Aero 4", new Color(255, 0, 0), window);
    // Add each aerodynamics into the aerodynamics arraylist
    aerodynamics.add(aero1);
    aerodynamics.add(aero2);
    aerodynamics.add(aero3);
    aerodynamics.add(aero4);
    // Instantiate the gears buttons
    gears1 = new Button(new PVector((window.displayWidth / 8) + 1100, (window.displayHeight / 5) + 50),
        200, 100, "Gears 1", new Color(255, 0, 0), window);
    gears2 = new Button(new PVector((window.displayWidth / 8) + 1100, (window.displayHeight / 5) + 175),
        200, 100, "Gears 2", new Color(255, 0, 0), window);
    gears3 = new Button(new PVector((window.displayWidth / 8) + 1100, (window.displayHeight / 5) + 300),
        200, 100, "Gears 3", new Color(255, 0, 0), window);
    gears4 = new Button(new PVector((window.displayWidth / 8) + 1100, (window.displayHeight / 5) + 425),
        200, 100, "Gears 4", new Color(255, 0, 0), window);
    // Add each tires into the tires arraylist
    gears.add(gears1);
    gears.add(gears2);
    gears.add(gears3);
    gears.add(gears4);

    // Set up images for engine buttons
    engine1image = window.loadImage("Game/images/engine1.png");
    engine2image = window.loadImage("Game/images/engine2.png");
    engine3image = window.loadImage("Game/images/engine3.png");
    engine4image = window.loadImage("Game/images/engine4.png");
    // Set up images for chassis buttons
    chassis1image = window.loadImage("Game/images/chassis1.png");
    chassis2image = window.loadImage("Game/images/chassis2.png");
    chassis3image = window.loadImage("Game/images/chassis3.png");
    chassis4image = window.loadImage("Game/images/chassis4.png");
    // Set up images for aero buttons
    aero1image = window.loadImage("Game/images/aero1.png");
    aero2image = window.loadImage("Game/images/aero2.png");
    aero3image = window.loadImage("Game/images/aero3.png");
    aero4image = window.loadImage("Game/images/aero4.png");
  }

  public void draw() {
    stopwatch = Stopwatch.getInstance(window);
    window.background(64, 64, 64);
    window.fill(0);
    window.text("Car Modification", window.displayWidth / 2 + 10,window.displayHeight / 10);
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
    // Draw images for each engine
    window.image(engine1image, (window.displayWidth / 8) - 100, (window.displayHeight / 5) + 50);
    window.image(engine2image, (window.displayWidth / 8) - 100, (window.displayHeight / 5) + 175);
    window.image(engine3image, (window.displayWidth / 8) - 100, (window.displayHeight / 5) + 300);
    window.image(engine4image, (window.displayWidth / 8) - 100, (window.displayHeight / 5) + 425);

    // Draw buttons for the chassis
    for (Button chassi : chassis) {
      chassi.draw();
      chassi.update();

    }
    // Draw images for each chassis
    window.image(chassis1image, (window.displayWidth / 8) + 300, (window.displayHeight / 5) + 50);
    window.image(chassis2image, (window.displayWidth / 8) + 300, (window.displayHeight / 5) + 175);
    window.image(chassis3image, (window.displayWidth / 8) + 300, (window.displayHeight / 5) + 300);
    window.image(chassis4image, (window.displayWidth / 8) + 300, (window.displayHeight / 5) + 425);

    // Draw buttons for the aerodynamics
    for (Button aero : aerodynamics) {
      aero.draw();
      aero.update();
      // If an aero button is clicked, update the player's part to that aerodynamic
      if (aero.isClicked()) {

      }
    }
    // Draw images for each aero
    window.image(aero1image, (window.displayWidth / 8) + 700, (window.displayHeight / 5) + 50);
    window.image(aero2image, (window.displayWidth / 8) + 700, (window.displayHeight / 5) + 175);
    window.image(aero3image, (window.displayWidth / 8) + 700, (window.displayHeight / 5) + 300);
    window.image(aero4image, (window.displayWidth / 8) + 700, (window.displayHeight / 5) + 425);

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
        singlePlayer.setTimerCheck(true);
        window.menu = 1;
      } else if (mainMenu.gameType == 2) {
        stopwatch.resetTimer();
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
   * setPlayerEngine, sets the player's engine part depending one which
   * engine button was clicked.
   *
   * @param engine button
   */
  private void setPlayerEngine(Button engine) {
    if (engine == engine1) {
      // Check for left click
      if (engine1.isClicked() && window.mouseButton == PApplet.LEFT) {
        // Set player 1 engine to engine 1
        System.out.println("test");
        // Check for right click
      } else if (engine1.isClicked() && window.mouseButton == PApplet.RIGHT) {
        // Set player 2 engine to engine 1
        System.out.println("right test");
      }
    } else if (engine == engine2) {
      // Check for left click
      if (engine2.isClicked() && window.mouseButton == PApplet.LEFT) {
        // Set player 1 engine to engine 2
        System.out.println("test 2");
        // Check for right click
      } else if (engine2.isClicked() && window.mouseButton == PApplet.RIGHT) {
        // Set player 2 engine to engine 2
        System.out.println("right test 2");
      }
    } else if (engine == engine3) {
      // Check for left click
      if (engine3.isClicked() && window.mouseButton == PApplet.LEFT) {
        // Set player 1 engine to engine 3
        System.out.println("test 3");
        // Check for right click
      } else if (engine3.isClicked() && window.mouseButton == PApplet.RIGHT) {
        // Set player 2 engine to engine 3
        System.out.println("right test 3");
      }
    } else if (engine == engine4) {
      // Check for left click
      if (engine4.isClicked() && window.mouseButton == PApplet.LEFT) {
        // Set player 1 engine to engine 4
        System.out.println("test 4");
        // Check for right click
      } else if (engine4.isClicked() && window.mouseButton == PApplet.RIGHT) {
        // Set player 2 engine to engine 4
        System.out.println("right test 4");
      }
    }
  }
}
