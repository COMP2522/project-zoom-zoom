package project;

/**
 * RightCommand, command class that manages the right control.
 *
 * @author James Langille
 */
public class RightCommand extends ControlCommand {
  @Override
  public void executeP1() {
    player1.turn(1);
  }

  @Override
  public void executeP2() {
    player2.turn(1);
  }
}
