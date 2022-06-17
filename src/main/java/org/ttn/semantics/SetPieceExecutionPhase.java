package org.ttn.semantics;

import org.ttn.engine.rules.SetPiece;

public class SetPieceExecutionPhase implements MatchPhase {

    private SetPiece setPiece;
    private String team;

    public SetPieceExecutionPhase(SetPiece setPiece, String team) {
        this.setPiece = setPiece;
        this.team = team;
    }

    public SetPiece getType() {
        return this.setPiece;
    }

    public String getTeam() {
        return this.team;
    }
}
