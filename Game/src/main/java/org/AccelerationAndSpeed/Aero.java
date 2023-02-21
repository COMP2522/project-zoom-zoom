package org.AccelerationAndSpeed;

public class Aero {
    int downForce;
    static int drag;
    int weight;

    public Aero (int df, int drg, int w){
        downForce = df;
        drag = drg;
        weight = w;
    }

    public void setDownForce(int downForce) {
        this.downForce = downForce;
    }

    public void setDrag(int drag) {
        this.drag = drag;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDownForce() {
        return downForce;
    }

    public static int getDrag() {
        return drag;
    }

    public int getWeight() {
        return weight;
    }
}
