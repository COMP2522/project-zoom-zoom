package org.AccelerationAndSpeed;

public class Engine {
    public void setPower(int power) {
        this.power = power;
    }

    public void setSmooth(double smooth) {
        this.smooth = smooth;
    }

    public void setDropoff(double dropoff) {
        this.dropoff = dropoff;
    }

    public void setOpRevs(int opRevs) {
        this.opRevs = opRevs;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getPower() {
        return power;
    }

    public double getSmooth() {
        return smooth;
    }

    public double getDropoff() {
        return dropoff;
    }

    public int getOpRevs() {
        return opRevs;
    }

    public int getWeight() {
        return weight;
    }

    private double power;
    private double smooth;
    private double dropoff;
    private int opRevs;
    private int weight;
    public Engine (double p, double sm, double droff, int oprev, int w){
        power = p;
        smooth = sm;
        dropoff = droff;
        opRevs = oprev;
        weight = w;
    }
}
