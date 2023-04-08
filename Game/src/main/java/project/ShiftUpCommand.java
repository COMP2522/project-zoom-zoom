package project;

/**
 * ShiftUpCommand, command class that manages the shift up control.
 *
 * @author James Langille
 */
public class ShiftUpCommand extends ControlCommand {
  @Override
  public void executeP1() {
    player1.shiftUp();
  }

  @Override
  public void executeP2() {
    player2.shiftUp();
  }
}
