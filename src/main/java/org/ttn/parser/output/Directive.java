package org.ttn.parser.output;

import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.rules.SetPiece;

public class Directive implements Parsable {

    public enum Type { // TODO Split directive and statement types
        SET_PIECE_EXECUTION_BLOCK,
        POSSESSION_CHAIN_BLOCK,
        BALL_RECOVERY_BLOCK,
        ATTACK_CHAIN_BLOCK,
        BUILDUP_PRESSURE_BLOCK,
        TRANSITION_CHAIN_BLOCK,
        POSSESSOR_DEFINITION,
        INDIRECT_OUTCOME,
        STANDARD,
        TRIVIAL_POSSESSION_CHAIN,
        BREAK,
        DEFAULT_SET_PIECE_EXECUTION
    }

    private final Type type;
    private String team;
    private SetPiece setPiece;
    private TacticalPosition tacticalPosition;

    public SetPiece getSetPiece() {
        return setPiece;
    }

    public Type getType() {
        return type;
    }

    public String getTeam() {
        return team;
    }

    public TacticalPosition getTacticalPosition() {
        return tacticalPosition;
    }

    public Directive(Type type) {
        this.type = type;
    }

    public Directive(Type type, String team) {
        this(type);
        this.team = team;
    }

    public Directive(Type type, TacticalPosition tacticalPosition) {
        this(type);
        this.tacticalPosition = tacticalPosition;
    }

    public Directive(Type type, String team, SetPiece setPiece) {
        this(type, team);
        this.setPiece = setPiece;
    }
}
