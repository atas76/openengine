package org.fgn.parser;

public class Statement {

    private MatchTime time;
    private String team;
    private State stateIn;

    public State getStateIn() {
        return stateIn;
    }

    public void setStateIn(State stateIn) {
        this.stateIn = stateIn;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public MatchTime getTime() {
        return this.time;
    }

    public void setTime(MatchTime time) {
        this.time = time;
    }
}
