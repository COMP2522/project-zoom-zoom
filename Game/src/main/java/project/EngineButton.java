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
    int buffer = 50;
    float x = (window.displayWidth / 8) - 100;
    float y;
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
      player1.getEngine().setEngine(PartEngine.engineParts[engineIndex]);
      System.out.println("p1 e" + engineIndex);
    } else if (engine.isRightClicked()) {
      player2.getEngine().setEngine(PartEngine.engineParts[engineIndex]);
      System.out.println("p2 e" + engineIndex);
    }
  }
}
