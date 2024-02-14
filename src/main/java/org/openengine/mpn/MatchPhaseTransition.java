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
    /*
    Having a flag for goals (from successful attempts) is probably redundant and a proven source of bugs.
    For example, we opted to use an intermediate GOAL_ATTEMPT_OUTCOME state as a result of the dynamic calculation
    of goal attempts outcomes, instead of a flag. Rather for using a flag for goals - for consistency among other things -
    we should just update the end state of the transition and take only transitions with GOAL_ATTEMPT_OUTCOME as initial
    state into account for counting goals (as we have to do anyway, it is just we won't need to refer to a redundant flag)
     */
    boolean isGoal();  // TODO this is probably bad practice, since we just generate the transitions dynamically
    String getTeamKey();
}
