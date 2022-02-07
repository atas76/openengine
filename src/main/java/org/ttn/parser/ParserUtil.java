package org.ttn.parser;

import org.ttn.engine.agent.Action;
import org.ttn.engine.agent.ActionParameter;
import org.ttn.engine.agent.ActionType;
import org.ttn.engine.environment.ActionOutcome;
import org.ttn.engine.environment.ActionOutcomeParameter;
import org.ttn.engine.environment.ActionOutcomeType;
import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.space.PitchPosition;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.exceptions.ValueException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static org.ttn.engine.environment.ActionOutcomeParameter.*;
import static org.ttn.engine.environment.ActionOutcomeType.GOAL;

public class ParserUtil {

    static final Map<Parser.Keyword, Directive.Type> keywordMapping = Map.ofEntries(
            entry(Parser.Keyword.SET, Directive.Type.SET_PIECE_EXECUTION_BLOCK),
            entry(Parser.Keyword.POSSESSION, Directive.Type.POSSESSION_CHAIN_BLOCK),
            entry(Parser.Keyword.RECOVERY, Directive.Type.BALL_RECOVERY_BLOCK),
            entry(Parser.Keyword.ATTACK, Directive.Type.ATTACK_CHAIN_BLOCK),
            entry(Parser.Keyword.BREAK, Directive.Type.BREAK),
            entry(Parser.Keyword.PRESSURE, Directive.Type.PRESSURE_STATEMENT_BLOCK),
            entry(Parser.Keyword.POSSESSOR, Directive.Type.POSSESSOR_DEFINITION),
            entry(Parser.Keyword.TRANSITION, Directive.Type.TRANSITION_STATEMENT_BLOCK));

    public static PitchPosition getPitchPosition(String pitchPosition) throws IllegalArgumentException {
        return PitchPosition.valueOf(pitchPosition);
    }

    public static TacticalPosition.X getTacticalPositionX(String tacticalPositionX) throws IllegalArgumentException {
        return TacticalPosition.X.valueOf(tacticalPositionX);
    }

    public static TacticalPosition.Y getTacticalPositionY(String tacticalPositionY) throws IllegalArgumentException {
        return TacticalPosition.Y.valueOf(tacticalPositionY);
    }

    public static TacticalPosition getGoalkeeperPosition(String goalkeeperPosition) throws IllegalArgumentException {
        return new TacticalPositionImpl(TacticalPosition.X.valueOf(goalkeeperPosition));
    }

    public static ActionType getActionType(String actionType) throws IllegalArgumentException {
        return ActionType.valueOf(actionType);
    }

    public static ActionParameter getActionParameter(String actionParameterValue) throws ValueException {
        return switch (actionParameterValue) {
            case "FT" -> ActionParameter.FIRST_TOUCH;
            case "Open" -> ActionParameter.OPEN_PASS;
            default -> throw new ValueException("Could not map action parameter value");
        };
    }

    public static ActionOutcomeParameter getActionOutcomeParameter(String actionOutcomeParameterValue) throws ValueException {
        return switch(actionOutcomeParameterValue) {
            case "Fr" -> FREE_SPACE;
            case "I" -> INTERCEPTION;
            case "HD" -> HEADER;
            case "Cnt" -> CONTROL;
            default -> throw new ValueException("Could not map action outcome parameter value");
        };
    }

    public static ActionOutcomeType getActionOutcomeType(String actionOutcomeValue) throws ValueException {
        return switch (actionOutcomeValue) {
            case "H" -> ActionOutcomeType.HANDBALL;
            case "C" -> ActionOutcomeType.CORNER;
            case "G" -> GOAL;
            default -> throw new ValueException("Could not map action outcome value");
        };
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
        if ("Gkr".equals(tokens.get(0))) {
            return new TacticalPositionImpl(getTacticalPositionX(tokens.get(0)));
        }
        return new TacticalPositionImpl(getTacticalPositionX(tokens.get(0)), getTacticalPositionY(tokens.get(1)));
    }

    public static ActionOutcome parseSpaceBoundActionOutcome(List<String> tokens, boolean possessionChange)
            throws ValueException, ParserException {
        TacticalPosition tacticalPosition = parseTacticalPosition(tokens);
        if (!tacticalPosition.isGoalkeeper()) {
            expectToken("@", tokens.get(2));
            PitchPosition pitchPosition = getPitchPosition(tokens.get(3));
            if (tokens.size() > 4) {
                return switch(tokens.get(4)) {
                    case "*" -> new ActionOutcome(tacticalPosition, pitchPosition, getActionOutcomeType(tokens.get(5)),
                            possessionChange);
                    case ":" -> new ActionOutcome(tacticalPosition, pitchPosition, getActionOutcomeParameter(tokens.get(5)),
                            possessionChange);
                    default -> throw new ParserException("Outcome delimiter parameter expected");
                };
            }
            return new ActionOutcome(tacticalPosition, pitchPosition, possessionChange);
        } else {
            return new ActionOutcome(tacticalPosition, possessionChange);
        }
    }

