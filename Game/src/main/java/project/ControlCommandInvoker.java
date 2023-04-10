package project;

import java.util.Hashtable;

/**
 * ControlCommandInvoker, invoker class that manages the player's controls
 * and executes each control.
 *
 * @author James Langille
 */
public class ControlCommandInvoker {
  /** Constant. */
  private static final int CONTROLS = 6;
  /** Player 1 default controls. */
  private static int p1Up = 87;
  private static int p1Down = 83;
  private static int p1Left = 65;
  private static int p1Right = 68;
  private static int p1ShiftUp = 20;
  private static int p1ShiftDown = 16;
  private static int[] player1Keys = {p1Up, p1Down, p1Left,
    p1Right, p1ShiftUp, p1ShiftDown};
  /** Player 2 default controls. */
  private static int p2Up = 73;
  private static int p2Down = 75;
  private static int p2Left = 74;
  private static int p2Right = 76;
  private static int p2ShiftUp = 59;
  private static int p2ShiftDown = 47;
  private static int[] player2Keys = {p2Up, p2Down, p2Left,
    p2Right, p2ShiftUp, p2ShiftDown};
  /** Hash Tables for each player's controls and commands. */
  private static Hashtable<ControlCommand, Integer> p1HashTable =
      new Hashtable<>(CONTROLS);
  private static Hashtable<ControlCommand, Integer> p2HashTable =
      new Hashtable<>(CONTROLS);
  /** Arrays for each player's boolean expressions. */
  private static boolean[] player1flags = new boolean[CONTROLS];
  private static boolean[] player2flags = new boolean[CONTROLS];
  /** Control commands. */
  private static UpCommand player1Up = UpCommand.player1Up;
  private static DownCommand player1Down = DownCommand.player1Down;
  private static LeftCommand player1Left = LeftCommand.player1Left;
  private static RightCommand player1Right = RightCommand.player1Right;
  private static UpCommand player2Up = UpCommand.player2Up;
  private static DownCommand player2Down = DownCommand.player2Down;
  private static LeftCommand player2Left = LeftCommand.player2Left;
  private static RightCommand player2Right = RightCommand.player2Right;

  /**
   * p1Commands, adds keycodes and movement commands to p1 hash table.
   */
  public static void p1Commands() {
    p1HashTable.put(UpCommand.player1Up, player1Keys[0]);
    p1HashTable.put(DownCommand.player1Down, player1Keys[1]);
    p1HashTable.put(LeftCommand.player1Left, player1Keys[2]);
    p1HashTable.put(RightCommand.player1Right, player1Keys[3]);
    p1HashTable.put(ShiftUpCommand.player1ShiftUp, player1Keys[4]);
    p1HashTable.put(ShiftDownCommand.player1ShiftDown, player1Keys[5]);
  }

  /**
   * p2Commands, adds keycodes and movement commands to p2 hash table.
   */
  public static void p2Commands() {
    p2HashTable.put(UpCommand.player2Up, player2Keys[0]);
    p2HashTable.put(DownCommand.player2Down, player2Keys[1]);
    p2HashTable.put(LeftCommand.player2Left, player2Keys[2]);
    p2HashTable.put(RightCommand.player2Right, player2Keys[3]);
    p2HashTable.put(ShiftUpCommand.player2ShiftUp, player2Keys[4]);
    p2HashTable.put(ShiftDownCommand.player2ShiftDown, player2Keys[5]);
  }

  /**
   * setP1MovementTrue, set boolean variables to true when key is pressed
   * to handle multiple key events at once.
   *
   * @param keyCode key event integer value
   */
  public static void setP1MovementTrue(int keyCode) {
    for (int i = 0; i < player1Keys.length - 2; i++) {
      if (keyCode == player1Keys[i]) {
        player1flags[i] = true;
      }
    }
  }

  /**
   * setP1MovementFalse, set boolean variables to true when key is pressed
   * to handle multiple key events at once.
   *
   * @param keyCode key event integer value
   */
  public static void setP1MovementFalse(int keyCode) {
    for (int i = 0; i < player1Keys.length - 2; i++) {
      if (keyCode == player1Keys[i]) {
        player1flags[i] = false;
      }
    }
  }

  /**
   * setP2MovementTrue, set boolean variables to true when key is pressed
   * to handle multiple key events at once.
   *
   * @param keyCode key event integer value
   */
  public static void setP2MovementTrue(int keyCode) {
    for (int i = 0; i < player2Keys.length - 2; i++) {
      if (keyCode == player2Keys[i]) {
        player2flags[i] = true;
      }
    }
  }

  /**
   * setP2MovementFalse, set boolean variables to true when key is pressed
   * to handle multiple key events at once.
   *
   * @param keyCode key event integer value
   */
  public static void setP2MovementFalse(int keyCode) {
    for (int i = 0; i < player2Keys.length - 2; i++) {
      if (keyCode == player2Keys[i]) {
        player2flags[i] = false;
      }
    }
  }

