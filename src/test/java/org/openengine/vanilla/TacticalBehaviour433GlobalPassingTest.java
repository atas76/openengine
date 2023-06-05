package org.openengine.vanilla;

import org.junit.Before;
import org.junit.Test;
import org.openengine.vanilla.util.Flags;

import static org.junit.Assert.assertEquals;

public class TacticalBehaviour433GlobalPassingTest {

    private final double xP = 0.7;
    private TacticalTestOutput testOutput;
    private final double DELTA = 0.15;

    @Before
    public void setUp() {
        Flags.LOGGING = false;
        testOutput = new TacticalTestOutput(Tactics._4_3_3, Tactics._4_3_3, xP);
    }

    @Test
    public void testGoalkeeperActionsProbabilisticAssertions() {
        testOutput.runTest(Position.GK);

        assertEquals(0.1 * xP * 2.0, testOutput.getPossessionOutcomeByPosition(Position.D_R), DELTA);
        assertEquals(0.1 * xP * 2.0, testOutput.getPossessionOutcomeByPosition(Position.D_L), DELTA);
        assertEquals(0.1 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CR), DELTA);
        assertEquals(0.1 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CL), DELTA);
        assertEquals(0.05 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_RC), DELTA);
        assertEquals(0.05 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_C), DELTA);
        assertEquals(0.05 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_LC), DELTA);
        assertEquals(0.033 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_RC), DELTA);
        assertEquals(0.033 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_C), DELTA);
        assertEquals(0.033 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_LC), DELTA);
    }
}
