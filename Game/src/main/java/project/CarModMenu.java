package project;

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
  private CarModMenuButtons carModMenuButtons;
  // Text boxes
  protected TextBox gearInput;
  // Other data
  private GameManager window;
  private static CarModMenu instance;
  private DisplayEngineStats engineStats;
  private DisplayChassisStats chassisStats;
  private DisplayAerodynamicStats aerodynamicStats;

  /**
   * CarModMenu, private constructor to create a singleton of the class.
   *
   * @param window current window
   */
  private CarModMenu(GameManager window) {
    this.window = window;
    carModMenuImages = CarModMenuImages.getInstance(window);
    carModMenuButtons = CarModMenuButtons.getInstance(window);
    engineStats = DisplayEngineStats.getInstance(window);
    chassisStats = DisplayChassisStats.getInstance(window);
    aerodynamicStats = DisplayAerodynamicStats.getInstance(window);
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
    carModMenuButtons.setup();
    carModMenuImages.setup();
    int x = (window.displayWidth / 8) + 1100;
    int y = (window.displayHeight / 5) + 575;
    int width = 200;
    int height = 50;
    gearInput = new TextBox(new PVector(x, y), width, height, window);
  }

  /**
   * draw, draws all objects needed for this class.
   */
  @Override
  public void draw() {
    carModMenuImages.drawBackground();
    carModMenuButtons.draw();
    gearInput.draw();
    carModMenuImages.draw();
    engineStats.draw();
    chassisStats.draw();
    aerodynamicStats.draw();
  }
}
