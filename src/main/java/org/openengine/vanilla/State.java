package org.openengine.vanilla;

import java.util.Random;

public class State {

    private Player possessionPlayer;
    private Team possessionTeam;

    private final double xG = 0.5; // Use arbitrary probabilities for now
    private final double xP = 0.5; // Expected pass
    private static Random rnd = new Random();

    public Player getPossessionPlayer() {
        return possessionPlayer;
    }

    public Team getPossessionTeam() {
        return possessionTeam;
    }

    public void setPossessionTeam(Team possessionTeam) {
        this.possessionTeam = possessionTeam;
    }

    public void setPossessionPlayer(Player possessionPlayer) {
        this.possessionPlayer = possessionPlayer;
    }

    public void setDefaultPossessionPlayer() {
        this.possessionPlayer = possessionTeam.getGoalkeeper();
    }

    public ActionOutcome execute(Action action) {
        if (action.getType() == null) {
            return new ActionOutcome();
        }
        return switch (action.getType()) {
            case Shoot -> this.shootEval(action);
            case Pass -> this.passEval(action);
        };
    }

    public ActionOutcome shootEval(Action action) {
        ActionOutcome actionOutcome = new ActionOutcome();
        double outcome = rnd.nextDouble();
        if (outcome < xG) {
            actionOutcome.addEvent(new Event(EventType.GOAL_SCORED));
        }
        actionOutcome.setPossessionChange(true);
        return actionOutcome;
    }

    public ActionOutcome passEval(Action action) {
        ActionOutcome actionOutcome = new ActionOutcome();
        double outcome = rnd.nextDouble();
        if (outcome < xP || action.getTarget().getMarker() == null) {
            actionOutcome.setPossessionChange(false);
            actionOutcome.setPossessionPlayer(action.getTarget());
        } else {
            actionOutcome.setPossessionChange(true);
            actionOutcome.setPossessionPlayer(action.getTarget().getMarker());
        }
        return actionOutcome;
    }

    @Override
    public String toString() {
        return "State{" +
                "possessionPlayer=" + possessionPlayer.getName() +
                ", possessionTeam=" + possessionTeam +
                '}';
    }
}
