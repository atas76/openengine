package org.openengine.vanilla;

import org.openengine.vanilla.util.Logger;

import java.util.HashMap;
import java.util.Map;

public class TacticalTestOutput {

    private final int SAMPLE_SIZE = 200;

    private final Map<Position, Integer> actionOutcomes = new HashMap<>();
    private final Map<Team, Integer> matchStates = new HashMap<>();

    private final Tactics homeTactics;
    private final Tactics awayTactics;

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

            Logger.log("Running for player: " + player.getPosition().toString());

            Action action = player.decide();
            ActionOutcome outcome = match.getState().execute(action);
            match.updateState(outcome);

            Position possessionPlayerPosition = outcome.getPossessionPlayer() != null ?
                    outcome.getPossessionPlayer().getPosition() : Position.GK;
            actionOutcomes.putIfAbsent(possessionPlayerPosition, 0);
            actionOutcomes.put(possessionPlayerPosition,
                    actionOutcomes.get(possessionPlayerPosition) + 1);
            matchStates.putIfAbsent(match.getState().getPossessionTeam(), 0);
            matchStates.put(match.getState().getPossessionTeam(),
                    matchStates.get(match.getState().getPossessionTeam()) + 1);
        }
    }

    public double getPossessionOutcomeByPosition(Position position) {
        return actionOutcomes.get(position) / (double) SAMPLE_SIZE;
    }
}
