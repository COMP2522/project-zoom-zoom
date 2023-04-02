package project;

import processing.core.PImage;

/**
 * EngineImages, sets up and draws the images needed for engine buttons.
 *
 * @author James Langille
 */
public class EngineImages {
  private static final int IMAGE_AMOUNT = 4;
  private static PImage[] engineImages = new PImage[IMAGE_AMOUNT];
  private static String[] engineImageNames;
  private static GameManager window = CarModMenuImages.window;

  /**
   * setupEngineImages, sets up all engine images for the car mod menu.
   */
  public static void setupEngineImages() {
    // Get image file names
    FileReader.readFiles("Game/images/");
    // Put file paths of engine images into engineImageNames
    engineImageNames = FileReader.engineImages();
    // Set up images
    for (int i = 0; i < IMAGE_AMOUNT; i++) {
      engineImages[i] = window.loadImage("Game/images/" + engineImageNames[i]);
    }
  }

  /**
   * drawEngineImages, draws the images onto the screen.
   */
  public static void drawEngineImages() {
    int buffer = 50;
    for (PImage engine : engineImages) {
      window.image(engine, (window.displayWidth / 8) - 100, (window.displayHeight / 5) + buffer);
      buffer += 125;
    }
  }
}
