package org.openengine.vanilla;

import org.junit.Before;
import org.junit.Test;
import org.openengine.vanilla.util.Flags;

import static org.junit.Assert.assertEquals;
import static org.openengine.vanilla.State.HORIZONTAL_DISTANCE_FACTOR;
import static org.openengine.vanilla.State.VERTICAL_DISTANCE_UNIT_FACTOR;

public class TacticalBehaviour442GlobalPassingTest {

    private final double xP = 0.7;
    private TacticalTestOutput testOutput;
    private final double DELTA = 0.15;

    @Before
    public void setUp() {
        Flags.LOGGING = false;
        testOutput = new TacticalTestOutput(Tactics._4_4_2, Tactics._4_4_2, xP);
    }

    @Test
    public void testGoalkeeperActionsProbabilisticAssertions() {
        testOutput.runTest(Position.GK);

        assertEquals(0.1, testOutput.getPossessionOutcomeByPosition(Position.D_R), DELTA);
        assertEquals(0.1, testOutput.getPossessionOutcomeByPosition(Position.D_L), DELTA);
        assertEquals(0.1 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CR), DELTA);
        assertEquals(0.1 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CL), DELTA);
        assertEquals(0.1 * xP / VERTICAL_DISTANCE_UNIT_FACTOR, testOutput.getPossessionOutcomeByPosition(Position.M_R), DELTA);
        assertEquals(0.1 * xP / VERTICAL_DISTANCE_UNIT_FACTOR, testOutput.getPossessionOutcomeByPosition(Position.M_CR), DELTA);
        assertEquals(0.1 * xP / VERTICAL_DISTANCE_UNIT_FACTOR, testOutput.getPossessionOutcomeByPosition(Position.M_CL), DELTA);
        assertEquals(0.1 * xP / VERTICAL_DISTANCE_UNIT_FACTOR, testOutput.getPossessionOutcomeByPosition(Position.M_L), DELTA);
        assertEquals(0.1 * xP / (VERTICAL_DISTANCE_UNIT_FACTOR * 2), testOutput.getPossessionOutcomeByPosition(Position.F_CR), DELTA);
        assertEquals(0.1 * xP / (VERTICAL_DISTANCE_UNIT_FACTOR * 2), testOutput.getPossessionOutcomeByPosition(Position.F_CL), DELTA);
    }

    @Test
    public void testRightBackActionsProbabilisticAssertions() {
        testOutput.runTest(Position.D_R);

        assertEquals(0.2, testOutput.getPossessionOutcomeByPosition(Position.GK), DELTA);
        assertEquals(0.2 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CR), DELTA);
        assertEquals(0.2 * xP / VERTICAL_DISTANCE_UNIT_FACTOR, testOutput.getPossessionOutcomeByPosition(Position.M_R), DELTA);
        assertEquals(0.2 * xP / (VERTICAL_DISTANCE_UNIT_FACTOR * HORIZONTAL_DISTANCE_FACTOR),
                testOutput.getPossessionOutcomeByPosition(Position.M_CR), DELTA);
        assertEquals(0.2 * xP / (2 * VERTICAL_DISTANCE_UNIT_FACTOR * HORIZONTAL_DISTANCE_FACTOR),
                testOutput.getPossessionOutcomeByPosition(Position.F_CR), DELTA);
    }

    @Test
    public void testLeftBackActionsProbabilisticAssertions() {
        testOutput.runTest(Position.D_L);

        assertEquals(0.2, testOutput.getPossessionOutcomeByPosition(Position.GK), DELTA);
        assertEquals(0.2 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CL), DELTA);
        assertEquals(0.2 * xP / VERTICAL_DISTANCE_UNIT_FACTOR, testOutput.getPossessionOutcomeByPosition(Position.M_L), DELTA);
        assertEquals(0.2 * xP / (VERTICAL_DISTANCE_UNIT_FACTOR * HORIZONTAL_DISTANCE_FACTOR), testOutput.getPossessionOutcomeByPosition(Position.M_CL), DELTA);
        assertEquals(0.2 * xP / (2 * VERTICAL_DISTANCE_UNIT_FACTOR * HORIZONTAL_DISTANCE_FACTOR), testOutput.getPossessionOutcomeByPosition(Position.F_CL), DELTA);
    }

    @Test
    public void testCentreRightBackProbabilisticAssertions() {
        testOutput.runTest(Position.D_CR);

        assertEquals(0.17, testOutput.getPossessionOutcomeByPosition(Position.GK), DELTA);
        assertEquals(0.17, testOutput.getPossessionOutcomeByPosition(Position.D_R), DELTA);
        assertEquals(0.17 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CL), DELTA);
        assertEquals(0.17 * xP / VERTICAL_DISTANCE_UNIT_FACTOR, testOutput.getPossessionOutcomeByPosition(Position.M_CR), DELTA);
        assertEquals(0.17 * xP / VERTICAL_DISTANCE_UNIT_FACTOR, testOutput.getPossessionOutcomeByPosition(Position.M_R), DELTA);
        assertEquals(0.17 * xP / (2 * VERTICAL_DISTANCE_UNIT_FACTOR), testOutput.getPossessionOutcomeByPosition(Position.F_CR), DELTA);
    }

    @Test
    public void testCentreLeftBackProbabilisticAssertions() {
        testOutput.runTest(Position.D_CL);

        assertEquals(0.17, testOutput.getPossessionOutcomeByPosition(Position.GK), DELTA);
        assertEquals(0.17, testOutput.getPossessionOutcomeByPosition(Position.D_L), DELTA);
        assertEquals(0.17 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CR), DELTA);
        assertEquals(0.17 * xP / VERTICAL_DISTANCE_UNIT_FACTOR, testOutput.getPossessionOutcomeByPosition(Position.M_CL), DELTA);
        assertEquals(0.17 * xP / VERTICAL_DISTANCE_UNIT_FACTOR, testOutput.getPossessionOutcomeByPosition(Position.M_L), DELTA);
        assertEquals(0.17 * xP / (2 * VERTICAL_DISTANCE_UNIT_FACTOR), testOutput.getPossessionOutcomeByPosition(Position.F_CL), DELTA);
    }

    @Test
    public void testRightMidfielderActionsProbabilisticAssertions() {
        testOutput.runTest(Position.M_R);

        assertEquals(xP * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_CR), DELTA);
        assertEquals(xP * 0.5 / (VERTICAL_DISTANCE_UNIT_FACTOR * HORIZONTAL_DISTANCE_FACTOR), testOutput.getPossessionOutcomeByPosition(Position.F_CR), DELTA);
    }

    @Test
    public void testLeftMidfielderActionsProbabilisticAssertions() {
        testOutput.runTest(Position.M_L);

        assertEquals(xP * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_CL), DELTA);
        assertEquals(xP * 0.5 / 2.8, testOutput.getPossessionOutcomeByPosition(Position.F_CL), DELTA);
    }

    @Test
    public void testCentreRightMidfielderActionsProbabilisticAssertions() {
        testOutput.runTest(Position.M_CR);

        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_R), DELTA);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_CL), DELTA);
        assertEquals(0.33 * xP / 2.0, testOutput.getPossessionOutcomeByPosition(Position.F_CR), DELTA);
    }

    @Test
    public void testCentreLeftMidfielderActionsProbabilisticAssertions() {
        testOutput.runTest(Position.M_CL);

        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_L), DELTA);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_CR), DELTA);
        assertEquals(0.33 * xP / 2.0, testOutput.getPossessionOutcomeByPosition(Position.F_CL), DELTA);
    }

    @Test
    public void testCentreRightForwardActionsProbabilisticAssertions() {
        testOutput.runTest(Position.F_CR);

        assertEquals(xP * 0.5, testOutput.getPossessionOutcomeByPosition(Position.F_CL), DELTA);
        assertEquals(0.5, testOutput.getPossessionOutcomeByPosition(Position.GK), DELTA);
    }

    @Test
    public void testCentreLeftForwardActionsProbabilisticAssertions() {
        testOutput.runTest(Position.F_CL);

        assertEquals(xP * 0.5, testOutput.getPossessionOutcomeByPosition(Position.F_CR), DELTA);
        assertEquals(0.5, testOutput.getPossessionOutcomeByPosition(Position.GK), DELTA);
    }
}
