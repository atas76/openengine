package org.ttn.engine.agent;

import org.ttn.engine.space.PitchPosition;

import java.util.List;

public class Action {

    private final ActionType type;
    private final PitchPosition pitchPosition;
    private boolean firstTouch;
    private boolean openPass;

    public Action(ActionType type, PitchPosition pitchPosition, List<ActionParameter> actionParameters) {
        this.type = type;
        this.pitchPosition = pitchPosition;
        assignActionParameters(actionParameters);
    }

    private void assignActionParameters(List<ActionParameter> actionParameters) {
        actionParameters.forEach(param -> {
           switch(param) {
               case FIRST_TOUCH:
                   this.firstTouch = true;
                   break;
               case OPEN_PASS:
                   this.openPass = true;
                   break;
           }
        });
    }

    public boolean isFirstTouch() {
        return firstTouch;
    }

    public boolean isOpenPass() {
        return openPass;
    }

    public ActionType getType() {
        return type;
    }

    public PitchPosition getPitchPosition() {
        return pitchPosition;
    }
}
