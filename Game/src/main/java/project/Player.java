package project;

import processing.core.PVector;

import java.awt.*;



/**
 * Player, creates a player sprite to be controlled by the user.
 */
public class Player extends Sprite {

  //Setting defaults

  int GEAR1 = 300;
  int GEAR2 = 100;
  int GEAR3 = 30;
  int GEAR4 = 5;

  double POWER = 10000;
  final double DROPOFF = 0.3;
  int OPREV = 3000;
  int EWEIGHT = 1000;

  int DOWNFORCE = 10;
  int ADRAG = 10;
  int AWEIGHT = 50;
  double DRAGFACTOR = 0.02;

  int CWEIGHT = 1000;
  int WHEELBASE = 100;

  public void setBRAKE(double BRAKE) {
    this.BRAKE = BRAKE;
  }

  public void setCAMBER(int CAMBER) {
    this.CAMBER = CAMBER;
  }

  double BRAKE = 1;
  int CAMBER = 15;

  int TIREGRIP = 2000;

  static final double WFACTOR = 0.001;
  double CSFACTOR = 0.1;
  double CGFACTOR = 0.1;


  double speed = 0;
  double revs = 0;
  double direction = 0;
  boolean drifting = false;
  boolean braking = false;
  boolean accelerating = true;
  int gearRatio = 300;

  float xpos = 500;
  float ypos = 500;

  int weight;
  int grip;

  int LIMITER = 50;

  public int getTimer() {
    return timer;
  }

  int timer = 0;

  PartGears gear = new PartGears(GEAR1, GEAR2, GEAR3, GEAR4);
  PartEngine engine = new PartEngine(POWER, DROPOFF, OPREV, EWEIGHT);
  PartAero aero = new PartAero(DOWNFORCE, ADRAG, AWEIGHT);
  PartChassis chassis = new PartChassis(CWEIGHT, WHEELBASE);


  public Player(PVector position, PVector direction, float size, float speed,
                Color color, Window window) {
    super(position, direction, size, speed, color, window);
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
    revs = speed * gearRatio;
    xpos += speed / 10 * Math.cos(direction);
    ypos += speed / 10 * Math.sin(direction);
//    System.out.println(direction);
//    System.out.println("speed " + speed);
//    System.out.println("RPM " + revs);

  }

  public void drag(){
    speed -= (aero.getDrag() * DRAGFACTOR * speed) / (weight * WFACTOR) / LIMITER;
  }

  public void acc(){
    double prpacc = engine.getPower() / (Math.abs(revs - engine.getOpRevs()) * engine.getDropoff())
            / (weight * WFACTOR) / (CAMBER * CSFACTOR);
    if (prpacc > 20) prpacc = 20;
    speed += prpacc / LIMITER;
    //System.out.println("acc " + prpacc);
  }

//  public void move(int g){
//    int gearRatio = g;
//    speed -= drag();
//    if (speed < 0) speed = 0;
//    speed += acc();
//    revs = speed * gearRatio;
//    xpos += speed * Math.cos(direction);
//    ypos += speed * Math.sin(direction);
//  }

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
//    drifting = false;
//    double momentum = weight * speed;
//    if(Math.abs(turnAmt) * momentum > grip) {
//      turnAmt /= momentum * 2 / grip;
//      System.out.println("lost grip");
//      drifting = true;
//    }
    direction += turnAmt;

  }
  public void brake(){
      if(speed - (BRAKE / weight * WFACTOR) > 0){
      speed -= BRAKE / (weight * WFACTOR);}
      else speed = 0;
  }

  public void shiftUp(){

  }
  public void shiftDown(){

  }


}
