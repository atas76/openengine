package org.mpn;

import org.mpn.exceptions.UnknownStateException;

import java.util.HashMap;
import java.util.Map;

public class Statement {

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

    private PitchPosition initialPitchPosition;

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

    public void addOptionalElements(PitchPosition initialPitchPosition, boolean keepPossession) {
        this.initialPitchPosition = initialPitchPosition;
        this.retainPossession = keepPossession;
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

    public int getEndMinutes() {
        return endTime != null ? endTime.minutes() : startTime.minutes();
    }

    public int getEndSeconds() {
        return endTime != null ? endTime.seconds() : startTime.seconds();
    }

    public State getInitialState() {
        return initialState;
    }

    public State getEndState() {
        return endState;
    }

    public PitchPosition getInitialPitchPosition() {
        return this.initialPitchPosition;
    }

    public Parameters getParameters() {
        return this.parameters;
    }

    public boolean isPossessionRetained() {
        return this.retainPossession;
    }
}
