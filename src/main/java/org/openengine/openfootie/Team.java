package org.openengine.openfootie;

public class Team {

    private String name;
    private MatchFlowMatrix matchFlowMatrix;
    private int goalsScored;

    public Team(String name, MatchFlowMatrix matchFlowMatrix) {
        this.name = name;
        this.matchFlowMatrix = matchFlowMatrix;
    }

    public void score() {
        this.goalsScored++;
    }

    public MatchFlowMatrix getMatchFlowMatrix() {
        return matchFlowMatrix;
    }

    public int getGoalsScored() {
        return this.goalsScored;
    }

    @Override
    public String toString() {
        return name;
    }
}
