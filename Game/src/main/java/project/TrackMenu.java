package project;

import processing.core.PImage;
import processing.core.PVector;

/**
 * The TrackMenu class displays the track selection menu for the game.
 * It provides the user with the option to select one of the three
 * available tracks. This class is a singleton class, meaning there
 * can only be one instance of this class throughout the game.
 */
public class TrackMenu {

  private TrackManager trackManager;

  /**
   * The GameManager object that creates and manages the game window.
   */
  private final GameManager window;

  /**
   * This is a private static instance of the TrackMenu class.
   */
  private static TrackMenu instance;

  /**
   * This is a private instance variable of the Button class representing the first track button.
   */
  private Button track1;

  /**
   * This is a private instance variable of the Button class representing the second track button.
   */
  private Button track2;

  /**
   * This is a private instance variable of the Button class representing the third track button.
   */
  private Button track3;

  /**
   * This is a private boolean variable that determines whether
   * or not to show the title of the track menu
   * depends on the frame of the window.
   */
  private boolean showTitle = true;

  /**
   * This is a private static final integer variable
   * representing the clock rate of the title animation.
   */
  private static final int clock = 5;

  /**
   * These are private instance variables of the PImage
   * class representing the background image, title
   * image, and font images for each track button.
   */
  private PImage bg, title, track1Font, track2Font, track3Font;
  private static final int buttonWidth = 300;
  private static final int buttonHeight = 80;
  public static float halfWindowSize;

  /**
   * This is a private constructor for the TrackMenu class that takes a GameManager object as a parameter.
   *
   * @param gameManager The window for the current window.
   */
  private TrackMenu(GameManager gameManager) {
    this.window = gameManager;
    trackManager = window.getTrackManager();
    halfWindowSize = window.displayWidth / 2.0f;
  }

  /**
   * This is a public static method that returns an instance of the TrackMenu class. If an instance of the
   * class does not already exist, a new one is created.
   *
   * @param window The game manager for the current game.
   * @return The singleton instance of the TrackMenu class.
   */
  public static TrackMenu getInstance(GameManager window) {
    if (instance == null) {
      instance = new TrackMenu(window);
    }
    return instance;
  }

  /**
   * This is a public method that sets up the track menu. It loads the background image, title image, and
   * font images for each track button, creates the track buttons, and sets their positions and colors.
   */
  public void setUp() {
    bg = window.loadImage("Game/images/BGImage2.png");
    title = window.loadImage("Game/images/Select Track.png");
    track1Font = window.loadImage("Game/images/Track1.png");
    track2Font = window.loadImage("Game/images/Track2.png");
    track3Font = window.loadImage("Game/images/Track3.png");
    window.textSize(40);
    track1 = new Button(new PVector(halfWindowSize - 525, 490), buttonWidth,
        buttonHeight, "", Button.PURPLE, window);
    track2 = new Button(new PVector(halfWindowSize - 125, 490), buttonWidth,
        buttonHeight, "", Button.PURPLE, window);
    track3 = new Button(new PVector(halfWindowSize + 325, 490), buttonWidth,
        buttonHeight, "", Button.PURPLE, window);
  }

  /**
   * This is a public method that draws the track menu. It sets the background color,
   * draws the background image, toggles the title visibility based on the clock rate,
   * draws the title image, updates and draws each track button, and changes the menu
   * to the race screen if the first track button is clicked.
   */
  public void draw() {
    window.background(64, 64, 64);
    window.image(bg, 0, 0, window.displayWidth, window.displayHeight);
    drawTitle();
    drawFont();
    buttonIsClicked();
  }

  public void drawTitle() {
    if (window.frameCount % clock == 0) {
      showTitle = !showTitle;
    }
    if (showTitle) {
      window.image(title, halfWindowSize - 200,window.displayHeight/4);
    }
  }

  public void drawFont() {
    track1.click();
    track1.draw();
    window.image(track1Font, halfWindowSize - 500, 500);
    track2.click();
    track2.draw();
    window.image(track2Font, halfWindowSize - 100, 500);
    track3.click();
    track3.draw();
    window.image(track3Font, halfWindowSize + 350, 500);
  }

  public void buttonIsClicked() {
    if (track1.isLeftClicked()) {
      trackManager.clearTrack();
      trackManager.initTrack("Track 1");
      window.startRace();
      window.menu = 4;
    }
    if (track2.isLeftClicked()) {
      trackManager.clearTrack();
      trackManager.initTrack("Track 2");
      window.startRace();
      window.menu = 4;
    }
    if (track3.isLeftClicked()) {
      trackManager.clearTrack();
      trackManager.initTrack("Track 3");
      window.startRace();
      window.menu = 4;
    }
  }
}
