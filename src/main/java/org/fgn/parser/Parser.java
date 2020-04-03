package org.fgn.parser;

import org.fgn.lexan.Token;
import org.fgn.ontology.ActionOutcome;
import org.fgn.ontology.Coordinates;
import org.fgn.ontology.StateContext;
import org.fgn.parser.exceptions.ParserException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.fgn.lexan.Token.*;

public class Parser {

    private List<String> tokens;

    public Parser(List<String> tokens) {
        this.tokens = tokens;
    }

    private void confirmToken(int tokenIndex, String token) throws ParserException {
        if (!(token.equals(tokens.get(tokenIndex)))) throw new ParserException("'" + token + "'" + " expected");
    }

    private int index = 0;

    private boolean containsDescription(Enum [] enumValues, String description) {
        return Arrays.stream(enumValues).map(Enum::toString).collect(Collectors.toList()).contains(description);
    }

    public Statement parse() throws ParserException {

        Statement statement = new Statement();

        if (COMMENT.equals(tokens.get(0))) {
            statement.setComment(tokens.get(1));
            return statement;
        }

        parseTime(statement);
        parseTeam(statement);
        checkTeamSeparator();
        parseStateIn(statement);    // TODO Use a common parseStatement method: this had better be left for later

        String token = getNextToken();

        switch (token) {
            case ACTION_DELIMITER:
                parseAction(statement);
                confirmToken(index++, OUTCOME_DELIMITER);
                evaluateCurrentToken(statement);
                break;
            case OUTCOME_DELIMITER:
                evaluateCurrentToken(statement);
                break;
            case COMMENT:
                statement.setComment(tokens.get(++index));
                break;
            default:
                throw new ParserException("Syntax error: action or default outcome operator expected");
        }

        if (hasTokens()) {
            switch(tokens.get(index)) {
                case COMMENT:
                    statement.setComment(tokens.get(++index));
                    break;
                case OUTCOME_STATE_DELIMITER:
                    ++index;
                    parseStateOut(statement);
                    break;
                default:
                    throw new ParserException("Unexpected end of statement");
            }
        }

        return statement;
    }

    private void evaluateCurrentToken(Statement statement) throws ParserException {
        if (containsDescription(ActionOutcome.values(), lookaheadToken())) {
            statement.setActionOutcome(ActionOutcome.valueOf(this.tokens.get(index++)));
        } else {
            parseStateOut(statement);
        }
    }

    private void parseStateIn(Statement statement) throws ParserException {

        State state = new State();
        String description = tokens.get(index++);

        if (containsDescription(StateContext.values(), description)) {
            state.setContext(StateContext.valueOf(description));
        } else {
            state.setSpace(Coordinates.valueOf(description));
        }

        parseParameter(state);
        statement.setStateIn(state);
    }

    private void parseParameter(State state) throws ParserException {
        if (OPEN_PARENTHESIS.equals(lookaheadToken())) {
            state.setContext(StateContext.valueOf(tokens.get(++index)));
            confirmToken(++index, CLOSE_PARENTHESIS);
            index++;
        }
    }

    private void defineStateContext(State state, String description) {
        if (containsDescription(StateContext.values(), description)) {
            state.setContext(StateContext.valueOf(description));
        } else {
            state.setSpace(Coordinates.valueOf(description));
        }
    }

    private void parseStateOut(Statement statement) throws ParserException {

        boolean keepPossession = true;

        if (NEGATION.equals(lookaheadToken())) {
            keepPossession = false;
            index++;
        }

        State state = new State(keepPossession);
        defineStateContext(state, tokens.get(index++));

        if (index < tokens.size() && OPEN_PARENTHESIS.equals(lookaheadToken())) {
            state.setSpace(Coordinates.valueOf(tokens.get(++index)));
            confirmToken(++index, CLOSE_PARENTHESIS);
            if (hasTokens()) index++;
        }

        statement.setStateOut(state);
    }

    private void parseAction(Statement statement) {
        statement.setAction(new Action(tokens.get(index++)));
    }

    private String getNextToken() {
        return this.tokens.get(index++);
    }

    private boolean hasTokens() {
        return this.index < this.tokens.size() - 1;
    }

    private String lookaheadToken() {
        return this.tokens.get(index);
    }

    private void checkTeamSeparator() throws ParserException {
        confirmToken(index++, ":");
    }

    private void parseTeam(Statement statement) {
        statement.setTeam(tokens.get(index++));
    }

    private void parseTime(Statement statement) throws ParserException {

        String minutesToken = tokens.get(index++);
        String timeSeparator = tokens.get(index++);
        String secondsToken = tokens.get(index++);

        if (!Token.TIME_SEPARATOR.equals(timeSeparator)) {
            throw new ParserException("'" + Token.TIME_SEPARATOR + "'" + " expected");
        }

        try {

            int minutes = Integer.parseInt(minutesToken);
            int seconds = Integer.parseInt(secondsToken);

            statement.setTime(new MatchTime(minutes, seconds));

        } catch (NumberFormatException nfe) {
            throw new ParserException(ParserException.INVALID_TIME_FORMAT);
        }
    }
}
