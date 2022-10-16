package org.ttn;

import org.junit.Before;
import org.junit.Test;
import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.rules.SetPiece;
import org.ttn.engine.space.PitchPosition;
import org.ttn.parser.Parser;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.output.Directive;
import org.ttn.parser.output.InPlayPhaseType;
import org.ttn.parser.output.MatchDataElement;
import org.ttn.parser.output.Statement;
import org.ttn.semantics.InPlayPhase;
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

import static org.junit.Assert.*;
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
        // Phases
        MatchPhase kickOffPhase = matchRepresentation.getPhase(0);
        MatchPhase possessionPhase = matchRepresentation.getPhase(1);
        MatchPhase attackPhase = matchRepresentation.getPhase(2);
        MatchPhase penaltyPhase = matchRepresentation.getPhase(3);
        MatchPhase throwInPhase = matchRepresentation.getPhase(4);
        MatchPhase pressurePhase = matchRepresentation.getPhase(5);
        MatchPhase transitionPhase = matchRepresentation.getPhase(6);
        MatchPhase cornerPhase = matchRepresentation.getPhase(9);
        MatchPhase ballRecoveryPhase = matchRepresentation.getPhase(13);
        MatchPhase attackingPossessionPhase = matchRepresentation.getPhase(22);
        MatchPhase freeKickPhase = matchRepresentation.getPhase(29);
        MatchPhase defensiveTransitionPhase = matchRepresentation.getPhase(31);
        MatchPhase counterAttackPhase = matchRepresentation.getPhase(48);
        MatchPhase firstHalfLastPhase = matchRepresentation.getPhase(154);
        MatchPhase secondHalfKickOffPhase = matchRepresentation.getPhase(155);
        Statement kickOffExecution = kickOffPhase.getEventByIndex(0);
        assertEquals(321, matchRepresentation.getNumberOfPhases());

        // Kick-off phase
        assertTrue(kickOffPhase instanceof SetPieceExecutionPhase);
        assertEquals(SetPiece.KICK_OFF, ((SetPieceExecutionPhase) kickOffPhase).getType());
        assertEquals("L", kickOffPhase.getTeam());
        assertEquals(0, kickOffExecution.getTime());
        assertEquals(PitchPosition.KO, kickOffExecution.getPitchPosition());
        assertEquals(PitchPosition.DM, kickOffExecution.getActionOutcome().getPitchPosition());
        assertEquals(TacticalPosition.X.D, kickOffExecution.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.CR, kickOffExecution.getActionOutcome().getTacticalPosition().getY());
        // Possession phase
        assertTrue(possessionPhase instanceof InPlayPhase);
        assertEquals(InPlayPhaseType.POSSESSION, ((InPlayPhase) possessionPhase).getType());
        assertEquals("L", possessionPhase.getTeam());
        assertEquals(2, possessionPhase.getEventsNumber());
        assertEquals(TacticalPosition.X.F, possessionPhase.getEventByIndex(1).getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.LC, possessionPhase.getEventByIndex(1).getActionOutcome().getTacticalPosition().getY());
        // Attack phase
        assertTrue(attackPhase instanceof InPlayPhase);
        assertEquals(InPlayPhaseType.ATTACK, ((InPlayPhase) attackPhase).getType());
        assertEquals("L", attackPhase.getTeam());
        assertEquals(1, attackPhase.getEventsNumber());
        assertFalse(attackPhase.isFlowBroken());
        // Penalty phase
        assertTrue(penaltyPhase instanceof SetPieceExecutionPhase);
        assertEquals(SetPiece.PENALTY, ((SetPieceExecutionPhase) penaltyPhase).getType());
        assertEquals("L", kickOffPhase.getTeam());
        assertEquals(1, penaltyPhase.getEventsNumber());
        // Break
        assertTrue(penaltyPhase.isFlowBroken()); // Flow is broken after last phase
        // Throw-in phase
        assertTrue(throwInPhase instanceof SetPieceExecutionPhase);
        assertEquals(SetPiece.THROW_IN, ((SetPieceExecutionPhase) throwInPhase).getType());
        assertEquals("L", throwInPhase.getTeam());
        assertEquals(3, throwInPhase.getEventsNumber());
        assertTrue(throwInPhase.isFlowBroken()); // for the subsequent break
        // Pressure phase
        assertTrue(pressurePhase instanceof InPlayPhase);
        assertEquals(InPlayPhaseType.PRESSURE, ((InPlayPhase) pressurePhase).getType());
        assertEquals(new MatchPhase.TacticalPosition(TacticalPosition.X.D, TacticalPosition.Y.CR),
                pressurePhase.getInitialPossessor());
        assertEquals(8, pressurePhase.getEventsNumber());
        // Transition phase
        assertTrue(transitionPhase instanceof InPlayPhase);
        assertEquals(InPlayPhaseType.TRANSITION, ((InPlayPhase) transitionPhase).getType());
        assertEquals(4, transitionPhase.getEventsNumber());
        // Corner kick phase
        assertTrue(cornerPhase instanceof SetPieceExecutionPhase);
        assertEquals(SetPiece.CORNER_KICK, ((SetPieceExecutionPhase) cornerPhase).getType());
        assertEquals("T", cornerPhase.getTeam());
        assertEquals(2, cornerPhase.getEventsNumber());
        // Ball recovery phase
        assertTrue(ballRecoveryPhase instanceof InPlayPhase);
        assertEquals(InPlayPhaseType.BALL_RECOVERY, ((InPlayPhase) ballRecoveryPhase).getType());
        assertEquals(1, ballRecoveryPhase.getEventsNumber());
        assertTrue(ballRecoveryPhase.isFlowBroken());
        // Attacking possession phase
        assertTrue(attackingPossessionPhase instanceof InPlayPhase);
        assertEquals(InPlayPhaseType.ATTACKING_POSSESSION, ((InPlayPhase) attackingPossessionPhase).getType());
        assertEquals(8, attackingPossessionPhase.getEventsNumber());
        // Free kick phase
        assertTrue(freeKickPhase instanceof SetPieceExecutionPhase);
        assertEquals(SetPiece.FREEKICK, ((SetPieceExecutionPhase) freeKickPhase).getType());
        assertEquals("T", freeKickPhase.getTeam());
        assertEquals(1, freeKickPhase.getEventsNumber());
        assertEquals(new MatchPhase.TacticalPosition(TacticalPosition.X.AM, TacticalPosition.Y.R),
                freeKickPhase.getInitialPossessor());
        // Defensive transition phase
        assertTrue(defensiveTransitionPhase instanceof InPlayPhase);
        assertEquals(InPlayPhaseType.DEFENSIVE_TRANSITION, ((InPlayPhase) defensiveTransitionPhase).getType());
        assertEquals(1, defensiveTransitionPhase.getEventsNumber());
        assertTrue(defensiveTransitionPhase.isFlowBroken());
        // Counter-attack phase
        assertTrue(counterAttackPhase instanceof InPlayPhase);
        assertEquals(InPlayPhaseType.COUNTER_ATTACK, ((InPlayPhase) counterAttackPhase).getType());
        assertEquals(2, counterAttackPhase.getEventsNumber());
        // Half-time final whistle
        assertTrue(firstHalfLastPhase.isFlowBroken());
        // Second half kick-off
        assertTrue(secondHalfKickOffPhase instanceof SetPieceExecutionPhase);
        assertEquals(SetPiece.KICK_OFF, ((SetPieceExecutionPhase) secondHalfKickOffPhase).getType());
        assertEquals(1, secondHalfKickOffPhase.getEventsNumber());
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
