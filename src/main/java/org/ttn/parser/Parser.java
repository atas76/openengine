package org.ttn.parser;

import org.ttn.parser.exceptions.ParserException;

import java.util.List;

public class Parser {

    private List<String> tokens;
    private int index = 0;

    public Parser(List<String> tokens) {
        this.tokens = tokens;
    }

    public Statement parse() throws ParserException {

        Statement statement = new Statement();

        parseTime(statement);

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
