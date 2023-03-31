package project;

import java.util.Arrays;
import processing.core.PVector;

/**
 * EngineButton, button subclass designated for engine parts.
 *
 * @author James Langille
 */
public class EngineButton extends Button {
  /** Constants for engine button. */
  private static final float WIDTH = 200;
  private static final float HEIGHT = 100;
  /** Other data. */
  private static EngineButton[] engines = new EngineButton[4];
  private static Player player1 = GameManager.player1;
  private static Player player2 = GameManager.player2;
  private static int x = (window.displayWidth / 8) - 100;
  private static int y;
  private static int engineLength = PartEngine.engineParts.length;
  // Buffer used to adjust x or y position of button
  private static int buffer;

  /**
   * Constructor to create an engine button object.
   *
   * @param position of button on window
   * @param text  of button
   * @param window of game
   */
  public EngineButton(PVector position, String text, GameManager window) {
    super(position, WIDTH, HEIGHT, text, window);
  }

  /**
   * setUpEngineButtons, sets up the engine buttons needed for CarModMenu.
   */
  public static void setupEngineButtons() {
    // Buffer used to adjust x or y position of button
    buffer = 50;
    PVector position;
    for (int i = 0; i < engines.length; i++) {
      y = (window.displayHeight / 5) + buffer;
      position = new PVector(x, y);
      engines[i] = new EngineButton(position, "", window);
      buffer += 125;
    }
  }

  public static void drawEngines() {
    for (Button engine : engines) {
      window.noStroke();
      window.fill(0, 0);
      engine.draw();
      engine.click();
      setPlayerEngine(engine);
      drawPlayer1Indicator();
      if (window.gameType == 2) {
        drawPlayer2Indicator();
      }
    }
  }

  /**
   * setPlayerEngine, sets the player's engine part depending on which
   * engine button was clicked.
   *
   * @param engine button
   */
  private static void setPlayerEngine(Button engine) {
    int engineIndex = Arrays.asList(engines).indexOf(engine);

    if (engine.isLeftClicked()) {
      player1.setEngine(PartEngine.engineParts[engineIndex]);
      System.out.println("p1 e" + engineIndex);
    } else if (engine.isRightClicked()) {
      player2.setEngine(PartEngine.engineParts[engineIndex]);
      System.out.println("p2 e" + engineIndex);
    }
  }

  /**
   * drawPlayer1Indicator, draws a circle next to an engine button
   *  to show player 1's current engine.
   */
  private static void drawPlayer1Indicator() {
    buffer = 50;
    for (int i = 0; i < engineLength; i++) {
      y = (window.displayHeight / 5) + buffer;
      if (player1.getEngine().equals(PartEngine.engineParts[i])) {
        window.fill(0, 255, 0);
        window.ellipse(x, y, 20, 20);
      }
      buffer += 125;
    }
  }

  /**
   * drawPlayer1Indicator, draws a circle next to an engine button
   *  to show player 1's current engine.
   */
  private static void drawPlayer2Indicator() {
    int x = (window.displayWidth / 8) + 90;
    buffer = 150;
    for (int i = 0; i < engineLength; i++) {
      int y = (window.displayHeight / 5) + buffer;
      if (player2.getEngine().equals(PartEngine.engineParts[i])) {
        window.fill(0, 255, 247);
        window.ellipse(x, y, 20, 20);
      }
      buffer += 125;
    }
  }
}
