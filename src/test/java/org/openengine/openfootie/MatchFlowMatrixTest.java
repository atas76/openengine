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

        MatchPhaseTransition awayTeamInitialSequenceElement = matchEngine.getNextSequenceElement(awayTeamInitialSequence, 0);
        MatchPhaseTransition awayTeamKickOffSequenceElement = matchEngine.getNextSequenceElement(awayTeamKickOffSequence, 0);
        MatchPhaseTransition awayTeamInterceptionSequenceElement = matchEngine.getNextSequenceElement(awayTeamInterceptionSequence, 0);
        MatchPhaseTransition awayTeamPressureSequenceElement = matchEngine.getNextSequenceElement(awayTeamPressureSequence, 6);
        MatchPhaseTransition awayTeamPossessionSequenceElement = matchEngine.getNextSequenceElement(awayTeamPossessionSequence, 21);
        MatchPhaseTransition awayTeamCornerKickSequenceElement = matchEngine.getNextSequenceElement(awayTeamCornerKickSequence, 4);
        MatchPhaseTransition awayTeamThrowInSequenceElement = matchEngine.getNextSequenceElement(awayTeamThrowInSequence, 2);
        MatchPhaseTransition awayTeamTransitionSequenceElement = matchEngine.getNextSequenceElement(awayTeamTransitionSequence, 5);
        MatchPhaseTransition awayTeamAttackingPossessionSequenceElement = matchEngine.getNextSequenceElement(awayTeamAttackingPossessionSequence, 0);
        MatchPhaseTransition awayTeamAttackSequenceElement = matchEngine.getNextSequenceElement(awayTeamAttackSequence, 6);
        MatchPhaseTransition awayTeamFreeKickSequenceElement = matchEngine.getNextSequenceElement(awayTeamFreeKickSequence, 3);
        MatchPhaseTransition awayTeamCounterAttackSequenceElement = matchEngine.getNextSequenceElement(awayTeamCounterAttackSequence, 1);
        MatchPhaseTransition awayTeamDefensiveTransitionSequenceElement = matchEngine.getNextSequenceElement(awayTeamDefensiveTransitionSequence, 3);
        MatchPhaseTransition awayTeamAttackingTransitionSequenceElement = matchEngine.getNextSequenceElement(awayTeamAttackingTransitionSequence, 3);

        assertEquals(KICK_OFF, awayTeamInitialSequenceElement.outcomeType());
        assertTrue(awayTeamInitialSequenceElement.retainPossession());
        assertEquals(0, awayTeamInitialSequenceElement.duration());
        //
        assertEquals(POSSESSION, awayTeamKickOffSequenceElement.outcomeType());
        assertTrue(awayTeamKickOffSequenceElement.retainPossession());
        assertEquals(4, awayTeamKickOffSequenceElement.duration());
        //
        assertEquals(TRANSITION, awayTeamInterceptionSequenceElement.outcomeType());
        assertFalse(awayTeamInterceptionSequenceElement.retainPossession());
        assertEquals(11, awayTeamInterceptionSequenceElement.duration());
        //
        assertEquals(POSSESSION, awayTeamPressureSequenceElement.outcomeType());
        assertTrue(awayTeamPressureSequenceElement.retainPossession());
        assertEquals(10, awayTeamPressureSequenceElement.duration());
        //
        assertEquals(ATTACK, awayTeamPossessionSequenceElement.outcomeType());
        assertTrue(awayTeamPossessionSequenceElement.retainPossession());
        assertEquals(12, awayTeamPossessionSequenceElement.duration());
        //
        assertEquals(ATTACK, awayTeamCornerKickSequenceElement.outcomeType());
        assertTrue(awayTeamCornerKickSequenceElement.retainPossession());
        assertEquals(3, awayTeamCornerKickSequenceElement.duration());
        //
        assertEquals(POSSESSION, awayTeamThrowInSequenceElement.outcomeType());
        assertTrue(awayTeamThrowInSequenceElement.retainPossession());
        assertEquals(6, awayTeamThrowInSequenceElement.duration());
        //
        assertEquals(ATTACKING_POSSESSION, awayTeamTransitionSequenceElement.outcomeType());
        assertTrue(awayTeamTransitionSequenceElement.retainPossession());
        assertEquals(4, awayTeamTransitionSequenceElement.duration());
        //
        assertEquals(TRANSITION, awayTeamAttackingPossessionSequenceElement.outcomeType());
        assertFalse(awayTeamAttackingPossessionSequenceElement.retainPossession());
        assertEquals(5, awayTeamAttackingPossessionSequenceElement.duration());
        //
        assertEquals(THROW_IN, awayTeamAttackSequenceElement.outcomeType());
        assertTrue(awayTeamAttackSequenceElement.retainPossession());
        assertEquals(25, awayTeamAttackSequenceElement.duration());
        //
        assertEquals(CORNER_KICK, awayTeamFreeKickSequenceElement.outcomeType());
        assertTrue(awayTeamFreeKickSequenceElement.retainPossession());
        assertEquals(32, awayTeamFreeKickSequenceElement.duration());
        assertEquals(0, awayTeamFreeKickSequenceElement.breakTime());
        //
        assertEquals(THROW_IN, awayTeamCounterAttackSequenceElement.outcomeType());
        assertFalse(awayTeamCounterAttackSequenceElement.retainPossession());
        assertEquals(28, awayTeamCounterAttackSequenceElement.duration());
        //
        assertEquals(ATTACKING_TRANSITION, awayTeamDefensiveTransitionSequenceElement.outcomeType());
        assertFalse(awayTeamDefensiveTransitionSequenceElement.retainPossession());
        assertEquals(6, awayTeamDefensiveTransitionSequenceElement.duration());
        //
        assertEquals(ATTACKING_POSSESSION, awayTeamAttackingTransitionSequenceElement.outcomeType());
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

        MatchPhaseTransition homeTeamInitialSequenceElement = matchEngine.getNextSequenceElement(homeTeamInitialSequence, 0);
        MatchPhaseTransition homeTeamKickOffSequenceElement = matchEngine.getNextSequenceElement(homeTeamKickOffSequence, 0);
        MatchPhaseTransition homeTeamPossessionSequenceElement = matchEngine.getNextSequenceElement(homeTeamPossessionSequence, 12);
        MatchPhaseTransition homeTeamTransitionSequenceElement = matchEngine.getNextSequenceElement(homeTeamTransitionSequence, 1);
        MatchPhaseTransition homeTeamAttackingTransitionSequenceElement = matchEngine.getNextSequenceElement(homeTeamAttackingTransitionSequence, 4);
        MatchPhaseTransition homeTeamAttackingPenaltySequenceElement = matchEngine.getNextSequenceElement(homeTeamPenaltySequence, 0);
        MatchPhaseTransition homeTeamThrowInSequenceElement = matchEngine.getNextSequenceElement(homeTeamThrowInSequence, 15);
        MatchPhaseTransition homeTeamAttackingPossessionSequenceElement = matchEngine.getNextSequenceElement(homeTeamAttackingPossessionSequence, 0);
        MatchPhaseTransition homeTeamAttackSequenceElement = matchEngine.getNextSequenceElement(homeTeamAttackSequence, 2);
        MatchPhaseTransition homeTeamPressureSequenceElement = matchEngine.getNextSequenceElement(homeTeamPressureSequence, 5);
        MatchPhaseTransition homeTeamCornerKickSequenceElement = matchEngine.getNextSequenceElement(homeTeamCornerKickSequence, 3);
        MatchPhaseTransition homeTeamDefensiveTransitionSequenceElement = matchEngine.getNextSequenceElement(homeTeamDefensiveTransitionSequence, 4);
        MatchPhaseTransition homeTeamCounterAttackSequenceElement = matchEngine.getNextSequenceElement(homeTeamCounterAttackSequence, 0);
        MatchPhaseTransition homeTeamFreeKickSequenceElement = matchEngine.getNextSequenceElement(homeTeamFreeKickSequence, 2);
        MatchPhaseTransition homeTeamInterceptionSequenceElement = matchEngine.getNextSequenceElement(homeTeamInterceptionSequence, 1);

        assertEquals(KICK_OFF, homeTeamInitialSequenceElement.outcomeType());
        assertTrue(homeTeamInitialSequenceElement.retainPossession());
        assertEquals(0, homeTeamInitialSequenceElement.duration());
        //
        assertEquals(POSSESSION, homeTeamKickOffSequenceElement.outcomeType());
        assertTrue(homeTeamKickOffSequenceElement.retainPossession());
        assertEquals(2, homeTeamKickOffSequenceElement.duration());
        //
        assertEquals(DEFENSIVE_TRANSITION, homeTeamPossessionSequenceElement.outcomeType());
        assertFalse(homeTeamPossessionSequenceElement.retainPossession());
        assertEquals(6, homeTeamPossessionSequenceElement.duration());
        //
        assertEquals(DEFENSIVE_TRANSITION, homeTeamTransitionSequenceElement.outcomeType());
        assertFalse(homeTeamTransitionSequenceElement.retainPossession());
        assertEquals(14, homeTeamTransitionSequenceElement.duration());
        assertEquals(0, homeTeamTransitionSequenceElement.breakTime());
        //
        assertEquals(ATTACKING_POSSESSION, homeTeamAttackingTransitionSequenceElement.outcomeType());
        assertTrue(homeTeamAttackingTransitionSequenceElement.retainPossession());
        assertEquals(10, homeTeamAttackingTransitionSequenceElement.duration());
        //
        assertEquals(GOAL, homeTeamAttackingPenaltySequenceElement.outcomeType());
        assertFalse(homeTeamAttackingPenaltySequenceElement.retainPossession());
        assertEquals(91, homeTeamAttackingPenaltySequenceElement.duration());
        assertEquals(91, homeTeamAttackingPenaltySequenceElement.breakTime());
        //
        assertEquals(POSSESSION, homeTeamThrowInSequenceElement.outcomeType());
        assertFalse(homeTeamThrowInSequenceElement.retainPossession());
        assertEquals(17, homeTeamThrowInSequenceElement.duration());
        //
        assertEquals(ATTACK, homeTeamAttackingPossessionSequenceElement.outcomeType());
        assertTrue(homeTeamAttackingPossessionSequenceElement.retainPossession());
        assertEquals(5, homeTeamAttackingPossessionSequenceElement.duration());
        assertEquals(0, homeTeamAttackingPossessionSequenceElement.breakTime());
        //
        assertEquals(THROW_IN, homeTeamAttackSequenceElement.outcomeType());
        assertTrue(homeTeamAttackSequenceElement.retainPossession());
        assertEquals(20, homeTeamAttackSequenceElement.duration());
        //
        assertEquals(TRANSITION, homeTeamPressureSequenceElement.outcomeType());
        assertTrue(homeTeamPressureSequenceElement.retainPossession());
        assertEquals(6, homeTeamPressureSequenceElement.duration());
        assertEquals(0, homeTeamPressureSequenceElement.breakTime());
        //
        assertEquals(THROW_IN, homeTeamCornerKickSequenceElement.outcomeType());
        assertTrue(homeTeamCornerKickSequenceElement.retainPossession());
        assertEquals(16, homeTeamCornerKickSequenceElement.duration());
        //
        assertEquals(POSSESSION, homeTeamDefensiveTransitionSequenceElement.outcomeType());
        assertFalse(homeTeamDefensiveTransitionSequenceElement.retainPossession());
        assertEquals(34, homeTeamDefensiveTransitionSequenceElement.duration());
        //
        assertEquals(POSSESSION, homeTeamCounterAttackSequenceElement.outcomeType());
        assertTrue(homeTeamCounterAttackSequenceElement.retainPossession());
        assertEquals(18, homeTeamCounterAttackSequenceElement.duration());
        //
        assertEquals(PRESSURE, homeTeamFreeKickSequenceElement.outcomeType());
        assertFalse(homeTeamFreeKickSequenceElement.retainPossession());
        assertEquals(19, homeTeamFreeKickSequenceElement.duration());
        assertEquals(0, homeTeamFreeKickSequenceElement.breakTime());
        //
        assertEquals(TRANSITION, homeTeamInterceptionSequenceElement.outcomeType());
        assertTrue(homeTeamInterceptionSequenceElement.retainPossession());
        assertEquals(4, homeTeamInterceptionSequenceElement.duration());
    }
}
