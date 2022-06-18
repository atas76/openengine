package org.ttn;

import org.junit.Before;
import org.junit.Test;
import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.rules.SetPiece;
import org.ttn.engine.space.PitchPosition;
import org.ttn.parser.Parser;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.output.Directive;
import org.ttn.parser.output.MatchDataElement;
import org.ttn.parser.output.Statement;
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
import static org.ttn.parser.output.MatchDataElement.DirectiveType.SET_PIECE_EXECUTION_BLOCK;

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
        Statement kickOffExecution = kickOffPhase.getEventByIndex(0);

        // Kick-off phase
        assertTrue(kickOffPhase instanceof SetPieceExecutionPhase);
        assertEquals(SetPiece.KICK_OFF, ((SetPieceExecutionPhase) kickOffPhase).getType());
        assertEquals("L", kickOffPhase.getTeam());
        assertEquals(0, kickOffExecution.getTime());
        assertEquals(PitchPosition.KO, kickOffExecution.getPitchPosition());
        assertEquals(PitchPosition.DM, kickOffExecution.getActionOutcome().getPitchPosition());
        assertEquals(TacticalPosition.X.D, kickOffExecution.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.CR, kickOffExecution.getActionOutcome().getTacticalPosition().getY());
    }

    @Test(expected = InvalidPhaseException.class)
    public void testMatchDoesNotStartWithKickOff_InPlay() throws InvalidPhaseDefinitionException, InvalidPhaseException {
        new MatchRepresentation(List.of(new Directive(INPLAY_PHASE, "X")));
    }

    @Test(expected = InvalidPhaseDefinitionException.class)
    public void testMatchKickOffDefinitionMissing() throws IOException, ParserException,
            InvalidPhaseException, InvalidPhaseDefinitionException {
        new MatchRepresentation(new Parser()
                .parse(Files.lines(Paths.get("src/test/resources/data/ttn/cl_test_kickoff_missing.ttn"))
                .collect(Collectors.toList())));
    }

    @Test(expected = InvalidPhaseException.class)
    public void testMatchDoesNotStartWithKickOff_SetPiece() throws InvalidPhaseDefinitionException, InvalidPhaseException {
        new MatchRepresentation(List.of(new Directive(SET_PIECE_EXECUTION_BLOCK, "X", SetPiece.CORNER_KICK)));
    }
}
