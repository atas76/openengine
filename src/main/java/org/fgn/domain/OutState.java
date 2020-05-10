package org.fgn.domain;

import java.util.Objects;

public class OutState extends State {

    private ActionOutcome actionOutcome;
    private Context.OutState context;
    private Possession possession = Possession.getDefault();

    public OutState() {}

    public OutState(Context.OutState stateContext) {
        this.context = stateContext;
    }

    public OutState(Context.Coordinate coordinateContext, Context.OutState stateContext) {
        this.coordinates = new Coordinates(coordinateContext);
        this.context = stateContext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutState)) return false;
        if (!super.equals(o)) return false;
        OutState outState = (OutState) o;
        return actionOutcome == outState.actionOutcome &&
                context == outState.context &&
                possession == outState.possession;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), actionOutcome, context, possession);
    }
}