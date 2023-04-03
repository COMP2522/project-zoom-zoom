package project;

import processing.core.PConstants;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

/** Manages the track segments.
 *
 *
 */
public class TrackManager implements Drawable {

  /** Enables testing mode */
  final static boolean isOnTesterMode = false;

  /** Active window. */
  private GameManager window;

  //private PImage grassImage;

  /** Color of the grass. */
  private Color grassColor = new Color(0, 132, 0);

  /** Contain all track segments. */
  private ArrayList<TrackPiece> tracks = new ArrayList<TrackPiece>();

  Color roadCol = new Color(50,50,50);

  public void setTrackChoice(int trackChoice) {
    this.trackChoice = trackChoice;
  }

  private int trackChoice;

  /** Track Manager constructor.
   *
   * @param window Current window
   */
  public TrackManager(GameManager window) {
    this.window = window;
  }

  /** Initializes track manager. */

  public PVector getStartCords(int numberOfPlayers, int playerNumber) {
    return new PVector(300, 200);
  }

  /** Draws to screen. */
  public void draw() {
    window.background(grassColor.getRed(), grassColor.getGreen(), grassColor.getBlue());
    switch (trackChoice){
      case 1:
        window.rectMode(PConstants.CORNER);
        window.pushStyle();
        window.stroke(roadCol.getRed(), roadCol.getGreen(), roadCol.getBlue());
        window.fill(roadCol.getRed(), roadCol.getGreen(), roadCol.getBlue());
        window.rect(100, 100, 1200, 200);
        window.rect(1100, 300, 200, 500);
        window.rect(1100, 600, -800, 200);
        window.rect(100, 800, 200, -500);
        window.popStyle();
        break;
      case 2:
        window.rectMode(PConstants.CORNER);
        window.pushStyle();
        window.stroke(roadCol.getRed(), roadCol.getGreen(), roadCol.getBlue());
        window.fill(roadCol.getRed(), roadCol.getGreen(), roadCol.getBlue());
        window.rect(100, 100, 1000, 150);
        window.rect(1100, 100, 150, 400);
        window.rect(1250, 500, -300, 100);
        window.rect(950, 500, -150, 200);
        window.rect(950, 700, -800, 100);
        window.rect(100, 800, 100, -550);
        window.popStyle();
        break;
      case 3:
        window.rectMode(PConstants.CORNER);
        window.pushStyle();
        window.stroke(roadCol.getRed(), roadCol.getGreen(), roadCol.getBlue());
        window.fill(roadCol.getRed(), roadCol.getGreen(), roadCol.getBlue());
        window.rect(100, 100, 700, 150);

        window.popStyle();
    }
  }
}
