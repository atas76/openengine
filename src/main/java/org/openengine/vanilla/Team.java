package org.openengine.vanilla;

public class Team {

    private String name;

    public Team(String name) {
        this.name = name;
    }

    public Player getGoalkeeper() {
        return new Player();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
