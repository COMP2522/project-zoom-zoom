package project;

import java.util.HashMap;
import java.util.Map;

public class ControlTest {

  private static Player player1;
  private static Player player2;
  private static Map<Integer, ControlCommand> player1Commands = new HashMap<>();
  private static Map<Integer, ControlCommand> player2Commands = new HashMap<>();
  /* Each player needs their own set of boolean variables
   * so each players controls don't get mixed up.
   */
  private static boolean player1up = false;
  private static boolean player1down = false;
  private static boolean player1left = false;
  private static boolean player1right = false;

  private static boolean player2up = false;
  private static boolean player2down = false;
  private static boolean player2left = false;
  private static boolean player2right = false;

  /**
   * Constructor for controls of a 1-Player game.
   *
   * @param player1 player
   * @param player1controls int array of keycodes
   */
  public ControlTest(Player player1, int[] player1controls) {
    ControlTest.player1 = player1;
    player1Commands.put(player1controls[0], new ControlUpCommand(player1));
    player1Commands.put(player1controls[1], new ControlDownCommand(player1));
    player1Commands.put(player1controls[2], new ControlLeftCommand(player1));
    player1Commands.put(player1controls[3], new ControlRightCommand(player1));
  }

  /**
   * Constructor for controls of a 2-Player game.
   *
   * @param player1 first player
   * @param player2 second player
   * @param player1controls int array of keycodes for first player
   * @param player2controls int array of keycodes for second player
   */
  public ControlTest(Player player1, Player player2, int[] player1controls, int[] player2controls) {
    ControlTest.player1 = player1;
    player1Commands.put(player1controls[0], new ControlUpCommand(player1));
    player1Commands.put(player1controls[1], new ControlDownCommand(player1));
    player1Commands.put(player1controls[2], new ControlLeftCommand(player1));
    player1Commands.put(player1controls[3], new ControlRightCommand(player1));
    ControlTest.player2 = player2;
    player2Commands.put(player2controls[0], new ControlUpCommand(player2));
    player2Commands.put(player2controls[1], new ControlDownCommand(player2));
    player2Commands.put(player2controls[2], new ControlLeftCommand(player2));
    player2Commands.put(player2controls[3], new ControlRightCommand(player2));
  }

  /**
   * setCommand, allows user to remap a key for the specified control command.
   *
   * @param keycode key input as an int
   * @param command control command
   * @param player current player
   */
  public void setCommand(int keycode, ControlCommand command, Player player) {
    if (player == player2) {
      // Remove the current key/command pair
      player2Commands.remove(getKey(command, player));
      player2Commands.put(keycode, command);
    } else {
      // Remove the current key/command pair
      player1Commands.remove(getKey(command, player));
      player1Commands.put(keycode, command);
    }
  }

  /**
   * getCommand, gets the command for the specified key and user.
   *
   * @param keycode key input
   * @param player current player
   */
  public static ControlCommand getCommand(int keycode, Player player) {
    if (player == player2) {
      return player2Commands.get(keycode);
    } else {
      return player1Commands.get(keycode);
    }
  }

  /**
   * getKey, get the key from the command for the specified player.
   *
   * @param command current command
   * @param player current player
   * @return key if command was found, -1 if no command was found.
   */
  public static int getKey(ControlCommand command, Player player) {
    if (player == player2) {
      // Iterate through the player2Commands hashmap
      for (Map.Entry<Integer, ControlCommand> i : player2Commands.entrySet()) {
        if (i.getValue().equals(command)) {
          return i.getKey();
        }
      }
    } else {
      // Iterate through the player1Commands hashmap
      for (Map.Entry<Integer, ControlCommand> i : player1Commands.entrySet()) {
        if (i.getValue().equals(command)) {
          return i.getKey();
        }
      }
    }
    // Command was not found
    return -1;
  }

  /**
   * setMovementTrue, set boolean variables to true when key is pressed
   * to handle multiple key events at once.
   *
   * @param keycode key event int value
   */
  public static void setMovementTrue(int keycode) {
    if (player2 != null) {
      ControlCommand command = getCommand(keycode, player2);
      if (command.getType() == ControlCommandType.UP) {
        player2up = true;
      }
      if (command.getType() == ControlCommandType.DOWN) {
        player2down = true;
      }
      if (command.getType() == ControlCommandType.LEFT) {
        player2left = true;
      }
      if (command.getType() == ControlCommandType.RIGHT) {
        player2right = true;
      }
    } else {
      ControlCommand command = getCommand(keycode, player1);
      if (command.getType() == ControlCommandType.UP) {
        player1up = true;
      }
      if (command.getType() == ControlCommandType.DOWN) {
        player1down = true;
      }
      if (command.getType() == ControlCommandType.LEFT) {
        player1left = true;
      }
      if (command.getType() == ControlCommandType.RIGHT) {
        player1right = true;
      }
    }
  }

  /**
   * setMovementFalse, set boolean variables to false when key is released
   * to handle multiple key inputs at once.
   *
   * @param keycode key event integer value
   */
  public static void setMovementFalse(int keycode) {
    if (player2 != null) {
      ControlCommand command = getCommand(keycode, player2);
      if (command.getType() == ControlCommandType.UP) {
        player2up = false;
      }
      if (command.getType() == ControlCommandType.DOWN) {
        player2down = false;
      }
      if (command.getType() == ControlCommandType.LEFT) {
        player2left = false;
      }
      if (command.getType() == ControlCommandType.RIGHT) {
        player2right = false;
      }
    } else {
      ControlCommand command = getCommand(keycode, player1);
      if (command.getType() == ControlCommandType.UP) {
        player1up = false;
      }
      if (command.getType() == ControlCommandType.DOWN) {
        player1down = false;
      }
      if (command.getType() == ControlCommandType.LEFT) {
        player1left = false;
      }
      if (command.getType() == ControlCommandType.RIGHT) {
        player1right = false;
      }
    }
  }

  /**
   * playerMovement, update the player's movement based on boolean variables.
   */
  public static void playerMovement() {

    if (player1up) {
      // handle player1 up control
      ControlUpCommand ControlUpCommand = new ControlUpCommand(player1);
      int key = getKey(ControlUpCommand, player1);
      player1Commands.get(key).execute();
    }
    if (player1down) {
      // handle player1 down control
      player1.setSpeed(0);
    }
    if (player1left) {
      // handle player1 left control
      player1.setDirection(player1.getDirection().rotate(-Window.PI / 16));
    }
    if (player1right) {
      // handle player1 right control
      player1.setDirection(player1.getDirection().rotate(Window.PI / 16));
    }
    if (player2up) {
      // handle player2 up control
      player2.setSpeed(0.01F);
    }
    if (player2down) {
      // handle player2 down control
      player2.setSpeed(0);
    }
    if (player2left) {
      // handle player2 left control
      player2.setDirection(player2.getDirection().rotate(-Window.PI / 16));
    }
    if (player2right) {
      // handle player2 right control
      player2.setDirection(player2.getDirection().rotate(Window.PI / 16));
    }
  }

}
