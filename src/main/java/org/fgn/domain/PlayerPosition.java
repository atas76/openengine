package org.fgn.domain;

public enum PlayerPosition {

    GK, OUTFIELD;

    public static PlayerPosition getDefault() {
        return OUTFIELD;
    }
}
