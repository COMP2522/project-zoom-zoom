package project;

import processing.core.PImage;

/**
 * CarModMenuImages, sets up all images needed for CarModMenu
 * to help make CarModMenu more readable and less bloated.
 *
 * @author James Langille
 */
public class CarModMenuImages implements Drawable {
  private PImage bgImage;
  private PImage menuTitleImage;
  private PImage startRaceImage;
  private PImage saveBuild;
  private PImage mainMenuImage;
  private static CarModMenuImages instance;
  protected static GameManager window;
  private static final int clock = 5;
  private boolean showTitle = true;
  private static int x;
  private static int y;

  /**
   * CarModMenuImages, private constructor for class using singleton design.
   *
   * @param window of game
   */
  private CarModMenuImages(GameManager window) {
    this.window = window;
  }

  /**
   * getInstance, gets one object of class.
   * @param window of screen
   * @return singleton object of class
   */
  public static CarModMenuImages getInstance(GameManager window) {
    if (instance == null) {
      instance = new CarModMenuImages(window);
    }
    return instance;
  }

  /**
   * setup, sets up all images needed for the car mod menu.
   */
  public void setup() {
    /* Instantiate a slightly different background image
     if one player or two player game was selected. */
    if (window.gameType == 1) {
      bgImage = window.loadImage("Game/images/BGImage2.png");
    } else if (window.gameType == 2) {
      bgImage = window.loadImage("Game/images/BGImage.png");
    }
    EngineImages.setupEngineImages();
    ChassisImages.setupChassisImages();
    AerodynamicsImages.setupAeroImages();
    GearImages.setupGearImages();
    PartTitleImages.setupPartTitleImages();
    // Instantiate other images
    menuTitleImage = window.loadImage("Game/images/CarModTitle.png");
    startRaceImage = window.loadImage("Game/images/StartRace.png");
    mainMenuImage = window.loadImage("Game/images/MainMenu.png");
    saveBuild = window.loadImage("Game/images/SaveBuild.png");
  }

  /**
   * drawBackground, draw the background image first before
   * any other objects are drawn.
   */
  public void drawBackground() {
    window.image(bgImage, 0, 0, window.displayWidth, window.displayHeight);
  }

  /**
   * draw, draw all images needed for the CarModMenu screen.
   */
  @Override
  public void draw() {
    EngineImages.drawEngineImages();
    ChassisImages.drawChassisImages();
    AerodynamicsImages.drawAeroImages();
    GearImages.drawGearImages();
    PartTitleImages.drawPartTitleImages();
    // Draw text images
    showTitle();
    x = (window.displayWidth / 8) - 100;
    y = 760;
    window.image(saveBuild, x, y);
    x = (window.displayWidth / 8) + 305;
    window.image(mainMenuImage, x, y);
    x = (window.displayWidth / 8) + 705;
    window.image(startRaceImage, x, y);
  }

  /**
   * showTitle, shows the menu title every half second.
   */
  private void showTitle() {
    x = window.displayWidth / 4 + 75;
    y = window.displayHeight / 10;
    if (window.frameCount % clock == 0) {
      // Sets to opposite boolean expression every second
      showTitle = !showTitle;
    }
    if (showTitle) {
      window.image(menuTitleImage, x, y);
    }
  }
}
