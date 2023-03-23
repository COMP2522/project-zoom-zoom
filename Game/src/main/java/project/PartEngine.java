package project;

public class PartEngine {
    public void setPower(int power) {
        this.power = power;
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
    private double dropoff;
    private int opRevs;
    private int weight;
    public PartEngine(double p, double droff, int oprev, int w){
        power = p;
        dropoff = droff;
        opRevs = oprev;
        weight = w;
    }
}
