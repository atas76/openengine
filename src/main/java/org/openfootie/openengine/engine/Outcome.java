package org.openfootie.openengine.engine;

public interface Outcome {

    Coordinates getCoordinates();
    boolean isKeepPossession();
    boolean isGoal();
}
