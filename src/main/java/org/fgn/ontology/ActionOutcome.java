package org.fgn.ontology;

public enum ActionOutcome {

    PST("Post"), GS("Goalkeeper save");

    private String description;

    ActionOutcome(String description) {
        this.description = description;
    }
}
