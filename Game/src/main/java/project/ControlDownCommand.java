package project;

public class ControlDownCommand implements ControlCommand {

  private Player player;
  /**
   * Constructor for the command that controls the player's down control input.
   *
   * @param player current player
   */
  public ControlDownCommand(Player player) {
    this.player = player;
  }

  @Override
  public void execute() {
    player.setSpeed(0);
  }

  @Override
  public ControlCommandType getType() {
    return ControlCommandType.DOWN;
  }
}
