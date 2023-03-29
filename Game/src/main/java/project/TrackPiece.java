package project;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

import javax.sound.midi.Track;
import java.awt.*;

/** Bezier curves for all tracks in our game.
 *
 * @author MaxwellV
 */
public class TrackPiece extends PApplet implements Drawable {
  /** Booleans that toggle System outs. */
  private boolean arrayPrintOverride = false;

  /** Color of the road. */
  private Color roadColor = new Color(76, 76, 76);


  /** Stores the largest and smallest of each coordinate. */
  private int smallestX;
  private int largestX;
  private int smallestY;
  private int largestY;

  PVector cordStartLeft;
  PVector cordStartRight;

  /** This segment's shape object. */
  private PShape road;

  /** This segment's shape object for the border. */
  private PShape border;

  /** Width of the border. */
  private final int borderWidth = 20;

  /** Color of the border. */
//  private final Color borderColor = new Color(255, 0, 0);

  /** The gameManager. */
  private GameManager gameManager;

  /** Boolean array that mimics the shape. */
  private boolean[][] shapePixels;

  private int segmentWidth;
  private int segmentHeight;
  private int arrayWidth;
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

    cordStartLeft = new PVector(closeLeftX, closeLeftY);
    cordStartRight = new PVector(closeRightX, closeRightY);

    // Find smallest X
    smallestX = Math.min(closeLeftX, farLeftX);
    smallestX = Math.min(smallestX, closeRightX);
    smallestX = Math.min(smallestX, farRightX);

    // Find largest X
    largestX = Math.max(closeLeftX, farLeftX);
    largestX = Math.max(largestX, closeRightX);
    largestX = Math.max(largestX, farRightX);

    // Find smallest Y
    smallestY = Math.min(closeLeftY, farLeftY);
    smallestY = Math.min(smallestY, closeRightY);
    smallestY = Math.min(smallestY, farRightY);

    // Find largest Y
    largestY = Math.max(closeLeftY, farLeftY);
    largestY = Math.max(largestY, closeRightY);
    largestY = Math.max(largestY, farRightY);

    // Add 1 to account for index 0 in arrays
    segmentWidth = largestX - smallestX;
    segmentHeight = largestY - smallestY;
    arrayWidth = segmentWidth + 1;
    arrayHeight = segmentHeight + 1;

    // Boolean array that constitutes shape of this segment
    shapePixels = new boolean[arrayWidth][arrayHeight];

    // Setup boolean array to accurately portray actual shape
    addSlopeLine(closeRightX - smallestX, closeRightY - smallestY, closeLeftX - smallestX, closeLeftY - smallestY);
    addSlopeLine(farLeftX - smallestX, farLeftY - smallestY, farRightX - smallestX, farRightY - smallestY);
    addSlopeLine(closeLeftX - smallestX, closeLeftY - smallestY, farLeftX - smallestX, farLeftY - smallestY);
    addSlopeLine(closeRightX - smallestX, closeRightY - smallestY, farRightX - smallestX, farRightY - smallestY);
    fillArray();

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


    //Setup drawable shape object for the border.
    border = gameManager.createShape();
    border.beginShape();
//    border.stroke(borderColor.getRGB());
    border.strokeWeight(borderWidth);

