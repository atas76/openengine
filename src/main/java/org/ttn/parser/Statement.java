package org.ttn.parser;

import org.ttn.engine.agent.Action;
import org.ttn.engine.agent.ActionType;
import org.ttn.engine.environment.ActionOutcome;
import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.rules.SetPiece;
import org.ttn.engine.space.PitchPosition;

public class Statement {

    public enum Type {
        SET_PIECE_EXECUTION_BLOCK,
        POSSESSION_STATEMENT_BLOCK,
        PRESSURE_STATEMENT_BLOCK,
        TRANSITION_STATEMENT_BLOCK,
        POSSESSOR_DEFINITION,
        INDIRECT_OUTCOME,
        STANDARD,
        BREAK,
        DEFAULT_SET_PIECE_EXECUTION
    }

    private int time;
    private PitchPosition pitchPosition;
    private Action action;
    private TacticalPosition.X tacticalPositionX;
    private TacticalPosition.Y tacticalPositionY;
    private ActionOutcome actionOutcome;
    private ActionOutcome restingOutcome;
    private String team;
    private SetPiece setPiece;
    private Type type;
    private boolean ballPossessionChange;

    public Statement() {} // Bring on mutability (for the time being)

    public Statement(int time, PitchPosition pitchPosition, Action action, ActionOutcome actionOutcome, Type type) {
        this.time = time;
        this.pitchPosition = pitchPosition;
        this.action = action;
        this.actionOutcome = actionOutcome;
        this.type = type;
    }

    public Statement(int time, PitchPosition pitchPosition, ActionOutcome actionOutcome) {
        this(time, pitchPosition, new Action(ActionType.Default), actionOutcome, Type.STANDARD);
    }

    public Statement(int time, PitchPosition pitchPosition,
                     ActionType actionType, ActionOutcome actionOutcome) {
        this(time, pitchPosition, new Action(actionType), actionOutcome, Type.STANDARD);
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public PitchPosition getPitchPosition() {
        return pitchPosition;
    }

    public void setPitchPosition(PitchPosition pitchPosition) {
        this.pitchPosition = pitchPosition;
    }

    @Deprecated
    public TacticalPosition.X getTacticalPositionX() {
        return tacticalPositionX;
    }

    public void setTacticalPositionX(TacticalPosition.X tacticalPositionX) {
        this.tacticalPositionX = tacticalPositionX;
    }

    @Deprecated
    public TacticalPosition.Y getTacticalPositionY() {
        return tacticalPositionY;
    }

    public void setTacticalPositionY(TacticalPosition.Y tacticalPositionY) {
        this.tacticalPositionY = tacticalPositionY;
    }

    public void setActionOutcome(ActionOutcome actionOutcome) {
        this.actionOutcome = actionOutcome;
    }

    public ActionOutcome getActionOutcome() {
        return this.actionOutcome;
    }

    public ActionOutcome getRestingOutcome() {
        return restingOutcome;
    }

    public void setRestingOutcome(ActionOutcome restingOutcome) {
        this.restingOutcome = restingOutcome;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public SetPiece getSetPiece() {
        return setPiece;
    }

    public void setSetPiece(SetPiece setPiece) {
        this.setPiece = setPiece;
    }

    public void setBallPossessionChange(boolean ballPossessionChange) {
        this.ballPossessionChange = true;
    }

    public boolean isBallPossessionChange() {
        return this.ballPossessionChange;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
