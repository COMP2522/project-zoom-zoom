package project;

/**
 * AiNode class that gives the bots an idea of distance.
 *
 * @author Rohil Patel
 */
public class AiNode {
  public float x, y;
  public float g;
  public float h;
  public AiNode parent;

  public AiNode(float x, float y) {
    this.x = x;
    this.y = y;
    this.g = 0;
    this.h = 0;
    this.parent = null;
  }

  public float getF() {
    return g + h;
  }
}
