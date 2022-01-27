package org.ttn.engine.agent;

import java.util.ArrayList;
import java.util.List;

public class Action {

    private final ActionType type;
    List<ActionParameter> actionParameters = new ArrayList<>();

    public Action(ActionType type, List<ActionParameter> actionParameters) {
        this.type = type;
        this.actionParameters = actionParameters;
    }

    public Action(ActionType type) {
        this.type = type;
    }

    public boolean isFirstTouch() {
        return this.actionParameters.contains(ActionParameter.FIRST_TOUCH);
    }

    public boolean isOpenPass() {
        return this.actionParameters.contains(ActionParameter.OPEN_PASS);
    }

    public ActionType getType() {
        return type;
    }

}
