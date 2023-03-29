package project;

/**
 * Represents the aerodynamics part of a car, which affects downforce, drag, and weight.
 */
public class PartAero {
    public static PartAero aero1 = new PartAero(30, 15, 500);
    public static PartAero aero2 = new PartAero(40, 20, 300);
    public static PartAero aero3 = new PartAero(10, 5, 300);
    public static PartAero aero4 = new PartAero(0, 0, 0);
    public static PartAero[] aeroParts = {aero1, aero2, aero3, aero4};

    /**
     * The amount of downforce generated by the car's aerodynamics.
     */
    int downForce;
    /**
     * The amount of air resistance (drag) generated by the car's aerodynamics.
     */
    int drag;
    /**
     * The weight of the car's aerodynamics.
     */
    int weight;

    /**
     * Constructor for PartAero objects.
     * @param df The amount of downforce generated by the car's aerodynamics.
     * @param drg The amount of air resistance (drag) generated by the car's aerodynamics.
     * @param w The weight of the car's aerodynamics.
     */
    public PartAero(int df, int drg, int w){
        downForce = df;
        drag = drg;
        weight = w;
    }

    public void setAero(PartAero aero){
        downForce = aero.downForce;
        drag = aero.drag;
        weight = aero.weight;
    }

    /**
     * Sets the amount of downforce generated by the car's aerodynamics.
     * @param downForce The new amount of downforce.
     */
    public void setDownForce(int downForce) {
        this.downForce = downForce;
    }

    /**
     * Sets the amount of air resistance (drag) generated by the car's aerodynamics.
     * @param drag The new amount of drag.
     */
    public void setDrag(int drag) {
        this.drag = drag;
    }

    /**
     * Sets the weight of the car's aerodynamics.
     * @param weight The new weight.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Returns the current amount of downforce generated by the car's aerodynamics.
     * @return The amount of downforce.
     */
    public int getDownForce() {
        return downForce;
    }

    /**
     * Returns the current amount of air resistance (drag) generated by the car's aerodynamics.
     * @return The amount of drag.
     */
    public int getDrag() {
        return drag;
    }

    /**
     * Returns the current weight of the car's aerodynamics.
     * @return The weight.
     */
    public int getWeight() {
        return weight;
    }
}