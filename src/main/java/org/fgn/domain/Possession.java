package org.fgn.domain;

public enum Possession {

    OWN, OPPONENT;

    public static Possession getDefault() {
        return OWN;
    }
}
