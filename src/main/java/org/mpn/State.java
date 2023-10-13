package org.mpn;

public enum State {
    KICK_OFF, ATTACK, PENALTY;

    public static State createFromName(String name) {
        return switch (name) {
            case "KickOff" -> KICK_OFF;
            case "Attack" -> ATTACK;
            case "Penalty" -> PENALTY;
            default -> null;
        };
    }
}
