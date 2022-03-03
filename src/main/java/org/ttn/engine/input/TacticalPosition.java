package org.ttn.engine.input;

import java.util.Arrays;

import static java.util.Objects.nonNull;

public interface TacticalPosition {

    enum X { Gkr, Gkd, D, M, AM, F }
    enum Y { R, RC, CR, C, CL, LC, L }
    enum Gk { Gkr, Gkd }

    X getX();
    Y getY();
    Gk getGk();

    static boolean isGoalkeeper(String token) {
        return Arrays.stream(Gk.values()).anyMatch(value -> value.toString().equals(token));
    }

    default boolean isGoalkeeper() {
        return nonNull(getGk());
    }
}
