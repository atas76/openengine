package org.mpn;

import org.mpn.exceptions.UnknownStateException;

public enum State {
    KICK_OFF,
    FREEKICK,
    GOAL_ATTEMPT_FREEKICK,
    GOAL_KICK,
    FOUL,
    OFFSIDE,
    ATTACK,
    COUNTER_ATTACK,
    PENALTY,
    GOAL,
    CORNER,
    THROW_IN,
    TRANSITION,
    GOALKEEPER,
    BUILDUP,
    FAST_BREAK,
    PRESSING,
    POSSESSION,
    RUNNING_POSSESSION,
    DEFENSIVE_TRANSITION,
    ATTACKING_TRANSITION,
    ATTACKING_POSSESSION,
    GOAL_ATTEMPT,
    GOAL_ATTEMPT_OUTCOME,
    OFF_TARGET,
    BLOCK,
    POST,
    SAVE,
    LONG_BALL,
    ATTACKING_ENCROACHMENT,
    DEFENSIVE_ENCROACHMENT
    ;

    public static State createFromName(String name) throws UnknownStateException {
        return switch (name) {
            case "KickOff" -> KICK_OFF;
            case "FreeKick" -> FREEKICK;
            case "GAFreeKick" -> GOAL_ATTEMPT_FREEKICK;
            case "GoalKick" -> GOAL_KICK;
            case "Foul" -> FOUL;
            case "Offside" -> OFFSIDE;
            case "Attack" -> ATTACK;
            case "CounterAttack" -> COUNTER_ATTACK;
            case "Penalty" -> PENALTY;
            case "Goal" -> GOAL;
            case "Corner" -> CORNER;
            case "ThrowIn" -> THROW_IN;
            case "Transition" -> TRANSITION;
            case "Goalkeeper" -> GOALKEEPER;
            case "Buildup" -> BUILDUP;
            case "FastBreak" -> FAST_BREAK;
            case "Pressing" -> PRESSING;
            case "Possession" -> POSSESSION;
            case "RunningPossession" -> RUNNING_POSSESSION;
            case "DefensiveTransition" -> DEFENSIVE_TRANSITION;
            case "AttackingTransition" -> ATTACKING_TRANSITION;
            case "AttackingPossession" -> ATTACKING_POSSESSION;
            case "GoalAttempt" -> GOAL_ATTEMPT;
            case "OffTarget" -> OFF_TARGET;
            case "Block" -> BLOCK;
            case "Post" -> POST;
            case "Save" -> SAVE;
            case "LongBall" -> LONG_BALL;
            case "AttackingEncroachment" -> ATTACKING_ENCROACHMENT;
            case "DefensiveEncroachment" -> DEFENSIVE_ENCROACHMENT;
            default -> throw new UnknownStateException();
        };
    }
}
