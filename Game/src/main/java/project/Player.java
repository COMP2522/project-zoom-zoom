package project;

import processing.core.PConstants;
import processing.core.PVector;

import java.awt.*;

/**
 * Player, creates a player sprite to be controlled by the user.
 */
public class Player extends Car {

  private String playerNum;
  private TrackManager trackManager;

  //This sets the default options for the car

  /**
   * The gear ratios for the car's transmission.
   */
  int GEAR1 = 700;
  int GEAR2 = 300;
  int GEAR3 = 125;
  int GEAR4 = 80;

  /**
   * The maximum power output of the car's engine.
   */
  double POWER = 7000;

  /**
   * The amount of power dropoff at high revs.
   */
  final double DROPOFF = 0.6;
  /**
   * The optimal rev range for the engine.
   */
  int OPTIMAL_REVS = 3000;
  /**
   * The weight of the engine.
   */
  int ENGINE_WEIGHT = 1000;

  /**
   * The amount of downforce generated by the car's aerodynamics.
   */
  int DOWNFORCE = 20;
  /**
   * The amount of air resistance (drag) generated by the car's aerodynamics.
   */
  int AERO_DRAG = 10;
  /**
   * The weight of the car's aerodynamics.
   */
  int AERO_WEIGHT = 300;
  /**
   * The factor that determines how much drag affects the car's speed.
   */
  double DRAGFACTOR = 0.02;

  /**
   * The weight of the car's chassis.
   */
  int CHASSIS_WEIGHT = 1000;
  /**
   * The distance between the car's front and rear wheels (in the x direction).
   */
  int WHEELBASEX = 30;
  /**
   * The distance between the car's front and rear wheels (in the y direction).
   */
  int WHEELBASEY = 15;

  /**
   * Sets the amount of braking force applied to the car.
   */
  public void setBRAKE(double BRAKE) {
    this.BRAKE = BRAKE;
  }

  /**
   * Sets the camber angle of the car's wheels.
   */
  public void setCAMBER(int CAMBER) {
    this.CAMBER = CAMBER;
  }

  /**
   * The amount of braking force applied to the car.
   */
  double BRAKE = 1;
  /**
   * The camber angle of the car's wheels.
   */
  double CAMBER = 0.06;

  /**
   * The maximum amount of grip that the car's tires can generate.
   */
  int TIREGRIP = 3000;

  /**
   * The weight factor for various calculations.
   */
  static final double WFACTOR = 0.001;

  //these are the variables working on the car

  @Override
  public float getSpeed() {
    return speed;
  }

  /**
   * The current speed of the car.
   */
  float speed = 0;

  /**
   * current RPM of the car.
   * @return revs
   */
  public double getRevs() {
    return revs;
  }

  /**
   * The current engine speed (revolutions per minute).
   */
  double revs = 0;
  /**
   * The current direction that the car is facing (in radians).
   */
  double direction = 0;
  /**
   * The amount of grip that the car currently has.
   */
  double grip;
  /**
   * Indicates whether the car is currently drifting.
   */
  boolean drifting = false;
  /**
   * Indicates whether the car is currently braking.
   */
  boolean braking = false;
  /**
   * Indicates whether the car is currently accelerating.
   */
  boolean accelerating = true;
  /**
   * The current gear ratio of the car's transmission.
   */
  int gearRatio = 300;

  /**
   * The x position of the car on the screen.
   */
  float xpos = 500;
  /**
   * The y position of the car on the screen.
   */
  float ypos = 500;

  /**
   * The total weight of the car.
   */
  int weight;

  /**
   * The current gear number
   */
  int currGear = 1;

  public int getCurrGear() {
    return currGear;
  }

  /**
   * The maximum amount of speed reduction due to various factors (such as drag).
   */
  int LIMITER = 50;

  /**
   * Limits the amount the speed value changes the x and y position
   */
  int POSITION_LIMITER = 10;

  /**
   * Declaring the defaults to all of the car parts
   */
  PartGears gears = new PartGears(GEAR1, GEAR2, GEAR3, GEAR4);
  PartEngine engine = new PartEngine(POWER, DROPOFF, OPTIMAL_REVS, ENGINE_WEIGHT);
  PartAero aero = new PartAero(DOWNFORCE, AERO_DRAG, AERO_WEIGHT);
  PartChassis chassis = new PartChassis(CHASSIS_WEIGHT, WHEELBASEX, WHEELBASEY);

  public PartGears getGears() {
    return gears;
  }

  public PartEngine getEngine() {
    return engine;
  }

