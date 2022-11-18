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
        MatchSequence homeTeamThrowInSequence = homeTeamMatchFlow.getRow(THROW_IN);
        MatchSequence homeTeamAttackingPossessionSequence = homeTeamMatchFlow.getRow(ATTACKING_POSSESSION);
        MatchSequence homeTeamAttackSequence = homeTeamMatchFlow.getRow(ATTACK);
        MatchSequence homeTeamPressureSequence = homeTeamMatchFlow.getRow(PRESSURE);
        MatchSequence homeTeamCornerKickSequence = homeTeamMatchFlow.getRow(CORNER_KICK);
        MatchSequence homeTeamDefensiveTransitionSequence = homeTeamMatchFlow.getRow(DEFENSIVE_TRANSITION);

        MatchDataElement homeTeamKickOffSequenceElement = matchEngine.getNextSequenceElement(homeTeamKickOffSequence, 0);
        MatchDataElement homeTeamPossessionSequenceElement = matchEngine.getNextSequenceElement(homeTeamPossessionSequence, 12);
        MatchDataElement homeTeamTransitionSequenceElement = matchEngine.getNextSequenceElement(homeTeamTransitionSequence, 1);
        MatchDataElement homeTeamAttackingTransitionSequenceElement = matchEngine.getNextSequenceElement(homeTeamAttackingTransitionSequence, 4);
        MatchDataElement homeTeamAttackingPenaltySequenceElement = matchEngine.getNextSequenceElement(homeTeamPenaltySequence, 0);
        MatchDataElement homeTeamThrowInSequenceElement = matchEngine.getNextSequenceElement(homeTeamThrowInSequence, 15);
        MatchDataElement homeTeamAttackingPossessionSequenceElement = matchEngine.getNextSequenceElement(homeTeamAttackingPossessionSequence, 0);
        MatchDataElement homeTeamAttackSequenceElement = matchEngine.getNextSequenceElement(homeTeamAttackSequence, 2);
        MatchDataElement homeTeamPressureSequenceElement = matchEngine.getNextSequenceElement(homeTeamPressureSequence, 5);
        MatchDataElement homeTeamCornerKickSequenceElement = matchEngine.getNextSequenceElement(homeTeamCornerKickSequence, 3);
        MatchDataElement homeTeamDefensiveTransitionSequenceElement = matchEngine.getNextSequenceElement(homeTeamDefensiveTransitionSequence, 4);

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
        //
        assertEquals(POSSESSION, homeTeamThrowInSequenceElement.type());
        assertFalse(homeTeamThrowInSequenceElement.retainPossession());
        assertEquals(23, homeTeamThrowInSequenceElement.duration());
        //
        assertEquals(ATTACK, homeTeamAttackingPossessionSequenceElement.type());
        assertTrue(homeTeamAttackingPossessionSequenceElement.retainPossession());
        assertEquals(55, homeTeamAttackingPossessionSequenceElement.duration());
        assertEquals(55, homeTeamAttackingPossessionSequenceElement.breakTime());
        //
        assertEquals(THROW_IN, homeTeamAttackSequenceElement.type());
        assertTrue(homeTeamAttackSequenceElement.retainPossession());
        assertEquals(8, homeTeamAttackSequenceElement.duration());
        //
        assertEquals(TRANSITION, homeTeamPressureSequenceElement.type());
        assertTrue(homeTeamPressureSequenceElement.retainPossession());
        assertEquals(25, homeTeamPressureSequenceElement.duration());
        assertEquals(25, homeTeamPressureSequenceElement.breakTime());
        //
        assertEquals(THROW_IN, homeTeamCornerKickSequenceElement.type());
        assertTrue(homeTeamCornerKickSequenceElement.retainPossession());
        assertEquals(9, homeTeamCornerKickSequenceElement.duration());
        //
        assertEquals(POSSESSION, homeTeamDefensiveTransitionSequenceElement.type());
        assertFalse(homeTeamDefensiveTransitionSequenceElement.retainPossession());
        assertEquals(44, homeTeamDefensiveTransitionSequenceElement.duration());
    }
}
