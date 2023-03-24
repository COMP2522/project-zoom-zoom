package project;

import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AIPlayer extends Sprite {
  private AiNode currentTarget;
  private List<AiNode> path;

  public AIPlayer(PVector position, PVector direction, PVector velocity, float size, float speed,
                  Color color, GameManager window) {
    super(position, direction, velocity, size, speed, color, window);
  }

  public void update(int[][] grid, AiNode target) {
    // If we don't have a path or we've reached the current target, find a new path
    if (path == null || currentTarget == null || (getPosition().x == currentTarget.x && getPosition().y == currentTarget.y)) {
      AiNode start = new AiNode(getPosition().x, getPosition().y);
      path = Ai.findPath(start, target, grid);
      if (path != null && !path.isEmpty()) {
        currentTarget = path.get(0);
      }
    }

//    // If we have a path, follow it
//    if (path != null && !path.isEmpty()) {
//      if (getPosition().x < currentTarget.x) {
//        turn(0);
//        acc();
//      } else if (getPosition().x > currentTarget.x) {
//        turn(180);
//        acc();
//      } else if (getPosition().y < currentTarget.y) {
//        turn(90);
//        acc();
//      } else if (getPosition().y > currentTarget.y) {
//        turn(270);
//        acc();
//      }

      // If we've reached the current target, update it to the next node in the path
      if (getPosition().x == currentTarget.x && getPosition().y == currentTarget.y) {
        path.remove(0);
        if (!path.isEmpty()) {
          currentTarget = path.get(0);
        }
      }
    }
  public void updateAI(Player player, ArrayList<Sprite> sprites) {
    PVector targetPosition = player.getPosition();
    float targetSpeed = player.getSpeed();

    // Calculate the direction of the target player relative to this AI player
    PVector targetDirection = PVector.sub(targetPosition, position).normalize();

    // Calculate the difference in speed between the target player and this AI player
    float speedDifference = targetSpeed - speed;

    // Calculate the desired acceleration of this AI player towards the target player
    PVector desiredAcceleration = targetDirection.mult(speedDifference);

    // Apply the desired acceleration to this AI player's velocity
    velocity.add(desiredAcceleration);

    // Limit the maximum speed of this AI player
    float maxSpeed = 5.0f;
    velocity.limit(maxSpeed);

    // Update this AI player's position
    position.add(velocity);
  }

}
