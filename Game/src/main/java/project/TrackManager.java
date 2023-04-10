package project;

import java.awt.Color;
import java.util.ArrayList;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Manages the track segments.
 *
 * @author MaxwellV
 */
public class TrackManager implements Drawable {
  /**
   * Contains whether each pixel is on the track.
   */
  private boolean[][] trackPixels;

  /**
   * Active gameManager.
   */
  private GameManager gameManager;

  /**
   * Color of the grass.
   */
  private Color grassColor = new Color(0, 132, 0);

  /**
   * Contain all track segments.
   */
  private ArrayList<TrackPiece> tracks = new ArrayList<TrackPiece>();

  /**
   * Constructor.
   *
   * @param gameManager The game manager
   */
  public TrackManager(GameManager gameManager) {
    this.gameManager = gameManager;
    this.trackPixels = new boolean[gameManager.getDisplayWidthCustom() + 1][
            gameManager.getDisplayHeightCustom() + 1];
  }

  /**
   * Initializes track, parse cords from input string.
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
        case ' ', ',': // ' ' and , character separates portions of single piece
          // Store int parsed from the input string.
          currCordInt = Integer.parseInt(currCordString);
          cordsHolder[arrayPlacement] = currCordInt;
          arrayPlacement++;

          // Reset parsing string, now int is stored
          currCordString = "";

          break;

        case 'n': // n Character separates a each pieces cords.
          // Parse and store final last int
          currCordInt = Integer.parseInt(currCordString);
          cordsHolder[arrayPlacement] = currCordInt;

          // Add track to arraylist
          tracks.add(new TrackPiece(cordsHolder[0], cordsHolder[1],
                  cordsHolder[2], cordsHolder[3], cordsHolder[4],
                  cordsHolder[5], cordsHolder[6], cordsHolder[7],
                  gameManager));

          // Reset values for next piece
          resetArray(cordsHolder);
          arrayPlacement = 0;
          currCordString = "";

          break;

        default:
          // Checks if char is digit.
          if (Character.isDigit(input.charAt(timer))) {
            currCordString += input.charAt(timer);

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
      tracks.add(new TrackPiece(cordsHolder[0], cordsHolder[1], cordsHolder[2],
              cordsHolder[3], cordsHolder[4], cordsHolder[5], cordsHolder[6],
              cordsHolder[7], gameManager));
    }
  }


  /**
   * Draws the track background and pieces to screen.
   */
  public void draw() {

    // Flat color option kept because BG Image causing lag on some computers
    gameManager.background(grassColor.getRed(), grassColor.getGreen(), grassColor.getRed());
    for (TrackPiece eachPiece : tracks) {
      eachPiece.draw();
    }
  }

  /**
   * Clear's the tracks arraylist and onTrack array.
   */
  public void clearTrack() {
    for (int widthTimer = 0; widthTimer < gameManager.getDisplayWidthCustom(); widthTimer++) {
      for (int heightTimer = 0; heightTimer < gameManager.getDisplayHeightCustom(); heightTimer++) {
        trackPixels[widthTimer][heightTimer] = false;
      }
    }
    tracks.clear();
  }

  /**
   * Checks if the cords are on the track.
   *
   * @param cordX X cord to be checked
   * @param cordY Y cord to be checked
   * @return True / False for if cords fall on the track
   */
  public boolean isOnTrack(int cordX, int cordY) {
    // Check if cords are on screen (Range of the array)
    if (cordX > gameManager.getDisplayWidthCustom() - 1
            || (int) cordY > gameManager.getDisplayHeightCustom() - 1
            || (int) cordX < 0 || (int) cordY < 0) {
      return false;
    }
    return trackPixels[cordX][cordY];
  }

  /**
   * Prints the array to console, '-' is grass, 'X' is track.
   */
  public void printPixels() {
    String output = "";
    for (int heightTime = 0; heightTime < gameManager.getDisplayHeightCustom(); heightTime += 2) {
      System.out.print(heightTime + "\t");
      for (int widthTimer = 0; widthTimer < gameManager.getDisplayWidthCustom(); widthTimer += 2) {
        if (trackPixels[widthTimer][heightTime] == true) {
          System.out.print("X");
        } else {
          System.out.print("-");
        }
      }
      System.out.print("\n");
    }
    System.out.println(gameManager.getDisplayWidthCustom() + " x "
            + gameManager.getDisplayHeightCustom());
  }

  /**
   * Return the starting position for designated player.
   * Kills program if called while tracks is empty.
   *
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