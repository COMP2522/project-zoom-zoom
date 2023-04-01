package project;

import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

/** Manages the track segments.
 *
 * @author MaxwellV
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

  PImage trackImg;

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
//  public void initTrack1() {
//    tracks.add(new TrackPiece(180, 40, 1100, 40, 1100, 140, 180, 140, window)); // Top Straight
//    tracks.add(new TrackPiece(1100, 40, 1240, 180, 1140, 180, 1100, 140, window)); // Top -> Right
//    tracks.add(new TrackPiece(1240, 180, 1240, 540, 1140, 540, 1140, 180, window)); // Right
//    tracks.add(new TrackPiece(1240, 540, 1100, 680, 1100, 580, 1140, 540, window)); // Right -> Bottom
//    tracks.add(new TrackPiece(1100, 680, 180, 680, 180, 580, 1100, 580, window)); // Bottom Straight
//    tracks.add(new TrackPiece(180, 680, 40, 540, 140, 540, 180, 580, window)); // Bottom -> Left
//    tracks.add(new TrackPiece(40, 540, 40, 180, 140, 180, 140, 540, window)); // Left
//    tracks.add(new TrackPiece(40, 180, 180, 40, 180, 140, 140, 180, window)); // Left -> Top
//
//    //grassImage = window.loadImage("Game/images/trackGrass4.png");
//  }

  public PVector getStartCords(int numberOfPlayers, int playerNumber) {
    return new PVector(300, 200);
  }

  /** Draws to screen. */
  public void draw() {
    window.background(grassColor.getRed(), grassColor.getGreen(), grassColor.getBlue());
    switch (trackChoice){
      case 1:
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
        window.pushStyle();
        window.stroke(roadCol.getRed(), roadCol.getGreen(), roadCol.getBlue());
        window.fill(roadCol.getRed(), roadCol.getGreen(), roadCol.getBlue());
        window.rect(100, 100, 700, 150);

        window.popStyle();
    }
  }

  public boolean isOnTrack(int xCord, int yCord) {
    return onTrackCheck(xCord, yCord);
  }

  public boolean isOnTrack(PVector cords) {
    return onTrackCheck(Math.round(cords.x), Math.round(cords.y));
  }

  private boolean onTrackCheck(int xCord, int yCord) {
    for (TrackPiece eachPiece : tracks) {
      if (eachPiece.isOnTrack(xCord, yCord)) {
        return true;
      }
    }
    return false;
  }
}
