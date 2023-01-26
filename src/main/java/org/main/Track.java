package org.main;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Abstract class for all tracks.
 * The tracks will differ in shape, but they all check
 * if the inputted position is in their drawn area.
 */
public abstract class Track extends PApplet {

  public abstract boolean isOnTrack(PVector currentPosition);

  public abstract void draw();
}
