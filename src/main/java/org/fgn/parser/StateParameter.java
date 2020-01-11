package org.fgn.parser;

public enum StateParameter {

    SP("Set Piece"),
    T("Throw In");

    private String description;

    StateParameter(String description) {
        this.description = description;
    }
}
