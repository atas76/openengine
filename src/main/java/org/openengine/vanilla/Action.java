package org.openengine.vanilla;

public class Action {

    private Player actor;
    private Player target;
    private ActionType type;

    public Action() {}

    public Action(Player actor, Player target, ActionType type) {
        this.actor = actor;
        this.target = target;
        this.type = type;
    }

    public ActionType getType() {
        return type;
    }

    public Player getTarget() {
        return target;
    }
}
