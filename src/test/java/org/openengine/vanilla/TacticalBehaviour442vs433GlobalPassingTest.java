package org.openengine.vanilla;

import org.junit.Before;
import org.junit.Test;
import org.openengine.vanilla.util.Flags;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TacticalBehaviour442vs433GlobalPassingTest {

    private final double xP = 0.7;
    private TacticalTestOutput testOutput;
    private final double DELTA = 0.15;
    private final static int SAMPLE_SIZE = 100;

    @Before
    public void setUp() {
        Flags.LOGGING = false;
        testOutput = new TacticalTestOutput(Tactics._4_4_2, Tactics._4_3_3, xP);
    }

    @Test
    public void testGoalkeeperActionsProbabilisticAssertions() {

        Match sampleMatch = new Match();
        sampleMatch.getState().setXP(xP);

        Map<Position, Integer> actionOutcomes = new HashMap<>();
        Map<Team, Integer> matchStates = new HashMap<>();

        for (int i = 0; i < SAMPLE_SIZE; i++) {
            Match match = new Match();
            match.getState().setPossessionTeam(match.getHomeTeam());
            match.getState().setPossessionPlayer(match.getHomeTeam().getGoalkeeper());
            Player goalkeeper = match.getState().getPossessionPlayer();

            Action action = goalkeeper.decide();
            ActionOutcome outcome = match.getState().execute(action);
            match.updateState(outcome);

            assertEquals(ActionType.Pass, action.getType());
            actionOutcomes.putIfAbsent(outcome.getPossessionPlayer().getPosition(), 0);
            actionOutcomes.put(outcome.getPossessionPlayer().getPosition(),
                    actionOutcomes.get(outcome.getPossessionPlayer().getPosition()) + 1);
            matchStates.putIfAbsent(match.getState().getPossessionTeam(), 0);
            matchStates.put(match.getState().getPossessionTeam(),
                    matchStates.get(match.getState().getPossessionTeam()) + 1);
        }

        assertEquals(0.1 * xP * 0.5, actionOutcomes.get(Position.D_R) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.1 * xP * 0.5, actionOutcomes.get(Position.D_L) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.1 * xP, actionOutcomes.get(Position.D_CR) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.1 * xP, actionOutcomes.get(Position.D_CL) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.05 * xP * 0.5, actionOutcomes.get(Position.M_R) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.05 * xP, actionOutcomes.get(Position.M_CR) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.05 * xP, actionOutcomes.get(Position.M_CL) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.05 * xP * 0.5, actionOutcomes.get(Position.M_L) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.033 * xP, actionOutcomes.get(Position.F_CR) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.033 * xP, actionOutcomes.get(Position.F_CL) / (double) SAMPLE_SIZE, DELTA);
    }

    @Test
    public void testRightBackActionsProbabilisticAssertions() {
        testOutput.runTest(Position.D_R);

        assertEquals(0.2, testOutput.getPossessionOutcomeByPosition(Position.GK), DELTA);
        assertEquals(0.2 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CR), DELTA);
        assertEquals(0.2 * xP * 2.0, testOutput.getPossessionOutcomeByPosition(Position.M_R), DELTA);
        assertEquals(0.2 * xP / 1.4, testOutput.getPossessionOutcomeByPosition(Position.M_CR), DELTA);
        assertEquals(0.2 * xP / 2.8, testOutput.getPossessionOutcomeByPosition(Position.F_CR), DELTA);
    }

    @Test
    public void testLeftBackActionsProbabilisticAssertions() {
        testOutput.runTest(Position.D_L);

        assertEquals(0.2, testOutput.getPossessionOutcomeByPosition(Position.GK), DELTA);
        assertEquals(0.2 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CL), DELTA);
        assertEquals(0.2 * xP * 2.0, testOutput.getPossessionOutcomeByPosition(Position.M_L), DELTA);
        assertEquals(0.2 * xP / 1.4, testOutput.getPossessionOutcomeByPosition(Position.M_CL), DELTA);
        assertEquals(0.2 * xP / 2.8, testOutput.getPossessionOutcomeByPosition(Position.F_CL), DELTA);
    }

    @Test
    public void testCentreRightBackProbabilisticAssertions() {
        testOutput.runTest(Position.D_CR);

        assertEquals(0.17, testOutput.getPossessionOutcomeByPosition(Position.GK), DELTA);
        assertEquals(0.17 * xP * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_R), DELTA);
        assertEquals(0.17 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CL), DELTA);
        assertEquals(0.17 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_CR), DELTA);
        assertEquals(0.17 * xP * 0.5 / 1.4, testOutput.getPossessionOutcomeByPosition(Position.M_R), DELTA);
        assertEquals(0.17 * xP / 2.0, testOutput.getPossessionOutcomeByPosition(Position.F_CR), DELTA);
    }

    @Test
    public void testCentreLeftBackProbabilisticAssertions() {
        testOutput.runTest(Position.D_CL);

        assertEquals(0.17, testOutput.getPossessionOutcomeByPosition(Position.GK), DELTA);
        assertEquals(0.17 * xP * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_L), DELTA);
        assertEquals(0.17 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CR), DELTA);
        assertEquals(0.17 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_CL), DELTA);
        assertEquals(0.17 * xP * 0.5 / 1.4, testOutput.getPossessionOutcomeByPosition(Position.M_L), DELTA);
        assertEquals(0.17 * xP / 2.0, testOutput.getPossessionOutcomeByPosition(Position.F_CL), DELTA);
    }

    @Test
    public void testRightMidfielderActionsProbabilisticAssertions() {
        testOutput.runTest(Position.M_R);

        assertEquals(xP * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_CR), DELTA);
        assertEquals(xP * 0.5 / 1.4, testOutput.getPossessionOutcomeByPosition(Position.F_CR), DELTA);
    }

    @Test
    public void testLeftMidfielderActionsProbabilisticAssertions() {
        testOutput.runTest(Position.M_L);

        assertEquals(xP * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_CL), DELTA);
        assertEquals(xP * 0.5 / 1.4, testOutput.getPossessionOutcomeByPosition(Position.F_CL), DELTA);
    }

    @Test
    public void testCentreRightMidfielderActionsProbabilisticAssertions() {
        testOutput.runTest(Position.M_CR);

        assertEquals(0.33 * xP * 2.0, testOutput.getPossessionOutcomeByPosition(Position.M_R), DELTA);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_CL), DELTA);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_CR), DELTA);
    }

    @Test
    public void testCentreLeftMidfielderActionsProbabilisticAssertions() {
        testOutput.runTest(Position.M_CL);

        assertEquals(0.33 * xP * 2.0, testOutput.getPossessionOutcomeByPosition(Position.M_L), DELTA);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_CR), DELTA);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_CL), DELTA);
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
