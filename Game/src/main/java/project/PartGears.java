package project;

/**
 * Represents the gears part of a car, which includes gear ratios and shifting.
 */
public class PartGears {
    /**
     * An array of gear ratios for the car.
     */
    int [] gears = new int[4];
    /**
     * The current gear of the car.
     */
    int currGear = 0;

    /**
     * Constructor for PartGears objects.
     * @param a The gear ratio for gear 1.
     * @param b The gear ratio for gear 2.
     * @param c The gear ratio for gear 3.
     * @param d The gear ratio for gear 4.
     */
    public PartGears(int a, int b, int c, int d) {
        gears[0] = a;
        gears[1] = b;
        gears[2] = c;
        gears[3] = d;
    }

    /**
     * Sets the gear ratio for gear 1.
     * @param r The new gear ratio.
     */
    public void setGear1(int r){
        gears[0] = r;
    }

    /**
     * Sets the gear ratio for gear 2.
     * @param r The new gear ratio.
     */
    public void setGear2(int r){
        gears[1] = r;
    }

    /**
     * Sets the gear ratio for gear 3.
     * @param r The new gear ratio.
     */
    public void setGear3(int r){
        gears[2] = r;
    }

    /**
     * Sets the gear ratio for gear 4.
     * @param r The new gear ratio.
     */
    public void setGear4(int r){
        gears[3] = r;
    }

    /**
     * Returns the gear ratio for gear 1.
     * @return The gear ratio.
     */
    public int start(){
        return gears[0];
    }

    /**
     * Shifts up to the next gear ratio and returns the new gear ratio.
     * @return The new gear ratio.
     */
    public int shiftUp(){
        currGear++;
        return gears[currGear];
    }

    /**
     * Shifts down to the previous gear ratio and returns the new gear ratio.
     * @return The new gear ratio.
     */
    public int shiftDown(){
        currGear--;
        return gears[currGear];
    }
}