package project;

import processing.core.PImage;

/**
 * AerodynamicsImages, sets up and draws the images
 * needed for aerodynamics buttons.
 *
 * @author James Langille
 */
public class AerodynamicsImages {

  private static final int IMAGE_AMOUNT = 4;
  private static PImage[] aerodynamicImages = new PImage[IMAGE_AMOUNT];
  private static String[] aeroImageFilePaths;
  private static GameManager window = CarModMenuImages.window;

  /**
   * setupAeroImages, sets up all aero images for the car mod menu.
   */
  public static void setupAeroImages() {
    FileReader.readFiles("Game/images/");
    // Put file paths of aero images
    aeroImageFilePaths = FileReader.getImageFilePath("aero");
    for (int i = 0; i < IMAGE_AMOUNT; i++) {
      aerodynamicImages[i] = window.loadImage("Game/images/" + aeroImageFilePaths[i]);
    }
  }

  /**
   * drawAeroImages, draws the images onto the window.
   */
  public static void drawAeroImages() {
    int buffer = 50;
    int x = (window.displayWidth / 8) + 700;
    for (PImage aero : aerodynamicImages) {
      int y = (window.displayHeight / 5) + buffer;
      window.image(aero, x, y);
      buffer += 125;
    }
  }
}
