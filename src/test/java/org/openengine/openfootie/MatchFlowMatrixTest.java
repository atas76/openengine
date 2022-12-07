package org.openengine.openfootie;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.openengine.openfootie.MatchEvent.GOAL;
import static org.openengine.openfootie.MatchEvent.INTERCEPTION;
import static org.openengine.openfootie.MatchPhase.*;
import static org.openengine.openfootie.SetPiece.*;
import static org.openengine.openfootie.Special.MAIN;

public class MatchFlowMatrixTest {

    private MatchFlowMatrix homeTeamMatchFlow = MatchFlowMatrixRepository.L_CLF19;
    private MatchFlowMatrix awayTeamMatchFlow = MatchFlowMatrixRepository.T_CLF19;
    private MatchEngine matchEngine = new MatchEngine();

    @BeforeClass
    public static void setUp() {
        MatchFlowMatrixRepository.load();
    }

    @Test
    public void testMatchFlowMatrixAwayTeam() {
        MatchSequence awayTeamInitialSequence = awayTeamMatchFlow.getRow(MAIN);
        MatchSequence awayTeamKickOffSequence = awayTeamMatchFlow.getRow(KICK_OFF);
        MatchSequence awayTeamInterceptionSequence = awayTeamMatchFlow.getRow(INTERCEPTION);
        MatchSequence awayTeamPressureSequence = awayTeamMatchFlow.getRow(PRESSURE);
        MatchSequence awayTeamPossessionSequence = awayTeamMatchFlow.getRow(POSSESSION);
        MatchSequence awayTeamCornerKickSequence = awayTeamMatchFlow.getRow(CORNER_KICK);
        MatchSequence awayTeamThrowInSequence = awayTeamMatchFlow.getRow(THROW_IN);
        MatchSequence awayTeamTransitionSequence = awayTeamMatchFlow.getRow(TRANSITION);
        MatchSequence awayTeamAttackingPossessionSequence = awayTeamMatchFlow.getRow(ATTACKING_POSSESSION);
        MatchSequence awayTeamAttackSequence = awayTeamMatchFlow.getRow(ATTACK);
        MatchSequence awayTeamFreeKickSequence = awayTeamMatchFlow.getRow(FREE_KICK);
        MatchSequence awayTeamCounterAttackSequence = awayTeamMatchFlow.getRow(COUNTER_ATTACK);
        MatchSequence awayTeamDefensiveTransitionSequence = awayTeamMatchFlow.getRow(DEFENSIVE_TRANSITION);
        MatchSequence awayTeamAttackingTransitionSequence = awayTeamMatchFlow.getRow(ATTACKING_TRANSITION);

        MatchDataElement awayTeamInitialSequenceElement = matchEngine.getNextSequenceElement(awayTeamInitialSequence, 0);
        MatchDataElement awayTeamKickOffSequenceElement = matchEngine.getNextSequenceElement(awayTeamKickOffSequence, 0);
        MatchDataElement awayTeamInterceptionSequenceElement = matchEngine.getNextSequenceElement(awayTeamInterceptionSequence, 0);
        MatchDataElement awayTeamPressureSequenceElement = matchEngine.getNextSequenceElement(awayTeamPressureSequence, 6);
        MatchDataElement awayTeamPossessionSequenceElement = matchEngine.getNextSequenceElement(awayTeamPossessionSequence, 21);
        MatchDataElement awayTeamCornerKickSequenceElement = matchEngine.getNextSequenceElement(awayTeamCornerKickSequence, 4);
        MatchDataElement awayTeamThrowInSequenceElement = matchEngine.getNextSequenceElement(awayTeamThrowInSequence, 2);
        MatchDataElement awayTeamTransitionSequenceElement = matchEngine.getNextSequenceElement(awayTeamTransitionSequence, 5);
        MatchDataElement awayTeamAttackingPossessionSequenceElement = matchEngine.getNextSequenceElement(awayTeamAttackingPossessionSequence, 0);
        MatchDataElement awayTeamAttackSequenceElement = matchEngine.getNextSequenceElement(awayTeamAttackSequence, 6);
        MatchDataElement awayTeamFreeKickSequenceElement = matchEngine.getNextSequenceElement(awayTeamFreeKickSequence, 3);
        MatchDataElement awayTeamCounterAttackSequenceElement = matchEngine.getNextSequenceElement(awayTeamCounterAttackSequence, 1);
        MatchDataElement awayTeamDefensiveTransitionSequenceElement = matchEngine.getNextSequenceElement(awayTeamDefensiveTransitionSequence, 3);
        MatchDataElement awayTeamAttackingTransitionSequenceElement = matchEngine.getNextSequenceElement(awayTeamAttackingTransitionSequence, 3);

        assertEquals(KICK_OFF, awayTeamInitialSequenceElement.type());
        assertTrue(awayTeamInitialSequenceElement.retainPossession());
        assertEquals(4, awayTeamInitialSequenceElement.duration());
        //
        assertEquals(POSSESSION, awayTeamKickOffSequenceElement.type());
        assertTrue(awayTeamKickOffSequenceElement.retainPossession());
        assertEquals(6, awayTeamKickOffSequenceElement.duration());
        //
        assertEquals(TRANSITION, awayTeamInterceptionSequenceElement.type());
        assertFalse(awayTeamInterceptionSequenceElement.retainPossession());
        assertEquals(5, awayTeamInterceptionSequenceElement.duration());
        //
        assertEquals(POSSESSION, awayTeamPressureSequenceElement.type());
        assertTrue(awayTeamPressureSequenceElement.retainPossession());
        assertEquals(23, awayTeamPressureSequenceElement.duration());
        //
        assertEquals(ATTACK, awayTeamPossessionSequenceElement.type());
        assertTrue(awayTeamPossessionSequenceElement.retainPossession());
        assertEquals(17, awayTeamPossessionSequenceElement.duration());
        //
        assertEquals(ATTACK, awayTeamCornerKickSequenceElement.type());
        assertTrue(awayTeamCornerKickSequenceElement.retainPossession());
        assertEquals(25, awayTeamCornerKickSequenceElement.duration());
        //
        assertEquals(POSSESSION, awayTeamThrowInSequenceElement.type());
        assertTrue(awayTeamThrowInSequenceElement.retainPossession());
        assertEquals(23, awayTeamThrowInSequenceElement.duration());
        //
        assertEquals(ATTACKING_POSSESSION, awayTeamTransitionSequenceElement.type());
        assertTrue(awayTeamTransitionSequenceElement.retainPossession());
        assertEquals(5, awayTeamTransitionSequenceElement.duration());
        //
        assertEquals(TRANSITION, awayTeamAttackingPossessionSequenceElement.type());
        assertFalse(awayTeamAttackingPossessionSequenceElement.retainPossession());
        assertEquals(9, awayTeamAttackingPossessionSequenceElement.duration());
        //
        assertEquals(THROW_IN, awayTeamAttackSequenceElement.type());
        assertTrue(awayTeamAttackSequenceElement.retainPossession());
        assertEquals(15, awayTeamAttackSequenceElement.duration());
        //
        assertEquals(CORNER_KICK, awayTeamFreeKickSequenceElement.type());
        assertTrue(awayTeamFreeKickSequenceElement.retainPossession());
        assertEquals(43, awayTeamFreeKickSequenceElement.duration());
        assertEquals(43, awayTeamFreeKickSequenceElement.breakTime());
        //
        assertEquals(THROW_IN, awayTeamCounterAttackSequenceElement.type());
        assertFalse(awayTeamCounterAttackSequenceElement.retainPossession());
        assertEquals(10, awayTeamCounterAttackSequenceElement.duration());
        //
        assertEquals(ATTACKING_TRANSITION, awayTeamDefensiveTransitionSequenceElement.type());
        assertFalse(awayTeamDefensiveTransitionSequenceElement.retainPossession());
        assertEquals(35, awayTeamDefensiveTransitionSequenceElement.duration());
        //
        assertEquals(ATTACKING_POSSESSION, awayTeamAttackingTransitionSequenceElement.type());
        assertTrue(awayTeamAttackingTransitionSequenceElement.retainPossession());
        assertEquals(7, awayTeamAttackingTransitionSequenceElement.duration());
    }

