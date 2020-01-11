package org.fgn.parser;

import static java.util.Objects.nonNull;
import static org.fgn.parser.StateParameter.SP;

public class State extends DomainTerm {

    private Coordinates space;
    private StateParameter sp;
    private boolean keepPossession = true;

    public Coordinates getSpace() {
        return this.space;
    }

    void set(StateParameter parameter) {
        this.sp = parameter;
    }

    public boolean isSetPiece() {
        return nonNull(sp) && SP.equals(sp);
    }

    public boolean isSamePossesion() {
        return this.keepPossession;
    }

    State(String description) {
        this(description, true);
    }

    State(String description, boolean keepPossession) {
        super(description);
        this.keepPossession = keepPossession;
        try {
            this.space = Coordinates.valueOf(description);
        } catch (IllegalArgumentException ex) {
            // TODO Will be handled properly with defined ontology
        }
    }
}
