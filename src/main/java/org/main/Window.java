package org.main;

import java.awt.Color;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

/**
 * Standard PApplet window, acts as manager.
 */
public class Window extends PApplet {
  Player player;
  ArrayList<Track> tracks = new ArrayList<Track>();

  // Largely for testing
  StraightTrack track1;
  StraightTrack track2;
  StraightTrack track3;
  StraightTrack track4;
  HardTurnTrack bend1;
  HardTurnTrack bend2;
  HardTurnTrack bend3;
  HardTurnTrack bend4;

  /**
   * Checks for user input.
   *
   * @param event key pressed
   */
  public void keyPressed(KeyEvent event) {
    int keyCode = event.getKeyCode();
    switch (keyCode) {
      case LEFT:
        player.moveLeft();
        break;
      case RIGHT:
        player.moveRight();
        break;
      case UP:
        player.moveUp();
        break;
      case DOWN:
        player.moveDown();
        break;
      default:
        break;
    }
  }

  /**
   * Window size.
   */
  public void settings() {
    size(640, 360);
  }


  public void setup() {
    this.init();
  }

  /**
   * Initializes all components.
   */
  public void init() {
    /*
     Track are all straight paths
     */
    track1 = new StraightTrack(115, 15, 410, 75, this);
    track2 = new StraightTrack(550, 115, 75, 130, this);
    track3 = new StraightTrack(115, 270, 410, 75, this);
    track4 = new StraightTrack(15, 115, 75, 130, this);

    /*
     Bends are curved the paths. Display not accurate to detection.
     */
    bend1 = new HardTurnTrack(525, 115, 25, 75, this, 1);
    bend2 = new HardTurnTrack(115, 115, 25, 75, this, 2);
    bend3 = new HardTurnTrack(115, 245, 25, 75, this, 3);
    bend4 = new HardTurnTrack(525, 245, 25, 75, this, 4);

    tracks.add(bend1);
    tracks.add(bend2);
    tracks.add(bend3);
    tracks.add(bend4);
    tracks.add(track1);
    tracks.add(track2);
    tracks.add(track3);
    tracks.add(track4);

    player = new Player(
            new PVector((float) this.width / 2, (float) this.height / 2),
            5,
            new Color(255, 0, 0),
            tracks,
            this);
  }

  /**
   * Runs every frame.
   */
  public void draw() {
    background(0, 255, 0);
    for (Track track : tracks) {
      track.draw();
    }
    player.update();
    player.draw();
  }

  /**
   * Runs the program.
   *
   * @param passedArgs unused
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"eatBubbles"};
    Window eatBubbles = new Window();
    PApplet.runSketch(appletArgs, eatBubbles);
  }
}

