package org.ttn.parser;

import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.space.PitchPosition;
import org.ttn.parser.exceptions.ParserException;

import java.util.List;
import java.util.regex.Pattern;

public class Parser {

    private List<String> tokens;
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

    private String readNextToken() {
        return tokens.get(index++);
    }

    private void expectToken(String token) throws ParserException {
        if (!token.equals(tokens.get(index++))) {
            throw new ParserException("Expected: " + token);
        }
    }

    public Statement parse() throws ParserException {

        Statement statement = new Statement();
        String token = peekNextToken();

        if (numericMinutesPattern.matcher(token).matches()) {
            parseTime(statement);
        } else {
            switch(token) {
                case "=>":
                    nextToken();
                    statement.setTacticalPositionX(TacticalPosition.X.valueOf(readNextToken()));
                    statement.setTacticalPositionY(TacticalPosition.Y.valueOf(readNextToken()));
                    expectToken("@");
                    statement.setPitchPosition(PitchPosition.valueOf(readNextToken()));
                    break;
                case ":":
                default:
                    throw new ParserException("Unknown symbol: " + token);
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
