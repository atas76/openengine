package org.ttn.engine.input;

public interface TacticalPosition {

    enum X { Gkr, Gkd, D, M, AM, F }
    enum Y { R, RC, CR, C, CL, LC, L }

    X getX();
    Y getY();
    boolean isGoalkeeper();
}