    public static ActionOutcome parseSpaceBoundActionOutcome(List<String> tokens)
            throws ValueException, ParserException {
        return parseSpaceBoundActionOutcome(tokens, false);
    }

    public static ActionOutcome parseActionOutcome(List<String> tokens) throws ValueException, ParserException {
        int outcomeIndex = "!".equals(tokens.get(0)) ? 1 : 0;
        boolean possessionChange = outcomeIndex > 0;

        if (Arrays.stream(TacticalPosition.X.values()).anyMatch(value -> value.name().equals(tokens.get(outcomeIndex)))) {
            return parseSpaceBoundActionOutcome(tokens.subList(outcomeIndex, tokens.size()), possessionChange);
        } else if (Arrays.stream(ActionOutcomeType.values()).anyMatch(value -> value.getName().equals(tokens.get(outcomeIndex)))) {
            ActionOutcomeType actionOutcomeType = getActionOutcomeType(tokens.get(outcomeIndex));
            if (GOAL.equals(actionOutcomeType)) {
                possessionChange = true;
            }
            return new ActionOutcome(actionOutcomeType, possessionChange);
        } else {
            throw new ParserException("Tactical position or action outcome type expected");
        }
    }

    public static Statement parseStatement(List<String> tokens) throws ParserException, ValueException {
        int time = parseTime(tokens.subList(0, 3));
        PitchPosition pitchPosition = getPitchPosition(tokens.get(3));
        int currentIndex = 4;
        final String actionDelimiter = tokens.get(currentIndex++);

        int outcomeDelimiterIndex;
        Directive.Type statementType;
        if (tokens.contains(">>>")) {
            statementType = Directive.Type.INDIRECT_OUTCOME;
            outcomeDelimiterIndex = tokens.indexOf(">>>");
        } else if (tokens.contains("=>")) {
            statementType = Directive.Type.STANDARD;
            outcomeDelimiterIndex = tokens.indexOf("=>");
        } else {
            throw new ParserException("Outcome delimiter expected");
        }

        ActionOutcome actionOutcome;
        Statement statement;
        int actionOutcomeBound = tokens.size();
        if (tokens.contains(">>")) {
            actionOutcomeBound = tokens.indexOf(">>");
        }
        if ("=>".equals(actionDelimiter)) {
            actionOutcome = parseActionOutcome(tokens.subList(currentIndex, actionOutcomeBound));
            statement = new Statement(time, pitchPosition, actionOutcome);
        } else if ("->".equals(actionDelimiter)) {
            Action action = parseAction(tokens.subList(currentIndex, outcomeDelimiterIndex));
            actionOutcome = parseActionOutcome(tokens.subList(outcomeDelimiterIndex + 1, actionOutcomeBound));
            statement = new Statement(time, pitchPosition, action, actionOutcome, statementType);
        } else {
            throw new ParserException("Action delimiter expected");
        }
        if (actionOutcomeBound < tokens.size()) {
            statement.setRestingOutcome(parseActionOutcome(tokens.subList(actionOutcomeBound + 1, tokens.size())));
        }

        return statement;
    }

    public static Directive parseDirective(List<String> tokens) throws ParserException {
        if (!":".equals(tokens.get(0))) {
            throw new ParserException("Directives must start with ':'");
        }
        Directive directive = new Directive(keywordMapping.get(expectKeyword(tokens.get(1))), tokens.get(2));
        if ("set".equals(tokens.get(1))) {
            expectToken(":", tokens.get(3));
            directive.setSetPiece(Parser.setPieceMapping.get(tokens.get(4)));
        }
        return directive;
    }

    // TODO define those in a file
    private static Parser.Keyword expectKeyword(String keyword) throws ParserException {

        return switch(keyword) {
            case "set" -> Parser.Keyword.SET;
            case "possession" -> Parser.Keyword.POSSESSION;
            case "recovery" -> Parser.Keyword.RECOVERY;
            case "attack" -> Parser.Keyword.ATTACK;
            case "pressure" -> Parser.Keyword.PRESSURE;
            case "break" -> Parser.Keyword.BREAK;
            case "possessor" -> Parser.Keyword.POSSESSOR;
            case "transition" -> Parser.Keyword.TRANSITION;
            default -> throw new ParserException("Keyword expected");
        };
    }

    private static void expectToken(String token, String currentToken) throws ParserException {
        if (!token.equals(currentToken)) {
            throw new ParserException("Expected: " + token);
        }
    }
}
