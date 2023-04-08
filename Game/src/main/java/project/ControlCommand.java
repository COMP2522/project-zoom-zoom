package project;

/**
 * ControlCommand, abstract class to execute the player's movements.
 *
 * @author James Langille
 */
public abstract class ControlCommand {
  protected Player player1 = GameManager.player1;
  protected Player player2 = GameManager.player2;

  public abstract void executeP1();

  public abstract void executeP2();
}
