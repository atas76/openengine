package org.fgn.parser;

public enum ActionOutcome {

    PST ("Post"), GS ("Goalkeeper save");

    private String description;

    ActionOutcome(String description) {
        this.description = description;
    }
}
