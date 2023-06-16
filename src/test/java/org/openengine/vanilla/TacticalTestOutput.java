package org.openengine.vanilla;

import java.util.HashMap;
import java.util.Map;

public class TacticalTestOutput {

    private final int SAMPLE_SIZE = 200;

    private Map<Position, Integer> actionOutcomes = new HashMap<>();
    private Map<Team, Integer> matchStates = new HashMap<>();

    private Tactics homeTactics;
    private Tactics awayTactics;
    private double xP;

    public TacticalTestOutput(Tactics homeTactics, Tactics awayTactics, double xP) {
        this.homeTactics = homeTactics;
        this.awayTactics = awayTactics;
        this.xP = xP;
    }

    public void runTest(Position position) {
        for (int i = 0; i < SAMPLE_SIZE; i++) {
            Match match = new Match(this.homeTactics, this.awayTactics);
            match.getState().setXP(xP);
            match.getState().setPossessionTeam(match.getHomeTeam());
            match.getState().setPossessionPlayer(match.getHomeTeam().getPlayerByPosition(position));
            Player player = match.getState().getPossessionPlayer();

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
