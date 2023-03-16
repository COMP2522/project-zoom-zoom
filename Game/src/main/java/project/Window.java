package project;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.ArrayList;


/**
 * Zoom zoom window class.
 * Example window class from lab 03 to test
 * other classes.
 *
 */
public class Window extends PApplet {

  MainMenu mainMenu;
  ArrayList<Sprite> sprites;
  ArrayList<Sprite> enemies;
  Player player;
  int numEnemies = 1;
  int minSize = 10;
  int maxSize = 40;

  /*
   * 0. Main menu
   * 1. game
   */
  int menu = 0;

  /**
   * Called once at the beginning of the program.
   */
  public void settings() {
    size(displayWidth, displayHeight);
  }

  /**
   * Called once at the beginning of the program.
   * Initializes all objects.
   */
  public void setup() {
    this.init();
  }

  /**
   * Initializes all sprites needed to be drawn onto the window.
   */
  public void init() {
    enemies = new ArrayList<Sprite>();
    sprites = new ArrayList<Sprite>();
    player = new Player(
        new PVector(this.width / 2, this.height / 2),
        new PVector(50, 1),
        (minSize + 10),
        0.1F,
        new Color(0, 255, 0),
        this);

    for (int i = 0; i < numEnemies; i++) {
      enemies.add(new Enemy(
          new PVector(random(0, this.width), random(0, this.height)),
          new PVector(random(-1, 1), random(-1, 1)),
          random(minSize, maxSize),
          random(0, 2),
          new Color(255, 0, 0),
          this
      ));
    }
    sprites.addAll(enemies);
    sprites.add(player);
  }

  @Override
  public void keyPressed(KeyEvent event) {
    int keyCode = event.getKeyCode();
    switch (keyCode) {
      case LEFT ->
          // handle left
          player.setDirection(player.getDirection().rotate(-Window.PI / 16));
      case RIGHT ->
          // handle right
          player.setDirection(player.getDirection().rotate(Window.PI / 16));
      case UP ->
        player.setSpeed(0.1F);
      case DOWN ->
        player.setSpeed(0);
      case TAB -> {
        menu = 0;
        break;
      }
      default -> {
        break;
      }
    }
  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {
    background(0);
    switch (menu) {
      case 0 -> { // Main menu
        mainMenu = MainMenu.getInstance(this);
        mainMenu.setup();
        mainMenu.draw();
        break;
      }
      case 1 -> { // Game
        background(0);
        for (Sprite sprite : sprites) {
          sprite.update();
          sprite.draw();
        }
        ArrayList<Sprite> toRemove = new ArrayList<Sprite>();
        for (Sprite enemy : enemies) {
          if (Collidable.collided(player, enemy)) {
            toRemove.add(enemy);
          }

        }
        for (Sprite enemy : toRemove) {
          // TODO: implement compareTo and equals to make this work
          if (enemy.compareTo(player) > 0 || enemy.equals(player)) {
            enemies.remove(enemy);
            sprites.remove(enemy);
          } else if (enemy.compareTo(player) < 0) {
            init();
          }
        }
        break;
      }
      default -> {
        break;
      }
    }

  }


  /**
   * Main function.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"eatBubbles"};
    Window eatBubbles = new Window();
    PApplet.runSketch(appletArgs, eatBubbles);
  }
}