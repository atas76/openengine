package org.ttn.parser;

import org.ttn.engine.rules.SetPiece;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.output.MatchDataElement;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class Parser {

    public static final Map<String, SetPiece> setPieceMapping = Map.ofEntries(
            entry("Kickoff", SetPiece.KICK_OFF),
            entry("Penalty", SetPiece.PENALTY),
            entry("ThrowIn", SetPiece.THROW_IN),
            entry("Corner", SetPiece.CORNER_KICK));

    public Parser() {}

    public MatchDataElement parse(List<String> tokens) throws ParserException {
        ParserUtil.checkMissingTokens(tokens.size(), 0, "TTN statement or directive");
        if (":".equals(tokens.get(0))) {
            return ParserUtil.parseDirective(tokens);
        }
        return ParserUtil.parseStatement(tokens);
    }
}
