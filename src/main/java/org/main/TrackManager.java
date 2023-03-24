package org.main;

import processing.core.PVector;
import java.awt.Color;
import java.util.ArrayList;

/** Manages the track segments. */
public class TrackManager {

  /** Enables testing mode */
  final static boolean isOnTesterMode = false;

  /** Active window. */
  private Window window;

  /** Color of the grass. */
  private Color grassColor = new Color(0, 255, 0);

  /** Timer to disable repeated prints. */
  private int testTimer = 0;

  /** Contain all track segments. */
  private ArrayList<TrackPiece> tracks = new ArrayList<TrackPiece>();

  /** Track Manager constructor.
   *
   * @param window Current window
   */
  public TrackManager(Window window) {
    this.window = window;
  }

  /** Initializes track manager. */
  public void init() {
      tracks.add(new TrackPiece(90, 20, 550, 20, 550, 70, 90, 70, window)); // Top Straight
      tracks.add(new TrackPiece(550, 20, 620, 90, 570, 90, 550, 70, window)); // Top -> Right
      tracks.add(new TrackPiece(620, 90, 620, 270, 570, 270, 570, 90, window)); // Right
      tracks.add(new TrackPiece(620, 270, 550, 340, 550, 290, 570, 270, window)); // Right -> Bottom
      tracks.add(new TrackPiece(550, 340, 90, 340, 90, 290, 550, 290, window)); // Bottom Straight
      tracks.add(new TrackPiece(90, 340, 20, 270, 70, 270, 90, 290, window)); // Bottom -> Left
      tracks.add(new TrackPiece(20, 270, 20, 90, 70, 90, 70, 270, window)); // Left
      tracks.add(new TrackPiece(20, 90, 90, 20, 90, 70, 70, 90, window)); // Left -> Top
//    } else {
//      int offset = 4;
//      tracks.add(new TrackPiece(window, 90, 20, 550, 20, 550, 70, 90, 70)); // Top Straight
//      tracks.add(new TrackPiece(window, 550, 20, 620, 90, 570, 90, 550, 70)); // Top -> Right
//      tracks.add(new TrackPiece(window, 620, 90, 620, 270, 570, 270, 570, 90)); // Right
//      tracks.add(new TrackPiece(window, 620, 270, 550, 340, 550, 290, 570, 270)); // Right -> Bottom
//      tracks.add(new TrackPiece(window, 550, 340, 90, 340, 90, 290, 550, 290)); // Bottom Straight
//      tracks.add(new TrackPiece(window, 90, 340, 20, 270, 70, 270, 90, 290)); // Bottom -> Left
//      tracks.add(new TrackPiece(window, 20, 270, 20, 90, 70, 90, 70, 270)); // Left
//      tracks.add(new TrackPiece(window, 20, 90, 90, 20, 90, 70, 70, 90)); // Left -> Top
//    }
  }

  /** Draws to screen. */
  public void draw() {
    window.background(grassColor.getRGB(), grassColor.getGreen(),
            grassColor.getBlue());
    for (TrackPiece eachPiece : tracks) {
      eachPiece.draw();
    }
//    if (false) {
    if (isOnTesterMode) { // Shows X - Y Coordinates
      window.fill(0, 0, 255);
      window.ellipse(15, 15, 10, 10);
      window.fill(255, 0, 0);
      window.ellipse(630, 345, 10, 10);
      if (testTimer == 0) {
        System.out.println("Blue: 0, 0\t Red: 640, 360");
        testTimer++;
      }
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
