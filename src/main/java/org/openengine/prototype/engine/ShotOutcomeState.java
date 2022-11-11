package org.openengine.prototype.engine;

public class ShotOutcomeState implements Outcome {

    private Coordinates coordinates;
    private boolean keepPossession;
    private boolean goal;

    public ShotOutcomeState(Coordinates coordinates, boolean keepPossession, boolean goal) {
        this.coordinates = coordinates;
        this.keepPossession = keepPossession;
        this.goal = goal;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public boolean isKeepPossession() {
        return keepPossession;
    }

    public boolean isGoal() {
        return goal;
    }
}
