package org.mpn;

public class Statement {

    private String teamKey;

    private Time startTime;
    private Time endTime;

    private State initialState;
    private State endState;

    public Statement(String teamKey, int minutes, int seconds, State initialState, State endState) {
        this(teamKey, new Time(minutes, seconds), initialState, endState);
    }

    public Statement(String teamKey, Time startTime, State initialState, State endState) {
        this.teamKey = teamKey;
        this.startTime = startTime;
        this.initialState = initialState;
        this.endState = endState;
    }

    public Statement(String teamKey, Time startTime, Time endTime, State initialState, State endState) {
        this(teamKey, startTime, initialState, endState);
        this.endTime = endTime;
    }

    public String getTeamKey() {
        return teamKey;
    }

    public int getMinutes() {
        return startTime.minutes();
    }

    public int getSeconds() {
        return startTime.seconds();
    }

    public State getInitialState() {
        return initialState;
    }

    public State getEndState() {
        return endState;
    }
}
