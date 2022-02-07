package org.ttn.parser;

import org.ttn.engine.rules.SetPiece;

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
    private SetPiece setPiece;

    public void setSetPiece(SetPiece setPiece) {
        this.setPiece = setPiece;
    }

    public SetPiece getSetPiece() {
        return setPiece;
    }

    public Type getType() {
        return type;
    }

    public String getTeam() {
        return team;
    }

    public Directive(Type type) {
        this.type = type;
    }

    public Directive(Type type, String team) {
        this(type);
        this.team = team;
    }

    public Directive(Type type, String team, SetPiece setPiece) {
        this(type, team);
        this.setPiece = setPiece;
    }
}
