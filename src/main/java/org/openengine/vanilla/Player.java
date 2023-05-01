package org.openengine.vanilla;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Player {

    private String name;
    private Position position;
    private int shirtNo;
    private Team team;
    private Map<Player, Double> weightedMarkers = new HashMap<>();

    private static final Logger logger = LogManager.getLogger(Player.class);
    
    private static Random rnd = new Random();
    
    private List<Action> permissibleActions = new ArrayList<>();

    public Player() {

    }

    public Player(Position position, int shirtNo) {
        this.position = position;
        this.name = position.name();
        this.shirtNo = shirtNo;
    }

    public Team getTeam() {
        return team;
    }

    public String getName() {
        return this.name;
    }

    public int getShirtNo() {
        return this.shirtNo;
    }

    public Action decide() {
        if (permissibleActions.isEmpty()) return new Action(); // Fail fast
        return permissibleActions.size() > 1 ?
                permissibleActions.get(rnd.nextInt(permissibleActions.size())) : permissibleActions.get(0);
    }

    public void addMarker(Player player) {
        this.weightedMarkers.put(player, 1.0);
    }

    public void addMarker(Player player, double markingFactor) {
        this.weightedMarkers.put(player, markingFactor);
    }

    public double getWeightedMarkersNumber() {
        return weightedMarkers.values().stream().reduce(0.0, Double::sum);
    }

    public Player getChallengeMarker() {
        double challengeFactor = rnd.nextDouble(getWeightedMarkersNumber());
        logger.debug("Challenge factor: " + challengeFactor);
        double sum = 0.0;
        for (Map.Entry<Player, Double> weightedMarker: weightedMarkers.entrySet()) {
            sum += weightedMarker.getValue();
            if (challengeFactor < sum) {
                return weightedMarker.getKey();
            }
        }
        return weightedMarkers.keySet().stream().findAny().get();
    }

    public void setPermissibleActions(List<Action> permissibleActions) {
        this.permissibleActions = permissibleActions;
    }
}
