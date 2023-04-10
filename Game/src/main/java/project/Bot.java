package project;

import processing.core.*;
import java.util.ArrayList;

import static java.lang.Math.PI;
import static processing.core.PApplet.*;

/**
 * The Bot class represents a semi-computer-controlled player in the game.
 * It extends the Car class.
 */
public class Bot extends Car {
  /**
   * The position instance variable represents the position of the bot.
   */
  protected PVector position;
  /**
   * The direction instance variable represents the direction of the bot.
   */
  protected PVector direction;
  /**
   * The speed instance variable represents the speed of the bot.
   */
  private PVector velocity;
  /**
   * The steeringAngle instance variable represents the steering angle of the bot.
   */
  private float steeringAngle;
  /**
   * The pid instance variable represents the PID controller for the bot.
   */
  private PID pid;
/**
   * The frameRate instance variable represents the frame rate of the bot from the window.
   */
  private float frameRate;
  /**
   * The waypoints instance variable represents the list of waypoints for the bot to follow.
   */
  ArrayList<PVector> waypoints;
  /**
   * The currentWaypointIndex instance variable represents the index of the current waypoint.
   */
  int currentWaypointIndex;
  /**
   * The waypointReached instance variable represents whether the bot has reached the current waypoint.
   */
  boolean waypointReached = false;

  /**
   * The constructor for the Bot class.
   * @param position the position of the bot
   * @param direction the direction of the bot
   * @param speed the speed of the bot
   * @param window the game manager for the current game
   * @param b the type of bot
   */
  public Bot(PVector position, PVector direction, float speed,
             GameManager window, String b) {
    super(position, direction, speed, window);
    this.position = position;
    this.direction = direction;
    this.steeringAngle = 0;
    this.pid = new PID(0.1, 0.1, 0.1);
    this.speed = 3;
    this.frameRate = window.frameRate;
    this.currentWaypointIndex = 0;
    waypoints = new ArrayList<>();
  }

  /**
   * The update method updates the bot's position and direction.
   */
  @Override
  public void update() {
    // check if we have reached the current waypoint
    if (position.dist(waypoints.get(currentWaypointIndex)) < WIDTH) {
      waypointReached = true;
      // if we have, increment to the next waypoint and update the heading
      currentWaypointIndex = (currentWaypointIndex + 1) % waypoints.size();
    }

    // calculate the steering angle using PID control
    PVector desiredDirection = PVector.sub(waypoints.get((currentWaypointIndex + 1) % waypoints.size()), position);
    float desiredHeading = desiredDirection.heading();
    double error = desiredHeading - direction.heading();
    double dt = 1 / frameRate;
    double output = pid.calculate(0, error, dt);
    setSteeringAngle((float)output);

    // set the velocity to be proportional to the vector pointing towards the current waypoint
    PVector velocity = PVector.sub(waypoints.get(currentWaypointIndex), position);
    velocity.normalize();
    velocity.mult(speed);
    setVelocity(velocity);
    position.add(velocity);
    direction.rotate(steeringAngle);

    // update the bots position
    super.update();
  }

  /**
   * The setVelocity method sets the velocity of the bot.
   * @param velocity the velocity of the bot
   */
  public void setVelocity(PVector velocity) {
    this.velocity = velocity;
  }

  /**
   * The setSteeringAngle method sets the steering angle of the bot.
   * @param angle the steering angle of the bot
   */
  public void setSteeringAngle(float angle) {
    float minAngle = (float) (-PI/4);
    float maxAngle = (float) (PI/4);
    this.steeringAngle = constrain(angle, minAngle, maxAngle);
  }

  public void setWaypoints(ArrayList<PVector> waypoints) {
    this.waypoints = waypoints;
  }
}
