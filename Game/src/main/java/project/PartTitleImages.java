package project;

import processing.core.PImage;

/**
 * PartTitleImages, helper class that sets up and draws the
 * part title images shown near the top of the car mod menu.
 *
 * @author James Langille
 */
public class PartTitleImages {

  private static final int IMAGE_AMOUNT = 4;
  private static PImage[] partTitleImages = new PImage[IMAGE_AMOUNT];
  private static String[] titleImageFilePaths;
  private static GameManager window = CarModMenuImages.window;

  /**
   * setupTitleImages, sets up the images shown near the top
   * of the car mod menu.
   */
  public static void setupPartTitleImages() {
    FileReader.readFiles("Game/images/");
    // Put files paths
    titleImageFilePaths = FileReader.getImageFilePath("partTitle");
    for (int i = 0; i < IMAGE_AMOUNT; i++) {
      partTitleImages[i] = window.loadImage("Game/images/" + titleImageFilePaths[i]);
    }
  }

  /**
   * drawPartTitleImages, draws the images onto the window.
   */
  public static void drawPartTitleImages() {
    int buffer = -100;
    int y = window.displayHeight / 5;
    for (int i = 0; i < partTitleImages.length; i++) {
      int x = (window.displayWidth / 8) + buffer;
      window.image(partTitleImages[i], x, y);
      buffer = (i == 1) ? 1125 : ((i == 2) ? 650 : (buffer + 400));
    }
  }
}
