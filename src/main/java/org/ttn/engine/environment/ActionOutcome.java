package org.ttn.engine.environment;

import org.ttn.engine.space.PitchPosition;

import java.util.List;

public class ActionOutcome {

    private PitchPosition pitchPosition;
    private OutcomeType type;
    private boolean freeSpace;
    private boolean interception;
    private OutcomeType restingOutcome;

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
        assignOutcomeParameters(outcomeParameters);
    }

    private void assignOutcomeParameters(List<OutcomeParameter> outcomeParameters) {
        outcomeParameters.forEach(param -> {
            switch(param) {
                case FREE_SPACE:
                    this.freeSpace = true;
                    break;
                case INTERCEPTION:
                    this.interception = true;
                    break;
            }
        });
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

    public boolean isFreeSpace() {
        return freeSpace;
    }

    public boolean isInterception() {
        return interception;
    }
}
