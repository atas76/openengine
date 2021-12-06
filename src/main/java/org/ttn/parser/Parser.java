package org.ttn.parser;

import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.rules.SetPiece;
import org.ttn.engine.space.PitchPosition;
import org.ttn.parser.exceptions.ParserException;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.Map.entry;
import static org.ttn.parser.Statement.Type.SPX;

public class Parser {

    private enum Keyword {
        SET
    }

    private static final Map<Keyword, Statement.Type> keywordMapping = Map.ofEntries(
            entry(Keyword.SET, SPX));

    private static final Map<String, SetPiece> setPieceMapping = Map.ofEntries(
            entry("Kickoff", SetPiece.KICK_OFF)
    );

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
            default:
                throw new ParserException("Keyword expected");
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
                    nextToken();
                    statement.setType(keywordMapping.get(expectKeyword()));
                    statement.setTeam(readNextToken());
                    expectToken(":");
                    statement.setSetPiece(setPieceMapping.get(readNextToken()));
                    break;
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
