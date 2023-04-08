package project;

import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

/**
 * MainMenuImages, sets up and draws all images needed in Main Menu.
 *
 * @author James Langille
 */
public class MainMenuImages {
  private static PImage bgImage;
  private static PImage gameTitle;
  private static PImage onePlayerImage;
  private static PImage twoPlayerImage;
  private static PImage rankings;
  private static PImage controlsImage;
  private static PImage quitImage;
  /** Button positions. */
  private static PVector onePlayerPosition = MainMenuButtons.onePlayer.getPosition();
  private static PVector twoPlayerPosition = MainMenuButtons.twoPlayer.getPosition();
  private static PVector rankingPosition = MainMenuButtons.ranking.getPosition();
  private static PVector controlsPosition = MainMenuButtons.controls.getPosition();
  private static PVector quitPosition = MainMenuButtons.quit.getPosition();
  /** Other data. */
  private static GameManager window = MainMenu.window;
  private static boolean showTitle = true;
  private static final int CLOCK = 5;
  private static final int FIVE = 5;
  private static final int TEN = 10;
  private static final int SIXTY = 60;

  /**
   * setupImages, sets up all images needed for main menu.
   */
  public static void setupImages() {
    window.imageMode(PConstants.CORNER);
    bgImage = window.loadImage("Game/images/BGImage.png");
    gameTitle = window.loadImage("Game/images/zoomZoom.png");
    onePlayerImage = window.loadImage("Game/images/1Player.png");
    twoPlayerImage = window.loadImage("Game/images/2Player.png");
    rankings = window.loadImage("Game/images/Rankings.png");
    controlsImage = window.loadImage("Game/images/Controls.png");
    quitImage = window.loadImage("Game/images/Quit.png");
  }

  /**
   * drawImages, draws all images needed for main menu.
   */
  public static void drawImages() {
    showTitle();
    window.image(onePlayerImage, onePlayerPosition.x + FIVE, onePlayerPosition.y + TEN);
    window.image(twoPlayerImage, twoPlayerPosition.x + FIVE, twoPlayerPosition.y + TEN);
    window.image(rankings, rankingPosition.x + FIVE, rankingPosition.y + TEN);
    window.image(controlsImage, controlsPosition.x + FIVE, controlsPosition.y + TEN);
    window.image(quitImage, quitPosition.x + SIXTY, quitPosition.y + TEN);
  }

  /**
   * drawBackground, draw the background image first before
   * any other objects are drawn.
   */
  public static void drawBackground() {
    window.image(bgImage, 0, 0, window.displayWidth, window.displayHeight);
  }

  /**
   * showTitle, shows the game title every half second.
   */
  public static void showTitle() {
    int x = window.displayWidth / 3 - 100;
    int y = window.displayHeight / 8;
    if (window.frameCount % CLOCK == 0) {
      // Sets to opposite boolean expression every second
      showTitle = !showTitle;
    }
    if (showTitle) {
      window.image(gameTitle, x, y);
    }
  }
}