    // Add the border around the road.
    border.vertex((closeLeftX - smallestX) - borderWidth/2, (closeLeftY - smallestY) - borderWidth/2);
    border.vertex((farLeftX - smallestX) - borderWidth/2, (farLeftY - smallestY) - borderWidth/2);
    border.vertex((farRightX - smallestX) + borderWidth/2, (farRightY - smallestY) + borderWidth/2);
    border.vertex((closeRightX - smallestX) + borderWidth/2, (closeRightY - smallestY) + borderWidth/2);
    border.endShape();

  }

  /** Get the starting location
   *
   * @param numberOfPlayers Numbers of players
   * @param playerNumber Either 1 or 2
   * @return Starting position of the relevant player
   */
  public PVector getStartCord(int numberOfPlayers, int playerNumber) {
    if (numberOfPlayers == 1) {
      int averageX = (int) ((cordStartLeft.x + cordStartRight.x) / 2);
      int averageY = (int) ((cordStartLeft.y + cordStartRight.y) / 2);
      return new PVector(averageX * playerNumber, averageY * playerNumber);
    } else {
      if (playerNumber == 1) {
        return cordStartLeft;
      } else {
        return cordStartRight;
      }
    }
  }

  /** Fills line created by addSlopeLines. */
  public void fillArray() {
    int activePixels = 0;

    // Runs each column
    for (int wTimer = 0; wTimer < arrayWidth; wTimer++) {
      activePixels = 0;
      boolean currentFill = false;
      // Checks how many lines are in this column
      for (int hTimer = 0; hTimer < arrayHeight; hTimer++) {
        if (shapePixels[wTimer][hTimer] == true) {
          activePixels++;
        }
      }
      // If there is a gap in the segment, fills until other is reached
      if (activePixels % 2 == 0) {
        for (int hTimer = 0; hTimer < arrayHeight; hTimer++) {
          // If the newly reached segment is true, toggle fill mode
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
        return;
      } else if (y1 == y2) { // Same Y = Horizontal line
        for (int timer = Math.min(x1, x2); timer < Math.max(x1, x2); timer++) {
          shapePixels[timer][y2] = true;
        }
        return;

      } else if (x1 == x2) { // Same X = Vertical Line
        for (int timer = Math.min(y1, y2); timer < Math.max(y1, y2); timer++) {
          shapePixels[x2][timer] = true;
        }
        return;
      }

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
      shapePixels[placeY][timer] = true;
    }
  }

  /** Check is coordinate is on the track.
   *  Uses int for X and Y coordinates
   *
   * @param xCord X Cord to check
   * @param yCord Y Cord to check
   * @return True if cords are on track, false if not
   */
  public boolean isOnTrack(int xCord, int yCord) {
    if ((smallestX <= xCord && xCord <= smallestX + segmentWidth) && (smallestY <= yCord && yCord <= smallestY + segmentHeight)) {
      if (TrackManager.isOnTesterMode)
        System.out.print("Touched Box\t");

      if (shapePixels[xCord - smallestX][yCord - smallestY] == true ) {
        if (TrackManager.isOnTesterMode)
          System.out.println("\t Actual");
        return true;
      } else {
        if (TrackManager.isOnTesterMode)
          System.out.println("\tNot Actual");
      }
    }
    return false;
  }

  /**
   * Draw each frame.
   */
  public void draw() {
    if (TrackManager.isOnTesterMode) { // Show general track hitboxes
      gameManager.fill(255, 255, 255);
      gameManager.rect(smallestX, smallestY, segmentWidth, segmentHeight);
    }
    gameManager.fill(roadColor.getRGB(), roadColor.getGreen(), roadColor.getBlue());
    gameManager.shape(road, smallestX, smallestY);
//    gameManager.shape(border, smallestX, smallestY);
  }

  /** Prints the shape array to visualize. */
  public void printPixels() {
    if (arrayPrintOverride && TrackManager.isOnTesterMode) { // Prints boolean array shapes

      int row = 0;
      // Testing pixels that prints '-' as false, 'X' as true
      System.out.print("  ");
      for (int timer = 0; timer < arrayWidth; timer++) {
        System.out.print(timer % 10);
      }
      System.out.print("\n");
      for (int wTimer = 0; wTimer < arrayWidth; wTimer++) {
        System.out.print(row % 10 + " ");
        for (int hTimer = 0; hTimer < arrayHeight; hTimer++) {
          if (shapePixels[wTimer][hTimer] == true) {
            System.out.print("X");
          } else {
            System.out.print("-");
          }
        }
        // Labels rows
        System.out.print(" " + row % 10 + "\n");
        row++;
      }
      System.out.print("  ");
      for (int timer = 0; timer < arrayWidth; timer++) {
        System.out.print(timer % 10);
      }
      System.out.println();
    }
  }

  public boolean isColliding(float x, float y, float radius) {
    // Check if the player's car is outside the bounds of the track
    if (x - radius < smallestX || x + radius > largestX || y - radius < smallestY || y + radius > largestY) {
      return true;
    }

    // Check if the player's car is inside the border of the track
    int px = (int) (x - smallestX);
    int py = (int) (y - smallestY);
    int pr = (int) radius;
    for (int i = px - pr; i <= px + pr; i++) {
      for (int j = py - pr; j <= py + pr; j++) {
        if (i < 0 || i >= arrayWidth || j < 0 || j >= arrayHeight) {
          continue;
        }
        if (shapePixels[i][j] == false) {
          float dx = i + smallestX - x;
          float dy = j + smallestY - y;
          if (dx * dx + dy * dy < radius * radius) {
            return true;
          }
        }
      }
    }

    return false;
  }

}
