package org.openengine.openfootie;

public class Team {

    private String name;
    private MatchFlowMatrix matchFlowMatrix;

    public Team(String name, MatchFlowMatrix matchFlowMatrix) {
        this.name = name;
        this.matchFlowMatrix = matchFlowMatrix;
    }

    public MatchFlowMatrix getMatchFlowMatrix() {
        return matchFlowMatrix;
    }

    @Override
    public String toString() {
        return name;
    }
}
