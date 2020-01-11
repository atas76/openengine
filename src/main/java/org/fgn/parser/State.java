package org.fgn.parser;

import static java.util.Objects.nonNull;
import static org.fgn.parser.StateParameter.SP;
import static org.fgn.parser.StateParameter.T;

public class State extends DomainTerm {

    private Coordinates space;
    private Coordinates spaceParameter;
    private StateParameter stateParameter;
    private boolean keepPossession = true;

    public Coordinates getSpace() {
        return this.space;
    }

    public Coordinates getSpaceParameter() {
        return this.spaceParameter;
    }

    void set(StateParameter parameter) {
        this.stateParameter = parameter;
    }

    public boolean isSetPiece() {
        return nonNull(stateParameter) && SP.equals(stateParameter);
    }

    public boolean isSamePossesion() {
        return this.keepPossession;
    }

    public boolean isThrowIn() {
        return nonNull(stateParameter) && T.equals(stateParameter);
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
