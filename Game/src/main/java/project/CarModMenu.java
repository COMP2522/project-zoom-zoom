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
  // Player objects
  static Player player1;
  static Player player2;

  /**
   * CarModMenu, private constructor to create a singleton of the class.
   *
   * @param window current window
   */
  private CarModMenu(GameManager window, Player p1, Player p2) {
    player1 = p1;
    player2 = p2;
    this.window = window;
    carModMenuImages = CarModMenuImages.getInstance(window);
    carModMenuButtons = CarModMenuButtons.getInstance(window);
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
    carModMenuButtons.setup();
    carModMenuImages.setup();
    gearInput = new TextBox(new PVector((window.displayWidth / 8) + 1100,
        (window.displayHeight / 5) + 550), 200, 40, window);
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
  }
}
