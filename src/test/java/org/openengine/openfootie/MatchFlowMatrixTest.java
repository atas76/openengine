package org.openengine.openfootie;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
        MatchSequence homeTeamSequence = homeTeamMatchFlow.getRow(KICK_OFF);

        MatchDataElement matchDataElement = matchEngine.getNextSequenceElement(homeTeamSequence, 0);

        assertEquals(POSSESSION, matchDataElement.type());
        assertTrue(matchDataElement.retainPossession());
        assertEquals(2, matchDataElement.duration());
    }
}
