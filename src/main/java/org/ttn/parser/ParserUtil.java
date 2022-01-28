package org.ttn.parser;

import org.ttn.engine.agent.Action;
import org.ttn.engine.agent.ActionParameter;
import org.ttn.engine.agent.ActionType;
import org.ttn.engine.environment.ActionOutcome;
import org.ttn.engine.environment.ActionOutcomeType;
import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.space.PitchPosition;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.exceptions.ValueException;

import java.util.ArrayList;
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

    public static ActionParameter getActionParameter(String actionParameterValue) throws ValueException {
        switch(actionParameterValue) {
            case "FT":
                return ActionParameter.FIRST_TOUCH;
            case "Open":
                return ActionParameter.OPEN_PASS;
            default:
                throw new ValueException("Could not map action parameter value");
        }
    }

    public static ActionOutcomeType getActionOutcomeType(String actionOutcomeValue) throws ValueException {
        switch(actionOutcomeValue) {
            case "H":
                return ActionOutcomeType.HANDBALL;
            case "C":
                return ActionOutcomeType.CORNER;
            case "G":
                return ActionOutcomeType.GOAL;
            default:
                throw new ValueException("Could not map action outcome value");
        }
    }

    public static List<ActionParameter> parseActionParameters(List<String> tokens)
            throws ValueException, ParserException {

        List<ActionParameter> actionParameters = new ArrayList<>();

        int currentIndex = 0;
        actionParameters.add(getActionParameter(tokens.get(currentIndex++)));
        while (currentIndex < tokens.size()) {
            expectToken(":", tokens.get(currentIndex++));
            actionParameters.add(getActionParameter(tokens.get(currentIndex++)));
        }

        return actionParameters;
    }

    public static Action parseAction(List<String> tokens) throws ValueException, ParserException {

        ActionType actionType = getActionType(tokens.get(0));

        if (tokens.size() > 1 && ":".equals(tokens.get(1))) {
            return new Action(actionType, parseActionParameters(tokens.subList(2, tokens.size())));
        }
        return new Action(actionType);
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

    public static Statement parseStatement(List<String> tokens) throws ParserException, ValueException {
        int time = parseTime(tokens.subList(0, 3));
        PitchPosition pitchPosition = getPitchPosition(tokens.get(3));
        int currentIndex = 4;
        final String actionDelimiter = tokens.get(currentIndex++);

        int outcomeDelimiterIndex;
        Statement.Type statementType;
        if (tokens.contains(">>>")) {
            statementType = Statement.Type.INDIRECT_OUTCOME;
            outcomeDelimiterIndex = tokens.indexOf(">>>");
        } else if (tokens.contains("=>")) {
            statementType = Statement.Type.STANDARD;
            outcomeDelimiterIndex = tokens.indexOf("=>");
        } else {
            throw new ParserException("Outcome delimiter expected");
        }

        if ("=>".equals(actionDelimiter)) {
            return new Statement(time, pitchPosition, parseSpaceBoundActionOutcome(tokens.subList(currentIndex, tokens.size())));
        } else if ("->".equals(actionDelimiter)) {
            return new Statement(time, pitchPosition,
                    parseAction(tokens.subList(currentIndex, outcomeDelimiterIndex)),
                    parseSpaceBoundActionOutcome(tokens.subList(outcomeDelimiterIndex + 1, tokens.size())), statementType);
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
