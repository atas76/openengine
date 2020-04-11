package org.fgn.ontology;

public enum StateContextEnum {

    FREE("Free play"),
    KO("Kick-off"),
    F("Foul"), H("Handball"), SP("Set Piece"), O("Offside"),
    T("Throw In"), C("Corner kick"),
    GKoo("Goal kick way over the bar"), GKo("Goal kick over the bar"), GK("Goal kick"),
    HD("Header"), FT("First touch"),
    // GS("Goalkeeper save"),
    G("Goal");

    private String description;

    StateContextEnum(String description) {
        this.description = description;
    }
}
