package org.ttn.parser;

import org.ttn.engine.agent.Action;
import org.ttn.engine.agent.ActionType;
import org.ttn.engine.agent.ActionParameter;
import org.ttn.engine.environment.ActionOutcome;
import org.ttn.engine.environment.OutcomeParameter;
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
import static java.util.Objects.nonNull;
import static org.ttn.engine.agent.ActionParameter.FIRST_TOUCH;
import static org.ttn.engine.agent.ActionParameter.OPEN_PASS;
import static org.ttn.engine.agent.ActionType.Default;
import static org.ttn.engine.agent.ActionType.Move;
import static org.ttn.engine.environment.OutcomeType.*;
import static org.ttn.parser.Statement.Type.*;

public class Parser {

    private final Set<String> STATEMENT_QUALIFIERS = Set.of("=>", ":");
    private final Set<String> OUTCOME_DELIMITERS = Set.of("=>", ">>>");
    private final Set<String> ACTION_DELIMITERS = Set.of("=>", "->");
    private final Set<Statement.Type> DIRECTIVE_STATEMENT_TYPES = Set.of(BREAK, POSSESSOR_DEFINITION);

    private enum Keyword {
        SET, POSSESSION, BREAK, PRESSURE, POSSESSOR, TRANSITION
    }

    private static final Map<Keyword, Statement.Type> keywordMapping = Map.ofEntries(
            entry(Keyword.SET, SET_PIECE_EXECUTION_BLOCK),
            entry(Keyword.POSSESSION, POSSESSION_STATEMENT_BLOCK),
            entry(Keyword.BREAK, BREAK),
            entry(Keyword.PRESSURE, PRESSURE_STATEMENT_BLOCK),
            entry(Keyword.POSSESSOR, POSSESSOR_DEFINITION),
            entry(Keyword.TRANSITION, TRANSITION_STATEMENT_BLOCK));

    private static final Map<String, SetPiece> setPieceMapping = Map.ofEntries(
            entry("Kickoff", SetPiece.KICK_OFF),
            entry("Penalty", SetPiece.PENALTY),
            entry("ThrowIn", SetPiece.THROW_IN),
            entry("Corner", SetPiece.CORNER_KICK));

    private static final Map<String, ActionParameter> actionParameterMapping = Map.ofEntries(
            entry("Open", OPEN_PASS),
            entry("FT", FIRST_TOUCH));

    private static final Map<String, OutcomeType> outcomeType = Map.ofEntries(
            entry("H", HANDBALL),
            entry("G", GOAL),
            entry("C", CORNER));

    private static final Map<String, OutcomeParameter> outcomeParameterMapping = Map.ofEntries(
            entry("Fr", OutcomeParameter.FREE_SPACE),
            entry("I", OutcomeParameter.INTERCEPTION));

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
            case "transition":
                return Keyword.TRANSITION;
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
                    statement.setType(DEFAULT_SET_PIECE_EXECUTION);
                    statement.setAction(new Action(Default, actionPitchPosition));
                } else {
                    parseOutcomeDelimiter(statement);
                }

                String outcomeFirstToken = readNextToken();

                if (nonNull(statement.getAction()) && Move.equals(statement.getAction().getType())) {
                    parseSpaceBoundOutcome(statement, outcomeFirstToken, true);
                } else if ("G".equals(outcomeFirstToken)) {
                    statement.setActionOutcome(new ActionOutcome(outcomeType.get(outcomeFirstToken)));
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
        parseSpaceBoundOutcome(statement, outcomeToken, false);
    }

    private void parseSpaceBoundOutcome(Statement statement, String outcomeToken, boolean skipTacticalPosition) throws ParserException {
        if ("!".equals(outcomeToken)) {
            statement.setBallPossessionChange(true);
            outcomeToken = readNextToken();
        }
        if (!skipTacticalPosition) {
            statement.setTacticalPositionX(TacticalPosition.X.valueOf(outcomeToken));
        }
        if (!"Gkr".equals(outcomeToken)) {
            if (!skipTacticalPosition) {
                statement.setTacticalPositionY(TacticalPosition.Y.valueOf(readNextToken()));
                expectToken("@");
                parseOutcomePitchPosition(statement, PitchPosition.valueOf(readNextToken()));
            } else {
                parseOutcomePitchPosition(statement, PitchPosition.valueOf(outcomeToken));
            }
        }
    }

    private void parseOutcomePitchPosition(Statement statement, PitchPosition outcomePitchPosition) throws ParserException {
        if (hasNextToken()) {
            if (peekNextToken().equals("*")) {
                nextToken();
                statement.setActionOutcome(new ActionOutcome(outcomePitchPosition,
                        outcomeType.get(readNextToken())));
            } else if (peekNextToken().equals(":")) {
                String parameterToken = peekNextToken();
                List<OutcomeParameter> outcomeParameters = new ArrayList<>();
                while (":".equals(parameterToken)) {
                    expectToken(":");
                    outcomeParameters.add(outcomeParameterMapping.get(readNextToken()));
                    if (hasNextToken()) {
                        parameterToken = peekNextToken();
                    } else {
                        break;
                    }
                }
                statement.setActionOutcome(new ActionOutcome(outcomePitchPosition, outcomeParameters));
            } else {
                throw new ParserException("Invalid token at the end of statement");
            }
            if (hasNextToken()) {
                parseRestingOutcome(statement);
            }
        } else {
            statement.setActionOutcome(new ActionOutcome(outcomePitchPosition));
        }
    }

    private void parseRestingOutcome(Statement statement) throws ParserException {
        if (peekNextToken().equals(">>")) {
            nextToken();
            statement.getActionOutcome().setRestingOutcome(outcomeType.get(readNextToken()));
        } else {
            throw new ParserException(">> expected");
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
