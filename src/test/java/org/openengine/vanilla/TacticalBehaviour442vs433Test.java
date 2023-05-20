package org.openengine.vanilla;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TacticalBehaviour442vs433Test {

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
            Match match = new Match(Tactics._4_4_2, Tactics._4_3_3);
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

        assertEquals(0.25 * xP * 2, actionOutcomes.get(Position.D_R) / (double) SAMPLE_SIZE, 0.1);
        assertEquals(0.25 * xP * 2, actionOutcomes.get(Position.D_L) / (double) SAMPLE_SIZE, 0.1);
        assertEquals(0.25 * xP, actionOutcomes.get(Position.D_CR) / (double) SAMPLE_SIZE, 0.1);
        assertEquals(0.25 * xP, actionOutcomes.get(Position.D_CL) / (double) SAMPLE_SIZE, 0.1);
        assertEquals(0.25 * (1 - xP) * 0.5, actionOutcomes.get(Position.F_RC) / (double) SAMPLE_SIZE, 0.1);
        assertEquals(0.25 * (1 - xP) * 0.5, actionOutcomes.get(Position.F_C) / (double) SAMPLE_SIZE, 0.1);
        assertEquals(0.25 * (1 - xP) * 0.5, actionOutcomes.get(Position.F_LC) / (double) SAMPLE_SIZE, 0.1);
        assertEquals(0.25 * xP * 6, matchStates.get(homeTeam) / (double) SAMPLE_SIZE, 0.1);
        assertEquals(3 * 0.25 * (1 - xP) * 0.5, matchStates.get(awayTeam) / (double) SAMPLE_SIZE, 0.1);
    }

    @Test
    public void testRightBackActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_4_2, Tactics._4_3_3);

        testOutput.runTest(Position.D_R);

        assertEquals(0.33, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CR), 0.1);
        assertEquals(0.33 * xP * 2, testOutput.getPossessionOutcomeByPosition(Position.M_R), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.F_C), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.F_LC), 0.1);
        assertEquals(0.33 + 0.33 * xP * 3, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testLeftBackActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_4_2, Tactics._4_3_3);

        testOutput.runTest(Position.D_L);

        assertEquals(0.33, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CL), 0.1);
        assertEquals(0.33 * xP * 2, testOutput.getPossessionOutcomeByPosition(Position.M_L), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.F_C), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.F_RC), 0.1);
        assertEquals(0.33 + 0.33 * xP * 3, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testCentreRightBackActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_4_2, Tactics._4_3_3);

        testOutput.runTest(Position.D_CR);
        assertEquals(0.25, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.25 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CL), 0.1);
        assertEquals(0.25 * xP * 2, testOutput.getPossessionOutcomeByPosition(Position.D_R), 0.1);
        assertEquals(0.25 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_CR), 0.1);
        assertEquals(0.25 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.F_RC), 0.1);
        assertEquals(0.25 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.F_C), 0.1);
        assertEquals(0.25 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_LC), 0.1);
        assertEquals(0.25 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_C), 0.1);
        assertEquals(xP + 0.25, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testCentreLeftBackActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_4_2, Tactics._4_3_3);

        testOutput.runTest(Position.D_CL);

        assertEquals(0.25, testOutput.getPossessionOutcomeByPosition(Position.GK), 0.1);
        assertEquals(0.25 * xP, testOutput.getPossessionOutcomeByPosition(Position.D_CR), 0.1);
        assertEquals(0.25 * xP * 2, testOutput.getPossessionOutcomeByPosition(Position.D_L), 0.1);
        assertEquals(0.25 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_CL), 0.1);
        assertEquals(0.25 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.F_LC), 0.1);
        assertEquals(0.25 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.F_C), 0.1);
        assertEquals(0.25 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_RC), 0.1);
        assertEquals(0.25 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_C), 0.1);
        assertEquals(xP + 0.25, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testRightMidfielderActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_4_2, Tactics._4_3_3);

        testOutput.runTest(Position.M_R);

        assertEquals(xP, testOutput.getPossessionOutcomeByPosition(Position.M_CR), 0.1);
        assertEquals(0.5 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.M_C), 0.1);
        assertEquals(0.5 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.M_LC), 0.1);
        assertEquals(xP, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testLeftMidfielderActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_4_2, Tactics._4_3_3);

        testOutput.runTest(Position.M_L);

        assertEquals(xP, testOutput.getPossessionOutcomeByPosition(Position.M_CL), 0.1);
        assertEquals(0.5 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.M_C), 0.1);
        assertEquals(0.5 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.M_RC), 0.1);
        assertEquals(xP, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testCentreRightMidfielderActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_4_2, Tactics._4_3_3);

        testOutput.runTest(Position.M_CR);

        assertEquals(0.33, testOutput.getPossessionOutcomeByPosition(Position.M_R), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_CL), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_CR), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_C), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_RC), 0.1);
        assertEquals(0.33 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.D_CL), 0.1);
        assertEquals(0.33 + 0.33 * xP * 2, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }

    @Test
    public void testCentreLeftMidfielderActionsProbabilisticAssertions() {
        Match sampleMatch = new Match();
        double xP = sampleMatch.getState().getXP();
        TacticalTestOutput testOutput = new TacticalTestOutput(Tactics._4_4_2, Tactics._4_3_3);

        testOutput.runTest(Position.M_CL);

        assertEquals(0.33, testOutput.getPossessionOutcomeByPosition(Position.M_L), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.M_CR), 0.1);
        assertEquals(0.33 * xP, testOutput.getPossessionOutcomeByPosition(Position.F_CL), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_C), 0.1);
        assertEquals(0.33 * (1 - xP) * 0.5, testOutput.getPossessionOutcomeByPosition(Position.M_LC), 0.1);
        assertEquals(0.33 * (1 - xP), testOutput.getPossessionOutcomeByPosition(Position.D_CR), 0.1);
        assertEquals(0.33 + 0.33 * xP * 2, testOutput.getPossessionOutcomeByTeam(sampleMatch.getHomeTeam()), 0.1);
    }
}
