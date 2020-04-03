package org.fgn.parser;

import java.util.Objects;

public class Action {

    private String description;

    public Action(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Action)) return false;
        Action action = (Action) o;
        return description.equals(action.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
