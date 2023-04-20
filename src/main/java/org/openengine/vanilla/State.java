package org.openengine.vanilla;

public class State {

    private Player possessionPlayer;
    private Team possessionTeam;

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
}
