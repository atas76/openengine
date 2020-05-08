package org.fgn.domain;

import java.util.Objects;

public class InState extends State {

    private Context.InState context;

    public InState(Context.InState stateContext) {
        this.context = stateContext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InState)) return false;
        if (!super.equals(o)) return false;
        InState inState = (InState) o;
        return context == inState.context;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), context);
    }
}
