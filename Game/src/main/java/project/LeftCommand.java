package project;

public class LeftCommand extends ControlCommand {
  @Override
  public void executeP1() {
    player1.turn(-1);
  }

  @Override
  public void executeP2() {
    player2.turn(-1);
  }
}
