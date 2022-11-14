package org.openengine.openfootie;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.openengine.openfootie.MatchEvent.GOAL;
import static org.openengine.openfootie.MatchPhase.*;
import static org.openengine.openfootie.SetPiece.*;

public class MatchFlowMatrixTest {

    private MatchFlowMatrix homeTeamMatchFlow = MatchFlowMatrixRepository.L_CLF19;
    private MatchEngine matchEngine = new MatchEngine();

    @BeforeClass
    public static void setUp() {
        MatchFlowMatrixRepository.load();
    }

    @Test
    public void testMatchFlowMatrix() {
        MatchSequence homeTeamKickOffSequence = homeTeamMatchFlow.getRow(KICK_OFF);
        MatchSequence homeTeamPossessionSequence = homeTeamMatchFlow.getRow(POSSESSION);
        MatchSequence homeTeamTransitionSequence = homeTeamMatchFlow.getRow(TRANSITION);
        MatchSequence homeTeamAttackingTransitionSequence = homeTeamMatchFlow.getRow(ATTACKING_TRANSITION);
        MatchSequence homeTeamPenaltySequence = homeTeamMatchFlow.getRow(PENALTY);

        MatchDataElement homeTeamKickOffSequenceElement = matchEngine.getNextSequenceElement(homeTeamKickOffSequence, 0);
        MatchDataElement homeTeamPossessionSequenceElement = matchEngine.getNextSequenceElement(homeTeamPossessionSequence, 12);
        MatchDataElement homeTeamTransitionSequenceElement = matchEngine.getNextSequenceElement(homeTeamTransitionSequence, 1);
        MatchDataElement homeTeamAttackingTransitionSequenceElement = matchEngine.getNextSequenceElement(homeTeamAttackingTransitionSequence, 4);
        MatchDataElement homeTeamAttackingPenaltySequenceElement = matchEngine.getNextSequenceElement(homeTeamPenaltySequence, 0);

        assertEquals(POSSESSION, homeTeamKickOffSequenceElement.type());
        assertTrue(homeTeamKickOffSequenceElement.retainPossession());
        assertEquals(2, homeTeamKickOffSequenceElement.duration());
        //
        assertEquals(DEFENSIVE_TRANSITION, homeTeamPossessionSequenceElement.type());
        assertFalse(homeTeamPossessionSequenceElement.retainPossession());
        assertEquals(8, homeTeamPossessionSequenceElement.duration());
        //
        assertEquals(DEFENSIVE_TRANSITION, homeTeamTransitionSequenceElement.type());
        assertFalse(homeTeamTransitionSequenceElement.retainPossession());
        assertEquals(17, homeTeamTransitionSequenceElement.duration());
        assertEquals(17, homeTeamTransitionSequenceElement.breakTime());
        //
        assertEquals(ATTACKING_POSSESSION, homeTeamAttackingTransitionSequenceElement.type());
        assertTrue(homeTeamAttackingTransitionSequenceElement.retainPossession());
        assertEquals(3, homeTeamAttackingTransitionSequenceElement.duration());
        //
        assertEquals(GOAL, homeTeamAttackingPenaltySequenceElement.type());
        assertFalse(homeTeamAttackingPenaltySequenceElement.retainPossession());
        assertEquals(91, homeTeamAttackingPenaltySequenceElement.duration());
        assertEquals(91, homeTeamAttackingPenaltySequenceElement.breakTime());
    }
}
