package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ai class that implements the A* algorithm
 *
 * @author Rohil Patel
 */
public class Ai {

  private ArrayList<AiNode> waypoints;
  private AiNode currentWaypoint;
  private List<AiNode> currentPath;

  public Ai(){
    // Initialize the list of waypoints
    waypoints = new ArrayList<AiNode>();
    waypoints.add(new AiNode(10, 10));  // Add the first waypoint
    waypoints.add(new AiNode(20, 20));  // Add the second waypoint
    // Add more waypoints as needed
    currentWaypoint = waypoints.get(0); // Set the starting waypoint as the first element
    currentPath = findPath(new AiNode(0, 0), currentWaypoint, new int[100][100]); // Generate initial path to first waypoint
  }

  // Define the heuristic function for A*
  public static float heuristic(AiNode current, AiNode goal) {
    float dx = Math.abs(current.x - goal.x);
    float dy = Math.abs(current.y - goal.y);
    return dx + dy;
  }

  // Implement the A* algorithm
  public static List<AiNode> findPath(AiNode start, AiNode goal, int[][] grid) {
    List<AiNode> open = new ArrayList<AiNode>();
    List<AiNode> closed = new ArrayList<AiNode>();
    open.add(start);

    while (!open.isEmpty()) {
      // Find the node with the lowest f score
      AiNode current = null;
      float lowestF = Integer.MAX_VALUE;
      for (AiNode node : open) {
        if (node.getF() < lowestF) {
          current = node;
          lowestF = node.getF();
        }
      }

      // If we've reached the goal, reconstruct the path and return it
      if (current == goal) {
        List<AiNode> path = new ArrayList<AiNode>();
        while (current != null) {
          path.add(current);
          current = current.parent;
        }
        Collections.reverse(path);
        return path;
      }

      // Move the current node from the open to the closed list
      open.remove(current);
      closed.add(current);

      // Check the neighbors of the current node
      float[] dx = {-1, 0, 1, 0};
      float[] dy = {0, -1, 0, 1};
      for (int i = 0; i < 4; i++) {
        float nx = current.x + dx[i];
        float ny = current.y + dy[i];

        // Ignore nodes that are outside the grid
        if (nx < 0 || ny < 0 || nx >= grid.length || ny >= grid[0].length) {
          continue;
        }

        // Ignore nodes that are walls
        if (grid[(int) nx][(int) ny] == 1) {
          continue;
        }

        // Ignore nodes that have already been visited
        AiNode neighbor = new AiNode(nx, ny);
        if (closed.contains(neighbor)) {
          continue;
        }

        // Calculate the tentative g score for the neighbor
        float tentativeG = current.g + 1;

        // If the neighbor is not in the open list, add it
        if (!open.contains(neighbor)) {
          neighbor.h = heuristic(neighbor, goal);
          open.add(neighbor);
        }
        // If the neighbor is already in the open list, update its g score if the new score is better
        else if (tentativeG >= neighbor.g) {
          continue;
        }

        // This path is the best so far, so record it
        neighbor.g = tentativeG;
        neighbor.parent = current;
      }
    }

    // If we reach this point, there is no path to the goal
    return null;
  }
}

// Implement the A* algorithm in the AIPlayer class to determine the optimal path for the AI driver to take around the track.
// The algorithm should use the Collidable and Drawable interfaces to check for collisions and draw the AI driver's sprite on the screen.
//
// Create a new instance of the AIPlayer class in the SinglePlayer or TwoPlayers class
// (depending on which mode you want the AI to be active in).
//
// Update the GameManager class to handle the AI player in the game loop. This includes updating the AI player's position based on its calculated path,
// checking for collisions with other objects on the track, and updating the game state (e.g., lap times, position in the race) accordingly.
//
// Optionally, you can also add a menu to the game to allow players to select different AI drivers with varying levels
// of skill or different car configurations (using the CarModMenu class).
