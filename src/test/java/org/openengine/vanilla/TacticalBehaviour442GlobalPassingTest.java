package org.openengine.vanilla;

import org.junit.Before;
import org.junit.Test;
import org.openengine.vanilla.util.Flags;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TacticalBehaviour442GlobalPassingTest {

    private Match sampleMatch;
    private final double xP = 0.7;
    private TacticalTestOutput testOutput;
    private final double DELTA = 0.15;
    private final static int SAMPLE_SIZE = 100;

    @Before
    public void setUp() {
        Flags.LOGGING = false;
        sampleMatch = new Match();
        sampleMatch.getState().setXP(xP);
        testOutput = new TacticalTestOutput();
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

        assertEquals(0.1, actionOutcomes.get(Position.D_R) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.1, actionOutcomes.get(Position.D_L) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.1 * xP, actionOutcomes.get(Position.D_CR) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.1 * xP, actionOutcomes.get(Position.D_CL) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.05 * xP, actionOutcomes.get(Position.M_R) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.05 * xP, actionOutcomes.get(Position.M_CR) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.05 * xP, actionOutcomes.get(Position.M_CL) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.05 * xP, actionOutcomes.get(Position.M_L) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.033 * xP, actionOutcomes.get(Position.F_CR) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.033 * xP, actionOutcomes.get(Position.F_CL) / (double) SAMPLE_SIZE, DELTA);
    }
}
