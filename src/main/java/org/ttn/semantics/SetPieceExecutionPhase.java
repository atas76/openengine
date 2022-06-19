package org.ttn.semantics;

import org.ttn.engine.rules.SetPiece;

public class SetPieceExecutionPhase extends MatchPhase {

    private SetPiece type;

    public SetPieceExecutionPhase(SetPiece type, String team) {
        this.type = type;
        this.team = team;
    }

    public SetPiece getType() {
        return this.type;
    }
}
