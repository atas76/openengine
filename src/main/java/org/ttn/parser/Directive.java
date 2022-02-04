package org.ttn.parser;

import org.ttn.parser.exceptions.ParserException;

public class Directive {

    public enum Type {
        SET_PIECE_EXECUTION_BLOCK,
        POSSESSION_STATEMENT_BLOCK,
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
