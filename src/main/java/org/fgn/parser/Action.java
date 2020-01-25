package org.fgn.parser;

public class Action {

    private String description;

    public Action(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
