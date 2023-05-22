package org.openengine.vanilla;

import org.junit.Before;
import org.junit.Test;
import org.openengine.vanilla.util.Flags;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TacticalBehaviour442Test {

    @Before
    public void setUp() {
        Flags.LOGGING = false;
    }

    @Test
    public void testGoalkeeperActions() {
        Match match = new Match();
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

    private final static int SAMPLE_SIZE = 100;

    @Test
    public void testGoalkeeperActionsProbabilisticAssertions() {

        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        Team homeTeam = sampleMatch.getHomeTeam();
        Team awayTeam = sampleMatch.getAwayTeam();

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

        assertEquals(0.25, actionOutcomes.get(Position.D_R) / (double) SAMPLE_SIZE, 0.1);
        assertEquals(0.25, actionOutcomes.get(Position.D_L) / (double) SAMPLE_SIZE, 0.1);
        assertEquals(0.25 * xP, actionOutcomes.get(Position.D_CR) / (double) SAMPLE_SIZE, 0.1);
        assertEquals(0.25 * xP, actionOutcomes.get(Position.D_CL) / (double) SAMPLE_SIZE, 0.1);
        assertEquals(0.25 * (1 - xP), actionOutcomes.get(Position.F_CR) / (double) SAMPLE_SIZE, 0.1);
        assertEquals(0.25 * (1 - xP), actionOutcomes.get(Position.F_CL) / (double) SAMPLE_SIZE, 0.1);
        assertEquals(0.5 + 0.5 * xP, matchStates.get(homeTeam) / (double) SAMPLE_SIZE, 0.1);
        assertEquals(0.5 * (1 - xP), matchStates.get(awayTeam) / (double) SAMPLE_SIZE, 0.1);
    }

    @Test
    public void testRightBackActions() {
        testPlayerBehaviourByPosition(Position.D_R);
    }

    @Test
    public void testRightBackActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput();

        testOutput.runTest(Position.D_R);

        assertEquals(0.33, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CR), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_R), 0.1);
        assertEquals(0.33 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.F_CL), 0.1);
        assertEquals(0.33 + 0.66 * xP, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testLeftBackActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput();

        testOutput.runTest(Position.D_L);

        assertEquals(0.33, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CL), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_L), 0.1);
        assertEquals(0.33 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.F_CR), 0.1);
        assertEquals(0.33 + 0.66 * xP, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testCentreRightBackActions() {
        testPlayerBehaviourByPosition(Position.D_CR);
    }

    @Test
    public void testCentreRightBackProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput();

        testOutput.runTest(Position.D_CR);

        assertEquals(0.25, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.25, testOutput.getPossessionOutcomeByPosition(Position.D_R), 0.1);
        assertEquals(0.25 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CL), 0.1);
        assertEquals(0.25 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_CR), 0.1);
        assertEquals(0.25 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.F_CR), 0.1);
        assertEquals(0.5 + 0.5 * xP, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testCentreLeftBackProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput();

        testOutput.runTest(Position.D_CL);

        assertEquals(0.25, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.25, testOutput.getPossessionOutcomeByPosition(Position.D_L), 0.1);
        assertEquals(0.25 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CR), 0.1);
        assertEquals(0.25 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_CL), 0.1);
        assertEquals(0.25 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.F_CL), 0.1);
        assertEquals(0.5 + 0.5 * xP, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
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
    public void testRightMidfielderActions() {
        testPlayerBehaviourByPosition(Position.M_R);
    }

    @Test
    public void testRightMidfielderActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput();

        testOutput.runTest(Position.M_R);

        assertEquals(xP, testOutput.getPossessionOutcomeByPosition(Position.M_CR), 0.1);
        assertEquals(1 - xP, testOutput.getPossessionOutcomeByPosition(Position.M_CL), 0.1);
        assertEquals(xP, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testLeftMidfielderActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput();

        testOutput.runTest(Position.M_L);

        assertEquals(xP, testOutput.getPossessionOutcomeByPosition(Position.M_CL), 0.1);
        assertEquals(1 - xP, testOutput.getPossessionOutcomeByPosition(Position.M_CR), 0.1);
        assertEquals(xP, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testCentreRightMidfielderActions() {
        testPlayerBehaviourByPosition(Position.M_CR);
    }

    @Test
    public void testCentreRightMidfielderActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput();

        testOutput.runTest(Position.M_CR);

        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_R), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_CL), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_CR), 0.1);
        assertEquals(0.33 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.M_L), 0.1);
        assertEquals(0.33 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.M_CL), 0.1);
        assertEquals(0.33 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.D_CL), 0.1);
        assertEquals(xP, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testCentreLeftMidfielderActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput();

        testOutput.runTest(Position.M_CL);

        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_L), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_CR), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_CL), 0.1);
        assertEquals(0.33 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.M_R), 0.1);
        assertEquals(0.33 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.M_CR), 0.1);
        assertEquals(0.33 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.D_CR), 0.1);
        assertEquals(xP, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testCenterLeftMidfielderActions() {
        testPlayerBehaviourByPosition(Position.M_CL);
    }

    @Test
    public void testLeftMidfielderActions() {
        testPlayerBehaviourByPosition(Position.M_L);
    }

    @Test
    public void testCentreRightForwardActions() {
        testPlayerBehaviourByPosition(Position.F_CR);
    }

    @Test
    public void testCentreRightForwardActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput();

        testOutput.runTest(Position.F_CR);

        assertEquals(xP * 0.5, testOutput.getPossessionOutcomeByPosition(Position.F_CL), 0.1);
        assertEquals(0.5, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals((1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_CR), 0.1);
        assertEquals(xP * 0.5, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testCentreLeftForwardActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput();

        testOutput.runTest(Position.F_CL);

        assertEquals(xP * 0.5, testOutput.getPossessionOutcomeByPosition(Position.F_CR), 0.1);
        assertEquals(0.5, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals((1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.D_CL), 0.1);
        assertEquals(xP * 0.5, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testCentreLeftForwardActions() {
        testPlayerBehaviourByPosition(Position.F_CL);
    }

    private static void testPlayerBehaviourByPosition(Position position) {
        Match match = new Match();
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
