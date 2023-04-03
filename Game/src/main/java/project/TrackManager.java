package project;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.Color;
import java.util.ArrayList;

/** Manages the track segments.
 *
 * @author MaxwellV
 */
public class TrackManager implements Drawable {

  /**
   * Enables testing mode
   */
  final static boolean isOnTesterMode = false;

  private boolean[][] trackPixels;

  /**
   * Active window.
   */
  private GameManager window;

  private PImage grassImage;

  /**
   * Color of the grass.
   */
  private Color grassColor = new Color(0, 132, 0);

  /**
   * Contain all track segments.
   */
  private ArrayList<TrackPiece> tracks = new ArrayList<TrackPiece>();

  /**
   * Track Manager constructor.
   *
   * @param window Current window
   */
  public TrackManager(GameManager window) {
    this.window = window;
    trackPixels = new boolean[window.getDisplayWidthCustom()][window.getDisplayHeightCustom()];
  }

  /**
   * Initializes track manager.
   */
  public void initTrack(String input) {
    if (input == "Track 1") {
      tracks.add(new TrackPiece(180, 40, 1100, 40, 1100, 140, 180, 140, window)); // Top Straight
      tracks.add(new TrackPiece(1100, 40, 1240, 180, 1140, 180, 1100, 140, window)); // Top -> Right
      tracks.add(new TrackPiece(1240, 180, 1240, 540, 1140, 540, 1140, 180, window)); // Right
      tracks.add(new TrackPiece(1240, 540, 1100, 680, 1100, 580, 1140, 540, window)); // Right -> Bottom
      tracks.add(new TrackPiece(1100, 680, 180, 680, 180, 580, 1100, 580, window)); // Bottom Straight
      tracks.add(new TrackPiece(180, 680, 40, 540, 140, 540, 180, 580, window)); // Bottom -> Left
      tracks.add(new TrackPiece(40, 540, 40, 180, 140, 180, 140, 540, window)); // Left
      tracks.add(new TrackPiece(40, 180, 180, 40, 180, 140, 140, 180, window)); // Left -> Top
    } else if (input == "Track 2") {

      /** Close/far and right/left are from the perspective a car driving,
       * not top/bottom or left/right of the screen.
       *
       * Slope calculations occasionally  round to out of bounds of the array, just work around it
       *
       * Also, not required, but it makes sense for the far cords of a piece match the close cords \
       * of the next piece.
       */
      tracks.add(new TrackPiece(20, 20, 20, 500, 1000, 500, 1000, 20, window));
    } else if (input == "Track 3") {
      tracks.add(new TrackPiece(20, 20, 20, 500, 1000, 500, 1000, 20, window)); // Left
    }

    for (TrackPiece eachPiece : tracks) {
      trackPixels = eachPiece.addPixels(trackPixels);
    }

    grassImage = window.loadImage("Game/images/trackGrass4.png");
    int pixelModifier = 100;   // Default BG Image Dimensions: 600 x 400.
    grassImage.width = window.getDisplayWidthCustom() / pixelModifier;
    grassImage.height = window.getDisplayHeightCustom() / pixelModifier;
    window.image(grassImage, 0, 0);
  }

  public PVector getStartCords(int playerNumber) {
    return tracks.get(0).getStartCord(playerNumber);
  }

  /**
   * Draws to screen.
   */
  public void draw() {
    // Flat color option kept because BG Image causing lag on some computers
    //window.image(grassImage, 0, 0, window.getDisplayWidthCustom(), window.getDisplayHeightCustom());
    window.background(grassColor.getRed(), grassColor.getGreen(), grassColor.getRed());
    for (TrackPiece eachPiece : tracks) {
      eachPiece.draw();
    }
    window.fill(255,255,255);
    for (int wTimer = 0; wTimer < window.getDisplayWidthCustom(); wTimer++) {
      for (int hTimer = 0; hTimer < window.getDisplayHeightCustom(); hTimer++) {
//        if (trackPixels[wTimer][hTimer]) {
////          window.ellipse(wTimer, hTimer, 2, 2);
//        }
      }
    }
  }

  /**
   * Clear's the tracks arraylist and onTrack array.
   */
  public void clearTrack() {
    for (int wTimer = 0; wTimer < window.getDisplayWidthCustom(); wTimer++) {
      for (int hTimer = 0; hTimer < window.getDisplayHeightCustom(); hTimer++) {
        trackPixels[wTimer][hTimer] = false;
      }
    }
    tracks.clear();
  }

  /**
   * Checks if the cords are on the track.
   *
   * @param inputCords Coordinates to be checked
   * @return True / False for if cords fall on the track
   */
  public boolean isOnTrack(PVector inputCords) {
    // Check if cords are on screen (Range of the array)
    if ((int) inputCords.x > window.getDisplayWidthCustom() - 1 || (int) inputCords.y > window.getDisplayHeightCustom() - 1||
            (int) inputCords.x < 0 || (int) inputCords.y < 0) {
      return false;
    }
    return trackPixels[(int) inputCords.x][(int) inputCords.y];
  }

  /**
   * Prints the array, - is grass, X is track.
   */
  public void printPixels() {
    String output = "";
    for (int hTimer = 0; hTimer < window.getDisplayHeightCustom(); hTimer += 2) {
      System.out.print(hTimer + "\t");
      for (int wTimer = 0; wTimer < window.getDisplayWidthCustom(); wTimer += 2) {
        if (trackPixels[wTimer][hTimer] == true) {
          System.out.print("X");
        } else {
          System.out.print("-");
        }
      }
      System.out.print("\n");
    }
    System.out.println(window.getDisplayWidthCustom() + " x " + window.getDisplayHeightCustom());
  }
}
