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
        assertEquals(0.33 + 0.33 * xP + 0.33 * xP / 1.4, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testLeftBackActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_3_3, Tactics._4_4_2);

        testOutput.runTest(Position.D_L);

        assertEquals(0.33, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CL), 0.1);
        assertEquals(0.33 * xP / 1.4, testOutput.getPossessionOutcomeByPosition(Position.M_LC), 0.1);
        assertEquals(0.33 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.F_CR), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5 * 1.4, testOutput.getPossessionOutcomeByPosition(Position.M_R), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5 * 1.4, testOutput.getPossessionOutcomeByPosition(Position.M_CR), 0.1);
        assertEquals(0.33 + 0.33 * xP + 0.33 * xP / 1.4, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testCentreRightBackActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_3_3, Tactics._4_4_2);

        testOutput.runTest(Position.D_CR);

        assertEquals(0.25, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.25, testOutput.getPossessionOutcomeByPosition(Position.D_R), 0.1);
        assertEquals(0.25 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CL), 0.1);
        assertEquals(0.25 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.F_CR), 0.1);
        assertEquals(0.25 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_C), 0.1);
        assertEquals(0.25 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_CR), 0.1);
        assertEquals(0.25 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_CL), 0.1);
    }

    @Test
    public void testCentreLeftBackActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_3_3, Tactics._4_4_2);

        testOutput.runTest(Position.D_CL);

        assertEquals(0.25, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.25, testOutput.getPossessionOutcomeByPosition(Position.D_L), 0.1);
        assertEquals(0.25 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CR), 0.1);
        assertEquals(0.25 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.F_CL), 0.1);
        assertEquals(0.25 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_C), 0.1);
        assertEquals(0.25 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_CR), 0.1);
        assertEquals(0.25 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_CL), 0.1);
    }

    @Test
    public void testMidfielderCentreActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_3_3, Tactics._4_4_2);

        testOutput.runTest(Position.M_C);

        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_RC), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_CL), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_L), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_LC), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_CR), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_R), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_C), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_CR), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_CL), 0.1);
    }

    @Test
    public void testMidfielderRightCentreActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_3_3, Tactics._4_4_2);

        testOutput.runTest(Position.M_RC);

        assertEquals(0.5 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_C), 0.1);
        assertEquals(0.5 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_CR), 0.1);
        assertEquals(0.5 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_CL), 0.1);
        assertEquals(0.5 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_RC), 0.1);
        assertEquals(0.5 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_CL), 0.1);
        assertEquals(0.5 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_L), 0.1);
    }

    @Test
    public void testMidfielderLeftCentreActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_3_3, Tactics._4_4_2);

        testOutput.runTest(Position.M_LC);

        assertEquals(0.5 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_C), 0.1);
        assertEquals(0.5 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_CR), 0.1);
        assertEquals(0.5 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_CL), 0.1);
        assertEquals(0.5 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_LC), 0.1);
        assertEquals(0.5 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_CR), 0.1);
        assertEquals(0.5 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_R), 0.1);
    }

    @Test
    public void testCentreForwardActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_3_3, Tactics._4_4_2);

        testOutput.runTest(Position.F_C);

        assertEquals(0.33, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_RC), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_LC), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_CR), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_CL), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_R), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_L), 0.1);
        assertEquals(0.33 * xP * 2, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testRightCentreForwardActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_3_3, Tactics._4_4_2);

        testOutput.runTest(Position.F_RC);

        assertEquals(0.5, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.5 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_C), 0.1);
        assertEquals(0.5 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_CR), 0.1);
        assertEquals(0.5 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_CL), 0.1);
        assertEquals(0.5 * xP, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testLeftCentreForwardActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_3_3, Tactics._4_4_2);

        testOutput.runTest(Position.F_LC);

        assertEquals(0.5, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.5 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_C), 0.1);
        assertEquals(0.5 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_CR), 0.1);
        assertEquals(0.5 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_CL), 0.1);
        assertEquals(0.5 * xP, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }
  }