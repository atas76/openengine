package org.fgn.parser;

public enum StateContext {

    FREE("Free play"),
    KO("Kick-off"),
    F("Foul"), H("Handball"), SP("Set Piece"),
    T("Throw In"),
    G("Goal");

    private String description;

    StateContext(String description) {
        this.description = description;
    }
}
