package project;

import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

/** Manages the track segments. */
public class TrackManager {

  /** Enables testing mode */
  final static boolean isOnTesterMode = false;

  /** Active window. */
  private GameManager window;

  /** Color of the grass. */
  private Color grassColor = new Color(0, 132, 0);

  /** Contain all track segments. */
  private ArrayList<TrackPiece> tracks = new ArrayList<TrackPiece>();

  /** Track Manager constructor.
   *
   * @param window Current window
   */
  public TrackManager(GameManager window) {
    this.window = window;
  }

  /** Initializes track manager. */
  public void initTrack() {
    tracks.add(new TrackPiece(180, 40, 1100, 40, 1100, 140, 180, 140, window)); // Top Straight
    tracks.add(new TrackPiece(1100, 40, 1240, 180, 1140, 180, 1100, 140, window)); // Top -> Right
    tracks.add(new TrackPiece(1240, 180, 1240, 540, 1140, 540, 1140, 180, window)); // Right
    tracks.add(new TrackPiece(1240, 540, 1100, 680, 1100, 580, 1140, 540, window)); // Right -> Bottom
    tracks.add(new TrackPiece(1100, 680, 180, 680, 180, 580, 1100, 580, window)); // Bottom Straight
    tracks.add(new TrackPiece(180, 680, 40, 540, 140, 540, 180, 580, window)); // Bottom -> Left
    tracks.add(new TrackPiece(40, 540, 40, 180, 140, 180, 140, 540, window)); // Left
    tracks.add(new TrackPiece(40, 180, 180, 40, 180, 140, 140, 180, window)); // Left -> Top
  }

  public PVector getStartCords(int numberOfPlayers, int playerNumber) {
    return tracks.get(0).getStartCord(numberOfPlayers, playerNumber);
  }

  /** Draws to screen. */
  public void draw() {
    window.background(grassColor.getRGB(), grassColor.getGreen(),
            grassColor.getBlue());
    for (TrackPiece eachPiece : tracks) {
      eachPiece.draw();
    }
    if (isOnTesterMode) { // Shows X - Y Coordinates
      window.fill(0, 0, 255);
      window.ellipse(15, 15, 10, 10);
      window.fill(255, 0, 0);
      window.ellipse(630, 345, 10, 10);
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
