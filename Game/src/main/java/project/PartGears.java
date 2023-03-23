package project;

public class PartGears {

    static int [] gears = new int[4];
    int currGear = 1;

    public PartGears(int a, int b, int c, int d) {
        gears[0] = a;
        gears[1] = b;
        gears[2] = c;
        gears[3] = d;
    }


    public void setGear1(int r){
        gears[0] = r;
    }

    public void setGear2(int r){
        gears[1] = r;
    }

    public void setGear3(int r){
        gears[2] = r;
    }

    public void setGear4(int r){
        gears[3] = r;
    }

    public static int start(){
        return gears[0];
    }

    public int shiftUp(){
        if(currGear < 4){
            currGear++;
        }
        return gears[currGear];
    }

    public int shiftDown(){
        if(currGear > 1){
            currGear--;
        }
        return gears[currGear];
    }

}
