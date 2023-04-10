package project;

/**
 * DownCommand, command class that manages the down control.
 *
 * @author James Langille
 */
public class DownCommand extends ControlCommand {
  public static DownCommand player1Down = new DownCommand();
  public static DownCommand player2Down = new DownCommand();
  @Override
  public void executeP1() {
    player1.brake();
  }

  @Override
  public void executeP2() {
    player2.brake();
  }
}
