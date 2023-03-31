package project;

/**
 * DisplayEngineStats, class that displays each engine's
 * statistics in the CarModMenu.
 *
 * @author James Langille
 */
public class DisplayEngineStats implements Drawable {

  private GameManager window;

  public static DisplayEngineStats instance;

  /**
   * DisplayEngineStats, private constructor to create a singleton.
   *
   * @param window of screen
   */
  private DisplayEngineStats(GameManager window) {
    this.window = window;
  }

  /**
   * getInstance, gets a singleton instance of the class.
   *
   * @param window of screen
   * @return singleton instance
   */
  public static DisplayEngineStats getInstance(GameManager window) {
    if (instance == null) {
      instance = new DisplayEngineStats(window);
    }
    return instance;
  }

  /**
   * draw, draws all engine stats onto the screen.
   */
  @Override
  public void draw() {
    int length = PartEngine.engineParts.length;
    int y = 50;
    for (int i = 0; i < length; i++) {
      engineStats(PartEngine.engineParts[i], y);
      y += 125;
    }
  }

  /**
   * engineStats, gets the stats of the specified engine.
   *
   * @param engine object
   * @param ypos of screen
   */
  private void engineStats(PartEngine engine, int ypos) {
    window.textSize(20);
    window.fill(0,0,0);
    int x = (window.displayWidth / 8) + 100;
    int y = (window.displayHeight / 5) + ypos;
    window.text("Power: " + (int) engine.getPower(), x, y);
    y += 25;
    window.text("Dropoff: " + engine.getDropoff(), x, y);
    y += 25;
    window.text("Optimal Revs: " + engine.getOpRevs(), x, y);
    y += 25;
    window.text("Weight: " + engine.getWeight(), x, y);
  }
}
