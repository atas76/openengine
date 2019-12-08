package org.fgn.parser;

import org.fgn.lexan.Token;
import org.fgn.parser.exceptions.ParserException;

import java.util.List;

public class Parser {

    private List<String> tokens;

    public Parser(List<String> tokens) {
        this.tokens = tokens;
    }

    private boolean confirmToken(int tokenIndex, String token) {
        return token.equals(tokens.get(tokenIndex));
    }

    public Statement parse() throws ParserException {

        Statement statement = new Statement();

        parseTime(statement);
        parseTeam(statement);
        checkTeamSeparator();

        return statement;
    }

    private void parseTeam(Statement statement) {
        statement.setTeam(tokens.get(3));
    }

    private void checkTeamSeparator() throws ParserException {
        if (!confirmToken(4, ":")) {
            throw new ParserException("'" + Token.TEAM_SEPARATOR + "'" + " expected");
        }
    }

    private void parseTime(Statement statement) throws ParserException {

        String minutesToken = tokens.get(0);
        String timeSeparator = tokens.get(1);
        String secondsToken = tokens.get(2);

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
