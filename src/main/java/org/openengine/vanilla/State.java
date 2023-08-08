package org.openengine.vanilla;

import org.openengine.vanilla.util.Logger;

import java.util.Map;
import java.util.Random;

public class State {

    private Player possessionPlayer;
    private Team possessionTeam;

    private final double xG = 0.1; // Use average probabilities
    public static final double xP = 0.9; // Expected pass
    private static final Random rnd = new Random();
    public static final double VERTICAL_DISTANCE_UNIT_FACTOR = 1.6;
    public static final double VERTICAL_DISTANCE_WEIGHT = VERTICAL_DISTANCE_UNIT_FACTOR - 1.0;
    public static final double HORIZONTAL_DISTANCE_UNIT_FACTOR = 1.4;
    public static final double HORIZONTAL_DISTANCE_WEIGHT = HORIZONTAL_DISTANCE_UNIT_FACTOR - 1.0;

    public static Map<Integer, Double> DISTANCE_UNIT_FACTORS = Map.of(
            0, 1.0, 1, 1.3, 2, 1.6, 3, 2.4, 4, 3.2
    );

    public static double getUnitFactor(double weight) {
        return 1.0 + weight;
    }

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
            case Shoot -> this.shootEval();
            case Pass -> this.passEval(action);
        };
    }

    public ActionOutcome shootEval() {
        ActionOutcome actionOutcome = new ActionOutcome();
        actionOutcome.addEvent(new Event(EventType.SHOT));
        double outcome = rnd.nextDouble();
        if (outcome < xG) {
            actionOutcome.addEvent(new Event(EventType.GOAL_SCORED));
        }
        actionOutcome.setPossessionChange(true);
        return actionOutcome;
    }

    public ActionOutcome passEval(Action action) {
        ActionOutcome actionOutcome = new ActionOutcome();
        Logger.log(action.toString());
        double markingFactor = action.getTarget().getWeightedMarkersNumber();
        double randomFactor = rnd.nextDouble();
        Logger.log("Random factor: " + randomFactor);
        Logger.log("Marking factor: " + markingFactor);
        Logger.log("Geometry factor: " + action.getGeometryFactor());
        double passOutcomeScore = randomFactor * (((action.getGeometryFactor() - 1.0) * markingFactor) + 1.0);
        Logger.log("Pass outcome score: " + passOutcomeScore);
        if (passOutcomeScore < xP || markingFactor == 0.0) {
            Logger.log("SUCCESS");
            actionOutcome.setPossessionChange(false);
            actionOutcome.setPossessionPlayer(action.getTarget());
        } else {
            Logger.log("FAIL");
            actionOutcome.setPossessionChange(true);
            actionOutcome.setPossessionPlayer(action.getTarget().getChallengeMarker());
        }
        if (actionOutcome.getPossessionPlayer().getPosition().toString().startsWith("F")) {
            actionOutcome.addEvent(new Event(EventType.ATTACKING_TOUCH));
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
