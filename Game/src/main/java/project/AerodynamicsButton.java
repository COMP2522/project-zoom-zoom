package project;

import java.util.Arrays;
import processing.core.PVector;

/**
 * AerodynamicsButton, button subclass designated for aerodynamics parts.
 *
 * @author James Langille
 */
public class AerodynamicsButton extends Button {
  /** Constants for aerodynamics button. */
  private static final float WIDTH = 200;
  private static final float HEIGHT = 100;
  private static final int AERO_AMOUNT = 4;
  /** Other data. */
  private static AerodynamicsButton[] aerodynamics =
      new AerodynamicsButton[AERO_AMOUNT];
  private static Player player1 = GameManager.player1;
  private static Player player2 = GameManager.player2;
  private static int x = (window.displayWidth / 8) + 700;
  private static int y;
  private static int aeroLength = PartAero.aeroParts.length;
  // Buffer used to adjust x or y position of button
  private static int buffer;

  /**
   * Constructor to create an aerodynamics button object.
   *
   * @param position of button on window
   * @param window   of game
   */
  public AerodynamicsButton(PVector position, GameManager window) {
    super(position, WIDTH, HEIGHT, window);
  }

  /**
   * setUpAerodynamicsButtons, sets up all aero buttons needed
   * for CarModMenu.
   */
  public static void setupAerodynamicsButtons() {
    buffer = 50;
    PVector position;
    for (int i = 0; i < aerodynamics.length; i++) {
      y = (window.displayHeight / 5) + buffer;
      position = new PVector(x, y);
      aerodynamics[i] = new AerodynamicsButton(position, window);
      buffer += 125;
    }
  }

  /**
   * drawAerodynamics, draws all aerodynamics buttons.
   */
  public static void drawAerodynamics() {
    for (Button aero : aerodynamics) {
      window.noStroke();
      window.fill(0, 0);
      aero.draw();
      aero.click();
      setPlayerAerodynamics(aero);
      drawPlayer1Indicator();
      if (window.gameType == 2) {
        drawPlayer2Indicator();
      }
    }
  }

  /**
   * sets the player's aerodynamics part depending on which
   * aerodynamics button was clicked.
   *
   * @param aero button
   */
  private static void setPlayerAerodynamics(Button aero) {
    int aeroIndex = Arrays.asList(aerodynamics).indexOf(aero);
    if (aero.isLeftClicked()) {
      player1.setAero(PartAero.aeroParts[aeroIndex]);
    } else if (aero.isRightClicked()) {
      player2.setAero(PartAero.aeroParts[aeroIndex]);
    }
  }

  /**
   * drawPlayer1Indicator, draws a circle next to an aero button
   *  to show player 1's current aerodynamics.
   */
  private static void drawPlayer1Indicator() {
    buffer = 50;
    for (int i = 0; i < aeroLength; i++) {
      y = (window.displayHeight / 5) + buffer;
      if (player1.getAero().equals(PartAero.aeroParts[i])) {
        window.fill(0, 255, 0);
        window.ellipse(x, y, 20, 20);
      }
      buffer += 125;
    }
  }

  /**
   * drawPlayer2Indicator, draws a circle next to an engine button
   *  to show player 2's current engine.
   */
  private static void drawPlayer2Indicator() {
    int x = (window.displayWidth / 8) + 890;
    buffer = 150;
    for (int i = 0; i < aeroLength; i++) {
      int y = (window.displayHeight / 5) + buffer;
      if (player2.getAero().equals(PartAero.aeroParts[i])) {
        window.fill(0, 255, 247);
        window.ellipse(x, y, 20, 20);
      }
      buffer += 125;
    }
  }


}
