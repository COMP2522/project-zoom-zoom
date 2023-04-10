package project;

/**
 * ShiftUpCommand, command class that manages the shift up control.
 *
 * @author James Langille
 */
public class ShiftUpCommand extends ControlCommand {
  public static ShiftUpCommand player1ShiftUp = new ShiftUpCommand();
  public static ShiftUpCommand player2ShiftUp = new ShiftUpCommand();
  @Override
  public void executeP1() {
    player1.shiftUp();
  }

  @Override
  public void executeP2() {
    player2.shiftUp();
  }
}
