package org.main;

import java.awt.Color;
import processing.core.PVector;


/**
 * Straight track piece.
 * DISPLAY DOES NOT MATCH TRACKING:
 * Circle is split in 4 pieces, along x-axis and y-axis.
 * 1 = Positive X & Positive Y
 * 2 = Negative X & Positive Y
 * 3 = Negative X & Negative Y
 * 4 = Positive X & Positive Y
 */
public class HardTurnTrack extends Track {
  private float xCord;
  private float yCord;
  private float innerRadius;
  private float outerRadius;
  private int quadrant;
  private Color roadColor = new Color(100, 100, 100);
  private Color bg = new Color(0, 100, 0);
  private Window window;

  /**
   * Straight track constructor.
   *
   * @param xCord Upper left point of the track
   * @param yCord Upper left point of the track
   * @param innerRadius Width of the track
   * @param width Height of the track
   * @param window Display window
   */
  public HardTurnTrack(float xCord, float yCord, float innerRadius, float width, Window window,
                       int quadrant) {
    this.xCord = xCord;
    this.yCord = yCord;
    this.innerRadius = innerRadius;
    this.outerRadius = innerRadius + width;
    this.quadrant = quadrant;
    this.window = window;
  }

  /**
   * Standard draw function.
   */
  public void draw() {
    // Draws outer circle
    window.pushStyle();
    window.fill(this.roadColor.getRed(), this.roadColor.getGreen(), this.roadColor.getBlue());
    window.circle(this.xCord, this.yCord, 2 * outerRadius);
    window.popStyle();

    // Draws inner circle
    window.pushStyle();
    window.fill(this.bg.getRed(), this.bg.getGreen(), this.bg.getBlue());
    window.circle(this.xCord, this.yCord, 2 * innerRadius);
    window.popStyle();
  }

  /**
   * DISPLAY DOES NOT MATCH TRACKING:
   * Checks if both on track and in tracked area, designated by quadrant.
   * Point for checking quadrant is at circle center.
   * <p>
   * Circle is split in 4 pieces, along x-axis and y-axis.
   * 1 = Positive X & Positive Y
   * 2 = Negative X & Positive Y
   * 3 = Negative X & Negative Y
   * 4 = Positive X & Positive Y
   *
   *      2   |   1
   *      ----|-----
   *      3   |   4
   * </p>
   *
   * @param checkPosition Position to check against
   * @return Wether or not inputted position is in area
   */
  public boolean isOnTrack(PVector checkPosition) {
    double distance = Math.sqrt(Math.pow((checkPosition.x - xCord), 2)
            + Math.pow((checkPosition.y - yCord), 2));
    boolean isPositiveX = checkPosition.x > this.xCord;
    boolean isPositiveY = checkPosition.y < this.yCord;
    if (innerRadius < distance && distance < outerRadius) {
      switch (quadrant) {
        case 1:
          return isPositiveX && isPositiveY;
        case 2:
          return !isPositiveX && isPositiveY;
        case 3:
          return !isPositiveX && !isPositiveY;
        case 4:
          return isPositiveX && !isPositiveY;
        default:
          return false;
      }
    }
    return false;
  }
}
