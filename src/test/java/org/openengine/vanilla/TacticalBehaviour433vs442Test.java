package org.openengine.vanilla;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TacticalBehaviour433vs442Test {

    @Test
    public void testRightBackActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_3_3, Tactics._4_4_2);

        testOutput.runTest(Position.D_R);

        assertEquals(0.33, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CR), 0.1);
        assertEquals(0.33 * xP / 1.4, testOutput.getPossessionOutcomeByPosition(Position.M_RC), 0.1);
        assertEquals(0.33 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.F_CL), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5 * 1.4, testOutput.getPossessionOutcomeByPosition(Position.M_L), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5 * 1.4, testOutput.getPossessionOutcomeByPosition(Position.M_CL), 0.1);
    }
}
