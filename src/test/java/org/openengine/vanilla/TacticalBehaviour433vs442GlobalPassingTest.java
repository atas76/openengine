package org.openengine.vanilla;

import org.junit.Before;
import org.junit.Test;
import org.openengine.vanilla.util.Flags;

import static org.junit.Assert.assertEquals;
import static org.openengine.vanilla.State.HORIZONTAL_DISTANCE_UNIT_FACTOR;
import static org.openengine.vanilla.State.VERTICAL_DISTANCE_UNIT_FACTOR;

public class TacticalBehaviour433vs442GlobalPassingTest {

    private final double xP = State.xP;
    private TacticalTestOutput testOutput;
    private final double DELTA = 0.15;

    @Before
    public void setUp() {
        Flags.LOGGING = false;
        testOutput = new TacticalTestOutput(Tactics._4_3_3, Tactics._4_4_2);
    }

    @Test
    public void testGoalkeeperActionsProbabilisticAssertions() {
        testOutput.runTest(Position.GK);

        assertEquals(0.1 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_R), DELTA);
        assertEquals(0.1 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_L), DELTA);
        assertEquals(0.1 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CR), DELTA);
        assertEquals(0.1 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CL), DELTA);
        assertEquals(0.1 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_RC), DELTA);
        assertEquals(0.1 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_LC), DELTA);
        assertEquals(0.1 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_C), DELTA);
        assertEquals(0.1 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_RC), DELTA);
        assertEquals(0.1 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_LC), DELTA);
        assertEquals(0.1 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_C), DELTA);
    }

    @Test
    public void testRightDefenderActionsProbabilisticAssertions() {
        testOutput.runTest(Position.D_R);

        assertEquals(0.17, testOutput.getPossessionOutcomeByPosition(Position.GK), DELTA);
        assertEquals(0.17 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CR), DELTA);
        assertEquals(0.17 * xP / HORIZONTAL_DISTANCE_UNIT_FACTOR * VERTICAL_DISTANCE_UNIT_FACTOR,
                testOutput.getPossessionOutcomeByPosition(Position.M_RC), DELTA);
        assertEquals(0.17 * xP / (HORIZONTAL_DISTANCE_UNIT_FACTOR * VERTICAL_DISTANCE_UNIT_FACTOR * 1.5),
                testOutput.getPossessionOutcomeByPosition(Position.M_C), DELTA);
        assertEquals(0.17 * xP / (2 * HORIZONTAL_DISTANCE_UNIT_FACTOR * VERTICAL_DISTANCE_UNIT_FACTOR),
                testOutput.getPossessionOutcomeByPosition(Position.F_RC), DELTA);
        assertEquals(0.17 * xP / (2 * HORIZONTAL_DISTANCE_UNIT_FACTOR * VERTICAL_DISTANCE_UNIT_FACTOR * 1.5),
                testOutput.getPossessionOutcomeByPosition(Position.F_C), DELTA);
    }
}
