package org.openengine.openfootie;

import java.util.Random;

import static org.openengine.openfootie.MatchEvent.GOAL;

public record GoalAttemptOutcome(double xG, MatchDataElementType unsuccessfulOutcome)
        implements MatchDataElementType {

    public MatchDataElementType finalOutcome() {
        return new Random().nextDouble() <= xG ? GOAL : unsuccessfulOutcome;
    }
}
