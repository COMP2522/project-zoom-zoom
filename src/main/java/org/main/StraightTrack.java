package org.main;

import java.awt.Color;
import processing.core.PVector;


/**
 * Straight track piece.
 */
public class StraightTrack extends Track {
  private float xCord1;
  private float xCord2;
  private float yCord1;
  private float yCord2;
  private float width;
  private float height;
  private int endDirection;
  private Color color = new Color(100, 100, 100);
  private Window window;

  /**
   * Straight track constructor.
   *
   * @param xCord Upper left point of the track
   * @param yCord Upper left point of the track
   * @param width Width of the track
   * @param height Height of the track
   * @param window Display window
   */
  public StraightTrack(float xCord, float yCord, float width, float height, Window window) {
    xCord1 = xCord;
    xCord2 = xCord + width;
    yCord1 = yCord;
    yCord2 = yCord + height;
    this.width = width;
    this.height = height;
    this.window = window;
  }

  /**
   * Standard draw function.
   */
  public void draw() {
    window.pushStyle();
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.rect(this.xCord1, this.yCord1, width, height);
    window.popStyle();
  }

  public int getEndDirection() {
    return endDirection;
  }

  /**
   * Checks if inputted position is within the area of this track.
   *
   * @param checkPosition Position to check against
   * @return Wether or not inputted position is in area
   */
  public boolean isOnTrack(PVector checkPosition) {
    if ((xCord1 < checkPosition.x && checkPosition.x < xCord2)
          && (yCord1 < checkPosition.y && checkPosition.y < yCord2)) {
      return true;
    } else {
      return false;
    }
  }
}
