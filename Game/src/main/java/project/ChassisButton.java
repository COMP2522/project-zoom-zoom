package project;

import java.util.Arrays;
import processing.core.PVector;

/**
 * ChassisButton, button subclass designated for chassis parts.
 *
 * @author James Langille
 */
public class ChassisButton extends Button {
  /** Constants for chassis button. */
  private static final float WIDTH = 200;
  private static final float HEIGHT = 100;
  /** Other data. */
  private static ChassisButton[] chassis = new ChassisButton[4];
  private static Player player1 = GameManager.player1;
  private static Player player2 = GameManager.player2;

  /**
   * Constructor to create a chassis button object.
   *
   * @param position of button on window
   * @param title    text of button
   * @param window   of game
   */
  public ChassisButton(PVector position, String title, GameManager window) {
    super(position, WIDTH, HEIGHT, title, window);
  }

  /**
   * setUpChassisButtons, sets up the chassis buttons needed for CarModMenu.
   */
  public static void setUpChassisButtons() {
    int buffer = 50;
    int x = (window.displayWidth / 8) + 300;
    int y;
    PVector position;
    for (int i = 0; i < chassis.length; i++) {
      y = (window.displayHeight / 5) + buffer;
      position = new PVector(x, y);
      chassis[i] = new ChassisButton(position, "", window);
      buffer += 125;
    }
  }

  /**
   * drawChassis, draws all chassis buttons.
   */
  public static void drawChassis() {
    for (Button chassi : chassis) {
      chassi.draw();
      chassi.click();
      setPlayerChassis(chassi);
    }
  }

  /**
   * setPlayerChassis, sets the player's chassis part depending on which
   * chassis button was clicked.
   *
   * @param chassi button
   */
  private static void setPlayerChassis(Button chassi) {
    int chassisIndex = Arrays.asList(chassis).indexOf(chassi);

    if (chassi.isLeftClicked()) {
      player1.getChassis().setChassis(PartChassis.chassisParts[chassisIndex]);
      System.out.println("p1 c" + (chassisIndex + 1));
    } else if (chassi.isRightClicked()) {
      player2.getChassis().setChassis(PartChassis.chassisParts[chassisIndex]);
      System.out.println("p2 c" + (chassisIndex + 1));
    }
  }
}
