package org.mpn;

import org.mpn.exceptions.UnknownStateException;

public enum State {
    KICK_OFF,
    FREEKICK,
    GOAL_KICK,
    FOUL,
    ATTACK,
    PENALTY,
    GOAL,
    CORNER,
    THROW_IN,
    TRANSITION,
    GOALKEEPER,
    BUILDUP,
    PRESSING,
    POSSESSION,
    DEFENSIVE_TRANSITION,
    ATTACKING_TRANSITION,
    ATTACKING_POSSESSION,
    GOAL_ATTEMPT,
    OFF_TARGET,
    LONG_BALL
    ;

    public static State createFromName(String name) throws UnknownStateException {
        return switch (name) {
            case "KickOff" -> KICK_OFF;
            case "FreeKick" -> FREEKICK;
            case "GoalKick" -> GOAL_KICK;
            case "Foul" -> FOUL;
            case "Attack" -> ATTACK;
            case "Penalty" -> PENALTY;
            case "Goal" -> GOAL;
            case "Corner" -> CORNER;
            case "ThrowIn" -> THROW_IN;
            case "Transition" -> TRANSITION;
            case "Goalkeeper" -> GOALKEEPER;
            case "Buildup" -> BUILDUP;
            case "Pressing" -> PRESSING;
            case "Possession" -> POSSESSION;
            case "DefensiveTransition" -> DEFENSIVE_TRANSITION;
            case "AttackingTransition" -> ATTACKING_TRANSITION;
            case "AttackingPossession" -> ATTACKING_POSSESSION;
            case "GoalAttempt" -> GOAL_ATTEMPT;
            case "OffTarget" -> OFF_TARGET;
            case "LongBall" -> LONG_BALL;
            default -> throw new UnknownStateException();
        };
    }
}
