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
import static org.ttn.engine.environment.OutcomeType.HANDBALL;
import static org.ttn.parser.Statement.Type.*;

public class Parser {

    private final Set<String> STATEMENT_QUALIFIERS = Set.of("=>", ":");
    private final Set<String> OUTCOME_DELIMITERS = Set.of("=>", ">>>");

    private enum Keyword {
        SET, POSSESSION
    }

    private static final Map<Keyword, Statement.Type> keywordMapping = Map.ofEntries(
            entry(Keyword.SET, SP_EXECUTION),
            entry(Keyword.POSSESSION, POSSESSION_BLOCK_START));

    private static final Map<String, SetPiece> setPieceMapping = Map.ofEntries(
            entry("Kickoff", SetPiece.KICK_OFF));

    private static final Map<String, ActionParameter> actionParameterMapping = Map.ofEntries(
            entry("Open", OPEN_PASS),
            entry("FT", FIRST_TOUCH));

    private static final Map<String, OutcomeType> actionOutcomeType = Map.ofEntries(
            entry("H", HANDBALL));

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
        return index < tokens.size() - 1;
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
                statement.setPitchPosition(PitchPosition.valueOf(readNextToken()));
                break;
            case ":":
                nextToken();
                statement.setType(keywordMapping.get(expectKeyword()));
                statement.setTeam(readNextToken());
                if (hasNextToken()) {
                    expectToken(":");
                    statement.setSetPiece(setPieceMapping.get(readNextToken()));
                }
                break;
            default: // TODO don't reuse token variable: each assignment should be a declaration with extracted method if possible
                PitchPosition actionPitchPosition = PitchPosition.valueOf(token);

                nextToken();
                expectToken("->");
                token = readNextToken();
                List<ActionParameter> actionParameters = new ArrayList<>();
                ActionType actionType = ActionType.valueOf(token);
                token = peekNextToken();
                while (":".equals(token)) {
                    expectToken(":");
                    actionParameters.add(actionParameterMapping.get(readNextToken()));
                    token = peekNextToken();
                }
                Action action = new Action(actionType, actionPitchPosition, actionParameters);
                statement.setAction(action);

                token = readNextToken();
                if (!OUTCOME_DELIMITERS.contains(token)) {
                    throw new ParserException("Outcome delimiter expected");
                }

                statement.setType(">>>".equals(token) ? INDIRECT_OUTCOME : STANDARD);

                statement.setTacticalPositionX(TacticalPosition.X.valueOf(readNextToken()));
                statement.setTacticalPositionY(TacticalPosition.Y.valueOf(readNextToken()));

                expectToken("@");
                PitchPosition outcomePitchPosition = PitchPosition.valueOf(readNextToken());

                if (hasNextToken()) {
                    if (peekNextToken().equals("*")) {
                        nextToken();
                        statement.setActionOutcome(new ActionOutcome(outcomePitchPosition,
                                actionOutcomeType.get(readNextToken())));
                    }
                } else {
                    statement.setPitchPosition(outcomePitchPosition);
                }
        }

        return statement;
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
