package org.ttn.semantics;

import org.ttn.engine.rules.SetPiece;

public class SetPieceExecutionPhase extends MatchPhase {

    private SetPiece setPiece;

    public SetPieceExecutionPhase(SetPiece setPiece, String team) {
        this.setPiece = setPiece;
        this.team = team;
    }

    public SetPiece getType() {
        return this.setPiece;
    }
}
