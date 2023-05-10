package org.openengine.vanilla;

public class Action {

    private Player actor;
    private Player target;
    private ActionType type;
    private double geometryFactor = 1.0;

    public Action() {}

    public Action(Player actor, Player target, ActionType type) {
        this.actor = actor;
        this.target = target;
        this.type = type;
    }

    public Action(Player actor, Player target, ActionType type, double geometryFactor) {
        this(actor, target, type);
        this.geometryFactor = geometryFactor;
    }

    public ActionType getType() {
        return type;
    }

    public Player getTarget() {
        return target;
    }

    public double getGeometryFactor() {
        return geometryFactor;
    }

    @Override
    public String toString() {
        return "Action{" +
                "actor=" + actor.getName() +
                ", target=" + (target != null ? target.getName() : "none") +
                ", type=" + type +
                '}';
    }
}
