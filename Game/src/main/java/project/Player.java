package project;

import processing.core.PConstants;
import processing.core.PVector;

/**
 * Player, creates a player sprite to be controlled by the user.
 */
public class Player extends Car {


  private String playerNum;
  private TrackManager trackManager;
  Stopwatch time;

  //This sets the default options for the car



  /**
   * The factor that determines how much drag affects the car's speed.
   */
  double DRAGFACTOR = 0.02;



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
  static final double WEIGHT_FACTOR = 0.001;

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
   * Number of laps completed
   */
  int laps = 0;

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


  boolean lapFlag = false;

  boolean start = false;

  long oldTime = 0;

  float bestTime = 1000000;

  public void setTrack(int track) {
    this.track = track;
  }

  int track = 0;

  /**
   * The maximum amount of speed reduction due to various factors (such as drag).
   */
  int LIMITER = 50;

  /**
   * Limits the amount the speed value changes the x and y position
   */
  private static final int POSITION_LIMITER = 8;

  private static final int MIN_GRASS_SPEED = 10;

  private static final double OFF_TRACK_PENALTY = 0.8;

  private static final int NUM_OF_LAPS = 5;



  /**
   * Declaring the defaults to all the car parts
   */
  PartGears gears = new PartGears(PartGears.gears);
  PartEngine engine = PartEngine.engineParts[0];
  PartAero aero = PartAero.aeroParts[0];
  PartChassis chassis = PartChassis.chassisParts[0];

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
                GameManager window, String playerNum, Stopwatch timer) {
    super(position, direction, speed, window);
    xpos = 300;
    ypos = 200;
    weight = engine.getWeight() + aero.getWeight() + chassis.getWeight();
    gearRatio = gears.start();
    this.playerNum = playerNum;
    trackManager = window.getTrackManager();
    time = timer;
  }
  @Override
  public void draw() {

    window.pushMatrix();
    window.translate((xpos + WIDTH) / 2, (xpos + HEIGHT) / 2);
    window.rotate((float) direction);
    window.rectMode(PConstants.CORNER);
    window.fill(0, 0);
    window.rect(0, 0, WIDTH, HEIGHT);
    window.popMatrix();
    if(laps == NUM_OF_LAPS){
      window.pushStyle();
      window.fill(255,255,255);
      window.textSize(100);
      window.text(bestTime, window.displayWidth / 2, window.displayHeight / 2);
      window.popStyle();

    }

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
    lap();
    drag();
    onTrack();
    if (speed < 0) speed = 0;
    grip = TIREGRIP + this.aero.getDownForce() * speed;
    revs = speed * gearRatio;
    xpos += speed / POSITION_LIMITER * Math.cos(direction);
    ypos += speed / POSITION_LIMITER * Math.sin(direction);
  }

  /**
   * Checks if the car is within the bounds of the track
   */
  public void onTrack(){
    switch (track) {
      case 1:
        if ((
                xpos > 1300
                || xpos < 100
                || ypos > 800
                || ypos < 100
                || (xpos > 300 && xpos < 1100 && ypos > 300 && ypos < 600))
                && (speed > MIN_GRASS_SPEED)) {
          speed -= OFF_TRACK_PENALTY;
        }
        break;
      case 2:
        if (
                (xpos > 1250
                 || xpos < 100
                 || ypos > 800
                 || ypos < 100
                 || (xpos > 200 && xpos < 1100 && ypos > 250 && ypos < 500 )
                 || (xpos > 200 && xpos < 800 && ypos > 250 && ypos < 700)
                 || (xpos > 950 && ypos > 600))
                 && speed > MIN_GRASS_SPEED){
          speed -= OFF_TRACK_PENALTY;

        }
        break;
      case 3:
        if(((xpos > 1200
                || xpos < 100
                || ypos > 800
                || ypos < 100)
                ||(xpos > 700 && xpos < 900 && ypos < 350)
                ||(xpos > 1000 && xpos < 1100 && ypos > 200 && ypos < 650)
                ||(xpos > 200 && xpos < 1100 && ypos > 450 && ypos < 650)
                ||(xpos > 200 && xpos < 550 && ypos > 250 && ypos < 650)
                || (xpos > 500 && xpos < 800 && ypos > 700))
                && speed > MIN_GRASS_SPEED
        )
        {
          speed -= OFF_TRACK_PENALTY;
        } else if (xpos > 1050 && xpos < 1075 && ypos > 250 && ypos < 450) {
          //hit barrier
          speed = 0;
        }

    }
  }

  /**
   * Checks if the player completes a lap
   */
  public void lap(){

    if(xpos > 300 && xpos < 350 && ypos < 350){
      if(!start){
        start = true;
        oldTime = time.getCurrentTime();
      }
      else if(lapFlag){
        long curTime = time.getCurrentTime();
        float thisTime = (curTime - oldTime) / 1000f;
        if (thisTime < bestTime){
          bestTime = thisTime;
        }
        System.out.println(thisTime + "secs");
        oldTime = curTime;
        lapFlag = false;
        laps++;
      }
    }
    if(xpos > 400 && xpos < 450 && ypos > 500){
      lapFlag = true;
    }
  }

  /**
   * Calculates the effect of drag on the car's speed.
   * Aero drag is the main factor
   * weight reduces it to push through the air better
   */
  public void drag(){
    // Calculate the reduction in speed due to drag
    speed -= (aero.getDrag() * DRAGFACTOR * speed * (1 + (revs / 5000))) / (weight * WEIGHT_FACTOR) / LIMITER;
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
    double possibleAcceleration = engine.getPower() / (Math.abs(revs - engine.getOpRevs()) * engine.getDropoff())
            / (weight * WEIGHT_FACTOR) / (1 + CAMBER);
    // Cap the acceleration
    if (possibleAcceleration > MAX_ACCELERATION) possibleAcceleration = MAX_ACCELERATION;
    // Increase the car's speed based on the possible acceleration
    speed += possibleAcceleration / LIMITER;
  }

  public void turn(int dir){
    double turnAmt = CAMBER * dir;
    if (speed > 1) {
      double momentum = weight * speed;
      turnAmt /= momentum * 0.15 / grip;
      if(Math.abs(turnAmt) > 0.1){
        turnAmt = CAMBER * dir;
      }

      direction += turnAmt;
    }
  }
  public void brake(){
    if(speed - (BRAKE / weight * WEIGHT_FACTOR) > 0){
      speed -= BRAKE / (weight * WEIGHT_FACTOR);}
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

  public void setGears(int gearsIndex, int inputVal) {
    gears.setGear(gearsIndex, inputVal);
  }
}
