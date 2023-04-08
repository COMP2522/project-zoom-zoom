package project;

import processing.core.PImage;
import processing.core.PVector;

import java.awt.Color;
import java.util.ArrayList;

/** Manages the track segments.
 *
 * @author MaxwellV
 */
public class TrackManager implements Drawable {
  /**
   * Contains whether each pixel is on the track.
   */
  private boolean[][] trackPixels;

  /**
   * Active window.
   */
  private GameManager window;

  /**
   * Grass background image.
   */
  private PImage grassImage;

  /**
   * Color of the grass.
   */
  private Color grassColor = new Color(0, 132, 0);

  /**
   * Contain all track segments.
   */
  private ArrayList<TrackPiece> tracks = new ArrayList<TrackPiece>();

  /**
   * Track Manager constructor.
   *
   * @param window Current window
   */
  public TrackManager(GameManager window) {
    this.window = window;
    trackPixels = new boolean[window.getDisplayWidthCustom()][window.getDisplayHeightCustom()];
  }

  private boolean isParseTrackTestingPrints = false;

  /**
   * Initializes track. Test version to init from string.
   *
   * @param input String to be parsed
   */
  public void initTrack(String input) {
    // Uses inputted string to populate track arraylist
    parseTrackCords(input);

    // Adds copies each tracks boolean array to manager's
    for (TrackPiece eachPiece : tracks) {
      trackPixels = eachPiece.addPixels(trackPixels);
    }
  }

  /**
   * Sets all values of int array to zero.
   *
   * @param inputArray Array to be reset
   */
  private void resetArray(int[] inputArray) {
    for (int timer = 0; timer < inputArray.length; timer++) {
      inputArray[timer] = 0;
    }
  }

  /**
   * Parses string to generate track pieces.
   *
   * @param input inputted string, to be converted to JSON
   */
  private void parseTrackCords(String input) {
    // Testing print
    if (isParseTrackTestingPrints) {
      System.out.println("parseTracks Called\nInput: \t" + input + "\n");
    }

    // Initialize array values
    int[] cordsHolder = new int[8];
    int arrayPlacement = 0;
    resetArray(cordsHolder);

    // Initialize parsing values
    int currCordInt = 0;
    String currCordString = "";

    // Decide what to do with inputted values
    for (int timer = 0; timer < input.length(); timer++) {
      switch (input.charAt(timer)) {
        case ' ', ',': // Space and comma character separates portions of single piece
          // Store int parsed from the input string.
          currCordInt = Integer.parseInt(currCordString);
          cordsHolder[arrayPlacement] = currCordInt;
          arrayPlacement++;

          // Reset parsing string, now int is stored
          currCordString = "";

          // Testing prints
          if (isParseTrackTestingPrints) {
            System.out.print("\t");
          }
          break;

        case 'n': // n Character separates a each pieces cords.
          // Parse and store final last int
          currCordInt = Integer.parseInt(currCordString);
          cordsHolder[arrayPlacement] = currCordInt;

          // Add track to arraylist
          tracks.add(new TrackPiece(cordsHolder[0], cordsHolder[1], cordsHolder[2], cordsHolder[3],
                      cordsHolder[4],cordsHolder[5], cordsHolder[6], cordsHolder[7], window));

          // Reset values for next piece
          resetArray(cordsHolder);
          arrayPlacement = 0;
          currCordString = "";

          // Testing prints
          if (isParseTrackTestingPrints) {
            System.out.print("\nNext Track\t");
          }
          break;

        default:
          // Checks if char is digit.
          if (Character.isDigit(input.charAt(timer))) {
            currCordString += input.charAt(timer);

            // Testing prints
            if (isParseTrackTestingPrints) {
              System.out.print("'" + input.charAt(timer) + "' ");
            }

          // Ignore tabs and newlines, to formatting when manually writing cords
          } else if (!(input.charAt(timer) == '\t' || (input.charAt(timer) == '\n'))) {
            System.out.println("Character '" + input.charAt(timer) + "' is not valid");
          }
        break;
      }
    }
    // If tracks cords aren't filled, create shape with 0 in remaining slots.
    if (arrayPlacement > 0) {
      System.out.println("Incomplete track added, remaining values set to 0");
      tracks.add(new TrackPiece(cordsHolder[0], cordsHolder[1], cordsHolder[2], cordsHolder[3],
              cordsHolder[4],cordsHolder[5], cordsHolder[6], cordsHolder[7], window));
    }
  }


  /**
   * Draws the track background and pieces to screen.
   */
  public void draw() {
    // Flat color option kept because BG Image causing lag on some computers
    //window.image(grassImage, 0, 0, window.getDisplayWidthCustom(), window.getDisplayHeightCustom());
    window.background(grassColor.getRed(), grassColor.getGreen(), grassColor.getRed());
    for (TrackPiece eachPiece : tracks) {
      eachPiece.draw();
    }
  }

  /**
   * Clear's the tracks arraylist and onTrack array.
   */
  public void clearTrack() {
    for (int wTimer = 0; wTimer < window.getDisplayWidthCustom(); wTimer++) {
      for (int hTimer = 0; hTimer < window.getDisplayHeightCustom(); hTimer++) {
        trackPixels[wTimer][hTimer] = false;
      }
    }
    tracks.clear();
  }

  /**
   * Checks if the cords are on the track.
   *
   * @param inputCords Coordinates to be checked
   * @return True / False for if cords fall on the track
   */
  public boolean isOnTrack(PVector inputCords) {
    // Check if cords are on screen (Range of the array)
    if ((int) inputCords.x > window.getDisplayWidthCustom() - 1
            || (int) inputCords.y > window.getDisplayHeightCustom() - 1
            || (int) inputCords.x < 0 || (int) inputCords.y < 0) {
      return false;
    }
    return trackPixels[(int) inputCords.x][(int) inputCords.y];
  }

  /**
   * Prints the array to console, '-' is grass, 'X' is track.
   */
  public void printPixels() {
    String output = "";
    for (int hTimer = 0; hTimer < window.getDisplayHeightCustom(); hTimer += 2) {
      System.out.print(hTimer + "\t");
      for (int wTimer = 0; wTimer < window.getDisplayWidthCustom(); wTimer += 2) {
        if (trackPixels[wTimer][hTimer] == true) {
          System.out.print("X");
        } else {
          System.out.print("-");
        }
      }
      System.out.print("\n");
    }
    System.out.println(window.getDisplayWidthCustom() + " x " + window.getDisplayHeightCustom());
  }

  /**
   * Return the starting position for designated player.
   * @param playerNumber Which player's position (1 or 2)
   * @return Relevant player's position
   */
  public PVector getStartCords(int playerNumber) {
    try {
      return tracks.get(0).getStartCord(playerNumber);
    } catch (Exception e) {
      throw new NullPointerException();
    }
  }
}
