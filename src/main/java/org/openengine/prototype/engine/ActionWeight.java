package org.openengine.prototype.engine;

public class ActionWeight {

    private Action action;
    private Integer weight;

    public ActionWeight(Action action, Integer weight) {
        this.action = action;
        this.weight = weight;
    }

    public Action getAction() {
        return this.action;
    }

    public Integer getWeight() {
        return this.weight;
    }
}
