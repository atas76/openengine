package org.ttn.parser;

import org.ttn.engine.agent.ActionParameter;
import org.ttn.engine.environment.ActionContext;
import org.ttn.engine.environment.ActionOutcomeType;
import org.ttn.engine.rules.SetPiece;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.output.Parsable;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static org.ttn.engine.agent.ActionParameter.FIRST_TOUCH;
import static org.ttn.engine.agent.ActionParameter.OPEN_PASS;
import static org.ttn.engine.environment.ActionOutcomeType.*;

public class Parser {
    enum Keyword {
        SET, POSSESSION, BREAK, PRESSURE, POSSESSOR, TRANSITION, RECOVERY, ATTACK
    }

    public static final Map<String, SetPiece> setPieceMapping = Map.ofEntries(
            entry("Kickoff", SetPiece.KICK_OFF),
            entry("Penalty", SetPiece.PENALTY),
            entry("ThrowIn", SetPiece.THROW_IN),
            entry("Corner", SetPiece.CORNER_KICK));

    private static final Map<String, ActionParameter> actionParameterMapping = Map.ofEntries(
            entry("Open", OPEN_PASS),
            entry("FT", FIRST_TOUCH));

    private static final Map<String, ActionOutcomeType> outcomeType = Map.ofEntries(
            entry("H", HANDBALL),
            entry("G", GOAL),
            entry("C", CORNER));

    private static final Map<String, ActionContext> outcomeParameterMapping = Map.ofEntries(
            entry("Fr", ActionContext.FREE_SPACE),
            entry("I", ActionContext.INTERCEPTION),
            entry("HD", ActionContext.HEADER),
            entry("Cnt", ActionContext.CONTROL));

    public Parser() {}

    public Parsable parse(List<String> tokens) throws ParserException {
        ParserUtil.checkMissingTokens(tokens.size(), 0, "TTN statement or directive");
        if (":".equals(tokens.get(0))) {
            return ParserUtil.parseDirective(tokens);
        }
        return ParserUtil.parseStatement(tokens);
    }
}
