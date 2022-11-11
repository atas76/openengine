package org.openengine.prototype.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Squad {

    private List<Player> players = new ArrayList<>();

    private Map<Integer, Player> playerNumbers = new HashMap<>();

    public Squad() {}

    public Squad(List<Player> players) {
        players.forEach(this::add);
    }

    void add(Player player) {
        this.players.add(player);
        playerNumbers.put(player.getShirtNumber(), player);
    }

    List<Player> getPlayers() {
        return this.players;
    }

    public Player getPlayerByNumber(int shirtNumber) {
        return this.playerNumbers.get(shirtNumber);
    }
}
