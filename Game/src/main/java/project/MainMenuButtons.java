package project;

import processing.core.PConstants;
import processing.core.PVector;

/**
 * MainMenuButtons, sets up and draws all buttons needed
 * for the main menu.
 *
 * @author James Langille
 */
public class MainMenuButtons {
  protected static Button onePlayer;
  protected static Button twoPlayer;
  protected static Button ranking;
  protected static Button controls;
  protected static Button quit;
  /** Other data. */
  private static GameManager window = MainMenu.window;
  private static int x = (window.displayWidth / 2) - 100;
  private static int y;
  private static final float WIDTH = 225;
  private static final float HEIGHT = 50;

  /**
   * setupButtons, sets up all buttons.
   */
  public static void setupButtons() {
    window.rectMode(PConstants.CORNER);
    y = 300;
    onePlayer = new Button(new PVector(x, y), WIDTH, HEIGHT, Button.GREEN, window);
    y = 400;
    twoPlayer = new Button(new PVector(x, y), WIDTH, HEIGHT, Button.BLUE, window);
    y = 500;
    ranking = new Button(new PVector(x, y), WIDTH, HEIGHT, "Ranking", Button.YELLOW, window);
    y = 600;
    controls = new Button(new PVector(x, y), WIDTH, HEIGHT, Button.PURPLE, window);
    y = 700;
    quit = new Button(new PVector(x, y), WIDTH, HEIGHT, Button.RED, window);
  }

  /**
   * drawButtons, draws all buttons for the main menu.
   */
  public static void drawButtons() {
    onePlayerButton();
    twoPlayerButton();
    rankingButton();
    controlButton();
    quitButton();
  }

  /**
   * onePlayerButton, helper method that handles the one player button.
   */
  private static void onePlayerButton() {
    onePlayer.draw();
    onePlayer.click();
    if (onePlayer.isLeftClicked()) {
      // Change menu to one player game
      window.gameType = 1;
      window.menu = 5;
    }
  }

  /**
   * twoPlayerButton, helper method that handles the two player button.
   */
  private static void twoPlayerButton() {
    twoPlayer.draw();
    twoPlayer.click();
    if (twoPlayer.isLeftClicked()) {
      // Change menu to two player game
      window.gameType = 2;
      window.menu = 5;
    }
  }

  /**
   * rankingButton, helper method that handles the ranking button.
   */
  private static void rankingButton() {
    ranking.draw();
    ranking.click();
    if (ranking.isLeftClicked()) {
      window.menu = 6;
    }
  }

  /**
   * controlButton, helper method that handles the control button.
   */
  private static void controlButton() {
    controls.draw();
    controls.click();
    if (controls.isLeftClicked()) {
      window.menu = 3;
    }
  }

  /**
   * quitButton, helper method that handles the quit button.
   */
  private static void quitButton() {
    quit.draw();
    quit.click();
    if (quit.isLeftClicked()) {
      window.exit();
    }
  }
}
