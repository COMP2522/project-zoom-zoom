package project;

/**
 * DisplayChassisStats, class that displays each chassis
 * statistics in the CarModMenu.
 *
 * @author James Langille
 */
public class DisplayChassisStats implements Drawable {

  private GameManager window;

  public static DisplayChassisStats instance;

  /**
   * DisplayChassisStats, private constructor to create a singleton.
   *
   * @param window of screen
   */
  private DisplayChassisStats(GameManager window) {
    this.window = window;
  }

  /**
   * getInstance, gets a singleton instance of the class.
   *
   * @param window of screen
   * @return singleton instance
   */
  public static DisplayChassisStats getInstance(GameManager window) {
    if (instance == null) {
      instance = new DisplayChassisStats(window);
    }
    return instance;
  }

  /**
   * draw, draws all chassis stats onto the screen.
   */
  @Override
  public void draw() {
    int length = PartChassis.chassisParts.length;
    int y = 50;
    for (int i = 0; i < length; i++) {
      chassisStats(PartChassis.chassisParts[i], y);
      y += 125;
    }
  }

  /**
   * chassisStats, gets the stats of the specified chassis.
   *
   * @param chassis object
   * @param ypos of screen
   */
  private void chassisStats(PartChassis chassis, int ypos) {
    window.textSize(20);
    window.fill(0);
    int x = (window.displayWidth / 8) + 500;
    int y = (window.displayHeight / 5) + ypos;
    window.text("Wheel Base X: " + chassis.getWheelBaseX(), x, y);
    y += 35;
    window.text("Wheel Base Y: " + chassis.getWheelBaseY(), x, y);
    y += 35;
    window.text("Weight: " + chassis.getWeight(), x, y);
  }
}
