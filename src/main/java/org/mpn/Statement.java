package org.mpn;

public class Statement {

    record Time(int minutes, int seconds) {}

    private String teamKey;

    private Time startTime;
    private Time endTime;

    private State initialState;
    private State endState;

    public Statement(String teamKey, int minutes, int seconds, State initialState, State endState) {
        this.teamKey = teamKey;
        this.startTime = new Time(minutes, seconds);
        this.initialState = initialState;
        this.endState = endState;
    }

    public String getTeamKey() {
        return teamKey;
    }

    public int getMinutes() {
        return startTime.minutes;
    }

    public int getSeconds() {
        return startTime.seconds;
    }

    public State getInitialState() {
        return initialState;
    }

    public State getEndState() {
        return endState;
    }
}
