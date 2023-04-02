package project;

import processing.core.PImage;

/**
 * ChassisImages, sets up and draws the images needed for chassis buttons.
 *
 * @author James Langille
 */
public class ChassisImages {

  private static final int IMAGE_AMOUNT = 4;
  private static PImage[] chassisImages = new PImage[IMAGE_AMOUNT];
  private static String[] chassisImageFilePaths;
  private static GameManager window = CarModMenuImages.window;

  /**
   * setupChassisImages, sets up all chassis images for the car mod menu.
   */
  public static void setupChassisImages() {
    FileReader.readFiles("Game/images/");
    // Put file paths of chassis images
    chassisImageFilePaths = FileReader.chassisImages();
    // Set up images
    for (int i = 0; i < IMAGE_AMOUNT; i++) {
      chassisImages[i] = window.loadImage("Game/images/" + chassisImageFilePaths[i]);
    }
  }

  /**
   * drawChassisImages, draws the chassis images onto the window.
   */
  public static void drawChassisImages() {
    int buffer = 50;
    int x = (window.displayWidth / 8) + 300;
    for (PImage chassis : chassisImages) {
      int y = (window.displayHeight / 5) + buffer;
      window.image(chassis, x, y);
      buffer += 125;
    }
  }
}
