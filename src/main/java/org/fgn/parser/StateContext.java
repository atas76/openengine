package org.fgn.parser;

public enum StateContext {

    FREE("Free play"),
    KO("Kick-off"),
    F("Foul"), H("Handball"), SP("Set Piece"),
    T("Throw In"), C("Corner kick"), GKo("Goal kick over the bar"),
    GS("Goalkeeper save"),
    G("Goal");

    private String description;

    StateContext(String description) {
        this.description = description;
    }
}
