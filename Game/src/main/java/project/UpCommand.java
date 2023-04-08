package project;

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
