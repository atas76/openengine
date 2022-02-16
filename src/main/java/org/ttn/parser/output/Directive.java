package org.ttn.parser.output;

import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.rules.SetPiece;

public class Directive implements Parsable {

    private final DirectiveType type;
    private String team;
    private SetPiece setPiece;
    private TacticalPosition tacticalPosition;

    public SetPiece getSetPiece() {
        return setPiece;
    }

    public DirectiveType getType() {
        return type;
    }

    public String getTeam() {
        return team;
    }

    public TacticalPosition getTacticalPosition() {
        return tacticalPosition;
    }

    public Directive(DirectiveType type) {
        this.type = type;
    }

    public Directive(DirectiveType type, String team) {
        this(type);
        this.team = team;
    }

    public Directive(DirectiveType type, TacticalPosition tacticalPosition) {
        this(type);
        this.tacticalPosition = tacticalPosition;
    }

    public Directive(DirectiveType type, String team, SetPiece setPiece) {
        this(type, team);
        this.setPiece = setPiece;
    }
}
