package project;

public class PartChassis {
    int weight;

    public int getWheelBaseX() {
        return wheelBaseX;
    }

    public void setWheelBaseX(int wheelBaseX) {
        this.wheelBaseX = wheelBaseX;
    }

    public int getWheelBaseY() {
        return wheelBaseY;
    }

    public void setWheelBaseY(int wheelBaseY) {
        this.wheelBaseY = wheelBaseY;
    }

    int wheelBaseX;
    int wheelBaseY;


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public PartChassis(int w, int wbx, int wby){
        weight = w;
        wheelBaseX = wbx;
        wheelBaseY = wby;
    }
}
