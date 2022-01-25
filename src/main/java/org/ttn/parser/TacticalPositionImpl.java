package org.ttn.parser;

import org.ttn.engine.input.TacticalPosition;

public class TacticalPositionImpl implements TacticalPosition {

    private X x;
    private Y y;

    public TacticalPositionImpl(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public X getX() {
        return this.x;
    }

    @Override
    public Y getY() {
        return this.y;
    }
}
