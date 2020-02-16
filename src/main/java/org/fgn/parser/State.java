package org.fgn.parser;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static org.fgn.parser.StateContext.SP;
import static org.fgn.parser.StateContext.T;

public class State {

    private Coordinates space;
    private StateContext context = StateContext.FREE;
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

    void setContext(StateContext parameter) {
        this.context = parameter;
    }

    public StateContext getContext() {
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
