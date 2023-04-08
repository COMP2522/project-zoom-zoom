package project;

import processing.core.PImage;

/**
 * GearImages, sets up and draws the images needed for gear buttons.
 *
 * @author James Langille
 */
public class GearImages {

  private static final int IMAGE_AMOUNT = 4;
  private static PImage[] gearImages = new PImage[IMAGE_AMOUNT];
  private static String[] gearImageFilePaths;
  private static GameManager window = CarModMenuImages.window;

  /**
   * setupGearImages, sets up all gear images for the car mod menu.
   */
  public static void setupGearImages() {
    FileReader.readFiles("Game/images/");
    // Put file paths of aero images
    gearImageFilePaths = FileReader.getImageFilePath("Gear");
    for (int i = 0; i < IMAGE_AMOUNT; i++) {
      gearImages[i] = window.loadImage("Game/images/" + gearImageFilePaths[i]);
    }
  }


  /**
   * drawGearImages, draws the images onto the window.
   */
  public static void drawGearImages() {
    int buffer = 50;
    int x = (window.displayWidth / 8) + 1140;
    for (PImage gear : gearImages) {
      int y = (window.displayHeight / 5) + buffer;
      window.image(gear, x, y);
      buffer += 125;
    }
  }
}
