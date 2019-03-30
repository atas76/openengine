package org.openfootie.openengine.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Squad {

    private List<Player> players = new ArrayList<>();

    private Map<Integer, Player> playerNumbers = new HashMap<>();

    public List<Player> getPlayers() {
        return this.players;
    }

    public void add(Player player) {
        this.players.add(player);
        playerNumbers.put(player.getShirtNumber(), player);
    }

    public Player getPlayerByNumber(int shirtNumber) {
        return this.playerNumbers.get(shirtNumber);
    }
}
