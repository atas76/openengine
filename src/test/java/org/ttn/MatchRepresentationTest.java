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
import org.ttn.semantics.exceptions.InvalidPhaseDefinitionException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.ttn.parser.output.MatchDataElement.DirectiveType.INPLAY_PHASE;

public class MatchRepresentationTest {

    private final String MATCH_DATA_RESOURCE_NAME = "src/test/resources/data/ttn/cl_rc.ttn";

    private Path matchDataResource;

    @Before
    public void setUp() {
        matchDataResource = Paths.get(MATCH_DATA_RESOURCE_NAME);
    }

    @Test
    public void testLoadMatchData() throws IOException, ParserException,
            InvalidPhaseException, InvalidPhaseDefinitionException {
        List<MatchDataElement> matchDataElements =
                new Parser().parse(Files.lines(matchDataResource).collect(Collectors.toList()));

        MatchRepresentation matchRepresentation = new MatchRepresentation(matchDataElements);
        MatchPhase kickOffPhase = matchRepresentation.getPhase(0);

        assertTrue(kickOffPhase instanceof SetPieceExecutionPhase);
        assertEquals(SetPiece.KICK_OFF, ((SetPieceExecutionPhase) kickOffPhase).getType());
        assertEquals("L", kickOffPhase.getTeam());
    }

    @Test(expected = InvalidPhaseException.class)
    public void testMatchDoesNotStartWithKickOff() throws InvalidPhaseDefinitionException, InvalidPhaseException {
        new MatchRepresentation(List.of(new Directive(INPLAY_PHASE, "X")));
    }

    @Test(expected = InvalidPhaseDefinitionException.class)
    public void testMatchKickOffDefinitionMissing() throws IOException, ParserException,
            InvalidPhaseException, InvalidPhaseDefinitionException {
        new MatchRepresentation(new Parser()
                .parse(Files.lines(Paths.get("src/test/resources/data/ttn/cl_test_kickoff_missing.ttn"))
                .collect(Collectors.toList())));
    }
}
