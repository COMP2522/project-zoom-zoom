package project;

public class ControlRightCommand implements ControlCommand {

  private Player player;
  /**
   * Constructor for the command that controls the player's right control input.
   *
   * @param player current player
   */
  public ControlRightCommand(Player player) {
    this.player = player;
  }

  @Override
  public void execute() {
    player.setDirection(player.getDirection().rotate(Window.PI / 16));
  }

  @Override
  public ControlCommandType getType() {
    return ControlCommandType.RIGHT;
  }
}
