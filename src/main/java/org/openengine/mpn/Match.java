package org.openengine.mpn;

import org.mpn.*;

import java.util.Map;

public class Match {

    private Dataset matchEvents;
    private Map<String, String> teamNameMappings = Map.of("L", "Liverpool", "T", "Tottenham");

    public Match() {
        this.matchEvents = Processable.loadData(false);
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
            PitchPosition initialPitchPosition = statement.getInitialPitchPosition();
            PitchPosition outcomePitchPosition = statement.getOutcomePitchPosition();
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
                    commentary.append(initialPitchPosition);
                    attachPitchPositionDescription(initialPitchPosition, commentary);
                }
                case ATTACK -> {
                    commentary.append(teamName);
                    commentary.append(" attacking from pitch position ");
                    commentary.append(initialPitchPosition);
                    attachPitchPositionDescription(initialPitchPosition, commentary);
                } case PENALTY -> {
                    commentary.append(teamName);
                    commentary.append(" player takes the penalty...");
                } case THROW_IN -> {
                    commentary.append(teamName);
                    commentary.append(" throw-in from pitch position ");
                    commentary.append(initialPitchPosition);
                    attachPitchPositionDescription(initialPitchPosition, commentary);
                } case TRANSITION -> {
                    commentary.append(teamName);
                    commentary.append(" in transition");
                    if (statement.getEndState() == State.POSSESSION && statement.isPossessionRetained()) {
                        commentary.append("...and they hold up the play");
                    }
                }
                case GOALKEEPER -> {
                    commentary.append(teamName);
                    commentary.append(" ");
                    commentary.append("goalkeeper resumes the ball in play");
                }
                case BUILDUP -> {
                    commentary.append(teamName);
                    commentary.append(" building up from their defence");
                }
                case PRESSING -> {
                    commentary.append(teamName);
                    commentary.append(" under pressure");
                    commentary.append(" from pitch position ");
                    commentary.append(initialPitchPosition);
                    attachPitchPositionDescription(initialPitchPosition, commentary);
                }
            }
            if (statement.getEndTime() != null) {
                commentary.append("\n");
                commentary.append(statement.getEndTime().toString());
                commentary.append(" ");
                switch (statement.getEndState()) {
                    case PENALTY -> {
                        commentary.append("Penalty kick for ");
                        commentary.append(teamName);
                    }
                    case CORNER -> {
                        commentary.append(teamName);
                        commentary.append(" win the corner");
                    }
                }
            }
            if (statement.getEndState().equals(State.GOAL)) {
                commentary.append("\n");
                commentary.append(statement.getStartTime().toString());
                commentary.append(" ");
                commentary.append(teamName);
                commentary.append(" score!");
            } else if (statement.getEndState().equals(State.GOALKEEPER) && !statement.isPossessionChanged()) {
                commentary.append("...ball back to the goalkeeper");
            }
            if (statement.isPossessionChanged()) {
                commentary.append(": possession lost at pitch position ");
                commentary.append(outcomePitchPosition);
                attachPitchPositionDescription(outcomePitchPosition, commentary);
            }
        } else if (record instanceof Directive) {
            System.out.println("----- " + record + " -----");
        }

        return commentary.toString();
    }

    private static void attachPitchPositionDescription(PitchPosition pitchPosition, StringBuilder commentary) {
        if (pitchPosition == null) return;
        commentary.append(" (");
        commentary.append(MatchCommentary.pitchPositionMappings.get(pitchPosition));
        commentary.append(")");
    }
}
