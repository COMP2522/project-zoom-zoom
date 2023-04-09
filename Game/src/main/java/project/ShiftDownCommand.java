package project;

/**
 * ShiftDownCommand, command class that manages the shift down control.
 *
 * @author James Langille
 */
public class ShiftDownCommand extends ControlCommand {
  @Override
  public void executeP1() {
    player1.shiftDown();
  }

  @Override
  public void executeP2() {
    player2.shiftDown();
  }
}
