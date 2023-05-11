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

    private final int SAMPLE_SIZE = 100;
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
        assertEquals(0.25 * xP, actionOutcomes.get(Position.F_CR) / (double) SAMPLE_SIZE, 0.1);
        assertEquals(0.25 * xP, actionOutcomes.get(Position.F_CL) / (double) SAMPLE_SIZE, 0.1);
        assertEquals(0.5 + 0.5 * xP, matchStates.get(homeTeam) / (double) SAMPLE_SIZE, 0.1);
        assertEquals(0.5 * (1 - xP), matchStates.get(awayTeam) / (double) SAMPLE_SIZE, 0.1);
    }

    @Test
    public void testRightBackActions() {
        testPlayerBehaviourByPosition(Position.D_R);
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
    public void testRightMidfielderActions() {
        testPlayerBehaviourByPosition(Position.M_R);
    }

    @Test
    public void testCentreRightMidfielderActions() {
        testPlayerBehaviourByPosition(Position.M_CR);
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
