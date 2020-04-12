package org.fgn.parser;

import org.fgn.ontology.BaseObject;
import org.fgn.ontology.Coordinates;
import org.fgn.ontology.StateContext;

import static java.util.Objects.nonNull;

public class State {

    private Coordinates space;
    private BaseObject context = StateContext.getDefault();
    private boolean keepPossession = true;

    // TODO include as labels/base entities in ontology
    private static final String SET_PIECE = "SP";
    private static final String THROW_IN = "T";

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

    void setContext(BaseObject entity) {
        this.context = entity;
    }

    public BaseObject getContext() {
        return this.context;
    }

    public boolean isSetPiece() {
        return nonNull(context) && SET_PIECE.equals(context.getId());
    }

    public boolean isSamePossesion() {
        return this.keepPossession;
    }

    public boolean isThrowIn() {
        return nonNull(context) && THROW_IN.equals(context.getId());
    }
}
