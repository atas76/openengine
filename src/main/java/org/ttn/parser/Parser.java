package org.ttn.parser;

import org.ttn.engine.agent.Action;
import org.ttn.engine.agent.ActionType;
import org.ttn.engine.agent.ActionParameter;
import org.ttn.engine.environment.ActionOutcome;
import org.ttn.engine.environment.OutcomeType;
import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.rules.SetPiece;
import org.ttn.engine.space.PitchPosition;
import org.ttn.parser.exceptions.ParserException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import static java.util.Map.entry;
import static org.ttn.engine.agent.ActionParameter.FIRST_TOUCH;
import static org.ttn.engine.agent.ActionParameter.OPEN_PASS;
import static org.ttn.engine.environment.OutcomeType.GOAL;
import static org.ttn.engine.environment.OutcomeType.HANDBALL;
import static org.ttn.parser.Statement.Type.*;

public class Parser {

    private final Set<String> STATEMENT_QUALIFIERS = Set.of("=>", ":");
    private final Set<String> OUTCOME_DELIMITERS = Set.of("=>", ">>>");
    private final Set<String> ACTION_DELIMITERS = Set.of("=>", "->");
    private final Set<Statement.Type> DIRECTIVE_STATEMENT_TYPES = Set.of(BREAK, POSSESSOR_DEFINITION);

    private enum Keyword {
        SET, POSSESSION, BREAK, PRESSURE, POSSESSOR
    }

    private static final Map<Keyword, Statement.Type> keywordMapping = Map.ofEntries(
            entry(Keyword.SET, SP_EXECUTION),
            entry(Keyword.POSSESSION, POSSESSION_STATEMENT_BLOCK),
            entry(Keyword.BREAK, BREAK),
            entry(Keyword.PRESSURE, PRESSURE_STATEMENT_BLOCK),
            entry(Keyword.POSSESSOR, POSSESSOR_DEFINITION));

    private static final Map<String, SetPiece> setPieceMapping = Map.ofEntries(
            entry("Kickoff", SetPiece.KICK_OFF),
            entry("Penalty", SetPiece.PENALTY),
            entry("ThrowIn", SetPiece.THROW_IN));

    private static final Map<String, ActionParameter> actionParameterMapping = Map.ofEntries(
            entry("Open", OPEN_PASS),
            entry("FT", FIRST_TOUCH));

    private static final Map<String, OutcomeType> actionOutcomeType = Map.ofEntries(
            entry("H", HANDBALL),
            entry("G", GOAL));

    private final List<String> tokens;
    private int index = 0;

    private static final Pattern numericMinutesPattern = Pattern.compile("\\d{2}");

    public Parser(List<String> tokens) {
        this.tokens = tokens;
    }

    private String peekNextToken() {
        return tokens.get(index);
    }

    private void nextToken() {
        ++index;
    }

    private boolean hasNextToken() {
        return index < tokens.size();
    }

    private String readNextToken() {
        return tokens.get(index++);
    }

    private void expectToken(String token) throws ParserException {
        if (!token.equals(tokens.get(index++))) {
            throw new ParserException("Expected: " + token);
        }
    }

    private Keyword expectKeyword() throws ParserException {
        String token = tokens.get(index++);
        switch (token) {
            case "set":
                return Keyword.SET;
            case "possession":
                return Keyword.POSSESSION;
            case "pressure":
                return Keyword.PRESSURE;
            case "break":
                return Keyword.BREAK;
            case "possessor":
                return Keyword.POSSESSOR;
            default:
                throw new ParserException("Keyword expected");
        }
    }

