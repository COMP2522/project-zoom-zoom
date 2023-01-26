package org.main;

import java.awt.Color;
import java.util.ArrayList;
import processing.core.PVector;

/**
 * Player class.
 */
public class Player {
  private PVector position;
  private PVector direction;
  private float size;
  private float speed;
  private Color color;
  private Window window;
  private ArrayList<Track> track;
  private Boolean isOnTrack;

  public Player(PVector position, PVector direction, float size, Color color,
                float speed, ArrayList<Track> track, Window window) {
    this.position = position;
    this.direction = direction;
    this.size = size;
    this.color = color;
    this.speed = speed;
    this.track = track;
    this.window = window;
  }

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
    this.position = this.getPosition().add(new PVector(0, -5).mult(speed));
  }
  public void moveDown() {
    this.position = this.getPosition().add(new PVector(0, 5).mult(speed));
  }
  public void moveLeft() {
    this.position = this.getPosition().add(new PVector(-5, 0).mult(speed));
  }
  public void moveRight() {
    this.position = this.getPosition().add(new PVector(5,  0).mult(speed));
  }

  public PVector getPosition() {
    return position.copy();
  }
}
