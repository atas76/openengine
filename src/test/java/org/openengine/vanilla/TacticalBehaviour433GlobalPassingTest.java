package org.openengine.vanilla;

import org.junit.Before;
import org.junit.Test;
import org.openengine.vanilla.util.Flags;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TacticalBehaviour433GlobalPassingTest {

    private final double xP = 0.7;
    private TacticalTestOutput testOutput;
    private final double DELTA = 0.15;
    private final static int SAMPLE_SIZE = 100;

    @Before
    public void setUp() {
        Flags.LOGGING = false;
        testOutput = new TacticalTestOutput(Tactics._4_3_3, Tactics._4_3_3, xP);
    }

    @Test
    public void testGoalkeeperActionsProbabilisticAssertions() {

        Map<Position, Integer> actionOutcomes = new HashMap<>();
        Map<Team, Integer> matchStates = new HashMap<>();

        for (int i = 0; i < SAMPLE_SIZE; i++) {
            Match match = new Match(Tactics._4_3_3, Tactics._4_3_3);
            match.getState().setXP(xP);
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
        assertEquals(0.05 * xP, actionOutcomes.get(Position.M_RC) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.05 * xP, actionOutcomes.get(Position.M_C) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.05 * xP, actionOutcomes.get(Position.M_LC) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.033 * xP, actionOutcomes.get(Position.F_RC) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.033 * xP, actionOutcomes.get(Position.F_C) / (double) SAMPLE_SIZE, DELTA);
        assertEquals(0.033 * xP, actionOutcomes.get(Position.F_LC) / (double) SAMPLE_SIZE, DELTA);
    }
}
