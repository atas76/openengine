package org.fgn.parser;

import org.fgn.schema.data.BaseObject;

public class Statement {

    private MatchTime time;
    private String team;
    private State stateIn;
    private Action action;
    private State stateOut;
    private String comment;
    private BaseObject actionOutcome;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public State getStateOut() {
        return stateOut;
    }

    public void setStateOut(State stateOut) {
        this.stateOut = stateOut;
    }

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

    public BaseObject getActionOutcome() {
        return this.actionOutcome;
    }

    public void setActionOutcome(BaseObject entity) {
        this.actionOutcome = entity;
    }
}
