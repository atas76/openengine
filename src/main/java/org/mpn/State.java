package org.mpn;

import org.mpn.exceptions.UnknownStateException;

public enum State {
    KICK_OFF,
    FREEKICK,
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
    OFF_TARGET,
    BLOCK,
    SAVE,
    LONG_BALL
    ;

    public static State createFromName(String name) throws UnknownStateException {
        return switch (name) {
            case "KickOff" -> KICK_OFF;
            case "FreeKick" -> FREEKICK;
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
            case "Save" -> SAVE;
            case "LongBall" -> LONG_BALL;
            default -> throw new UnknownStateException();
        };
    }
}
