package org.mpn;

import org.mpn.exceptions.UnknownStateException;
import org.openengine.mpn.MatchPhaseTransition;

import java.util.HashMap;
import java.util.Map;

public class Statement implements ProcessUnit, MatchPhaseTransition {

    public enum ParameterName {
        xG, defaultEndState;
        public static ParameterName createFromName(String name) {
            return switch (name) {
                case "xG" -> xG;
                case "default" -> defaultEndState;
                default -> null;
            };
        }
    }
    public record Parameters(Double xG, State defaultEndState) {}

    private String teamKey;

    private Time startTime;
    private Time endTime;

    private State initialState;
    private State endState;

    private State goalAttemptOutcome;

    private PitchPosition initialPitchPosition;
    private PitchPosition outcomePitchPosition;

    private boolean retainPossession = true;

    private Parameters parameters;

    public Statement(String teamKey, Time startTime, Time endTime, State initialState, State endState,
                     Map<String, String> argumentList) throws UnknownStateException {
        this.teamKey = teamKey;
        this.startTime = startTime;
        this.initialState = initialState;
        this.endState = endState;
        this.endTime = endTime;
        Map<ParameterName, String> argumentAssignments = new HashMap<>();
        argumentList.forEach((k, v) -> argumentAssignments.put(ParameterName.createFromName(k), v));
        this.parameters = new Parameters(
                argumentAssignments.get(ParameterName.xG) != null ? Double.parseDouble(argumentAssignments.get(ParameterName.xG)) : null,
                argumentAssignments.get(ParameterName.defaultEndState) != null ? State.createFromName(argumentAssignments.get(ParameterName.defaultEndState)) : null);
    }

    public void addOptionalElements(
            PitchPosition initialPitchPosition,
            PitchPosition outcomePitchPosition,
            boolean keepPossession,
            State goalAttemptOutcome) {
        this.initialPitchPosition = initialPitchPosition;
        this.outcomePitchPosition = outcomePitchPosition;
        this.retainPossession = keepPossession;
        this.goalAttemptOutcome = goalAttemptOutcome;
    }

    @Override
    public String toString() {
        return teamKey + ": " + initialState + " -> "
                + (this.isPossessionChanged() ? "!" : "")
                + endState
                + (this.goalAttemptOutcome != null ? " => " + this.goalAttemptOutcome : "");
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getTeamKey() {
        return teamKey;
    }

    public Time getStartTime() {
        return this.startTime;
    }

    public int getMinutes() {
        return startTime.minutes();
    }

    public int getSeconds() {
        return startTime.seconds();
    }

    public int getEndMinutes() {
        return endTime != null ? endTime.minutes() : startTime.minutes();
    }

    public int getEndSeconds() {
        return endTime != null ? endTime.seconds() : startTime.seconds();
    }

    public int getDuration() {
        if (endTime == null) {
            return -1;
        }
        return endTime.getAbsoluteTime() - startTime.getAbsoluteTime();
    }

    public State getInitialState() {
        return initialState;
    }

    public State getEndState() {
        return endState;
    }

    public State getGoalAttemptOutcome() {
        return goalAttemptOutcome;
    }

    public PitchPosition getInitialPitchPosition() {
        return this.initialPitchPosition;
    }

    public PitchPosition getOutcomePitchPosition() {
        return this.outcomePitchPosition;
    }

    public Parameters getParameters() {
        return this.parameters;
    }

    public Double getxG() {
        return this.parameters.xG();
    }

    public State getDefaultEndState() {
        return this.parameters.defaultEndState();
    }

    public boolean isPossessionRetained() {
        return this.retainPossession;
    }

    public boolean isPossessionChanged() {
        return !this.retainPossession;
    }
}
