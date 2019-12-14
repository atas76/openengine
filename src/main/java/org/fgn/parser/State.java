package org.fgn.parser;

public class State {

    private String description;

    public State(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
