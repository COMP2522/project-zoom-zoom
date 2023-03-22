package project;

import java.awt.*;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * CarModMenu, menu that allows users to change the parts to their cars.
 *
 * @author James Langille
 */
public class CarModMenu {

  private final Window window;
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
  private Button brakes1;
  private Button brakes2;
  private Button brakes3;
  private Button brakes4;
  private ArrayList<Button> brakes = new ArrayList<Button>();
  private Button aero1;
  private Button aero2;
  private Button aero3;
  private Button aero4;
  private ArrayList<Button> aerodynamics = new ArrayList<Button>();
  private Button tires1;
  private Button tires2;
  private Button tires3;
  private Button tires4;
  private ArrayList<Button> tires = new ArrayList<Button>();

  /**
   * CarModMenu, private constructor to create a singleton of the class.
   *
   * @param window current window
   */
  private CarModMenu(Window window) {
    this.window = window;
    mainMenu = MainMenu.getInstance(window);
  }

  /**
   * getInstance, method that creates a singleton of the class.
   *
   * @param window window class
   * @return a car mod menu object
   */
  public static CarModMenu getInstance(Window window) {
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
    // Instantiate the brakes buttons
    brakes1 = new Button(new PVector((window.displayWidth / 8) + 200, (window.displayHeight / 5) + 50),
        200, 100, "Brakes 1", new Color(255, 0, 0), window);
    brakes2 = new Button(new PVector((window.displayWidth / 8) + 200, (window.displayHeight / 5) + 175),
        200, 100, "Brakes 2", new Color(255, 0, 0), window);
    brakes3 = new Button(new PVector((window.displayWidth / 8) + 200, (window.displayHeight / 5) + 300),
        200, 100, "Brakes 3", new Color(255, 0, 0), window);
    brakes4 = new Button(new PVector((window.displayWidth / 8) + 200, (window.displayHeight / 5) + 425),
        200, 100, "Brakes 4", new Color(255, 0, 0), window);
    // Add each brakes into the brakes arraylist
    brakes.add(brakes1);
    brakes.add(brakes2);
    brakes.add(brakes3);
    brakes.add(brakes4);
    // Instantiate the chassis buttons
    chassis1 = new Button(new PVector((window.displayWidth / 8) + 500, (window.displayHeight / 5) + 50),
        200, 100, "Chassis 1", new Color(255, 0, 0), window);
    chassis2 = new Button(new PVector((window.displayWidth / 8) + 500, (window.displayHeight / 5) + 175),
        200, 100, "Chassis 2", new Color(255, 0, 0), window);
    chassis3 = new Button(new PVector((window.displayWidth / 8) + 500, (window.displayHeight / 5) + 300),
        200, 100, "Chassis 3", new Color(255, 0, 0), window);
    chassis4 = new Button(new PVector((window.displayWidth / 8) + 500, (window.displayHeight / 5) + 425),
        200, 100, "Chassis 4", new Color(255, 0, 0), window);
    // Add each chassis into the chassis arraylist
    chassis.add(chassis1);
    chassis.add(chassis2);
    chassis.add(chassis3);
    chassis.add(chassis4);
    // Instantiate the aerodynamics buttons
    aero1 = new Button(new PVector((window.displayWidth / 8) + 800, (window.displayHeight / 5) + 50),
        200, 100, "Aero 1", new Color(255, 0, 0), window);
    aero2 = new Button(new PVector((window.displayWidth / 8) + 800, (window.displayHeight / 5) + 175),
        200, 100, "Aero 2", new Color(255, 0, 0), window);
    aero3 = new Button(new PVector((window.displayWidth / 8) + 800, (window.displayHeight / 5) + 300),
        200, 100, "Aero 3", new Color(255, 0, 0), window);
    aero4 = new Button(new PVector((window.displayWidth / 8) + 800, (window.displayHeight / 5) + 425),
        200, 100, "Aero 4", new Color(255, 0, 0), window);
    // Add each aerodynamics into the aerodynamics arraylist
    aerodynamics.add(aero1);
    aerodynamics.add(aero2);
    aerodynamics.add(aero3);
    aerodynamics.add(aero4);
    // Instantiate the gears buttons
    tires1 = new Button(new PVector((window.displayWidth / 8) + 1100, (window.displayHeight / 5) + 50),
        200, 100, "Tires 1", new Color(255, 0, 0), window);
    tires2 = new Button(new PVector((window.displayWidth / 8) + 1100, (window.displayHeight / 5) + 175),
        200, 100, "Tires 2", new Color(255, 0, 0), window);
    tires3 = new Button(new PVector((window.displayWidth / 8) + 1100, (window.displayHeight / 5) + 300),
        200, 100, "Tires 3", new Color(255, 0, 0), window);
    tires4 = new Button(new PVector((window.displayWidth / 8) + 1100, (window.displayHeight / 5) + 425),
        200, 100, "Tires 4", new Color(255, 0, 0), window);
    // Add each tires into the tires arraylist
    tires.add(tires1);
    tires.add(tires2);
    tires.add(tires3);
    tires.add(tires4);
  }

  public void draw() {
    window.background(64, 64, 64);
    window.fill(0);
    window.text("Car Modification", window.displayWidth / 2 + 10,window.displayHeight / 10);
    // Create text for each car part
    window.textSize(30);
    window.text("Engine", (window.displayWidth / 8), window.displayHeight / 5);
    window.text("Brakes", (window.displayWidth / 8) + 300, window.displayHeight / 5);
    window.text("Chassis", (window.displayWidth / 8) + 600, window.displayHeight / 5);
    window.text("Aerodynamics", (window.displayWidth / 8) + 900, window.displayHeight / 5);
    window.text("Tires", (window.displayWidth / 8) + 1200, window.displayHeight / 5);

    // Draw buttons for the engine
    for (Button engine : engines) {
      engine.draw();
      engine.update();
      // If an engine button is clicked, update the player's part to that engine
      if (engine.isClicked()) {

      }
    }

    // Draw buttons for the brakes
    for (Button brake : brakes) {
      brake.draw();
      brake.update();
      // If a brake button is clicked, update the player's part to that brake
      if (brake.isClicked()) {

      }
    }

    // Draw buttons for the chassis
    for (Button chassi : chassis) {
      chassi.draw();
      chassi.update();
      if (chassi.isClicked()) {

      }
    }

    // Draw buttons for the aerodynamics
    for (Button aero : aerodynamics) {
      aero.draw();
      aero.update();
      // If an aero button is clicked, update the player's part to that aerodynamic
      if (aero.isClicked()) {

      }
    }

    // Draw buttons for the tires
    for (Button tire : tires) {
      tire.draw();
      tire.update();
      // If a tire button is clicked, update the player's part to that tire.
      if (tire.isClicked()) {

      }
    }

    // Draw start race button
    startRace.draw();
    startRace.update();
    if (startRace.isClicked()) {
      if (mainMenu.gameType == 1) {
        window.menu = 1;
      } else if (mainMenu.gameType == 2) {
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
}
