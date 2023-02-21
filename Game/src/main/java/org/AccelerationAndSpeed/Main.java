package org.AccelerationAndSpeed;

public class Main {

    static final int GEAR1 = 200;
    static final int GEAR2 = 80;
    static final int GEAR3 = 40;
    static final int GEAR4 = 300;
    static final int GEAR5 = 500;

    static final double POWER = 10000;
    static final double SMOOTH = 0.01;
    static final double DROPOFF = 0.3;
    static final int OPREV = 3000;
    static final int EWEIGHT = 1000;

    static final int DOWNFORCE = 10;
    static final int ADRAG = 10;
    static final int AWEIGHT = 50;
    static final double DRAGFACTOR = 0.02;

    static final int CWEIGHT = 1000;
    static final int WHEELBASE = 100;

    static final int BRAKE = 100;
    static final int CAMBER = 15;
    static final int TOE = 15;

    static final double WFACTOR = 0.001;
    static final double CSFACTOR = 0.1;

    static int speed = 0;
    static int acc = 0;
    static int revs = 0;

    static int weight;

    static Gears gear = new Gears(GEAR1, GEAR2, GEAR3, GEAR4, GEAR5);
    static Engine engine = new Engine(POWER, SMOOTH, DROPOFF, OPREV, EWEIGHT);
    static Aero aero = new Aero(DOWNFORCE, ADRAG, AWEIGHT);
    static Chassis chassis = new Chassis(CWEIGHT, WHEELBASE);

    public static void main(String[] args) {
        int gearRatio = gear.getG1();
        System.out.println("Started");
        weight = engine.getWeight() + aero.getWeight() + chassis.getWeight();
        int run = 10;
        for (int i = 0; i < run; i++){
            zoom(gearRatio);
        }
        System.out.println("change gear");
        gearRatio = gear.getG2();
        for (int i = 0; i < run; i++){
            zoom(gearRatio);
        }
        System.out.println("change gear");
        gearRatio = gear.getG3();
        for (int i = 0; i < run; i++){
            zoom(gearRatio);
        }



    }

    public static double drag(){
        return (aero.getDrag() * DRAGFACTOR * speed) / (weight * WFACTOR);
    //(engine.getSmooth() * revs) +
    }

    public static double acc(){
        double prpacc = engine.getPower() / (Math.abs(revs - engine.getOpRevs()) * engine.getDropoff())
                / (weight * WFACTOR) / (CAMBER * CSFACTOR);
        if (prpacc > 15) prpacc = 15;
        return prpacc;
    }

    public static void zoom(int g){
        int gearRatio = g;
        speed -= drag();
        if (speed < 0) speed = 0;
        speed += acc();
        revs = speed * gearRatio;

        System.out.println(speed);
        System.out.println(revs);
        System.out.println("acc " + acc());
        System.out.println("drag " + drag() + "\n");
    }




}