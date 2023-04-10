package project;

import processing.core.PImage;
import processing.core.PVector;

/**
 * The TrackMenu class displays the track selection menu for the game.
 * It provides the user with the option to select one of the three
 * available tracks. This class is a singleton class, meaning
 * there can only be one instance of this class throughout the game.
 */
public class TrackMenu {

  /**
   * The TrackManager object that creates and manages the tracks.
   */
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
   * This is a private instance variable of the Button class representing
   * the first track button.
   */
  private Button track1;

  /**
   * This is a private instance variable of the Button class
   * representing the second track button.
   */
  private Button track2;

  /**
   * This is a private instance variable of the Button class
   * representing the third track button.
   */
  private Button track3;

  /**
   * This is a private boolean variable that determines whether or not
   * to show the title of the track menu depends on the frame of the window.
   */
  private boolean showTitle = true;

  /**
   * This is a private static final integer variable representing the clock
   * rate of the title animation.
   */
  private static final int clock = 5;

  /**
   * Half the width of the display.
   */
  private float halfDisplayWidth;

  /**
   * Background image.
   */
  private PImage bg;

  /**
   * Title test image.
   */
  private PImage title;

  /**
   * Text for Track 1.
   */
  private PImage track1Font;

  /**
   * Text for Track 2.
   */
  private PImage track2Font;

  /**
   * Text for Track 3.
   */
  private PImage track3Font;

  /**
   * This is a private constructor for the TrackMenu class that takes
   * a GameManager object as a parameter.
   *
   * @param gameManager The window for the current window.
   */
  private TrackMenu(GameManager gameManager) {
    this.window = gameManager;
    trackManager = window.getTrackManager();
    halfDisplayWidth = (float) (window.displayWidth / 2);
  }

  /**
   * This is a public static method that returns an instance of the
   * TrackMenu class. If an instance of the class does not already
   * exist, a new one is created.
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
   * Gets the image from the path.
   *
   * @param name the name of the image
   * @return PImage the path of the image
   */
  private PImage getImage(String name) {
    return window.loadImage("Game/images/" + name);
  }

  /**
   * This is a public method that sets up the track menu.
   * It loads the background image, title image, and
   * font images for each track button, creates the track buttons,
   * and sets their positions and colors.
   */
  public void setUp() {
    bg = getImage("BGImage2.png");
    title = getImage("Select Track.png");
    track1Font = getImage("Track1.png");
    track2Font = getImage("Track2.png");
    track3Font = getImage("Track3.png");
    window.textSize(40);
    track1 = new Button(new PVector(halfDisplayWidth - 525, 490), 300,
        80, "", Button.PURPLE, window);
    track2 = new Button(new PVector(halfDisplayWidth - 125, 490), 300,
        80, "", Button.PURPLE, window);
    track3 = new Button(new PVector(halfDisplayWidth + 325, 490), 300,
        80, "", Button.PURPLE, window);
  }

  /**
   * This is a public method that draws the track menu.
   * It draws the background image, title image, and
   * font images for each track button, and draws the track buttons.
   */
  public void draw() {
    window.background(64, 64, 64);
    window.image(bg, 0, 0, window.displayWidth, window.displayHeight);
    drawTitle();
    drawFont();
    checkButtonClick();
  }

  /**
   * This is a private method that draws the title image of the track menu.
   * It animates the title image by changing the showTitle boolean variable
   * every 5 frames.
   */
  private void drawTitle() {
    if (window.frameCount % clock == 0) {
      showTitle = !showTitle;
    }
    if (showTitle) {
      window.image(title, halfDisplayWidth - 200, window.displayHeight / 4);
    }
  }

  /**
   * This is a private method that draws the font images for each track button.
   * It draws the font images for the track button, and draws the track buttons.
   */
  private void drawFont() {
    track1.click();
    track1.draw();
    window.image(track1Font, halfDisplayWidth - 500, 500);
    track2.click();
    track2.draw();
    window.image(track2Font, halfDisplayWidth - 100, 500);
    track3.click();
    track3.draw();
    window.image(track3Font, halfDisplayWidth + 350, 500);
  }

  /**
   * This is a private method that checks if the user has clicked on one of the
   * track buttons.
   */
  private void checkButtonClick() {
    if (track1.isLeftClicked()) {
      trackManager.initTrack("180,40 1100,40 1100,140 180,140n1100,40"
            + " 1240,180 1140,180 1100,140n1240,180 1240,540 1140,540 1140,"
            + "180n1240,540 1100,680 1100,580 1140,540n1100,680 180,680 180,"
            + "580 1100,580n180,680 40,540 140,540 180,580n40,540 40,180 140,"
            + "180 140,540n40,180 180,40 180,140 140,180n");
      window.startRace();
      window.menu = 4;
    }
    if (track2.isLeftClicked()) {
      trackManager.initTrack("180,40 1100,40 1100,140 180,140n1100,40"
              + " 1240,180 1140,180 1100,140n1240,180 1240,540 1140,540 1140,"
              + "180n1240,540 1100,680 1100,580 1140,540n1100,680 180,680 180,"
              + "580 1100,580n180,680 40,540 140,540 180,580n40,540 40,180 140,"
              + "180 140,540n40,180 180,40 180,140 140,180n");
      window.startRace();
      window.menu = 4;
    }
    if (track3.isLeftClicked()) {
      trackManager.initTrack("180,40 1100,40 1100,140 180,140n1100,40"
              + " 1240,180 1140,180 1100,140n1240,180 1240,540 1140,540 1140,"
              + "180n1240,540 1100,680 1100,580 1140,540n1100,680 180,680 180,"
              + "580 1100,580n180,680 40,540 140,540 180,580n40,540 40,180 140,"
              + "180 140,540n40,180 180,40 180,140 140,180n");
      window.startRace();
      window.menu = 4;
    }
  }
}
