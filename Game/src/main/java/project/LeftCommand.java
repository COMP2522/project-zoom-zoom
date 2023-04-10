package project;

/**
 * LeftCommand, command class that manages the left control.
 *
 * @author James Langille
 */
public class LeftCommand extends ControlCommand {
  public static LeftCommand player1Left = new LeftCommand();
  public static LeftCommand player2Left = new LeftCommand();
  @Override
  public void executeP1() {
    player1.turn(-1);
  }

  @Override
  public void executeP2() {
    player2.turn(-1);
  }
}
