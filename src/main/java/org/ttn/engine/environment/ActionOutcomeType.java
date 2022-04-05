package org.ttn.engine.environment;

public enum ActionOutcomeType {
    HANDBALL("H"),
    GOAL("G"),
    CORNER("C"),
    THROW_IN("T"),
    GOAL_KICK("GK"),
    FOUL("F"),
    OFFSIDE("O");

    private final String name;

    ActionOutcomeType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
