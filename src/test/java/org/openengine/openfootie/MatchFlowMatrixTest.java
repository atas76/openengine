package org.openengine.openfootie;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.openengine.openfootie.MatchPhase.DEFENSIVE_TRANSITION;
import static org.openengine.openfootie.MatchPhase.POSSESSION;
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

        MatchDataElement homeTeamKickOffSequenceElement = matchEngine.getNextSequenceElement(homeTeamKickOffSequence, 0);
        MatchDataElement homeTeamPossessionSequenceElement = matchEngine.getNextSequenceElement(homeTeamPossessionSequence, 12);

        assertEquals(POSSESSION, homeTeamKickOffSequenceElement.type());
        assertTrue(homeTeamKickOffSequenceElement.retainPossession());
        assertEquals(2, homeTeamKickOffSequenceElement.duration());
        //
        assertEquals(DEFENSIVE_TRANSITION, homeTeamPossessionSequenceElement.type());
        assertFalse(homeTeamPossessionSequenceElement.retainPossession());
        assertEquals(8, homeTeamPossessionSequenceElement.duration());
    }
}
