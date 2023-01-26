package org.main;

import java.awt.Color;
import java.util.ArrayList;
import processing.core.PVector;

/**
 * Player class.
 */
public class Player {
  private PVector position;
  private float size;
  private Color color;
  private Window window;
  private ArrayList<Track> track;
  private Boolean isOnTrack;

  // Movement distance variable, unnecessary in game.
  private int movement = 9;

  /**
   * Constructor mostly copied from ball labs.
   *
   * @param position Starting position
   * @param size Size of ball
   * @param color Ball's color (red goes faster)
   * @param track Track pieces array
   * @param window Window
   */
  public Player(PVector position, float size, Color color, ArrayList<Track> track, Window window) {
    this.position = position;
    this.size = size;
    this.color = color;
    this.track = track;
    this.window = window;
  }

  /**
   * Checks status. On track is checked here, but should be moved to car when it's implemented.
   */
  public void update() {
    isOnTrack = false;
    for (Track track : track) {
      if (track.isOnTrack(this.position)) {
        System.out.println("On The Track");
        isOnTrack = true;
      }
    }
  }

  /**
   * Standard draw.
   */
  public void draw() {
    window.pushStyle();
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.ellipse(this.position.x, this.position.y, size, size);
    window.popStyle();
  }

  /**
   * Movement methods for testing.
   */
  public void moveUp() {
    this.position = this.getPosition().add(new PVector(0, -movement));
  }
  public void moveDown() {
    this.position = this.getPosition().add(new PVector(0, movement));
  }
  public void moveLeft() {
    this.position = this.getPosition().add(new PVector(-movement, 0));
  }
  public void moveRight() {
    this.position = this.getPosition().add(new PVector(movement,  0));
  }

  public PVector getPosition() {
    return position.copy();
  }
}
