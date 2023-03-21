package project;

public class ControlLeftCommand implements ControlCommand {

  private Player player;

  /**
   * Constructor for the command that controls the player's left control input.
   *
   * @param player current player
   */
  public ControlLeftCommand (Player player) {
    this.player = player;
  }

  @Override
  public void execute() {
    player.setDirection(player.getDirection().rotate(-Window.PI / 16));
  }

  @Override
  public ControlCommandType getType() {
    return ControlCommandType.LEFT;
  }
}
