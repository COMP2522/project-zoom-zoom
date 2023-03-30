package project;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.Arrays;

/**
 * GearButton, button subclass designated for gear parts.
 *
 * @author James Langille
 */
public class GearButton extends Button {
  /** Constants for gear button. */
  private static final float WIDTH = 200;
  private static final float HEIGHT = 100;
  private static final Color COLOR = new Color(255, 0, 0);
  /** Other data. */
  private static GearButton[] gears = new GearButton[4];
  private static Player player1 = GameManager.player1;
  private static Player player2 = GameManager.player2;

  /**
   * Constructor to create a gear button object.
   *
   * @param position of button on window
   * @param title    text of button
   * @param window   of game
   */
  public GearButton(PVector position, String title, GameManager window) {
    super(position, WIDTH, HEIGHT, title, COLOR, window);
  }

  /**
   * setupGearButtons, sets up all gear buttons needed for CarModMenu.
   */
  public static void setupGearButtons() {
    window.textAlign(PApplet.CENTER, PApplet.CENTER);
    int buffer = 50;
    int x = (window.displayWidth / 8) + 1100;
    int y;
    PVector position;
    for (int i = 0; i < gears.length; i++) {
      y = (window.displayHeight / 5) + buffer;
      position = new PVector(x, y);
      gears[i] = new GearButton(position, "Gear " + (i + 1), window);
      buffer += 125;
    }
  }

  /**
   * drawGears, draws all gear buttons.
   */
  public static void drawGears() {
    for (Button gear : gears) {
      gear.draw();
      gear.click();
      setPlayerGears(gear);
    }
  }

  /**
   * setPlayerGears, sets the players gears based on the text box
   * and which button was clicked.
   * @param gear button
   */
  public static void setPlayerGears(Button gear) {
    int gearsIndex = Arrays.asList(gears).indexOf(gear);

    if (gear.isLeftClicked()) {
      // Set player1 gear1
      System.out.println("p1 g" + (gearsIndex + 1) + ": " + window.inputVal);
    } else if (gear.isRightClicked()) {
      // Set player2 gear1
      System.out.println("p2 g" + (gearsIndex + 1) + ": " + window.inputVal);
    }
  }
}
