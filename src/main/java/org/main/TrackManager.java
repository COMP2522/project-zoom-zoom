package org.main;

import processing.core.PVector;
import java.awt.Color;
import java.util.ArrayList;

/** Manages the track segments. */
public class TrackManager {
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
    tracks.add(new TrackPiece(20, 90, 70, 90, 90, 70, 90, 20, window));
    tracks.add(new TrackPiece(20, 270, 70, 270, 90, 290, 90, 340, window));
    tracks.add(new TrackPiece(620, 270, 570, 270, 550, 290, 550, 340, window));
    tracks.add(new TrackPiece(550, 20, 550, 70, 570, 90, 620, 90, window));
    tracks.add(new TrackPiece(90, 290, 550, 290, 550, 340, 90, 340, window));
    tracks.add(new TrackPiece(570, 270, 620, 270, 620, 90, 570, 90, window));
    tracks.add(new TrackPiece(20, 270, 70, 270, 70, 90, 20, 90, window));
    tracks.add(new TrackPiece(90, 20, 90, 70, 550, 70, 550, 20, window));
  }

  /** Draws to screen. */
  public void draw() {
    window.background(grassColor.getRGB(), grassColor.getGreen(),
            grassColor.getBlue());
    for (TrackPiece eachPiece : tracks) {
      eachPiece.draw();
    }
    boolean isOnTesterMode = false;
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
    for (TrackPiece eachPiece : tracks) {
      if (eachPiece.isOnTrack(xCord, yCord)) {
        return true;
      }
    }
    return false;
  }

  public boolean isOnTrack(PVector cords) {
    for (TrackPiece eachPiece : tracks) {
      if (eachPiece.isOnTrack((int) cords.x, (int) cords.y)) {
        return true;
      }
    }
    return false;
  }

}
