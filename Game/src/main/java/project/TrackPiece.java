package project;

import java.awt.Color;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

/** Bezier curves for all tracks in our game.
 *
 * @author MaxwellV
 */
public class TrackPiece extends PApplet implements Drawable {
  /** Color of the road. */
  private Color roadColor = new Color(76, 76, 76);

  /** Closest point to X = 0. */
  private int smallestX;
  /** Furthest point from X = 0. */
  private int largestX;
  /** Closest point to Y = 0. */
  private int smallestY;
  /** Furthest point from Y = 0. */
  private int largestY;

  /** Player1's starting locations. */
  private PVector cordStart1;
  /** Player2's starting location. */
  private PVector cordStart2;

  /** Cords in a string, for outputting custom track codes. */
  private String cordsString;

  /** This segment's shape object, that is drawn to the screen. */
  private PShape road;

  /** The gameManager. */
  private GameManager gameManager;

  /** Boolean array that mimics the shape. */
  private boolean[][] shapePixels;

  /** Width of this shape. */
  private int segmentWidth;
  /** Height of this shape. */
  private int segmentHeight;

  /** Width +1 to account for index 0. */
  private int arrayWidth;
  /** Height +1 to account for index 0. */
  private int arrayHeight;

  /** Constructor for a track piece.
   *
   * @param closeLeftX X cord for corner of close left side
   * @param closeLeftY Y cord for corner of close left side
   * @param farLeftX X cord for corner of far left side
   * @param farLeftY Y cord for corner of far left side
   * @param farRightX X cord for corner of far right side
   * @param farRightY Y cord for corner of far right side
   * @param closeRightX X cord for corner of close right side
   * @param closeRightY Y cord for corner of close right side
   * @param gameManager gameManager, link to the display
   */
  public TrackPiece(int closeLeftX, int closeLeftY, int farLeftX, int farLeftY,
                    int farRightX, int farRightY, int closeRightX, int closeRightY,
                    GameManager gameManager) {
    this.gameManager = gameManager;

    // Extremes of each coordinate
    smallestX = Math.min(Math.min(closeLeftX, farLeftX), Math.min(farRightX, closeRightX));
    smallestY = Math.min(Math.min(closeLeftY, farLeftY), Math.min(farRightY, closeRightY));
    largestX = Math.max(Math.max(closeLeftX, farLeftX), Math.max(farRightX, closeRightX));
    largestY = Math.max(Math.max(closeLeftY, farLeftY), Math.max(farRightY, closeRightY));

    // Overall size of the object
    segmentWidth = largestX - smallestX;
    segmentHeight = largestY - smallestY;

    // Overall size of the array (Object + 1 to account for index 0)
    arrayWidth = segmentWidth + 1;
    arrayHeight = segmentHeight + 1;

    // Boolean array that tracks onTrack of this segment
    shapePixels = new boolean[arrayWidth][arrayHeight];

    // Add each line segment, then fill
    addSlopeLine(closeLeftX - smallestX, closeLeftY - smallestY, farLeftX - smallestX, farLeftY - smallestY); // 1
    addSlopeLine(closeLeftX - smallestX, closeLeftY - smallestY, closeRightX - smallestX, closeRightY - smallestY); // 2
    addSlopeLine(farRightX - smallestX, farRightY - smallestY, closeRightX - smallestX, closeRightY - smallestY); // 3
    addSlopeLine(farRightX - smallestX, farRightY - smallestY, farLeftX - smallestX, farLeftY - smallestY); // 4
    fillArray();

    // Calculate the start point for each racer
    cordStart1 = calculateStartCords(closeLeftX, closeLeftY, closeRightX, closeRightY, 1);
    cordStart2 = calculateStartCords(closeLeftX, closeLeftY, closeRightX, closeRightY, 2);

    // This track's cords copied to a string. comma between 1 cord's X & Y, space between cords.
    cordsString = closeLeftX + "," + closeLeftY + " " + farLeftX + "," + farLeftY + " "
            + farRightX + "," + farRightY + " " + closeRightX + "," + closeRightY;

    // Setup drawable shape object.
    road = gameManager.createShape();
    road.beginShape();
    road.fill(roadColor.getRed(), roadColor.getGreen(), roadColor.getBlue());
    road.stroke(roadColor.getRed(), roadColor.getGreen(), roadColor.getBlue());
    road.vertex((closeLeftX - smallestX), (closeLeftY - smallestY));
    road.vertex((farLeftX - smallestX), (farLeftY - smallestY));
    road.vertex((farRightX - smallestX), (farRightY - smallestY));
    road.vertex((closeRightX - smallestX), (closeRightY - smallestY));
    road.endShape();
  }

