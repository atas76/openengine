package org.openfootie.openengine.engine;

public class ShotOutcomeWeight extends ActionOutcomeWeight {

    private boolean goal;
    private ShotOutcome shotOutcome;
    private double chanceRating;

    public Outcome getOutcome() {
        return new ShotOutcomeState(this.coordinates, this.keepPossession, this.goal);
    }

    public ShotOutcomeWeight(boolean goal, ShotOutcome shotOutcome, double chanceRating,
                             Coordinates coordinates, boolean keepPossession, int weight) {
        super(coordinates, keepPossession, weight);
        this.goal = goal;
        this.shotOutcome = shotOutcome;
        this.chanceRating = chanceRating;
    }

    public boolean isGoal() {
        return goal;
    }

    public ShotOutcome getShotOutcome() {
        return shotOutcome;
    }

    public double getChanceRating() {
        return chanceRating;
    }
}
