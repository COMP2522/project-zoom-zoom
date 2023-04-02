package project;

/**
 * DisplayAerodynamicStats, class that displays each
 * aerodynamics' statistics in the CarModMenu.
 *
 * @author James Langille
 */
public class DisplayAerodynamicStats implements Drawable {

  private GameManager window;

  public static DisplayAerodynamicStats instance;

  /**
   * DisplayAerodynamicStats, private constructor to create a singleton.
   *
   * @param window of screen
   */
  private DisplayAerodynamicStats(GameManager window) {
    this.window = window;
  }

  /**
   * getInstance, gets a singleton instance of the class.
   *
   * @param window of screen
   * @return singleton instance
   */
  public static DisplayAerodynamicStats getInstance(GameManager window) {
    if (instance == null) {
      instance = new DisplayAerodynamicStats(window);
    }
    return instance;
  }

  /**
   * draw, draws all aerodynamics' stats onto the screen.
   */
  @Override
  public void draw() {
    int length = PartAero.aeroParts.length;
    int y = 50;
    for (int i = 0; i < length; i++) {
      aeroStats(PartAero.aeroParts[i], y);
      y += 125;
    }
  }

  /**
   * aeroStats, gets the stats of the specified aerodynamics.
   *
   * @param aero object
   * @param ypos of screen
   */
  private void aeroStats(PartAero aero, int ypos) {
    int medium = 20;
    window.textSize(medium);
    window.fill(0);
    int x = (window.displayWidth / 8) + 900;
    int y = (window.displayHeight / 5) + ypos;
    window.text("Down Force: " + aero.getDownForce(), x, y);
    y += 35;
    window.text("Drag: " + aero.getDrag(), x, y);
    y += 35;
    window.text("Weight: " + aero.getWeight(), x, y);
  }
}
