package org.fgn.schema.data;

import static java.util.Objects.nonNull;

public class CoordinateObject extends BaseObject {

    private String x;
    private String y;

    private String front;
    private String back;
    private String lateral;

    private String context;
    private String player;
    private Boolean hold;

    @Override
    public String toString() {

        String contextField = nonNull(context) ? ", context: " + context : "";
        String playerField = nonNull(player) ? ", player: " + player : "";
        String holdField = nonNull(hold) && hold ? ", hold" :  "";

        return "{" +
                "x: " +
                x + ", " +
                "y: " +
                y +
                contextField +
                playerField +
                holdField +
                "}";
    }

    public CoordinateObject() {}

    public CoordinateObject(String id, String name, String description) {
        super(id, name, description);
    }

    public Boolean getHold() {
        return hold;
    }

    public void setHold(Boolean hold) {
        this.hold = hold;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
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
