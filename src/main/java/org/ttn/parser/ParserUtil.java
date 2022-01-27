package org.ttn.parser;

import org.ttn.engine.agent.ActionType;
import org.ttn.engine.environment.ActionOutcome;
import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.space.PitchPosition;
import org.ttn.parser.exceptions.ParserException;

import java.util.List;

public class ParserUtil {

    public static PitchPosition getPitchPosition(String pitchPosition) throws IllegalArgumentException {
        return PitchPosition.valueOf(pitchPosition);
    }

    public static TacticalPosition.X getTacticalPositionX(String tacticalPositionX) throws IllegalArgumentException {
        return TacticalPosition.X.valueOf(tacticalPositionX);
    }

    public static TacticalPosition.Y getTacticalPositionY(String tacticalPositionY) throws IllegalArgumentException {
        return TacticalPosition.Y.valueOf(tacticalPositionY);
    }

    public static ActionType getActionType(String actionType) throws IllegalArgumentException {
        return ActionType.valueOf(actionType);
    }

    public static int parseTime(List<String> tokens) throws ParserException, NumberFormatException {
        int minutes = Integer.parseInt(tokens.get(0));
        expectToken(":", tokens.get(1));
        int seconds = Integer.parseInt(tokens.get(2));

        return minutes * 60 + seconds;
    }

    public static TacticalPosition parseTacticalPosition(List<String> tokens) {
        return new TacticalPositionImpl(getTacticalPositionX(tokens.get(0)), getTacticalPositionY(tokens.get(1)));
    }

    public static ActionOutcome parseSpaceBoundActionOutcome(List<String> tokens) throws ParserException {
        TacticalPosition tacticalPosition = parseTacticalPosition(tokens.subList(0, 2));
        expectToken("@", tokens.get(2));
        PitchPosition pitchPosition = getPitchPosition(tokens.get(3));
        return new ActionOutcome(tacticalPosition, pitchPosition);
    }

    public static Statement parseStatement(List<String> tokens) throws ParserException {
        int time = parseTime(tokens.subList(0, 3));
        PitchPosition pitchPosition = getPitchPosition(tokens.get(3));
        int currentIndex = 4;
        final String actionDelimiter = tokens.get(currentIndex++);

        if ("=>".equals(actionDelimiter)) {
            return new Statement(time, pitchPosition, parseSpaceBoundActionOutcome(tokens.subList(currentIndex, tokens.size())));
        } else if ("->".equals(actionDelimiter)) {
            ActionType actionType = getActionType(tokens.get(currentIndex++));
            expectToken(">>>", tokens.get(currentIndex++));
            return new Statement(time, pitchPosition, actionType,
                    parseSpaceBoundActionOutcome(tokens.subList(currentIndex, tokens.size())));
        } else {
            throw new ParserException("Action delimiter expected");
        }
    }

    private static void expectToken(String token, String currentToken) throws ParserException {
        if (!token.equals(currentToken)) {
            throw new ParserException("Expected: " + token);
        }
    }
}
