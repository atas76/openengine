package org.openengine.mpn;

import org.mpn.*;

import java.util.Map;

import static org.mpn.PitchPosition.GD;
import static org.mpn.PitchPosition.GK;

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
                    if (initialPitchPosition != GD) {
                        commentary.append(" have possession at pitch position ");
                        commentary.append(initialPitchPosition);
                        attachPitchPositionDescription(initialPitchPosition, commentary);
                    } else {
                        commentary.append(" goalkeeper has the ball outside the penalty area");
                    }
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
                    attachThrowInPositionDescription(initialPitchPosition, commentary);
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
                    commentary.append(" building up from their ");
                    if (initialPitchPosition == GK ||initialPitchPosition == GD) {
                        commentary.append("goalkeeper");
                    } else {
                        commentary.append("defence");
                    }
                }
                case PRESSING -> {
                    commentary.append(teamName);
                    if (initialPitchPosition != GK) {
                        commentary.append(" under pressure");
                        commentary.append(" at pitch position ");
                        commentary.append(initialPitchPosition);
                        attachPitchPositionDescription(initialPitchPosition, commentary);
                    } else {
                        commentary.append(" goalkeeper under pressure");
                    }
                }
                case CORNER -> {
                    commentary.append(teamName);
                    commentary.append(" take the corner...");
                    if (statement.getEndState() == State.POSSESSION && statement.isPossessionRetained()) {
                       if (outcomePitchPosition == GD) {
                           commentary.append("ball back to their goalkeeper (outside the penalty area)");
                       }
                    }
                }
                case GOAL_ATTEMPT -> {
                    commentary.append(teamName);
                    commentary.append(" goal attempt from pitch position ");
                    commentary.append(initialPitchPosition);
                    attachPitchPositionDescription(initialPitchPosition, commentary);
                    appendGoalAttemptOutcomeCommentary(statement, commentary, teamName, outcomePitchPosition);
                }
                case GOAL_KICK -> {
                    commentary.append(teamName);
                    commentary.append(" take the goal kick");
                }
                case FREEKICK -> {
                    commentary.append(teamName);
                    commentary.append(" take the free kick from position ");
                    commentary.append(initialPitchPosition);
                    attachPitchPositionDescription(initialPitchPosition, commentary);
                }
                case GOAL_ATTEMPT_FREEKICK -> {
                    commentary.append(teamName);
                    commentary.append(" goal attempt from the free kick");
                    appendGoalAttemptOutcomeCommentary(statement, commentary, teamName, outcomePitchPosition);
                }
                case COUNTER_ATTACK -> {
                    commentary.append("Counter attack for ");
                    commentary.append(teamName);
                }
                case FAST_BREAK -> {
                    commentary.append(teamName);
                    commentary.append(" on the fast break");
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
                    case THROW_IN -> {
                        if (statement.isPossessionRetained()) {
                            commentary.append(teamName);
                            commentary.append(" win a throw-in at pitch position ");
                            commentary.append(outcomePitchPosition);
                            attachPitchPositionDescription(outcomePitchPosition, commentary);
                        } else {
                            commentary.append(teamName);
                            commentary.append(" concede a throw-in");
                        }
                    }
                    case FOUL -> {
                        commentary.append(teamName);
                        if (!statement.isPossessionChanged()) {
                            commentary.append(" win a foul at pitch position ");
                            commentary.append(outcomePitchPosition);
                            attachPitchPositionDescription(outcomePitchPosition, commentary);
                        } else {
                            commentary.append(" concede an offensive foul at pitch position ");
                            commentary.append(outcomePitchPosition);
                            attachPossessionChangePitchPositionDescription(outcomePitchPosition, commentary);
                        }
                    }
                    case GOAL_KICK -> {
                        if (!statement.isPossessionRetained()) {
                            commentary.append("Ball out for a goal kick");
                        }
                    }
                    case OFFSIDE -> {
                        if (statement.isPossessionChanged()) {
                            commentary.append(teamName);
                            commentary.append(" are offside");
                        }
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
            if (statement.isPossessionChanged()
                    && statement.getEndTime() == null
                    && statement.getInitialState() != State.GOAL_ATTEMPT) {
                if (outcomePitchPosition == GK) {
                    commentary.append(": ball falls to opponent goalkeeper");
                } else {
                    if (statement.getEndState().equals(State.GOALKEEPER)) {
                        commentary.append("...opponent goalkeeper takes hold of the ball");
                    } else {
                        commentary.append(": possession lost at pitch position ");
                        commentary.append(outcomePitchPosition);
                        attachPossessionChangePitchPositionDescription(outcomePitchPosition, commentary);
                    }
                }
            }
        } else if (record instanceof Directive) {
            System.out.println("----- " + record + " -----");
        }

        return commentary.toString();
    }

    private static void appendGoalAttemptOutcomeCommentary(
            Statement statement, StringBuilder commentary, String teamName, PitchPosition outcomePitchPosition) {
        switch (statement.getEndState()) {
            case OFF_TARGET -> commentary.append("...off target");
            case BLOCK -> {
                commentary.append(". Attempt is blocked...");
                switch (statement.getGoalAttemptOutcome()) {
                    case CORNER -> commentary.append("corner.");
                    case ATTACK, POSSESSION -> appendReboundCommentary(commentary, teamName, outcomePitchPosition);
                    case GOALKEEPER -> commentary.append("goalkeeper has the ball");
                }
            }
            case SAVE -> {
                commentary.append("...saved by the goalkeeper. ");
                switch (statement.getGoalAttemptOutcome()) {
                    case CORNER -> commentary.append("Corner.");
                    case ATTACK -> appendReboundCommentary(commentary, teamName, outcomePitchPosition);
                }
            }
        }
    }

    private static void appendReboundCommentary(StringBuilder commentary, String teamName, PitchPosition outcomePitchPosition) {
        commentary.append(teamName);
        commentary.append(" on the rebound at pitch position ");
        commentary.append(outcomePitchPosition);
        attachPitchPositionDescription(outcomePitchPosition, commentary);
    }

    private static void attachPitchPositionDescription(PitchPosition pitchPosition, StringBuilder commentary) {
        if (pitchPosition == null) return;
        commentary.append(" (");
        commentary.append(MatchCommentary.pitchPositionMappings.get(pitchPosition));
        commentary.append(")");
    }

    private static void attachThrowInPositionDescription(PitchPosition pitchPosition, StringBuilder commentary) {
        if (pitchPosition == null) return;
        commentary.append(" (");
        String pitchPositionCommentary = MatchCommentary.throwInPositionMappings.get(pitchPosition);
        commentary.append(pitchPositionCommentary != null ?
                pitchPositionCommentary : MatchCommentary.pitchPositionMappings.get(pitchPosition));
        commentary.append(")");
    }

    private static void attachPossessionChangePitchPositionDescription(PitchPosition pitchPosition,
                                                                       StringBuilder commentary) {
        if (pitchPosition == null) return;
        commentary.append(" (");
        String pitchPositionCommentary = MatchCommentary.possessionChangePitchPositionMappings.get(pitchPosition);
        commentary.append(pitchPositionCommentary != null ?
                pitchPositionCommentary : MatchCommentary.pitchPositionMappings.get(pitchPosition));
        commentary.append(")");
    }
}
