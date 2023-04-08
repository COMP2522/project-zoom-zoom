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
  private static Hashtable<Integer, ControlCommand> p1HashTable =
      new Hashtable<Integer, ControlCommand>(CONTROLS);
  private static Hashtable<Integer, ControlCommand> p2HashTable =
      new Hashtable<Integer, ControlCommand>(CONTROLS);
  /** Arrays for each player's boolean expressions. */
  private static boolean[] player1flags = new boolean[CONTROLS];
  private static boolean[] player2flags = new boolean[CONTROLS];

  /**
   * p1Commands, adds keycodes and movement commands to p1 hash table.
   */
  public static void p1Commands() {
    addCommands(p1HashTable, player1Keys);
  }

  /**
   * p2Commands, adds keycodes and movement commands to p2 hash table.
   */
  public static void p2Commands() {
    addCommands(p2HashTable, player2Keys);
  }

  /**
   * addCommands, adds keycodes and movement commands to each players hash table.
   *
   * @param table hashtable for player key/command
   * @param keys array of player keys
   */
  private static void addCommands(Hashtable<Integer, ControlCommand> table, int[] keys) {
    table.put(keys[0], new UpCommand());
    table.put(keys[1], new DownCommand());
    table.put(keys[2], new LeftCommand());
    table.put(keys[3], new RightCommand());
    table.put(keys[4], new ShiftUpCommand());
    table.put(keys[5], new ShiftDownCommand());
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
   * shiftGears, allows users to shift their car gears up or down
   * depending on keycode.
   *
   * @param keycode key press as int value
   */
  public static void shiftGears(int keycode) {
    if (keycode == p1ShiftUp) {
      p1HashTable.get(p1ShiftUp).executeP1();
    } else if (keycode == p1ShiftDown) {
      p1HashTable.get(p1ShiftDown).executeP1();
    } else if (keycode == p2ShiftUp) {
      p2HashTable.get(p2ShiftUp).executeP2();
    } else if (keycode == p2ShiftDown) {
      p2HashTable.get(p2ShiftDown).executeP2();
    }
  }

  /**
   * playerMovement, update the player's movement based on boolean variables.
   */
  public static void player1Movement() {
    if (player1flags[0]) {
      p1HashTable.get(p1Up).executeP1();
    }
    if (player1flags[1]) {
      p1HashTable.get(p1Down).executeP1();
    }
    if (player1flags[2]) {
      p1HashTable.get(p1Left).executeP1();
    }
    if (player1flags[3]) {
      p1HashTable.get(p1Right).executeP1();
    }
  }

  /**
   * playerMovement, update the player's movement based on boolean variables.
   */
  public static void player2Movement() {
    if (player2flags[0]) {
      p2HashTable.get(p2Up).executeP2();
    }
    if (player2flags[1]) {
      p2HashTable.get(p2Down).executeP2();
    }
    if (player2flags[2]) {
      p2HashTable.get(p2Left).executeP2();
    }
    if (player2flags[3]) {
      p2HashTable.get(p2Right).executeP2();
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
    player1Keys[index] = keycode;
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
    player2Keys[index] = keycode;
  }
}
