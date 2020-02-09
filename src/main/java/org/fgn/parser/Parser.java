package org.fgn.parser;

import org.fgn.lexan.Token;
import org.fgn.parser.exceptions.ParserException;

import java.util.List;

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
                parseStateOut(statement);
                break;
            case OUTCOME_DELIMITER:
                parseStateOut(statement);
                break;
            case COMMENT:
                statement.setComment(tokens.get(++index));
                break;
            default:
                throw new ParserException("Syntax error: action or default outcome operator expected");
        }

        if (hasTokens()) {
            if (COMMENT.equals(tokens.get(index))) {
                statement.setComment(tokens.get(++index));
            } else {
                throw new ParserException("Unexpected end of statement");
            }
        }

        return statement;
    }

    private void parseStateIn(Statement statement) throws ParserException {
        InState state = new InState(tokens.get(index++));
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

    private void parseStateOut(Statement statement) throws ParserException {

        boolean keepPossession = true;

        if (NEGATION.equals(lookaheadToken())) {
            keepPossession = false;
            index++;
        }

        State state = new State(tokens.get(index++), keepPossession);

        if (index < tokens.size() && OPEN_PARENTHESIS.equals(lookaheadToken())) {
            // TODO candidate change
            // state.defineStateContext(tokens.get(++index));
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
