package org.ttn.semantics;

import org.ttn.parser.output.InPlayPhaseType;

public class InPlayPhase extends MatchPhase {

    private InPlayPhaseType type;

    public InPlayPhase(InPlayPhaseType type, String team) {
        this.type = type;
        this.team = team;
    }

    public InPlayPhaseType getType() {
        return this.type;
    }
}
