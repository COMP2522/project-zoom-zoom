package org.main;

import java.awt.Color;
import processing.core.PApplet;
import processing.core.PShape;

/** Bezier curves for all tracks in our game. */
public class TrackPiece extends PApplet {
  /** Color of the road. */
  private Color roadColor = new Color(76, 76, 76);

  /** Smallest X. */
  private int smallestX;
  /** Largest X. */
  private int largestX;
  /** Smallest Y. */
  private int smallestY;
  /** Largest Y. */
  private int largestY;

  /** This piece's shape. */
  private PShape road;
  /** Window. */
  private Window window;

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
   * @param window Window to display
   */
  public TrackPiece(int closeLeftX, int closeLeftY, int farLeftX, int farLeftY,
                    int farRightX, int farRightY, int closeRightX, int closeRightY,
                    Window window) {
    this.window = window;

    // Find smallest and largest of each cord, used for temp tracking
    if (1 == 1) {
      smallestX = closeRightX;
      if (smallestX > farRightX) {
        smallestX = farRightX;
      }
      if (smallestX > closeLeftX) {
        smallestX = closeLeftX;
      }
      if (smallestX > farLeftX) {
        smallestX = farLeftX;
      }

      largestX = closeRightX;
      if (largestX < farRightX) {
        largestX = farRightX;
      }
      if (largestX < closeLeftX) {
        largestX = closeLeftX;
      }
      if (largestX < farLeftX) {
        largestX = farLeftX;
      }

      smallestY = closeRightY;
      if (smallestY > farRightY) {
        smallestY = farRightY;
      }
      if (smallestY > closeLeftX) {
        smallestY = closeLeftY;
      }
      if (smallestY > farLeftX) {
        smallestY = farLeftY;
      }

      largestY = closeRightY;
      if (largestY < farRightY) {
        largestY = farRightY;
      }
      if (largestY < closeLeftY) {
        largestY = closeLeftY;
      }
      if (largestY < farLeftY) {
        largestY = farLeftY;
      }
    }

    road = window.createShape();
    road.beginShape();
    road.fill(roadColor.getRed(), roadColor.getGreen(), roadColor.getBlue());
    road.vertex(closeLeftX, closeLeftY);
    road.vertex(farLeftX, farLeftY);
    road.vertex(farRightX, farRightY);
    road.vertex(closeRightX, closeRightY);
    road.endShape();
  }

  /** Check is coordinate is on the track.
   *  Uses int for X and Y coordinates
   *
   * @param xCord X Cord to check
   * @param yCord Y Cord to check
   * @return True if cords are on track, false if not
   */
  public boolean isOnTrack(int xCord, int yCord) {
    if (smallestX <= xCord && xCord <= largestX) {
      if (smallestY <= yCord && yCord <= largestY) {
        System.out.print("X: " + smallestX + "<" + xCord + "<" + largestX);
        System.out.println("\t\tY: " + smallestY + "<" + yCord + "<" + largestY);
        return true;
      }
    }
    return false;
  }

  /**
   * Draw each frame.
   */
  public void draw() {
    window.shape(road, 0, 0);
  }
}
