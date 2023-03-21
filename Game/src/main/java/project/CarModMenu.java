package project;

import java.awt.*;

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
  private Button chassis1;
  private Button chassis2;
  private Button chassis3;
  private Button chassis4;
  private Button brakes1;
  private Button brakes2;
  private Button brakes3;
  private Button brakes4;
  private Button gears1;
  private Button gears2;
  private Button gears3;
  private Button gears4;

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
    // Instantiate the brakes buttons
    brakes1 = new Button(new PVector((window.displayWidth / 8) + 300, (window.displayHeight / 5) + 50),
        200, 100, "Brakes 1", new Color(255, 0, 0), window);
    brakes2 = new Button(new PVector((window.displayWidth / 8) + 300, (window.displayHeight / 5) + 175),
        200, 100, "Brakes 2", new Color(255, 0, 0), window);
    brakes3 = new Button(new PVector((window.displayWidth / 8) + 300, (window.displayHeight / 5) + 300),
        200, 100, "Brakes 3", new Color(255, 0, 0), window);
    brakes4 = new Button(new PVector((window.displayWidth / 8) + 300, (window.displayHeight / 5) + 425),
        200, 100, "Brakes 4", new Color(255, 0, 0), window);
  }

  public void draw() {
    window.background(64, 64, 64);
    window.fill(0);
    window.text("Car Modification", window.displayWidth / 2 + 10,window.displayHeight / 10);
    // Create text for each car part
    window.text("Engine", (window.displayWidth / 8), window.displayHeight / 5);
    window.text("Brakes", (window.displayWidth / 8) + 400, window.displayHeight / 5);
    window.text("Chassis", (window.displayWidth / 8) + 800, window.displayHeight / 5);
    window.text("Gears", (window.displayWidth / 8) + 1200, window.displayHeight / 5);

    // Draw buttons for the engine
    engine1.draw();
    engine1.update();
    engine2.draw();
    engine2.update();
    engine3.draw();
    engine3.update();
    engine4.draw();
    engine4.update();

    // Draw buttons for the brakes
    brakes1.draw();
    brakes1.update();
    brakes2.draw();
    brakes2.update();
    brakes3.draw();
    brakes3.update();
    brakes4.draw();
    brakes4.update();

    // Draw buttons for the chassis


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
