package project;

public class DownCommand extends ControlCommand {
  @Override
  public void executeP1() {
    player1.brake();
  }

  @Override
  public void executeP2() {
    player2.brake();
  }
}
