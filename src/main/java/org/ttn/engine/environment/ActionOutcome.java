package org.ttn.engine.environment;

import org.ttn.engine.space.PitchPosition;

public class ActionOutcome {

    private PitchPosition pitchPosition;
    private OutcomeType type;

    public ActionOutcome(PitchPosition pitchPosition) {
        this.pitchPosition = pitchPosition;
    }

    public ActionOutcome(OutcomeType type) {
        this.type = type;
    }

    public ActionOutcome(PitchPosition pitchPosition, OutcomeType type) {
        this.pitchPosition = pitchPosition;
        this.type = type;
    }

    public PitchPosition getPitchPosition() {
        return pitchPosition;
    }

    public OutcomeType getType() {
        return type;
    }
}
