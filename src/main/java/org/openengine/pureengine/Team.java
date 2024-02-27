package org.openengine.pureengine;

import java.util.Random;
public class Team {
    private String name;
    private int goalsScored;

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void score() {
        goalsScored++;
    }
}
