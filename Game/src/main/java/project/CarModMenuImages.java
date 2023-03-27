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
  private PImage mainMenuImage;
  private PImage[] engineImages = new PImage[4];
  private PImage[] chassisImages = new PImage[4];
  private PImage[] aerodynamicImages = new PImage[4];
  private PImage[] partTitleImages = new PImage[4];
  // Image file names
  private String[] engineImageNames;
  private String[] chassisImageNames;
  private String[] aeroImageNames;
  private String[] titleImageNames;
  // Menu/Manager objects
  private MainMenu mainMenu;
  private static CarModMenuImages instance;
  private GameManager window;
  // Buffer used to adjust x or y position of image
  private int buffer;

  /**
   * CarModMenuImages, private constructor for class using singleton design.
   *
   * @param window of game
   */
  private CarModMenuImages(GameManager window) {
    this.window = window;
    this.mainMenu = MainMenu.getInstance(window);
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


  public void setup() {
    // Get image file names
    FileReader.readFiles("Game/images/");
    // Instantiate images for engine buttons
    engineImageNames = FileReader.engineImages();
    for (int i = 0; i < engineImages.length; i++) {
      engineImages[i] = window.loadImage("Game/images/" + engineImageNames[i]);
    }
    // Instantiate images for chassis buttons
    chassisImageNames = FileReader.chassisImages();
    for (int i = 0; i < chassisImages.length; i++) {
      chassisImages[i] = window.loadImage("Game/images/" + chassisImageNames[i]);
    }
    // Instantiate images for aero buttons
    aeroImageNames = FileReader.aerodynamicsImages();
    for (int i = 0; i < aerodynamicImages.length; i++) {
      aerodynamicImages[i] = window.loadImage("Game/images/" + aeroImageNames[i]);
    }
    /* Instantiate a slightly different background image
     if one player or two player game was selected. */
    if (mainMenu.gameType == 1) {
      bgImage = window.loadImage("Game/images/BGImage2.png");
    } else if (mainMenu.gameType == 2) {
      bgImage = window.loadImage("Game/images/BGImage.png");
    }
    // Instantiate part title images
    titleImageNames = FileReader.carModTitles();
    for (int i = 0; i < partTitleImages.length; i++) {
      partTitleImages[i] = window.loadImage("Game/images/" + titleImageNames[i]);
    }
    // Instantiate other images
    menuTitleImage = window.loadImage("Game/images/CarModTitle.png");
    startRaceImage = window.loadImage("Game/images/StartRace.png");
    mainMenuImage = window.loadImage("Game/images/MainMenu.png");
  }

  /**
   * drawBackground, draw the background image first before
   * any other objects are drawn.
   */
  public void drawBackground() {
    // Draw background image
    window.image(bgImage, 0, 0, window.displayWidth, window.displayHeight);
    buffer = -100;
    for (int i = 0; i < partTitleImages.length; i ++) {
      window.image(partTitleImages[i], (window.displayWidth / 8) + buffer, window.displayHeight / 5);
      if (i == 1) {
        // update aero x position
        buffer = 1125;
      } else if (i == 2) {
        // update gear x position
        buffer = 650;
      } else {
        buffer += 400;
      }
    }
  }

  /**
   * draw, draw all images needed for the CarModMenu screen.
   */
  @Override
  public void draw() {
    buffer = 50;
    // Draw images for each engine
    for (PImage engine : engineImages) {
      window.image(engine, (window.displayWidth / 8) - 100, (window.displayHeight / 5) + buffer);
      buffer += 125;
    }

    buffer = 50;
    // Draw images for each chassis
    for (PImage chassis : chassisImages) {
      window.image(chassis, (window.displayWidth / 8) + 300, (window.displayHeight / 5) + buffer);
      buffer += 125;
    }

    buffer = 50;
    // Draw images for each aerodynamics
    for (PImage aero : aerodynamicImages) {
      window.image(aero, (window.displayWidth / 8) + 700, (window.displayHeight / 5) + buffer);
      buffer += 125;
    }

    // Draw text images
    window.image(menuTitleImage, window.displayWidth / 4 + 75, window.displayHeight / 10);
    window.image(startRaceImage, window.displayWidth - 220, 760);
    window.image(mainMenuImage, (window.displayWidth / 2) - 90, 760);
  }
}
