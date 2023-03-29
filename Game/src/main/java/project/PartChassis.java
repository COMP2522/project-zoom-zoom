package project;

/**
 * Represents the chassis part of a car, which affects weight and wheelbase.
 */
public class PartChassis {
  private static PartChassis chassis1 = new PartChassis(2000, 1, 1);
  private static PartChassis chassis2 = new PartChassis(1500, 1, 1);
  private static PartChassis chassis3 = new PartChassis(1000, 1, 1);
  private static PartChassis chassis4 = new PartChassis(500, 1, 1);
  public static PartChassis[] chassisParts = {chassis1, chassis2, chassis3, chassis4};
  /**
   * The weight of the car's chassis.
   */
  int weight;
  /**
   * The distance between the car's front and rear wheels (in the x direction).
   */
  int wheelBaseX;
  /**
   * The distance between the car's front and rear wheels (in the y direction).
   */
  int wheelBaseY;

  /**
   * Constructor for PartChassis objects.
   * @param w The weight of the car's chassis.
   * @param wbx The distance between the car's front and rear wheels (in the x direction).
   * @param wby The distance between the car's front and rear wheels (in the y direction).
   */
  public PartChassis(int w, int wbx, int wby){
    weight = w;
    wheelBaseX = wbx;
    wheelBaseY = wby;
  }

  public void setChassis(PartChassis chassis){
    weight = chassis.weight;
    wheelBaseX = chassis.wheelBaseX;
    wheelBaseY = chassis.wheelBaseY;
  }

  /**
   * Returns the weight of the car's chassis.
   * @return The weight.
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Sets the weight of the car's chassis.
   * @param weight The new weight.
   */
  public void setWeight(int weight) {
    this.weight = weight;
  }

  /**
   * Returns the distance between the car's front and rear wheels (in the x direction).
   * @return The wheelbase in the x direction.
   */
  public int getWheelBaseX() {
    return wheelBaseX;
  }

  /**
   * Sets the distance between the car's front and rear wheels (in the x direction).
   * @param wheelBaseX The new wheelbase in the x direction.
   */
  public void setWheelBaseX(int wheelBaseX) {
    this.wheelBaseX = wheelBaseX;
  }

  /**
   * Returns the distance between the car's front and rear wheels (in the y direction).
   * @return The wheelbase in the y direction.
   */
  public int getWheelBaseY() {
    return wheelBaseY;
  }

  /**
   * Sets the distance between the car's left and right wheels (in the y direction).
   * @param wheelBaseY The new wheelbase in the y direction.
   */
  public void setWheelBaseY(int wheelBaseY) {
    this.wheelBaseY = wheelBaseY;
  }
}