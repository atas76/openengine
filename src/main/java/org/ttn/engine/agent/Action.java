package org.ttn.engine.agent;

import org.ttn.engine.space.PitchPosition;

public class Action {

    private ActionType type;
    private PitchPosition pitchPosition;

    public Action(ActionType type, PitchPosition pitchPosition) {
        this.type = type;
        this.pitchPosition = pitchPosition;
    }

    public ActionType getType() {
        return type;
    }

    public PitchPosition getPitchPosition() {
        return pitchPosition;
    }
}
