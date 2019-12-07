package org.fgn.parser;

import org.fgn.lexan.Token;
import org.fgn.parser.exceptions.ParserException;

import java.util.List;

public class Parser {

    private List<String> tokens;

    public Parser(List<String> tokens) {
        this.tokens = tokens;
    }

    public Statement parse() throws ParserException {

        Statement statement = new Statement();

        String minutesToken = tokens.get(0);
        String timeSeparator = tokens.get(1);
        String secondsToken = tokens.get(2);

        if (!Token.TIME_SEPARATOR.equals(timeSeparator)) {
            throw new ParserException(Token.TIME_SEPARATOR + " expected");
        }

        try {

            int minutes = Integer.parseInt(minutesToken);
            int seconds = Integer.parseInt(secondsToken);

            statement.setTime(new MatchTime(minutes, seconds));

        } catch (NumberFormatException nfe) {
            throw new ParserException(ParserException.INVALID_TIME_FORMAT);
        }

        return statement;
    }
}
