package org.ttn.parser;

import org.ttn.engine.agent.Action;
import org.ttn.engine.agent.ActionParameter;
import org.ttn.engine.agent.ActionType;
import org.ttn.engine.environment.ActionOutcome;
import org.ttn.engine.environment.ActionContext;
import org.ttn.engine.environment.ActionOutcomeType;
import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.space.PitchPosition;
import org.ttn.parser.exceptions.MissingTokenException;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.exceptions.ValueException;
import org.ttn.parser.output.Directive;
import org.ttn.parser.output.Parsable;
import org.ttn.parser.output.Statement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static org.ttn.engine.agent.ActionType.Implicit;
import static org.ttn.engine.environment.ActionContext.*;
import static org.ttn.engine.environment.ActionOutcomeType.*;
import static org.ttn.parser.output.Parsable.DirectiveType.*;
import static org.ttn.parser.output.Parsable.StatementType.*;

public class ParserUtil {

    static final Map<Parser.Keyword, Parsable.DirectiveType> keywordMapping = Map.ofEntries(
            entry(Parser.Keyword.SET, SET_PIECE_EXECUTION_BLOCK),
            entry(Parser.Keyword.POSSESSION, POSSESSION_CHAIN_BLOCK),
            entry(Parser.Keyword.RECOVERY, BALL_RECOVERY_BLOCK),
            entry(Parser.Keyword.ATTACK, ATTACK_CHAIN_BLOCK),
            entry(Parser.Keyword.BREAK, BREAK),
            entry(Parser.Keyword.ATTACKING_POSSESSION, ATTACKING_POSSESSION),
            entry(Parser.Keyword.PRESSURE, BUILDUP_PRESSURE_BLOCK),
            entry(Parser.Keyword.POSSESSOR, POSSESSOR_DEFINITION),
            entry(Parser.Keyword.TRANSITION, TRANSITION_CHAIN_BLOCK));

    public static PitchPosition getPitchPosition(String pitchPosition) throws IllegalArgumentException {
        return PitchPosition.valueOf(pitchPosition);
    }

    public static TacticalPosition.X getTacticalPositionX(String tacticalPositionX) throws IllegalArgumentException {
        return TacticalPosition.X.valueOf(tacticalPositionX);
    }

    public static TacticalPosition.Y getTacticalPositionY(String tacticalPositionY) throws IllegalArgumentException {
        return TacticalPosition.Y.valueOf(tacticalPositionY);
    }

    public static TacticalPosition.Gk getTacticalPositionGk(String tacticalPositionGk) throws IllegalArgumentException {
        return TacticalPosition.Gk.valueOf(tacticalPositionGk);
    }

