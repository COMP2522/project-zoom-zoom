package project;

public class ControlUpCommand implements ControlCommand {

  private Player player;
  /**
   * Constructor for the command that controls the player's up control input.
   *
   * @param player current player
   */
  public ControlUpCommand(Player player) {
    this.player = player;
  }

  @Override
  public void execute() {
    player.setSpeed(0.1F);
  }

  @Override
  public ControlCommandType getType() {
    return ControlCommandType.UP;
  }

}
