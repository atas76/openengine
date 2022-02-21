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

public class Parser {
    enum Keyword {
        SET, POSSESSION, BREAK, PRESSURE, POSSESSOR, TRANSITION, RECOVERY, ATTACK
    }

    public static final Map<String, SetPiece> setPieceMapping = Map.ofEntries(
            entry("Kickoff", SetPiece.KICK_OFF),
            entry("Penalty", SetPiece.PENALTY),
            entry("ThrowIn", SetPiece.THROW_IN),
            entry("Corner", SetPiece.CORNER_KICK));

    public Parser() {}

    public Parsable parse(List<String> tokens) throws ParserException {
        ParserUtil.checkMissingTokens(tokens.size(), 0, "TTN statement or directive");
        if (":".equals(tokens.get(0))) {
            return ParserUtil.parseDirective(tokens);
        }
        return ParserUtil.parseStatement(tokens);
    }
}
