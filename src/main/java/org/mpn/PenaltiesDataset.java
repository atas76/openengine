package org.mpn;

import java.util.List;

public class PenaltiesDataset extends Dataset {

    public PenaltiesDataset(List<ProcessUnit> data) {
        super(data);
    }

    public List<Statement> getGoalkeeperSavesList() {
        return listStateTransitionsByEndState(State.SAVE);
    }

    public List<Statement> getPostHitsList() {
        return listStateTransitionsByEndState(State.POST);
    }

    public List<Statement> getOffTargetShotsList() {
        return listStateTransitionsByEndState(State.OFF_TARGET);
    }

    public List<Statement> getGoalkeeperPossessionSaveList() {
        return listStateTransitionsByGoalAttemptOutcome(State.GOALKEEPER);
    }

    public List<Statement> getGoalAttemptsAfterShotsList() {
        return listStateTransitionsByGoalAttemptOutcome(State.GOAL_ATTEMPT);
    }

    public List<Statement> getDefensiveReboundsList() {
        return listStateTransitionsByGoalAttemptOutcome(State.TRANSITION);
    }

    public List<Statement> getCornersList() {
        return listStateTransitionsByGoalAttemptOutcome(State.CORNER);
    }

    public List<Statement> getAttackingEncroachmentsList() {
        return listStateTransitionsByGoalAttemptOutcome(State.ATTACKING_ENCROACHMENT);
    }

    public List<Statement> getDefensiveEncroachmentsList() {
        return listStateTransitionsByGoalAttemptOutcome(State.DEFENSIVE_ENCROACHMENT);
    }

    public List<Statement> getAttackingReboundsList() {
        return listStateTransitionsByGoalAttemptOutcome(State.ATTACK);
    }

    public List<Statement> getThrowInsList() {
        return listStateTransitionsByGoalAttemptOutcome(State.THROW_IN);
    }
}
