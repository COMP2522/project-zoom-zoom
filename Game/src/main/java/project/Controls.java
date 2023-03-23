package project;

public class Controls {

  private static GameManager window;
  private static Player player1;
  private static Player player2;
  // Index 0 is up, 1 is down, 2 is left, 3 is right
  private static int[] player1controls;
  private static int[] player2controls;

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
   * Constructor for controls with one player.
   *
   * @param window of game
   * @param player1 first player
   */
  public Controls (GameManager window, Player player1, int[] player1controls) {
    Controls.window = window;
    Controls.player1 = player1;
    Controls.player2 = null;
    Controls.player1controls = player1controls;
    Controls.player2controls = null;
  }

  /**
   * Constructor for controls with two players.
   *
   * @param window of game
   * @param player1 first player
   * @param player2 second player
   */
  public Controls (GameManager window, Player player1, Player player2,
    int[] player1controls, int[] player2controls) {
    Controls.window = window;
    Controls.player1 = player1;
    Controls.player2 = player2;
    Controls.player1controls = player1controls;
    Controls.player2controls = player2controls;
  }


  /**
   * setMovementTrue, set boolean variables to true when key is pressed
   * to handle multiple key events at once.
   *
   * @param keyCode key event integer value
   */
  public static void setMovementTrue(int keyCode) {
    // Up player1 control
    if (keyCode == player1controls[0])
      player1up = true;
    // Down player1 control
    if (keyCode == player1controls[1])
      player1down = true;
    // Left player1 control
    if (keyCode == player1controls[2])
      player1left = true;
    // Right player1 control
    if (keyCode == player1controls[3])
      player1right = true;
    // Up player2 control
    if (keyCode == player2controls[0])
      player2up = true;
    // Down player2 control
    if (keyCode == player2controls[1])
      player2down = true;
    // Left player2 control
    if (keyCode == player2controls[2])
      player2left = true;
    // Right player2 control
    if (keyCode == player2controls[3])
      player2right = true;
  }

  /**
   * setMovementFalse, set boolean variables to false when key is released
   * to handle multiple key inputs at once.
   *
   * @param keyCode key event integer value
   */
  public static void setMovementFalse(int keyCode) {
    // Up player1 control
    if (keyCode == player1controls[0])
      player1up = false;
    // Down player1 control
    if (keyCode == player1controls[1])
      player1down = false;
    // Left player1 control
    if (keyCode == player1controls[2])
      player1left = false;
    // Right player1 control
    if (keyCode == player1controls[3])
      player1right = false;
    // Up player2 control
    if (keyCode == player2controls[0])
      player2up = false;
    // Down player2 control
    if (keyCode == player2controls[1])
      player2down = false;
    // Left player2 control
    if (keyCode == player2controls[2])
      player2left = false;
    // Right player2 control
    if (keyCode == player2controls[3])
      player2right = false;
  }

  /**
   * playerMovement, update the player's movement based on boolean variables.
   */
  public static void playerMovement() {

    if (player1up) {
      // handle player1 up control
      player1.acc();
      System.out.println("up det");
    }
    if (player1down) {
      // handle player1 down control
      player1.brake();
    }
    if (player1left) {
      // handle player1 left control
      player1.turn(0.3);
    }
    if (player1right) {
      // handle player1 right control
      player1.turn(-0.3);
    }
    if (player2up) {
      // handle player2 up control
      player2.acc();
    }
    if (player2down) {
      // handle player2 down control
      player2.brake();
    }
    if (player2left) {
      // handle player2 left control
      player2.turn(0.3);
    }
    if (player2right) {
      // handle player2 right control
      player2.turn(-0.3);
    }
  }

  public void setUp(Player player, int key) {
    if (player == player1) {
      player1controls[0] = key;
    } else {
      player2controls[0] = key;
    }
  }

  public void setDown(Player player, int key) {
    if (player == player1) {
      player1controls[1] = key;
    } else {
      player2controls[1] = key;
    }
  }

  public void setLeft(Player player, int key) {
    if (player == player1) {
      player1controls[2] = key;
    } else {
      player2controls[2] = key;
    }
  }

  public void setRight(Player player, int key) {
    if (player == player1) {
      player1controls[3] = key;
    } else {
      player2controls[3] = key;
    }
  }









}
