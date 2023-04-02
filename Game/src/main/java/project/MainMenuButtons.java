package project;

import processing.core.PConstants;
import processing.core.PVector;

import java.awt.*;

/**
 * MainMenuButtons, sets up and draws all buttons needed
 * for the main menu.
 *
 * @author James Langille
 */
public class MainMenuButtons {
  private static Button onePlayer;
  private static Button twoPlayer;
  private static Button ranking;
  private static Button controls;
  private static Button quit;
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
    onePlayer.draw();
    onePlayer.click();
    handleOnePlayerClick();
    twoPlayer.draw();
    twoPlayer.click();
    handleTwoPlayerClick();
    ranking.click();
    ranking.draw();
    handleRankingClick();
    controls.draw();
    controls.click();
    handleControlClick();
    quit.draw();
    quit.click();
    handleQuitClick();
  }

  /**
   * handleOnePlayerClick, helper method when one player button is clicked.
   */
  private static void handleOnePlayerClick() {
    if (onePlayer.isLeftClicked()) {
      // Change menu to one player game
      window.gameType = 1;
      window.menu = 5;
    }
  }

  /**
   * handleTwoPlayerClick, helper method when two player button is clicked.
   */
  private static void handleTwoPlayerClick() {
    if (twoPlayer.isLeftClicked()) {
      // Change menu to two player game
      window.gameType = 2;
      window.menu = 5;
    }
  }

  /**
   * handleRankingClick, helper method when ranking button is clicked.
   */
  private static void handleRankingClick() {
    if (ranking.isLeftClicked()) {
      window.menu = 6;
    }
  }

  /**
   * handleControlClick, helper method when control button is clicked.
   */
  private static void handleControlClick() {
    if (controls.isLeftClicked()) {
      window.menu = 3;
    }
  }

  /**
   * handleQuitClick, helper method when quit button is clicked.
   */
  private static void handleQuitClick() {
    if (controls.isLeftClicked()) {
      window.menu = 3;
    }
  }
}
