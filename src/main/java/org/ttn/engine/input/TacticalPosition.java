package org.ttn.engine.input;

public interface TacticalPosition {

    enum X { Gkr, Gkd, D, M, AM, F }
    enum Y { R, RC, C, LC, L }

    X getX();
    Y getY();
    boolean isGoalkeeper();
}
