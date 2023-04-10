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

  /** Color of the grass. */
  private Color grassColor = new Color(0, 132, 0);

  /** Contain all track segments. */
  private ArrayList<TrackPiece> tracks = new ArrayList<TrackPiece>();

  Color roadCol = new Color(50,50,50);

  public void setTrackChoice(int trackChoice) {
    this.trackChoice = trackChoice;
  }

  public int getTrackChoice() {
    return trackChoice;
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

  public PVector getStartCords(int playerNum) {
      if (playerNum == 1){
        return new PVector(300, 200);
      }
      else return new PVector(300, 230);
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
        window.rect(100, 100, 600, 150);
        window.rect(550, 250, 150, 200);
        window.rect(600, 350, 300, 100);
        window.rect(900, 450, 100, -250);
        window.rect(900, 200, 300, -100);
        window.rect(1100, 200, 100, 600);
        window.rect(1100, 650, -300, 150);
        window.rect(900, 650, -400, 50);
        window.rect(500, 650, -300, 150);
        window.rect(200, 800, -100, -550);
        window.fill(255,235,205);
        window.rect(1050, 250, 25, 200);
        window.popStyle();
    }
  }
}
