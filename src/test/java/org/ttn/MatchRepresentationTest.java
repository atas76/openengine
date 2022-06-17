package org.ttn;

import org.junit.Before;
import org.junit.Test;
import org.ttn.engine.rules.SetPiece;
import org.ttn.parser.Parser;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.output.Directive;
import org.ttn.parser.output.MatchDataElement;
import org.ttn.semantics.MatchPhase;
import org.ttn.semantics.MatchRepresentation;
import org.ttn.semantics.SetPieceExecutionPhase;
import org.ttn.semantics.exceptions.InvalidPhaseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.ttn.engine.rules.SetPiece.CORNER_KICK;
import static org.ttn.parser.output.MatchDataElement.DirectiveType.SET_PIECE_EXECUTION_BLOCK;

public class MatchRepresentationTest {

    private final String MATCH_DATA_RESOURCE_NAME = "src/test/resources/data/ttn/cl_rc.ttn";

    private Path matchDataResource;

    @Before
    public void setUp() {
        matchDataResource = Paths.get(MATCH_DATA_RESOURCE_NAME);
    }

    @Test
    public void testLoadMatchData() throws IOException, ParserException, InvalidPhaseException {
        List<MatchDataElement> matchDataElements =
                new Parser().parse(Files.lines(matchDataResource).collect(Collectors.toList()));

        MatchRepresentation matchRepresentation = new MatchRepresentation(matchDataElements);
        MatchPhase kickOffPhase = matchRepresentation.getPhase(0);

        assertTrue(kickOffPhase instanceof SetPieceExecutionPhase);
        assertEquals(SetPiece.KICK_OFF, ((SetPieceExecutionPhase) kickOffPhase).getType());
        assertEquals("L", kickOffPhase.getTeam());
    }

    @Test(expected = InvalidPhaseException.class)
    public void testMatchDoesNotStartWithKickOff() throws InvalidPhaseException {
        new MatchRepresentation(List.of(new Directive(SET_PIECE_EXECUTION_BLOCK, "X", CORNER_KICK)));
    }
}
