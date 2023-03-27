package project;

/**
 * Represents the engine part of a car, which affects power, dropoff, and weight.
 */
public class PartEngine {
    /**
     * The engine power of the car.
     */
    private double power;
    /**
     * The engine dropoff of the car.
     */
    private double dropoff;
    /**
     * The engine operating revolutions per minute (RPM) of the car.
     */
    private int opRevs;
    /**
     * The weight of the car's engine.
     */
    private int weight;

    /**
     * Constructor for PartEngine objects.
     * @param p The engine power of the car.
     * @param droff The engine dropoff of the car.
     * @param oprev The engine operating RPM of the car.
     * @param w The weight of the car's engine.
     */
    public PartEngine(double p, double droff, int oprev, int w){
        power = p;
        dropoff = droff;
        opRevs = oprev;
        weight = w;
    }

    public void setEngine(double p, double droff, int oprev, int w){
        power = p;
        dropoff = droff;
        opRevs = oprev;
        weight = w;
    }
    /**
     * Sets the engine power of the car.
     * @param power The new engine power.
     */
    public void setPower(double power) {
        this.power = power;
    }

    /**
     * Sets the engine dropoff of the car.
     * @param dropoff The new engine dropoff.
     */
    public void setDropoff(double dropoff) {
        this.dropoff = dropoff;
    }

    /**
     * Sets the engine operating RPM of the car.
     * @param opRevs The new engine operating RPM.
     */
    public void setOpRevs(int opRevs) {
        this.opRevs = opRevs;
    }

    /**
     * Sets the weight of the car's engine.
     * @param weight The new weight.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Returns the engine power of the car.
     * @return The engine power.
     */
    public double getPower() {
        return power;
    }

    /**
     * Returns the engine dropoff of the car.
     * @return The engine dropoff.
     */
    public double getDropoff() {
        return dropoff;
    }

    /**
     * Returns the engine operating RPM of the car.
     * @return The engine operating RPM.
     */
    public int getOpRevs() {
        return opRevs;
    }

    /**
     * Returns the weight of the car's engine.
     * @return The weight.
     */
    public int getWeight() {
        return weight;
    }
}
