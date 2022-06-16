package org.ttn.semantics;

import org.ttn.engine.rules.SetPiece;

public class SetPieceExecutionPhase implements MatchPhase {

    private SetPiece setPiece;

    public SetPieceExecutionPhase(SetPiece setPiece) {
        this.setPiece = setPiece;
    }

    public SetPiece getType() {
        return this.setPiece;
    }
}
