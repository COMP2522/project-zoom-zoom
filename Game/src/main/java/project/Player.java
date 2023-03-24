package project;

import processing.core.PVector;

import java.awt.*;



/**
 * Player, creates a player sprite to be controlled by the user.
 */
public class Player extends Sprite {

  //This sets the default options for the car

  /**
   * The gear ratios for the car's transmission.
   */
  int GEAR1 = 300;
  int GEAR2 = 100;
  int GEAR3 = 30;
  int GEAR4 = 5;

  /**
   * The maximum power output of the car's engine.
   */
  double POWER = 10000;
  /**
   * The amount of power dropoff at high revs.
   */
  final double DROPOFF = 0.3;
  /**
   * The optimal rev range for the engine.
   */
  int OPREV = 3000;
  /**
   * The weight of the engine.
   */
  int EWEIGHT = 1000;

  /**
   * The amount of downforce generated by the car's aerodynamics.
   */
  int DOWNFORCE = 10;
  /**
   * The amount of air resistance (drag) generated by the car's aerodynamics.
   */
  int ADRAG = 10;
  /**
   * The weight of the car's aerodynamics.
   */
  int AWEIGHT = 50;
  /**
   * The factor that determines how much drag affects the car's speed.
   */
  double DRAGFACTOR = 0.02;

  /**
   * The weight of the car's chassis.
   */
  int CWEIGHT = 1000;
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
  int CAMBER = 15;

  /**
   * The maximum amount of grip that the car's tires can generate.
   */
  int TIREGRIP = 3000;

  /**
   * The weight factor for various calculations.
   */
  static final double WFACTOR = 0.001;
  /**
   * The factor that determines how much the camber angle affects the car's speed.
   */
  double CSFACTOR = 0.1;
  /**
   * The factor that determines how much the camber affects the car's grip.
   */
  double CGFACTOR = 0.1;

  //these are the variables working on the car

  /**
   * The current speed of the car.
   */
  double speed = 0;
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
   * The maximum amount of speed reduction due to various factors (such as drag).
   */
  int LIMITER = 50;
  /**
   * Declaring the defaults to all of the car parts
   */
  PartEngine engine = new PartEngine(POWER, DROPOFF, OPREV, EWEIGHT);
  PartAero aero = new PartAero(DOWNFORCE, ADRAG, AWEIGHT);
  PartChassis chassis = new PartChassis(CWEIGHT, WHEELBASEX, WHEELBASEY);


  public Player(PVector position, PVector direction, PVector velocity, float size, float speed,
                Color color, GameManager window) {
    super(position, direction, velocity, size, speed, color, window);
    weight = engine.getWeight() + aero.getWeight() + chassis.getWeight();
    gearRatio = PartGears.start();
  }


  @Override
  public void draw() {
    window.pushStyle();
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.ellipse(xpos, ypos, size, size);
    window.popStyle();
  }

  @Override
  public void update(){
    drag();
    grip = TIREGRIP + PartAero.getDownForce() * speed;
    revs = speed * gearRatio;
    xpos += speed / 10 * Math.cos(direction);
    ypos += speed / 10 * Math.sin(direction);
//    System.out.println(direction);
//    System.out.println("speed " + speed);
//    System.out.println("RPM " + revs);

  }

  /**
   * Calculates the effect of drag on the car's speed.
   * Aero drag is the main factor
   * weight reduces it to push through the air better
   */
  public void drag(){
    // Calculate the reduction in speed due to drag
    speed -= (aero.getDrag() * DRAGFACTOR * speed) / (weight * WFACTOR) / LIMITER;
  }

  /**
   * Accelerates the car based on engine power, revs, weight, and other factors.
   */
  public void acc(){
    // Calculate the proportionate acceleration based on engine power, revs, weight, and other factors
    double prpacc = engine.getPower() / (Math.abs(revs - engine.getOpRevs()) * engine.getDropoff())
            / (weight * WFACTOR) / (CAMBER * CSFACTOR);
    // Cap the proportionate acceleration at 20
    if (prpacc > 20) prpacc = 20;
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

  public void turn(double turnAmt){
    if (speed != 0) {
      drifting = false;
      double momentum = weight * speed;
      if (Math.abs(turnAmt) * momentum > grip) {
        turnAmt /= momentum / (grip * 1.5);
        //System.out.println("lost grip");
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
    gearRatio = PartGears.shiftUp();
  }
  public void shiftDown(){
    gearRatio = PartGears.shiftUp();
  }


}
