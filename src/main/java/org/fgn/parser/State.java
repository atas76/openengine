package org.fgn.parser;

import org.fgn.schema.BaseObject;
import org.fgn.schema.CoordinateObject;
import org.fgn.domain.StateContext;

import static java.util.Objects.nonNull;

public class State {

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

    @Override
    public String toString() {

        StringBuilder retVal = new StringBuilder();
        retVal.append("{");
        if (space != null) {
            retVal.append("space: ").append(space.toString());
            retVal.append(", ");
        }
        retVal.append("context: ").append(context.toString());
        if (isSetPiece()) {
            retVal.append(", ");
            retVal.append("set piece");
        }
        if (isThrowIn()) {
            retVal.append(", ");
            retVal.append("throw in");
        }
        if (!isSamePossesion()) {
            retVal.append(", ");
            retVal.append("possession change");
        }
        retVal.append("}");

        return retVal.toString();

    }
}
