package org.openengine.vanilla;

import org.junit.Before;
import org.junit.Test;
import org.openengine.vanilla.util.Flags;

import static org.junit.Assert.assertEquals;

public class TacticalBehaviour433Test {

    @Before
    public void setUp() {
        // Flags.LOGGING = true;
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
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_3_3, Tactics._4_3_3);

        testOutput.runTest(Position.D_R);

        assertEquals(0.33, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CR), 0.1);
        assertEquals(0.33 * xP / 1.4, testOutput.getPossessionOutcomeByPosition(Position.M_RC), 0.1);
        assertEquals(0.33 * (1 - xP) * 1.4, testOutput.getPossessionOutcomeByPosition(Position.M_LC), 0.1);
        assertEquals(0.33 + 0.33 * xP + 0.33 * xP / 1.4, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testLeftBackActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_3_3, Tactics._4_3_3);

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
    public void testCentreLeftBackActions() {
        testPlayerBehaviourByPosition(Position.D_CL);
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
