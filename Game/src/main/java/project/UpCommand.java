package project;

/**
 * UpCommand, command class that manages the up control.
 *
 * @author James Langille
 */
public class UpCommand extends ControlCommand {
  @Override
  public void executeP1() {
    player1.acc();
  }

  @Override
  public void executeP2() {
    player2.acc();
  }
}