    public Statement parse() throws ParserException {

        Statement statement = new Statement();
        String token = peekNextToken();

        if (numericMinutesPattern.matcher(token).matches()) {
            parseTime(statement);
            token = peekNextToken();
        } else if (!STATEMENT_QUALIFIERS.contains(token)) {
            throw new ParserException("Invalid time format");
        }

        switch(token) {
            case "=>":
                nextToken();
                statement.setTacticalPositionX(TacticalPosition.X.valueOf(readNextToken()));
                statement.setTacticalPositionY(TacticalPosition.Y.valueOf(readNextToken()));
                expectToken("@");
                statement.setActionOutcome(new ActionOutcome(PitchPosition.valueOf(readNextToken())));
                break;
            case ":":
                nextToken();
                statement.setType(keywordMapping.get(expectKeyword()));
                if (POSSESSOR_DEFINITION.equals(statement.getType())) {
                    statement.setTacticalPositionX(TacticalPosition.X.valueOf(readNextToken()));
                    statement.setTacticalPositionY(TacticalPosition.Y.valueOf(readNextToken()));
                }
                if (hasNextToken()) {
                    statement.setTeam(readNextToken());
                } else {
                    if (!DIRECTIVE_STATEMENT_TYPES.contains(statement.getType()))
                        throw new ParserException("Team expected in block definition");
                }
                if (hasNextToken()) {
                    expectToken(":");
                    statement.setSetPiece(setPieceMapping.get(readNextToken()));
                }
                break;
            default:
                PitchPosition actionPitchPosition = PitchPosition.valueOf(token);
                nextToken();
                String actionDelimiterToken = readNextToken();
                if (!ACTION_DELIMITERS.contains(actionDelimiterToken)) {
                    throw new ParserException("Action delimiter expected");
                }
                if ("->".equals(actionDelimiterToken)) {
                    parseAction(statement, actionPitchPosition);
                }

                if ("=>".equals(actionDelimiterToken)) {
                    statement.setType(DEFAULT_EXECUTION);
                } else {
                    parseOutcomeDelimiter(statement);
                }

                String outcomeFirstToken = readNextToken();

                if ("G".equals(outcomeFirstToken)) {
                    statement.setActionOutcome(new ActionOutcome(actionOutcomeType.get(outcomeFirstToken)));
                } else {
                    parseSpaceBoundOutcome(statement, outcomeFirstToken);
                }
        }

        return statement;
    }

    private void parseOutcomeDelimiter(Statement statement) throws ParserException {
        String outcomeDelimiterToken = readNextToken();
        if (!OUTCOME_DELIMITERS.contains(outcomeDelimiterToken)) {
            throw new ParserException("Outcome delimiter expected");
        }
        statement.setType(">>>".equals(outcomeDelimiterToken) ? INDIRECT_OUTCOME : STANDARD);
    }

    private void parseSpaceBoundOutcome(Statement statement, String outcomeToken) throws ParserException {

        if ("!".equals(outcomeToken)) {
            statement.setBallPossessionChange(true);
            outcomeToken = readNextToken();
        }

        statement.setTacticalPositionX(TacticalPosition.X.valueOf(outcomeToken));

        if (!"Gkr".equals(outcomeToken)) {
            statement.setTacticalPositionY(TacticalPosition.Y.valueOf(readNextToken()));
            expectToken("@");

            PitchPosition outcomePitchPosition = PitchPosition.valueOf(readNextToken());
            if (hasNextToken()) {
                if (peekNextToken().equals("*")) {
                    nextToken();
                    statement.setActionOutcome(new ActionOutcome(outcomePitchPosition,
                            actionOutcomeType.get(readNextToken())));
                } else {
                    throw new ParserException("Invalid token at the end of statement");
                }
            } else {
                statement.setActionOutcome(new ActionOutcome(outcomePitchPosition));
            }
        }
    }

    private void parseAction(Statement statement, PitchPosition actionPitchPosition) throws ParserException {
        String actionToken = readNextToken();
        List<ActionParameter> actionParameters = new ArrayList<>();
        ActionType actionType = ActionType.valueOf(actionToken);
        actionToken = peekNextToken();
        while (":".equals(actionToken)) {
            expectToken(":");
            actionParameters.add(actionParameterMapping.get(readNextToken()));
            actionToken = peekNextToken();
        }
        Action action = new Action(actionType, actionPitchPosition, actionParameters);
        statement.setAction(action);
    }

    private void parseTime(Statement statement) throws ParserException {

        String minutesToken = tokens.get(index++);
        String timeSeparator = tokens.get(index++);
        String secondsToken = tokens.get(index++);

        if (!":".equals(timeSeparator)) {
            throw new ParserException("':' expected");
        }

        try {

            int minutes = Integer.parseInt(minutesToken);
            int seconds = Integer.parseInt(secondsToken);

            statement.setTime(MatchTime.getTimeInSeconds(minutes, seconds));

        } catch (NumberFormatException nfe) {
            throw new ParserException(ParserException.INVALID_TIME_FORMAT);
        }
    }
}
