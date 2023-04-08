package project;

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
