package org.AccelerationAndSpeed;

public class Chassis {
    int weight;
    int wheelBase;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWheelBase() {
        return wheelBase;
    }

    public void setWheelBase(int wheelBase) {
        this.wheelBase = wheelBase;
    }

    public Chassis(int w, int wb){
        weight = w;
        wheelBase = wb;
    }
}
