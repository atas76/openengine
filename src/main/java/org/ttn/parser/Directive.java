package org.ttn.parser;

public class Directive {

    public enum Type {
        SET_PIECE_EXECUTION_BLOCK,
        POSSESSION_CHAIN_BLOCK,
        BALL_RECOVERY_BLOCK,
        ATTACK_CHAIN_BLOCK,
        PRESSURE_STATEMENT_BLOCK,
        TRANSITION_STATEMENT_BLOCK,
        POSSESSOR_DEFINITION,
        INDIRECT_OUTCOME,
        STANDARD,
        BREAK,
        DEFAULT_SET_PIECE_EXECUTION
    }

    private Type type;
    private String team;

    public Type getType() {
        return type;
    }

    public String getTeam() {
        return team;
    }

    public Directive(Type type, String team) {
        this.type = type;
        this.team = team;
    }
}
