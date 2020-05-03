package org.fgn.domain;

public enum BallPlay {

    CONTINUOUS, DISCRETE;

    public static BallPlay getDefault() {
        return CONTINUOUS;
    }
}
