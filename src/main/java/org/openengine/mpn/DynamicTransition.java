package org.openengine.mpn;

import org.mpn.PitchPosition;
import org.mpn.State;

public class DynamicTransition implements MatchPhaseTransition {

    record NextTransition(double xG, PitchPosition pitchPosition) {}

    private State initialState;
    private State endState;
    private int duration;
    private State goalAttemptOutcome;
    private boolean possessionChanged;
    private Double xG;
    private State defaultEndState;
    private String team;

    private NextTransition nextTransition;

    public DynamicTransition(String team, State initialState, State endState, int duration, State goalAttemptOutcome,
                             boolean possessionChanged, Double xG, State defaultEndState) {
        this.team = team;
        this.initialState = initialState;
        this.endState = endState;
        this.duration = duration;
        this.goalAttemptOutcome = goalAttemptOutcome;
        this.possessionChanged = possessionChanged;
        this.xG = xG;
        this.defaultEndState = defaultEndState;
    }

    public DynamicTransition(String team, State initialState, State endState, int duration, State goalAttemptOutcome,
                             boolean possessionChanged, Double xG, State defaultEndState, NextTransition nextTransition) {
        this(team, initialState, endState, duration, goalAttemptOutcome, possessionChanged, xG, defaultEndState);
        this.nextTransition = nextTransition;
    }

    @Override
    public State getInitialState() {
        return this.initialState;
    }

    @Override
    public State getEndState() {
        return this.endState;
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

    @Override
    public State getGoalAttemptOutcome() {
        return this.goalAttemptOutcome;
    }

    @Override
    public boolean isPossessionChanged() {
        return this.possessionChanged;
    }

    @Override
    public Double getxG() {
        return this.xG;
    }

    @Override
    public State getDefaultEndState() {
        return this.defaultEndState;
    }

    @Override
    public Double getNextGoalProbability() {
        return this.nextTransition.xG();
    }

    @Override
    public String getTeamKey() {
        return this.team;
    }
    public String toString() {
        return  "Dynamic\n" +
                team + ": " + initialState + " -> "
                + (this.isPossessionChanged() ? "!" : "")
                + endState
                + (this.goalAttemptOutcome != null ? " => " + this.goalAttemptOutcome : "");
    }
}
