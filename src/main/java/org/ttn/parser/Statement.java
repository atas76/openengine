package org.ttn.parser;

import org.ttn.engine.agent.Action;
import org.ttn.engine.environment.ActionOutcome;
import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.rules.SetPiece;
import org.ttn.engine.space.PitchPosition;

public class Statement {

    public enum Type {
        SP_EXECUTION, POSSESSION_BLOCK_START, INDIRECT_OUTCOME, STANDARD
    }

    private int time;
    private Action action;
    private TacticalPosition.X tacticalPositionX;
    private TacticalPosition.Y tacticalPositionY;
    private ActionOutcome actionOutcome;
    private String team;
    private SetPiece setPiece;
    private Type type;

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public TacticalPosition.X getTacticalPositionX() {
        return tacticalPositionX;
    }

    public void setTacticalPositionX(TacticalPosition.X tacticalPositionX) {
        this.tacticalPositionX = tacticalPositionX;
    }

    public TacticalPosition.Y getTacticalPositionY() {
        return tacticalPositionY;
    }

    public void setTacticalPositionY(TacticalPosition.Y tacticalPositionY) {
        this.tacticalPositionY = tacticalPositionY;
    }

    // TODO refactor
    public PitchPosition getPitchPosition() {
        return actionOutcome.getPitchPosition();
    }

    // TODO refactor
    public void setPitchPosition(PitchPosition pitchPosition) {
        this.actionOutcome = new ActionOutcome(pitchPosition);
    }

    public void setActionOutcome(ActionOutcome actionOutcome) {
        this.actionOutcome = actionOutcome;
    }

    public ActionOutcome getActionOutcome() {
        return this.actionOutcome;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
