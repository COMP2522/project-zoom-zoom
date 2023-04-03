package project;

/**
 * Represents the gears part of a car, which includes gear ratios and shifting.
 */
public class PartGears {

    private static int gear1 = 800;
    private static int gear2 = 300;
    private static int gear3 = 125;
    private static int gear4 = 80;
    /**
     * An array of gear ratios for the car.
     */
    static int[] gears = {gear1, gear2, gear3, gear4};
    /**
     * The current gear of the car.
     */
    int currGear = 0;

    /**
     * Constructor for PartGears objects.
     *
     * @param gear values of each gear in an array
     */
    public PartGears(int[] gear) {
        gears[0] = gear[0];
        gears[1] = gear[1];
        gears[2] = gear[2];
        gears[3] = gear[3];
    }

    /**
     * Sets the gear ratio for the specified gear.
     *
     * @param index of gear in the array
     * @param value The new gear ratio.
     */
    public void setGear(int index, int value) {
        gears[index] = value;
    }

    /**
     * Gets the gear ratio for gear i.
     *
     * @param i int value from 0 to 3
     * @return gear i ratio.
     */
    public int getGear(int i) {
        return gears[i];
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