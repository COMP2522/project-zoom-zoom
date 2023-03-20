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
  TrackManager trackManager;

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
      case UP:
        player.moveUp();
        break;
      case RIGHT:
        player.moveRight();
        break;
      case DOWN:
        player.moveDown();
        break;
      case ENTER:
        System.out.print("Mouse X: " + mouseX + "\tMouse Y: " + mouseY + "\n");
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
    //trackBuilder = new TrackBuilder(this);
    trackManager = new TrackManager(this);
    trackManager.init();
    player = new Player(
            new PVector((float) this.width / 2, (float) this.height / 2),
            5,
            new Color(255, 0, 0),
            this);
  }

  /**
   * Runs every frame.
   */
  public void draw() {
    background(0, 255, 0);
    trackManager.draw();
    player.draw();
    if (trackManager.isOnTrack(player.getPosition())) {
//      System.out.println("Player on track!");
    }
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

