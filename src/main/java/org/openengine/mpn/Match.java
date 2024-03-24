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
        this.matchEvents.getData().forEach(record -> {
            String commentary = getCommentary(record);
            if (!commentary.isBlank()) System.out.println(commentary);
        });
    }

    private String getCommentary(ProcessUnit record) {

        StringBuilder commentary = new StringBuilder();

        if (record instanceof Statement statement) {
            String initialState = MatchCommentary.stateMappings.get(statement.getInitialState());
            String teamName = teamNameMappings.get(statement.getTeamKey());
            commentary.append(statement.getStartTime().toString());
            commentary.append(" ");
            switch (statement.getInitialState()) {
                case KICK_OFF ->  {
                    commentary.append(initialState);
                    commentary.append(" for ");
                    commentary.append(teamName);
                }
                case POSSESSION -> {
                    commentary.append(teamName);
                    commentary.append(" have possession at pitch position ");
                    commentary.append(statement.getInitialPitchPosition());
                    commentary.append(" (");
                    commentary.append(MatchCommentary.pitchPositionMappings.get(statement.getInitialPitchPosition()));
                    commentary.append(")");
                }
                case ATTACK -> {
                    commentary.append(teamName);
                    commentary.append(" attacking from pitch position ");
                    commentary.append(statement.getInitialPitchPosition());
                    commentary.append(" (");
                    commentary.append(MatchCommentary.pitchPositionMappings.get(statement.getInitialPitchPosition()));
                    commentary.append(")");
                }
            }
            if (statement.getEndTime() != null
                    && statement.getEndTime().getAbsoluteTime() > statement.getStartTime().getAbsoluteTime()) {
                commentary.append("\n");
                commentary.append(statement.getEndTime().toString());
                commentary.append(" ");
                switch (statement.getEndState()) {
                    case PENALTY -> {
                        commentary.append("Penalty awarded to ");
                        commentary.append(teamName);
                    }
                }
            }
        } else if (record instanceof Directive) {

        }

        return commentary.toString();
    }
}
