package org.openengine.pureengine;

public class Team {
    private String name;
    private int goalsScored;

    private int skill;

    public Team(String name) {
        this.name = name;
    }

    public Team(String name, int skill) {
        this.name = name;
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getSkill() {
        return skill;
    }

    public void score() {
        goalsScored++;
    }
}
