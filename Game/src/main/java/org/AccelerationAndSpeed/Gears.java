package org.AccelerationAndSpeed;

public class Gears {
    private int g1;
    private int g2;
    private int g3;
    private int g4;

    public void setG1(int g1) {
        this.g1 = g1;
    }

    public void setG2(int g2) {
        this.g2 = g2;
    }

    public void setG3(int g3) {
        this.g3 = g3;
    }

    public void setG4(int g4) {
        this.g4 = g4;
    }

    public void setG5(int g5) {
        this.g5 = g5;
    }

    private int g5;
    public Gears(int a, int b, int c, int d, int e){
        g1 = a;
        g2 = b;
        g3 = c;
        g4 = d;
        g5 = e;
    }

    public int getG1() {
        return g1;
    }

    public int getG2() {
        return g2;
    }

    public int getG3() {
        return g3;
    }

    public int getG4() {
        return g4;
    }

    public int getG5() {
        return g5;
    }
}
