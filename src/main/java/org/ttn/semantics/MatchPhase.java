package org.ttn.semantics;

import org.ttn.parser.output.Statement;

import java.util.ArrayList;
import java.util.List;

public abstract class MatchPhase {

    String team;
    private List<Statement> events = new ArrayList<>();
    private boolean flowBroken = false;

    public void addEvent(Statement event) {
        this.events.add(event);
    }

    public Statement getEventByIndex(int index) {
        return events.get(index);
    }

    public String getTeam() {
        return this.team;
    }

    public int getEventsNumber() {
        return this.events.size();
    }

    public void setFlowBreak() {
        this.flowBroken = true;
    }

    public boolean isFlowBroken() {
        return this.flowBroken;
    }
}
