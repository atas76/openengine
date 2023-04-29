package org.openengine.vanilla;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {

    private String name;
    private Position position;
    private int shirtNo;
    private Team team;
    private Player marker;
    private List<Player> markers = new ArrayList<>();
    
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
        this.markers.add(player);
    }

    public int getMarkersNumber() {
        return markers.size();
    }

    public Player getChallengeMarker() {
        return markers.get(rnd.nextInt(markers.size()));
    }

    public void setPermissibleActions(List<Action> permissibleActions) {
        this.permissibleActions = permissibleActions;
    }
}
