package org.fgn.parser;

public enum StateContext {

    FREE("Free play"),
    KO("Kick-off"),
    SP("Set Piece"),
    T("Throw In");

    private String description;

    StateContext(String description) {
        this.description = description;
    }
}
