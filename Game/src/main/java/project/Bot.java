package project;

import processing.core.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.PI;
import static processing.core.PApplet.constrain;

public class Bot extends Car {
  protected PVector position;
  protected PVector direction;
  private PVector velocity;
  private float steeringAngle;
  private PID pid;
  private float frameRate;
  private ArrayList<PVector> waypoints;
  private int currentWaypointIndex;

  public Bot(PVector position, PVector direction, float speed,
             Color color, GameManager window, ArrayList<PVector> waypoints, String b) {
    super(position, direction, speed, color, window);
    this.position = position;
    this.direction = direction;
    this.steeringAngle = 0;
    this.pid = new PID(0.1, 0.1, 0.1);
    this.speed = 10;
    this.frameRate = window.frameRate;
    this.waypoints = waypoints;
    this.currentWaypointIndex = 0;
  }

  @Override
  public void update() {
    // check if we have reached the current waypoint
    if (position.dist(waypoints.get(currentWaypointIndex)) < WIDTH) {
      // if we have, increment to the next waypoint
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

    // update the bot's position
    super.update();
  }
  
  public void setVelocity(PVector velocity) {
    this.velocity = velocity;
  }


  public void setSteeringAngle(float angle) {
    float minAngle = (float) (-PI/4);
    float maxAngle = (float) (PI/4);
    this.steeringAngle = constrain(angle, minAngle, maxAngle);
  }

}
