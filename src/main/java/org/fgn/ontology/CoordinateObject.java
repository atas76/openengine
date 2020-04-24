package org.fgn.ontology;

public class CoordinateObject extends BaseObject {

    private String x;
    private String y;

    private String front;
    private String back;
    private String lateral;

    @Override
    public String toString() {
        return "{" +
                "x: " +
                x + ", " +
                "y: " +
                y +
                "}";
    }

    public CoordinateObject() {}

    public CoordinateObject(String id, String name, String description) {
        super(id, name, description);
    }

    public int getIntX() {
        return Integer.parseInt(x);
    }

    public int getIntY() {
        return Integer.parseInt(y);
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getLateral() {
        return lateral;
    }

    public void setLateral(String lateral) {
        this.lateral = lateral;
    }
}