  /**
   * shiftPlayer1Gears, allows player1 to shift their car gears up or down
   * depending on keycode.
   *
   * @param keycode key press as int value
   */
  public static void shiftPlayer1Gears(int keycode) {
    ShiftUpCommand p1GearUp = ShiftUpCommand.player1ShiftUp;
    ShiftDownCommand p1GearDown = ShiftDownCommand.player1ShiftDown;
    if (keycode == p1HashTable.get(p1GearUp)) {
      p1GearUp.executeP1();
    } else if (keycode == p1HashTable.get(p1GearDown)) {
      p1GearDown.executeP1();
    }
  }

  /**
   * shiftPlayer2Gears, allows player2 to shift their car gears up or down
   * depending on keycode.
   *
   * @param keycode key press as int value
   */
  public static void shiftPlayer2Gears(int keycode) {
    ShiftUpCommand p2GearUp = ShiftUpCommand.player2ShiftUp;
    ShiftDownCommand p2GearDown = ShiftDownCommand.player2ShiftDown;
    if (keycode == p2HashTable.get(p2GearUp)) {
      p2GearUp.executeP2();
    } else if (keycode == p2HashTable.get(p2GearDown)) {
      p2GearDown.executeP2();
    }
  }

  /**
   * playerMovement, update the player's movement based on boolean variables.
   */
  public static void player1Movement() {
    if (player1flags[0]) {
      player1Up.executeP1();
    }
    if (player1flags[1]) {
      player1Down.executeP1();
    }
    if (player1flags[2]) {
      player1Left.executeP1();
    }
    if (player1flags[3]) {
      player1Right.executeP1();
    }
  }

  /**
   * playerMovement, update the player's movement based on boolean variables.
   */
  public static void player2Movement() {
    if (player2flags[0]) {
      player2Up.executeP2();
    }
    if (player2flags[1]) {
      player2Down.executeP2();
    }
    if (player2flags[2]) {
      player2Left.executeP2();
    }
    if (player2flags[3]) {
      player2Right.executeP2();
    }
  }

  /**
   * getPlayer1Keys, gets the character value of player1's keys.
   *
   * @param index of array
   * @return key as a character
   */
  public static char getPlayer1Keys(int index) {
    return (char) player1Keys[index];
  }

  /**
   * setPlayer1Keys, sets the player 1 key to a new value.
   *
   * @param index of array
   * @param keycode new keycode
   */
  public static void setPlayer1Keys(int index, int keycode) {
    switch (index) {
      case 0 -> {
        p1HashTable.remove(player1Up, player1Keys[index]);
        player1Keys[index] = keycode;
        p1HashTable.put(player1Up, player1Keys[index]);
        break;
      }
      case 1 -> {
        p1HashTable.remove(player1Down, player1Keys[index]);
        player1Keys[index] = keycode;
        p1HashTable.put(player1Down, player1Keys[index]);
        break;
      }
      case 2 -> {
        p1HashTable.remove(player1Left, player1Keys[index]);
        player1Keys[index] = keycode;
        p1HashTable.put(player1Left, player1Keys[index]);
        break;
      }
      case 3 -> {
        p1HashTable.remove(player1Right, player1Keys[index]);
        player1Keys[index] = keycode;
        p1HashTable.put(player1Right, player1Keys[index]);
        break;
      }
      default -> {
        break;
      }
    }
  }

  /**
   * getPlayer2Keys, gets the character value of player2's keys.
   *
   * @param index of array
   * @return key as a character
   */
  public static char getPlayer2Keys(int index) {
    return (char) player2Keys[index];
  }

  /**
   * setPlayer2Keys, sets the player 2 key to a new value.
   *
   * @param index of array
   * @param keycode new keycode
   */
  public static void setPlayer2Keys(int index, int keycode) {
    switch (index) {
      case 0 -> {
        p2HashTable.remove(player2Up, player2Keys[index]);
        player2Keys[index] = keycode;
        p2HashTable.put(player2Up, player2Keys[index]);
        break;
      }
      case 1 -> {
        p2HashTable.remove(player2Down, player2Keys[index]);
        player2Keys[index] = keycode;
        p2HashTable.put(player2Down, player2Keys[index]);
        break;
      }
      case 2 -> {
        p2HashTable.remove(player2Left, player2Keys[index]);
        player2Keys[index] = keycode;
        p2HashTable.put(player2Left, player2Keys[index]);
        break;
      }
      case 3 -> {
        p2HashTable.remove(player2Right, player2Keys[index]);
        player2Keys[index] = keycode;
        p2HashTable.put(player2Right, player2Keys[index]);
        break;
      }
      default -> {
        break;
      }
    }
  }
}
