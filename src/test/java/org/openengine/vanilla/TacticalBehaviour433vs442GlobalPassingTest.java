package org.openengine.vanilla;

import org.junit.Before;
import org.junit.Test;
import org.openengine.vanilla.util.Flags;

import static org.junit.Assert.assertEquals;
import static org.openengine.vanilla.State.*;
import static org.openengine.vanilla.State.VERTICAL_DISTANCE_WEIGHT;

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

    @Test
    public void testLeftDefenderActionsProbabilisticAssertions() {
        testOutput.runTest(Position.D_L);

        assertEquals(0.17, testOutput.getPossessionOutcomeByPosition(Position.GK), DELTA);
        assertEquals(0.17 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CL), DELTA);
        assertEquals(0.17 * xP / (HORIZONTAL_DISTANCE_UNIT_FACTOR * VERTICAL_DISTANCE_UNIT_FACTOR),
                testOutput.getPossessionOutcomeByPosition(Position.M_LC), DELTA);
        assertEquals(0.17 * xP / (1.5 * HORIZONTAL_DISTANCE_UNIT_FACTOR * VERTICAL_DISTANCE_UNIT_FACTOR),
                testOutput.getPossessionOutcomeByPosition(Position.M_C), DELTA);
        assertEquals(0.17 * xP / (2 * HORIZONTAL_DISTANCE_UNIT_FACTOR * VERTICAL_DISTANCE_UNIT_FACTOR),
                testOutput.getPossessionOutcomeByPosition(Position.F_LC), DELTA);
        assertEquals(0.17 * xP / (2 * HORIZONTAL_DISTANCE_UNIT_FACTOR * VERTICAL_DISTANCE_UNIT_FACTOR * 1.5),
                testOutput.getPossessionOutcomeByPosition(Position.F_C), DELTA);
    }

    @Test
    public void testCentreRightDefenderActionsProbabilisticAssertions() {
        testOutput.runTest(Position.D_CR);

        assertEquals(0.14, testOutput.getPossessionOutcomeByPosition(Position.GK), DELTA);
        assertEquals(0.14, testOutput.getPossessionOutcomeByPosition(Position.D_R), DELTA);
        assertEquals(0.14 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CL), DELTA);
        assertEquals(0.14 * xP / VERTICAL_DISTANCE_UNIT_FACTOR,
                testOutput.getPossessionOutcomeByPosition(Position.M_C), DELTA);
        assertEquals(0.14 * xP / VERTICAL_DISTANCE_UNIT_FACTOR,
                testOutput.getPossessionOutcomeByPosition(Position.M_RC), DELTA);
        assertEquals(0.14 * xP / (2 * VERTICAL_DISTANCE_UNIT_FACTOR),
                testOutput.getPossessionOutcomeByPosition(Position.F_RC), DELTA);
        assertEquals(0.14 * xP / (2 * VERTICAL_DISTANCE_UNIT_FACTOR),
                testOutput.getPossessionOutcomeByPosition(Position.F_C), DELTA);
    }

    @Test
    public void testCentreLeftDefenderActionsProbabilisticAssertions() {
        testOutput.runTest(Position.D_CL);

        assertEquals(0.14, testOutput.getPossessionOutcomeByPosition(Position.GK), DELTA);
        assertEquals(0.14, testOutput.getPossessionOutcomeByPosition(Position.D_L), DELTA);
        assertEquals(0.14 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CR), DELTA);
        assertEquals(0.14 * xP / VERTICAL_DISTANCE_UNIT_FACTOR,
                testOutput.getPossessionOutcomeByPosition(Position.M_C), DELTA);
        assertEquals(0.14 * xP / VERTICAL_DISTANCE_UNIT_FACTOR,
                testOutput.getPossessionOutcomeByPosition(Position.M_LC), DELTA);
        assertEquals(0.14 * xP / (2 * VERTICAL_DISTANCE_UNIT_FACTOR),
                testOutput.getPossessionOutcomeByPosition(Position.F_LC), DELTA);
        assertEquals(0.14 * xP / (2 * VERTICAL_DISTANCE_UNIT_FACTOR),
                testOutput.getPossessionOutcomeByPosition(Position.F_C), DELTA);
    }

    @Test
    public void testRightCentreMidfielderActionsProbabilisticAssertions() {
        testOutput.runTest(Position.M_RC);

        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_C), DELTA);
        assertEquals(0.33 * xP / VERTICAL_DISTANCE_UNIT_FACTOR,
                testOutput.getPossessionOutcomeByPosition(Position.F_RC), DELTA);
        assertEquals(0.33 * xP / (VERTICAL_DISTANCE_UNIT_FACTOR * HORIZONTAL_DISTANCE_UNIT_FACTOR),
                testOutput.getPossessionOutcomeByPosition(Position.F_C), DELTA);
    }
}
