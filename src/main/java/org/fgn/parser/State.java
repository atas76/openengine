package org.fgn.parser;

import static java.util.Objects.nonNull;
import static org.fgn.parser.StateParameter.SP;

public class State extends DomainTerm {

    private Coordinates space;
    private StateParameter sp;

    public Coordinates getSpace() {
        return this.space;
    }

    void set(StateParameter parameter) {
        this.sp = parameter;
    }

    public boolean isSetPiece() {
        return nonNull(sp) && SP.equals(sp);
    }

    State(String description) {
        super(description);
        try {
            this.space = Coordinates.valueOf(description);
        } catch (IllegalArgumentException ex) {
            // TODO Will be handled properly with defined ontology
        }
    }
}
