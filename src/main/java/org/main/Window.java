package org.main;

import java.awt.Color;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

public class Window extends PApplet {
  Player player;
  ArrayList<Track> tracks = new ArrayList<Track>();
  StraightTrack track1;
  StraightTrack track2;
  StraightTrack track3;
  StraightTrack track4;

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

  public void settings() {
    size(640, 360);
  }

  public void setup() {
    this.init();
  }

  public void init() {
    track1 = new StraightTrack(20, 20, 600, 75, this);
    track2 = new StraightTrack(545, 20, 75, 320, this);
    track3 = new StraightTrack(20, 265, 600, 75, this);
    track4 = new StraightTrack(20, 20, 75, 320, this);

    tracks.add(track1);
    tracks.add(track2);
    tracks.add(track3);
    tracks.add(track4);

    player = new Player(
            new PVector((float) this.width / 2, (float) this.height / 2),
            new PVector(0, 1),
            11,
            new Color(255, 0, 0),
            2,
            tracks,
            this);
  }

  public void draw() {
    background(0, 255, 0);
    for (Track track : tracks) {
      track.draw();
    }
    player.update();
    player.draw();
  }

  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"eatBubbles"};
    Window eatBubbles = new Window();
    PApplet.runSketch(appletArgs, eatBubbles);
  }
}