  public void setEngine(PartEngine engine) {
    this.engine = engine;
  }

  public PartAero getAero() {
    return aero;
  }

  public void setAero(PartAero aero) {
    this.aero = aero;
  }

  public PartChassis getChassis() {
    return chassis;
  }

  public void setChassis(PartChassis chassis) {
    this.chassis = chassis;
  }

  public Player(PVector position, PVector direction, float speed,
                GameManager window, String playerNum) {
    super(position, direction, speed, window);
    xpos = position.x;
    ypos = position.y;
    weight = engine.getWeight() + aero.getWeight() + chassis.getWeight();
    gearRatio = gears.start();
    this.playerNum = playerNum;
    trackManager = window.getTrackManager();
  }
  @Override
  public void draw() {
    window.ellipse(position.x, position.y, 20, 20);
    window.pushMatrix();
    window.translate((position.x + WIDTH) / 2, (position.y + HEIGHT) / 2);
    window.rotate((float) direction);
    window.rectMode(PConstants.CENTER);
    window.fill(0, 0);
    window.rect(0, 0, WIDTH, HEIGHT);
    window.popMatrix();
    onTrack();
  }

  public void onTrack() {
    if (!trackManager.isOnTrack(position))
      System.out.println(playerNum + "\tOff Track");
  }

  /**
   * Each frame update of the players values
   *
   * grip: Tire grip and downforce times speed
   * revs: speed times the current gear ratio
   * x position adjusted by the speed, with trig to ensure it is changing in the correct direction
   * PApplet xpos set to keep consistency
   * y position adjusted by the speed, with trig to ensure it is changing in the correct direction
   * PApplet ypos set to keep consistency
   *
   */
  @Override
  public void update() {
    drag();
    grip = TIREGRIP + this.aero.getDownForce() * speed;
    revs = speed * gearRatio;
    xpos += speed / POSITION_LIMITER * Math.cos(direction);
    position.x = xpos;
    ypos += speed / POSITION_LIMITER * Math.sin(direction);
    position.y = ypos;
  }



  /**
   * Calculates the effect of drag on the car's speed.
   * Aero drag is the main factor
   * weight reduces it to push through the air better
   */
  public void drag(){
    // Calculate the reduction in speed due to drag
    speed -= (aero.getDrag() * DRAGFACTOR * speed * (1 + (revs / 5000))) / (weight * WFACTOR) / LIMITER;
  }

  /**
   * The cap for the acceleration a car can have.
   * This keeps things from going infinite in corner cases
   */
  static final int MAX_ACCELERATION = 20;

  /**
   * Accelerates the car based on engine power, revs, weight, and other factors.
   */

  public void acc(){
    // Calculate the proportionate acceleration based on engine power, revs, weight, and other factors
    double prpacc = engine.getPower() / (Math.abs(revs - engine.getOpRevs()) * engine.getDropoff())
        / (weight * WFACTOR) / (1 + CAMBER);
    // Cap the acceleration
    if (prpacc > MAX_ACCELERATION) prpacc = MAX_ACCELERATION;
    // Increase the car's speed based on the proportionate acceleration
    speed += prpacc / LIMITER;
    // Debugging print statement
    //System.out.println("acc " + prpacc);
  }



//  public void printInfo(){
//    System.out.println("speed " + speed);
//    System.out.println("RPM " + revs);
//    System.out.println("acc " + (int)acc());
//    System.out.println("drag " + ((int)drag() + 1));
//    System.out.println("xpos " + xpos);
//    System.out.println("ypos " + ypos + "\n");
//    System.out.println("Momentum " + (weight * speed));
//    System.out.println(("Drifting " + drifting));
//    System.out.println("Direction " + direction + "\n");
//  }

  public void turn(int dir){
    double turnAmt = CAMBER * dir;
    if (speed != 0) {
      drifting = false;
      double momentum = weight * speed;
      if (Math.abs(turnAmt) * momentum > grip) {
        turnAmt /= momentum * 0.15 / grip;
        //System.out.println((momentum * Math.abs(turnAmt))  + " " + grip);
        drifting = true;
      }
      direction += turnAmt;
    }
  }
  public void brake(){
    if(speed - (BRAKE / weight * WFACTOR) > 0){
      speed -= BRAKE / (weight * WFACTOR);}
    else speed = 0;
  }

  public void shiftUp(){
    if(currGear < 4){
      gearRatio = gears.shiftUp();
      currGear++;
    }
  }
  public void shiftDown(){

    if(currGear > 1){
      currGear--;
      gearRatio = gears.shiftDown();
    }
  }
}
