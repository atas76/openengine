package org.openengine.vanilla;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TacticalTestOutput {

    private final int SAMPLE_SIZE = 100;

    private Map<Position, Integer> actionOutcomes = new HashMap<>();
    private Map<Team, Integer> matchStates = new HashMap<>();

    private Tactics homeTactics;
    private Tactics awayTactics;

    public TacticalTestOutput() {
        this.homeTactics = Tactics._4_4_2;
        this.awayTactics = Tactics._4_4_2;
    }

    public TacticalTestOutput(Tactics homeTactics, Tactics awayTactics) {
        this.homeTactics = homeTactics;
        this.awayTactics = awayTactics;
    }

    public void runTest(Position position) {
        for (int i = 0; i < SAMPLE_SIZE; i++) {
            Match match = new Match(this.homeTactics, this.awayTactics);
            match.getState().setPossessionTeam(match.getHomeTeam());
            match.getState().setPossessionPlayer(match.getHomeTeam().getPlayerByPosition(position));
            Player player = match.getState().getPossessionPlayer();

            Action action = player.decide();
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
    }

    public double getPossessionOutcomeByPosition(Position position) {
        return actionOutcomes.get(position) / (double) SAMPLE_SIZE;
    }

    public double getPossessionOutcomeByTeam(Team team) {
        return matchStates.get(team) / (double) SAMPLE_SIZE;
    }
}
