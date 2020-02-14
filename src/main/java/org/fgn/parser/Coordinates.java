package org.fgn.parser;

/*
    about pseudo-coordinates: they are technically also states of play, but coordinates dominate context in in-states
 */
public enum Coordinates {
    GK, // pseudo-coordinate
    GD, Dg,Dp,
    D,Dw,
    DM,DMw,
    M, Mw,
    CK, // pseudo-coordinate
    AM, AMw,
    A,Aw,
    Awp,
    Ap, Apc, Apw;
}