    @Test
    public void testMatchFlowMatrixHomeTeam() {
        MatchSequence homeTeamInitialSequence = homeTeamMatchFlow.getRow(MAIN);
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
        MatchSequence homeTeamCounterAttackSequence = homeTeamMatchFlow.getRow(COUNTER_ATTACK);
        MatchSequence homeTeamFreeKickSequence = homeTeamMatchFlow.getRow(FREE_KICK);
        MatchSequence homeTeamInterceptionSequence = homeTeamMatchFlow.getRow(INTERCEPTION);

        MatchDataElement homeTeamInitialSequenceElement = matchEngine.getNextSequenceElement(homeTeamInitialSequence, 0);
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
        MatchDataElement homeTeamCounterAttackSequenceElement = matchEngine.getNextSequenceElement(homeTeamCounterAttackSequence, 0);
        MatchDataElement homeTeamFreeKickSequenceElement = matchEngine.getNextSequenceElement(homeTeamFreeKickSequence, 2);
        MatchDataElement homeTeamInterceptionSequenceElement = matchEngine.getNextSequenceElement(homeTeamInterceptionSequence, 1);

        assertEquals(KICK_OFF, homeTeamInitialSequenceElement.type());
        assertTrue(homeTeamInitialSequenceElement.retainPossession());
        assertEquals(2, homeTeamInitialSequenceElement.duration());
        //
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
        //
        assertEquals(POSSESSION, homeTeamCounterAttackSequenceElement.type());
        assertTrue(homeTeamCounterAttackSequenceElement.retainPossession());
        assertEquals(8, homeTeamCounterAttackSequenceElement.duration());
        //
        assertEquals(PRESSURE, homeTeamFreeKickSequenceElement.type());
        assertFalse(homeTeamFreeKickSequenceElement.retainPossession());
        assertEquals(30, homeTeamFreeKickSequenceElement.duration());
        assertEquals(30, homeTeamFreeKickSequenceElement.breakTime());
        //
        assertEquals(TRANSITION, homeTeamInterceptionSequenceElement.type());
        assertTrue(homeTeamInterceptionSequenceElement.retainPossession());
        assertEquals(4, homeTeamInterceptionSequenceElement.duration());
    }
}
