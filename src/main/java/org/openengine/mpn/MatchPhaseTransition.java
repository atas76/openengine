package org.openengine.mpn;

import org.mpn.State;

public interface MatchPhaseTransition {

    State getInitialState();
    State getEndState();
    int getDuration();
    State getGoalAttemptOutcome();
    boolean isPossessionChanged();
    Double getxG();
    State getDefaultEndState();
    String getTeamKey();
    Double getNextGoalProbability();
}
