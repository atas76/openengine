package org.mpn;

public class Statement {

    private String teamKey;
    private int minutes;
    private int seconds;

    private State initialState;
    private State endState;

    public Statement(String teamKey, int minutes, int seconds, State initialState, State endState) {
        this.teamKey = teamKey;
        this.minutes = minutes;
        this.seconds = seconds;
        this.initialState = initialState;
        this.endState = endState;
    }

    public String getTeamKey() {
        return teamKey;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public State getInitialState() {
        return initialState;
    }

    public State getEndState() {
        return endState;
    }
}
