package org.openengine.vanilla;

import org.junit.Before;
import org.junit.Test;
import org.openengine.vanilla.util.Flags;

import static org.junit.Assert.assertEquals;

public class TacticalBehaviour433Test {

    private Match sampleMatch;
    private final double xP = 0.5;
    private TacticalTestOutput testOutput;

    @Before
    public void setUp() {
        Flags.LOGGING = false;
        sampleMatch = new Match();
        sampleMatch.getState().setXP(xP);
        testOutput = new TacticalTestOutput(Tactics._4_3_3, Tactics._4_3_3);
    }

    @Test
    public void testGoalkeeperActions() {
        Match match = new Match(Tactics._4_3_3, Tactics._4_3_3);
        match.getState().setPossessionTeam(match.getHomeTeam());
        match.getState().setPossessionPlayer(match.getHomeTeam().getGoalkeeper());
        Player goalkeeper = match.getState().getPossessionPlayer();

        Action action = goalkeeper.decide();
        ActionOutcome outcome = match.getState().execute(action);
        match.updateState(outcome);

        System.out.println(action);
        System.out.println(outcome);
        System.out.println(match.getState());
    }

    @Test
    public void testRightBackActions() {
        testPlayerBehaviourByPosition(Position.D_R);
    }

    @Test
    public void testRightBackActionsProbabilisticAssertions() {
        testOutput.runTest(Position.D_R);

        assertEquals(0.33, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CR), 0.1);
        assertEquals(0.33 * xP / 1.4, testOutput.getPossessionOutcomeByPosition(Position.M_RC), 0.1);
        assertEquals(0.33 * (1 - xP) * 1.4, testOutput.getPossessionOutcomeByPosition(Position.M_LC), 0.1);
        assertEquals(0.33 + 0.33 * xP + 0.33 * xP / 1.4, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testLeftBackActionsProbabilisticAssertions() {
        testOutput.runTest(Position.D_L);

        assertEquals(0.33, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CL), 0.1);
        assertEquals(0.33 * xP / 1.4, testOutput.getPossessionOutcomeByPosition(Position.M_LC), 0.1);
        assertEquals(0.33 * (1 - xP) * 1.4, testOutput.getPossessionOutcomeByPosition(Position.M_RC), 0.1);
        assertEquals(0.33 + 0.33 * xP + 0.33 * xP / 1.4, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testCentreRightBackActions() {
        testPlayerBehaviourByPosition(Position.D_CR);
    }

    @Test
    public void testCentreRightBackActionsProbabilisticAssertions() {
        testOutput.runTest(Position.D_CR);

        assertEquals(0.25, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.25, testOutput.getPossessionOutcomeByPosition(Position.D_R), 0.1);
        assertEquals(0.25 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CL), 0.1);
        assertEquals(0.25, testOutput.getPossessionOutcomeByPosition(Position.M_C), 0.1); // Covers both teams
        assertEquals(0.25 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.F_RC), 0.1);
        assertEquals(0.25 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.F_C), 0.1);
        assertEquals(0.5 + 0.5 * xP, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testCentreLeftBackActions() {
        testPlayerBehaviourByPosition(Position.D_CL);
    }

    @Test
    public void testCentreLeftBackActionsProbabilisticAssertions() {
        testOutput.runTest(Position.D_CL);

        assertEquals(0.25, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.25, testOutput.getPossessionOutcomeByPosition(Position.D_L), 0.1);
        assertEquals(0.25 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CR), 0.1);
        assertEquals(0.25, testOutput.getPossessionOutcomeByPosition(Position.M_C), 0.1); // Covers both teams
        assertEquals(0.25 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.F_LC), 0.1);
        assertEquals(0.25 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.F_C), 0.1);
        assertEquals(0.5 + 0.5 * xP, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testLeftBackActions() {
        testPlayerBehaviourByPosition(Position.D_L);
    }

    @Test
    public void testRightCentreMidfielderActions() {
        testPlayerBehaviourByPosition(Position.M_RC);
    }

    @Test
    public void testCentreMidfielderActionsProbabilisticAssertions() {
        testOutput.runTest(Position.M_C);

        assertEquals(0.33, testOutput.getPossessionOutcomeByPosition(Position.M_RC), 0.1); // Covers both teams
        assertEquals(0.33, testOutput.getPossessionOutcomeByPosition(Position.M_LC), 0.1); // Covers both teams
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_C), 0.1);
        assertEquals(0.33 * xP * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_CR), 0.1);
        assertEquals(0.33 * xP * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_CL), 0.1);
        assertEquals(xP, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testCentralMidfielderActions() {
        testPlayerBehaviourByPosition(Position.M_C);
    }

    @Test
    public void testLeftCentreMidfielderActions() {
        testPlayerBehaviourByPosition(Position.M_LC);
    }

    @Test
    public void testRightCentreForwardAction() {
        testPlayerBehaviourByPosition(Position.F_RC);
    }

    @Test
    public void testCentreForwardAction() {
        testPlayerBehaviourByPosition(Position.F_C);
    }

    @Test
    public void testLeftCentreForwardAction() {
        testPlayerBehaviourByPosition(Position.F_LC);
    }

    private static void testPlayerBehaviourByPosition(Position position) {
        Match match = new Match(Tactics._4_3_3, Tactics._4_3_3);
        match.getState().setPossessionTeam(match.getHomeTeam());
        match.getState().setPossessionPlayer(match.getHomeTeam().getPlayerByPosition(position));
        Player player = match.getState().getPossessionPlayer();

        Action action = player.decide();
        ActionOutcome outcome = match.getState().execute(action);
        match.updateState(outcome);

        System.out.println(action);
        System.out.println(outcome);
        System.out.println(match.getState());
    }
}
