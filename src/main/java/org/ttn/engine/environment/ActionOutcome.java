package org.ttn.engine.environment;

import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.space.PitchPosition;

import java.util.ArrayList;
import java.util.List;

public class ActionOutcome {

    private TacticalPosition tacticalPosition;
    private PitchPosition pitchPosition;
    private ActionOutcomeType type;
    private ActionOutcomeType restingOutcome;
    private List<ActionOutcomeParameter> actionOutcomeParameters = new ArrayList<>();
    private boolean possessionChange;

    public ActionOutcome(PitchPosition pitchPosition) {
        this.pitchPosition = pitchPosition;
    }

    public ActionOutcome(ActionOutcomeType type, boolean possessionChange) {
        this.type = type;
        this.possessionChange = possessionChange;
    }

    public ActionOutcome(TacticalPosition tacticalPosition, boolean possessionChange) {
        this.tacticalPosition = tacticalPosition;
        this.possessionChange = possessionChange;
    }

    public ActionOutcome(TacticalPosition tacticalPosition, PitchPosition pitchPosition, boolean possessionChange) {
        this(tacticalPosition, possessionChange);
        this.pitchPosition = pitchPosition;
    }

    public ActionOutcome(TacticalPosition tacticalPosition, PitchPosition pitchPosition, ActionOutcomeType type,
                         boolean possessionChange) {
        this.tacticalPosition = tacticalPosition;
        this.pitchPosition = pitchPosition;
        this.type = type;
        this.possessionChange = possessionChange;
    }

    public ActionOutcome(TacticalPosition tacticalPosition, PitchPosition pitchPosition, ActionOutcomeParameter actionOutcomeParameter,
                         boolean possessionChange) {
        this.tacticalPosition = tacticalPosition;
        this.pitchPosition = pitchPosition;
        this.actionOutcomeParameters.add(actionOutcomeParameter);
        this.possessionChange = possessionChange;
    }

    public ActionOutcome(PitchPosition pitchPosition, ActionOutcomeType type) {
        this(null, pitchPosition, type, false);
    }

    public ActionOutcome(PitchPosition pitchPosition, List<ActionOutcomeParameter> actionOutcomeParameters) {
        this.pitchPosition = pitchPosition;
        this.actionOutcomeParameters = actionOutcomeParameters;
    }

    public void setRestingOutcome(ActionOutcomeType restingOutcome) {
        this.restingOutcome = restingOutcome;
    }

    public TacticalPosition getTacticalPosition() {
        return this.tacticalPosition;
    }

    public PitchPosition getPitchPosition() {
        return pitchPosition;
    }

    public ActionOutcomeType getType() {
        return type;
    }

    public ActionOutcomeType getRestingOutcome() {
        return this.restingOutcome;
    }

    public boolean isOutcome(ActionOutcomeParameter actionOutcomeParameter) {
        return actionOutcomeParameters.contains(actionOutcomeParameter);
    }

    public boolean isPossessionChange() {
        return possessionChange;
    }
}
