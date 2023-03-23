package project;

public class PartAero {
    int downForce;
    int drag;
    int weight;

    public PartAero(int df, int drg, int w){
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

    public int getDrag() {
        return drag;
    }

    public int getWeight() {
        return weight;
    }
}