    public static TacticalPosition getGoalkeeperPosition(String goalkeeperPosition) throws IllegalArgumentException {
        return new TacticalPositionImpl(TacticalPosition.Gk.valueOf(goalkeeperPosition));
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

    public static ActionContext getActionContext(String actionContextValue) throws ValueException {
        return switch(actionContextValue) {
            case "Fr" -> FREE_SPACE;
            case "I" -> INTERCEPTION;
            case "HD" -> HEADER;
            case "Cnt" -> CONTROL;
            case "Mrk" -> MARKED;
            case "Pvt" -> PIVOT;
            case "Clr" -> CLEARANCE;
            case "Pr" -> PRESSED;
            default -> throw new ValueException("Could not map action outcome parameter value");
        };
    }

    public static ActionOutcomeType getActionOutcomeType(String actionOutcomeValue) throws ValueException {
        return switch (actionOutcomeValue) {
            case "H" -> HANDBALL;
            case "C" -> CORNER;
            case "T" -> THROW_IN;
            case "G" -> GOAL;
            case "GK" -> GOAL_KICK;
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
        if (TacticalPosition.isGoalkeeper(tokens.get(0))) {
            return new TacticalPositionImpl(getTacticalPositionGk(tokens.get(0)));
        }
        return new TacticalPositionImpl(getTacticalPositionX(tokens.get(0)), getTacticalPositionY(tokens.get(1)));
    }

    public static ActionOutcome parseSpaceBoundActionOutcome(List<String> tokens, boolean possessionChange)
            throws ParserException {
        TacticalPosition tacticalPosition = parseTacticalPosition(tokens);
        if (!tacticalPosition.isGoalkeeper()) {
            expectToken("@", tokens.get(2));
            PitchPosition pitchPosition = getPitchPosition(tokens.get(3));
            if (tokens.size() > 4) {
                return switch(tokens.get(4)) {
                    case "*" -> new ActionOutcome(tacticalPosition, pitchPosition, getActionOutcomeType(tokens.get(5)),
                            possessionChange);
                    case ":" -> new ActionOutcome(tacticalPosition, pitchPosition, getActionContext(tokens.get(5)),
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
        } else if ((Arrays.stream(PitchPosition.values()).anyMatch(value -> value.name().equals(tokens.get(outcomeIndex))))) {
            if (outcomeIndex < tokens.size() - 1) {
                if (":".equals(tokens.get(outcomeIndex + 1))) {
                    return new ActionOutcome(getPitchPosition(tokens.get(outcomeIndex)), getActionContext(tokens.get(outcomeIndex + 2)));
                } else if ("*".equals(tokens.get(outcomeIndex + 1))) {
                    return new ActionOutcome(getPitchPosition(tokens.get(outcomeIndex)), getActionOutcomeType(tokens.get(outcomeIndex + 2)),
                            possessionChange);
                }
            }
            return new ActionOutcome(getPitchPosition(tokens.get(outcomeIndex)));
        } else {
            throw new ParserException("Tactical position or action outcome type expected");
        }
    }

    public static Statement parseStatement(List<String> tokens) throws ParserException {
        checkMissingTokens(tokens.size(), 2, "time");
        int time = parseTime(tokens.subList(0, 3));
        checkMissingTokens(tokens.size(), 3, "pitch position");
        PitchPosition pitchPosition = getPitchPosition(tokens.get(3));
        int currentIndex = 4;
        ActionContext actionContext = null;
        checkMissingTokens(tokens.size(), 4, "action definition");
        if (":".equals(tokens.get(4))) {
            checkMissingTokens(tokens.size(), 5, "action context");
            actionContext = getActionContext(tokens.get(5));
            currentIndex = 6;
        }
        final String actionDelimiter = tokens.get(currentIndex++);
        int outcomeDelimiterIndex;
        Parsable.StatementType statementType;
        if (tokens.contains(">>>")) {
            statementType = INDIRECT_OUTCOME;
            outcomeDelimiterIndex = tokens.indexOf(">>>");
        } else if (tokens.contains("=>")) {
            statementType = STANDARD;
            outcomeDelimiterIndex = tokens.indexOf("=>");
        } else if (tokens.contains("->")) {
            statementType = TRIVIAL_POSSESSION_CHAIN;
            outcomeDelimiterIndex = tokens.indexOf("->");
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
            Action action = TRIVIAL_POSSESSION_CHAIN.equals(statementType) ?
                    new Action(Implicit) :
                    parseAction(tokens.subList(currentIndex, outcomeDelimiterIndex));
            actionOutcome = parseActionOutcome(tokens.subList(outcomeDelimiterIndex + 1, actionOutcomeBound));
            statement = new Statement(time, pitchPosition, action, actionOutcome, statementType);
        } else {
            throw new ParserException("Action delimiter expected");
        }
        if (actionOutcomeBound < tokens.size()) {
            statement.setRestingOutcome(parseActionOutcome(tokens.subList(actionOutcomeBound + 1, tokens.size())));
        }
        statement.setActionContext(actionContext);

        return statement;
    }

    public static Directive parseDirective(List<String> tokens) throws ParserException {
        final int tokensNumber = tokens.size();
        checkMissingTokens(tokensNumber, 0, "':'");
        if (!":".equals(tokens.get(0))) {
            throw new ParserException("Directives must start with ':'");
        }
        checkMissingTokens(tokensNumber, 1, "directive");
        Parsable.DirectiveType directiveType = keywordMapping.get(expectKeyword(tokens.get(1)));
        switch(tokens.get(1)) {
            case "break":
                return new Directive(directiveType);
            case "set":
                checkMissingTokens(tokensNumber, 2, "team");
                checkMissingTokens(tokensNumber, 3, "':'");
                expectToken(":", tokens.get(3));
                checkMissingTokens(tokensNumber, 4, "set piece keyword");
                return new Directive(directiveType, tokens.get(2),
                        Parser.setPieceMapping.get(tokens.get(4)));
            case "possessor":
                checkMissingTokens(tokensNumber, 2, "tactical position");
                return new Directive(directiveType, parseTacticalPosition(tokens.subList(2, tokens.size())));
            default:
                checkMissingTokens(tokensNumber, 2, "team");
                return new Directive(directiveType, tokens.get(2));
        }
    }

    public static void checkMissingTokens(int tokensNumber, int tokenIndex, String expectedToken) throws MissingTokenException {
        if (tokenIndex > tokensNumber - 1) throw new MissingTokenException("Expected: " + expectedToken);
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
            case "attacking_possession" -> Parser.Keyword.ATTACKING_POSSESSION;
            default -> throw new ParserException("Keyword expected");
        };
    }

    private static void expectToken(String token, String currentToken) throws ParserException {
        if (!token.equals(currentToken)) {
            throw new ParserException("Expected: " + token);
        }
    }
}
