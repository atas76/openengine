package org.fgn.parser;

import org.fgn.ontology.Coordinates;
import org.fgn.ontology.StateContextEnum;

import static java.util.Objects.nonNull;
import static org.fgn.ontology.StateContextEnum.SP;
import static org.fgn.ontology.StateContextEnum.T;

public class State {

    private Coordinates space;
    private StateContextEnum context = StateContextEnum.FREE;
    private boolean keepPossession = true;

    public State() {}

    public State(boolean keepPossession) {
        this.keepPossession = keepPossession;
    }

    public Coordinates getSpace() {
        return this.space;
    }

    public void setSpace(Coordinates space) {
        this.space = space;
    }

    void setContext(StateContextEnum parameter) {
        this.context = parameter;
    }

    public StateContextEnum getContext() {
        return this.context;
    }

    public boolean isSetPiece() {
        return nonNull(context) && SP.equals(context);
    }

    public boolean isSamePossesion() {
        return this.keepPossession;
    }

    public boolean isThrowIn() {
        return nonNull(context) && T.equals(context);
    }
}
