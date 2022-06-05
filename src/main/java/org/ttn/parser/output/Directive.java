package org.ttn.parser.output;

import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.rules.SetPiece;

public class Directive implements MatchDataElement {

    private final DirectiveType type;
    private String team;
    private SetPiece setPiece;
    private InPlayPhase phase;
    private Intention intention;
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

    public InPlayPhase getInPlayPhase() {
        return this.phase;
    }

    public Intention getIntention() {
        return this.intention;
    }

    public Directive(DirectiveType type) {
        this.type = type;
    }

    public Directive(InPlayPhase phase, String team) {
        this.type = DirectiveType.INPLAY_PHASE;
        this.phase = phase;
        this.team = team;
    }

    public Directive(Intention intention) {
        this.type = DirectiveType.INTENTION;
        this.intention = intention;
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

    public Directive(DirectiveType type, String team, TacticalPosition tacticalPosition) {
        this(type, team);
        this.tacticalPosition = tacticalPosition;
    }
}
