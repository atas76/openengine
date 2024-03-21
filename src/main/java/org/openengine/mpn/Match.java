package org.openengine.mpn;

import org.mpn.*;

import java.util.Map;

public class Match {

    private Dataset matchEvents;
    private Map<String, String> teamNameMappings = Map.of("L", "Liverpool", "T", "Tottenham");

    public Match() {
        this.matchEvents = Processable.loadData();
    }

    public void display() {
        this.matchEvents.getData().forEach(record -> System.out.println(getCommentary(record)));
    }

    private String getCommentary(ProcessUnit record) {

        StringBuilder commentary = new StringBuilder();

        if (record instanceof Statement statement) {
            String initialState = MatchCommentary.stateMappings.get(statement.getInitialState());
            if (initialState != null) {
                commentary.append(statement.getStartTime().toString());
                commentary.append(" ");
                commentary.append(initialState);
                commentary.append(" for ");
                commentary.append(teamNameMappings.get(statement.getTeamKey()));
            }
            /*
            switch (statement.getInitialState()) {
                case State.KICK_OFF -> commentary.append(MatchCommentary.stateMappings.get())
            }
             */
        } else if (record instanceof Directive) {

        }

        return commentary.isEmpty() ? record.toString() : commentary.toString();
    }
}
