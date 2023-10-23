package org.mpn;

import org.mpn.exceptions.UnknownStateException;

public enum State {
    KICK_OFF,
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
    ATTACKING_TRANSITION
    ;

    public static State createFromName(String name) throws UnknownStateException {
        return switch (name) {
            case "KickOff" -> KICK_OFF;
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
            default -> throw new UnknownStateException();
        };
    }
}
