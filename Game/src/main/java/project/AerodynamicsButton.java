package project;

import processing.core.PVector;

import java.awt.*;
import java.util.Arrays;

/**
 * AerodynamicsButton, button subclass designated for aerodynamics parts.
 *
 * @author James Langille
 */
public class AerodynamicsButton extends Button {
  /** Constants for aerodynamics button. */
  private static final float WIDTH = 200;
  private static final float HEIGHT = 100;
  /** Other data. */
  private static AerodynamicsButton[] aerodynamics = new AerodynamicsButton[4];
  private static Player player1 = GameManager.player1;
  private static Player player2 = GameManager.player2;

  /**
   * Constructor to create an aerodynamics button object.
   *
   * @param position of button on window
   * @param title    text of button
   * @param window   of game
   */
  public AerodynamicsButton(PVector position, String title, GameManager window) {
    super(position, WIDTH, HEIGHT, title, window);
  }

  /**
   * setUpAerodynamicsButtons, sets up all aero buttons needed
   * for CarModMenu.
   */
  public static void setupAerodynamicsButtons() {
    int buffer = 50;
    int x = (window.displayWidth / 8) + 700;
    int y;
    PVector position;
    for (int i = 0; i < aerodynamics.length; i++) {
      y = (window.displayHeight / 5) + buffer;
      position = new PVector(x, y);
      aerodynamics[i] = new AerodynamicsButton(position, "", window);
      buffer += 125;
    }
  }

  /**
   * drawAerodynamics, draws all aerodynamics buttons.
   */
  public static void drawAerodynamics() {
    for (Button aero : aerodynamics) {
      aero.draw();
      aero.click();
      setPlayerAerodynamics(aero);
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
      player1.getAero().setAero(PartAero.aeroParts[aeroIndex]);
      System.out.println("p1 a" + (aeroIndex + 1));
    } else if (aero.isRightClicked()) {
      player2.getAero().setAero(PartAero.aeroParts[aeroIndex]);
      System.out.println("p2 a" + (aeroIndex + 1));
    }
  }


}
