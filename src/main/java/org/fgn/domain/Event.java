package org.fgn.domain;

public class Event {

    private int time;
    private String team;
    private InState inputState;
    private Action action;
    private OutState outputState;

    public Event() {
        this.inputState = new InState();
        this.outputState = new OutState();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public InState getInputState() {
        return inputState;
    }

    public void setInputState(InState inputState) {
        this.inputState = inputState;
    }

    public OutState getOutputState() {
        return outputState;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
