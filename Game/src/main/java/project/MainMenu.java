package project;

/**
 * MainMenu, menu that appears once the game has been launched.
 *
 * @author James Langille
 */
public class MainMenu implements Drawable {
  protected static GameManager window;
  private static MainMenu instance;

  /**
   * Main menu, private constructor to create a singleton of the class.
   *
   * @param window current window
   */
  private MainMenu(GameManager window) {
    MainMenu.window = window;
  }

  /**
   * getInstance, method that creates a singleton of the class.
   *
   * @param window window class
   * @return a main menu object
   */
  public static MainMenu getInstance(GameManager window) {
    if (instance == null) {
      instance = new MainMenu(window);
    }
    return instance;
  }

  /**
   * setup, Sets up all objects needed for the main menu.
   */
  public void setup() {
    MainMenuButtons.setupButtons();
    MainMenuImages.setupImages();
  }

  /**
   * draw, Draws all objects that are needed for this menu.
   */
  @Override
  public void draw() {
    MainMenuImages.drawBackground();
    MainMenuButtons.drawButtons();
    MainMenuImages.drawImages();
  }

}