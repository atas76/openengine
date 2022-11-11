package org.openengine.prototype.engine;

public class ActionOutcome implements Outcome {

    private Coordinates coordinates;
    private boolean keepPossession;

    public ActionOutcome(Coordinates coordinates, boolean keepPossession) {
        this.coordinates = coordinates;
        this.keepPossession = keepPossession;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public boolean isKeepPossession() {
        return keepPossession;
    }

    public boolean isGoal() {
        return false;
    }
}
