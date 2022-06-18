package org.ttn.semantics;

import org.ttn.parser.output.Statement;

import java.util.ArrayList;
import java.util.List;

public abstract class MatchPhase {

    String team;
    private List<Statement> events = new ArrayList<>();

    public void addEvent(Statement event) {
        this.events.add(event);
    }

    public Statement getEventByIndex(int index) {
        return events.get(index);
    }

    public String getTeam() {
        return this.team;
    }
}
