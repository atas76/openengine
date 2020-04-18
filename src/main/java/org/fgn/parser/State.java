package org.fgn.parser;

import org.fgn.ontology.BaseObject;
import org.fgn.ontology.CoordinateObject;
import org.fgn.ontology.CoordinatesEnum;
import org.fgn.ontology.StateContext;

import static java.util.Objects.nonNull;

public class State {

    private CoordinatesEnum spaceEnum;
    private CoordinateObject space;

    private BaseObject context = StateContext.getDefault();
    private boolean keepPossession = true;

    // TODO include as labels/base entities in ontology
    private static final String SET_PIECE = "SP";
    private static final String THROW_IN = "T";

    public State() {}

    public State(boolean keepPossession) {
        this.keepPossession = keepPossession;
    }

    public CoordinatesEnum getSpaceEnum() {
        return this.spaceEnum;
    }

    public void setSpaceEnum(CoordinatesEnum space) {
        this.spaceEnum = space;
    }

    public CoordinateObject getSpace() {
        return space;
    }

    public void setSpace(CoordinateObject space) {
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