  /**
   * Uses slope calculation (y = mx + b) to find lines between points.
   *
   * @param x1 First cord X
   * @param y1 First cord Y
   * @param x2 Second cord X
   * @param y2 Second cord Y
   */
  public void addSlopeLine(int x1, int y1, int x2, int y2) {
    int firstX;
    int firstY;
    int secondX;
    int secondY;
    int placeY;
    float slope;

    // Resolve corner case slopes (Same X and/or Y)
    if (y1 == y2 && x1 == x2) { // Same cords
      shapePixels[x2][y2] = true;

    } else if (y1 == y2) { // Same Y = Horizontal line
      for (int timer = Math.min(x1, x2); timer < Math.max(x1, x2); timer++) {
        shapePixels[timer][y2] = true;
      }

    } else if (x1 == x2) { // Same X = Vertical Line
      for (int timer = Math.min(y1, y2); timer < Math.max(y1, y2); timer++) {
        shapePixels[x2][timer] = true;
      }
    } else {
      // Cord with smaller X designated as "first"
      if (x1 < x2) {
        firstX = x1;
        firstY = y1;
        secondX = x2;
        secondY = y2;
      } else {
        secondX = x1;
        secondY = y1;
        firstX = x2;
        firstY = y2;
      }

      // Get slope for future calculations
      slope = (y2 - y1) / (x2 - x1);

      // Set y for each X between x1 & x2
      for (int timer = firstX; timer <= secondX; timer++) {
        placeY = (int) (firstY + (slope * (timer - firstX)));
        shapePixels[timer][placeY] = true;
      }
    }
  }

  /** Fills line area outlined by addSlopeLines. */
  public void fillArray() {
    int activePixels = 0;
    boolean currentFill = false;

    for (int wTimer = 0; wTimer < arrayWidth; wTimer++) {
      activePixels = 0;
      currentFill = false;
      for (int hTimer = 0; hTimer < arrayHeight; hTimer++) {
        if (shapePixels[wTimer][hTimer] == true) { // Get the number of lines crossed
          activePixels++;
        }
      }
      if (activePixels % 2 == 0 && activePixels < 5) { // If crosses even lines, fill
        for (int hTimer = 0; hTimer < arrayHeight; hTimer++) {
          if (shapePixels[wTimer][hTimer] == true) {
            currentFill = !currentFill;
          }
          if (currentFill == true) {
            shapePixels[wTimer][hTimer] = true;
          }
        }
      }
    }
  }


  /** Uses averages calc to find starting position.
   *
   * @param x1 X position of cord 1
   * @param y1 Y position of cord 1
   * @param x2 Y position of cord 2
   * @param y2 X position of cord 2
   * @param playerNum Which player's position is being calculated
   * @return Starting position of player
   */
  private PVector calculateStartCords(int x1, int y1, int x2, int y2, int playerNum) {
    int smallerX = Math.min(x1, x2);
    int smallerY = Math.min(y1, y2);
    int xPosition = smallerX + (x2 - x1) / 3 * playerNum;
    int yPosition = smallerY + (y2 - y1) / 3 * playerNum;
    return new PVector(xPosition, yPosition);
  }

  /** Draw each frame. */
  public void draw() {
    gameManager.fill(roadColor.getRGB(), roadColor.getGreen(), roadColor.getBlue());
    gameManager.shape(road, smallestX, smallestY);
  }

  /** Add this piece's array to the track manager's array.
   *
   * @param inputArray Array to add this pieces cords to
   * @return Modified input array, this array added.
   */
  public boolean[][] addPixels(boolean[][] inputArray) {
    for (int wTimer = 0; wTimer < arrayWidth; wTimer++) {
      for (int hTimer = 0; hTimer < arrayHeight; hTimer++) {
        if (shapePixels[wTimer][hTimer]) {
          if (!inputArray[wTimer + smallestX][hTimer + smallestY]) { // Don't override manager array
            inputArray[(wTimer + smallestX)][(hTimer + smallestY)] = shapePixels[wTimer][hTimer];
          }
        }
      }
    }
    if (false) {
      System.out.print("\n\nManagers:\n");
      for (int wTimer = 0; wTimer < arrayWidth; wTimer++) {
        for (int hTimer = 0; hTimer < arrayHeight; hTimer++) {
          if (inputArray[(wTimer + smallestX)][(hTimer + smallestY)]) {
            System.out.print("X");
          } else {
            System.out.print('-');
          }
        }
        System.out.print("\n");
      }
    }
    return inputArray;
  }

  /** Prints the shape array to assist visualization. */
  public void printPixels() {
    // Testing pixels that prints '-' as false, 'X' as true
    for (int hTimer = 0; hTimer <= arrayHeight - 1; hTimer++) {
      for (int wTimer = 0; wTimer <= arrayWidth - 1; wTimer++) {
        if (shapePixels[wTimer][hTimer] == true) {
          System.out.print("X");
          gameManager.ellipse(wTimer, hTimer, 1, 1);
        } else {
          System.out.print("-");
        }
      }
      System.out.print("\n");
    }
  }

  /** Get the starting location.
   *
   * @param playerNumber Either 1 or 2
   * @return Starting position of the relevant player
   */
  public PVector getStartCord(int playerNumber) {
    if (playerNumber == 1) {
      return cordStart1;
    } else {
      return cordStart2;
    }
  }

  /**
   *  Get string of this piece's coordinates.
   *
   * @return String of this piece's cords
   */
  public String getCodeString() {
    return cordsString;
  }

  /**
   * toString method
   *
   * @return String of this piece's coordinates
   */
  public String toString() {
    return cordsString;
  }
}
