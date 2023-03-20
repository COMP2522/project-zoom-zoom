package project;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Zoom zoom window class.
 * Example window class from lab 03 to test
 * other classes.
 *
 */
public class Window extends PApplet {
  Timer timer;
  Timer timer2;
  MainMenu mainMenu;
  ControlMenu controlMenu;
  ArrayList<Sprite> sprites;
  ArrayList<Sprite> enemies;
  Player player1;
  Player player2;
  Controls playerControls;
  // Default player1 controls (arrow keys)
  int[] player1Keys = {38, 40, 37, 39};
  // Default player2 controls (WASD)
  int[] player2Keys = {87, 83, 65, 68};
  int numEnemies = 1;
  int minSize = 10;
  int maxSize = 40;
  public static boolean audio = true;
  private int check = 1;

  /*
   * 0. Main menu
   * 1. 1 player game
   * 2. 2 player game
   * more values will come when menus are implemented.
   * TODO
   *  car mod menu
   *  track menu
   *  control menu
   * car mod and track menus should happen before game starts
   * control menu button in main menu
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
  }

  /**
   * Initializes all sprites needed for a one player game.
   */
  public void init1Player() {
    enemies = new ArrayList<Sprite>();
    sprites = new ArrayList<Sprite>();

    player1 = new Player(
        new PVector(this.width / 2, this.height / 2),
        new PVector(50, 1),
        (minSize + 10),
        0.1F,
        new Color(0, 255, 0),
        this);

    player2 = new Player(
        new PVector(this.width / 2, this.height / 2),
        new PVector(500, 1),
        (minSize + 10),
        0.01F,
        new Color(0, 255, 0),
        this);
    playerControls = new Controls(this, player1, player2, player1Keys, player2Keys);

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
    sprites.add(player1);
  }
  /**
   * Initializes all sprites needed for a two player game.
   */
  public void init2Player() {
    enemies = new ArrayList<Sprite>();
    sprites = new ArrayList<Sprite>();

    player1 = new Player(
        new PVector(this.width / 2, this.height / 2),
        new PVector(50, 1),
        (minSize + 10),
        0.1F,
        new Color(0, 255, 0),
        this);

    player2 = new Player(
        new PVector(this.width / 2, this.height / 2),
        new PVector(500, 1),
        (minSize + 10),
        0.01F,
        new Color(0, 255, 255),
        this);
    playerControls = new Controls(this, player1, player2, player1Keys, player2Keys);

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
    sprites.add(player1);
    sprites.add(player2);
  }

  /**
   * keyPressed, take key event input and update the controls boolean variable to true.
   *
   * @param event key event
   */
  @Override
  public void keyPressed(KeyEvent event) {
    int keyCode = event.getKeyCode();
    if (keyCode == TAB) {
      menu = 0;
    }
    if (menu == 1 || menu == 2)
      Controls.setMovementTrue(keyCode);
  }

  /**
   * keyReleased, set controls boolean variable to false when an input key is released.
   */
  @Override
  public void keyReleased() {
    if (menu == 1 || menu == 2)
      Controls.setMovementFalse(keyCode);
  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {
    if (audio && check == 1) {
      BGM.getBGM(true);
      audio = false;
      check++;
    }
    background(0);
    switch (menu) {
      case 0 -> { // Main menu
        mainMenu = MainMenu.getInstance(this);
        mainMenu.setup();
        mainMenu.draw();
        break;
      }
      case 1 -> { // 1 Player game
        background(64, 64, 64);
        timer = Timer.getInstance();
        timer.running = true;
        timer.showTimer(this);
        // Move player around the screen.
        Controls.playerMovement();
        for (Sprite sprite : sprites) {
          sprite.update();
          sprite.draw();
        }
        ArrayList<Sprite> toRemove = new ArrayList<Sprite>();
        for (Sprite enemy : enemies) {
          if (Collidable.collided(player1, enemy)) {
            toRemove.add(enemy);
          }

        }
        for (Sprite enemy : toRemove) {
          // TODO: implement compareTo and equals to make this work
          if (enemy.compareTo(player1) > 0 || enemy.equals(player1)) {
            enemies.remove(enemy);
            sprites.remove(enemy);
          } else if (enemy.compareTo(player1) < 0) {
          }
        }
        break;
      }
      case 2 -> { // 2 Player game
        background(255,255,0);
        timer2 = Timer.getInstance();
        timer2.running = true;
        timer2.showTimer(this);
        // Move player around the screen.
        Controls.playerMovement();
        for (Sprite sprite : sprites) {
          sprite.update();
          sprite.draw();
        }
        ArrayList<Sprite> toRemove = new ArrayList<Sprite>();
        for (Sprite enemy : enemies) {
          if (Collidable.collided(player1, enemy)) {
            toRemove.add(enemy);
          }

        }
        for (Sprite enemy : toRemove) {
          // TODO: implement compareTo and equals to make this work
          if (enemy.compareTo(player1) > 0 || enemy.equals(player1)) {
            enemies.remove(enemy);
            sprites.remove(enemy);
          } else if (enemy.compareTo(player1) < 0) {
          }
        }
        break;
      }
      case 3 -> {
        background(64, 64, 64);
        controlMenu = ControlMenu.getInstance(this);
        controlMenu.setup();
        controlMenu.draw();
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
    // Run background music
  }
}