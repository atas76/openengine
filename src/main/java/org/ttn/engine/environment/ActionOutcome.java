package org.ttn.engine.environment;

import org.ttn.engine.space.PitchPosition;

import java.util.ArrayList;
import java.util.List;

public class ActionOutcome {

    private PitchPosition pitchPosition;
    private OutcomeType type;
    private OutcomeType restingOutcome;
    private List<OutcomeParameter> outcomeParameters = new ArrayList<>();

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

    public ActionOutcome(PitchPosition pitchPosition, List<OutcomeParameter> outcomeParameters) {
        this.pitchPosition = pitchPosition;
        this.outcomeParameters = outcomeParameters;
    }

    public void setRestingOutcome(OutcomeType restingOutcome) {
        this.restingOutcome = restingOutcome;
    }

    public PitchPosition getPitchPosition() {
        return pitchPosition;
    }

    public OutcomeType getType() {
        return type;
    }

    public OutcomeType getRestingOutcome() {
        return this.restingOutcome;
    }

    public boolean isOutcome(OutcomeParameter outcomeParameter) {
        return outcomeParameters.contains(outcomeParameter);
    }
}
