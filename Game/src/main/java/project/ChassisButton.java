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
  private static final int CHASSIS_AMOUNT = 4;
  /** Other data. */
  private static ChassisButton[] chassis = new ChassisButton[CHASSIS_AMOUNT];
  private static Player player1 = GameManager.player1;
  private static Player player2 = GameManager.player2;
  private static int x = (window.displayWidth / 8) + 300;
  private static int y;
  private static int chassisLength = PartChassis.chassisParts.length;
  // Buffer used to adjust x or y position of button
  private static int buffer;

  /**
   * Constructor to create a chassis button object.
   *
   * @param position of button on window
   * @param window   of game
   */
  public ChassisButton(PVector position, GameManager window) {
    super(position, WIDTH, HEIGHT, window);
  }

  /**
   * setUpChassisButtons, sets up the chassis buttons needed for CarModMenu.
   */
  public static void setupChassisButtons() {
    buffer = 50;
    PVector position;
    for (int i = 0; i < chassis.length; i++) {
      y = (window.displayHeight / 5) + buffer;
      position = new PVector(x, y);
      chassis[i] = new ChassisButton(position, window);
      buffer += 125;
    }
  }

  /**
   * drawChassis, draws all chassis buttons.
   */
  public static void drawChassis() {
    for (Button chassi : chassis) {
      window.noStroke();
      window.fill(0, 0);
      chassi.draw();
      chassi.click();
      setPlayerChassis(chassi);
      drawPlayer1Indicator();
      if (window.gameType == 2) {
        drawPlayer2Indicator();
      }
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
      player1.setChassis(PartChassis.chassisParts[chassisIndex]);
    } else if (chassi.isRightClicked()) {
      player2.setChassis(PartChassis.chassisParts[chassisIndex]);
    }
  }

  /**
   * drawPlayer1Indicator, draws a circle next to a chassis button
   *  to show player 1's current chassis.
   */
  private static void drawPlayer1Indicator() {
    buffer = 50;
    for (int i = 0; i < chassisLength; i++) {
      y = (window.displayHeight / 5) + buffer;
      if (player1.getChassis().equals(PartChassis.chassisParts[i])) {
        window.fill(0, 255, 0);
        window.ellipse(x, y, 20, 20);
      }
      buffer += 125;
    }
  }

  /**
   * drawPlayer2Indicator, draws a circle next to a chassis button
   *  to show player 2's current chassis.
   */
  private static void drawPlayer2Indicator() {
    int x = (window.displayWidth / 8) + 490;
    buffer = 150;
    for (int i = 0; i < chassisLength; i++) {
      int y = (window.displayHeight / 5) + buffer;
      if (player2.getChassis().equals(PartChassis.chassisParts[i])) {
        window.fill(0, 255, 247);
        window.ellipse(x, y, 20, 20);
      }
      buffer += 125;
    }
  }
}
