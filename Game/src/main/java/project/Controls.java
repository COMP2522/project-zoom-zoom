package project;

public class Controls {

  private static GameManager window;
  private static Player player1;
  private static Player player2;
  // Index 0 is up, 1 is down, 2 is left, 3 is right, 4 is shift gear up, 5 is shift gear down
  private static int[] player1controls;
  private static int[] player2controls;

  /* Each player needs their own set of boolean variables
   * so each players controls don't get mixed up.
   */
  private static boolean player1up = false;
  private static boolean player1down = false;
  private static boolean player1left = false;
  private static boolean player1right = false;
  private static boolean player1gearUp = false;
  private static boolean player1gearDown = false;
  private static boolean player2up = false;
  private static boolean player2down = false;
  private static boolean player2left = false;
  private static boolean player2right = false;
  private static boolean player2gearUp = false;
  private static boolean player2gearDown = false;
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
    // Shift gear up player1 control
    if (keyCode == player1controls[4])
      player1gearUp = true;
    // Shift gear down player1 control
    if (keyCode == player1controls[5])
      player1gearDown = true;
    // Do inner if statements if a second player exists
    if (player2 != null) {
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
      // Shift gear up player2 control
      if (keyCode == player2controls[4])
        player2gearUp = true;
      // Shift gear down player2 control
      if (keyCode == player2controls[5])
        player2gearDown = true;
    }
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
    // Shift gear up player1 control
    if (keyCode == player1controls[4])
      player1gearUp = false;
    // Shift gear down player1 control
    if (keyCode == player1controls[5])
      player1gearDown = false;
    // Do inner if statements if player 2 exists
    if (player2 != null) {
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
      // Shift gear up player2 control
      if (keyCode == player2controls[4])
        player2gearUp = false;
      // Shift gear down player2 control
      if (keyCode == player2controls[5])
        player2gearDown = false;
    }
  }

  /**
   * playerMovement, update the player's movement based on boolean variables.
   */
  public static void playerMovement() {

    if (player1up) {
      // handle player1 up control
      player1.acc();
      System.out.println(player1controls[0]);
    }
    if (player1down) {
      // handle player1 down control
      player1.brake();
    }
    if (player1left) {
      // handle player1 left control
      player1.turn(-0.3);
    }
    if (player1right) {
      // handle player1 right control
      player1.turn(0.3);
    }
    if (player1gearUp) {
      // handle player1 gear up
      System.out.println("p1up");
    }
    if (player1gearDown) {
      // handle player1 gear down
      System.out.println("p1down");
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
    if (player2gearUp) {
      // handle player2 gear up
      System.out.println("p2up");
    }
    if (player2gearDown) {
      // handle player2 gear down
      System.out.println("p2down");
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
