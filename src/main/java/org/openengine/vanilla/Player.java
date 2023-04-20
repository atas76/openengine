package org.openengine.vanilla;

public class Player {

    private String name;
    private Position position;
    private int shirtNo;

    public Player() {

    }

    public Player(Position position, int shirtNo) {
        this.position = position;
        this.name = position.name();
        this.shirtNo = shirtNo;
    }

    public String getName() {
        return this.name;
    }

    public int getShirtNo() {
        return this.shirtNo;
    }

    public Action decide() {
        return new Action();
    }
}
