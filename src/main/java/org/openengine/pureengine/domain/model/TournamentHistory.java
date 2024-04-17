package org.openengine.pureengine.domain.model;

public class TournamentHistory {

    private int tournamentId;
    private String winner;
    private String runnerUp;

    public TournamentHistory(int tournamentId, String winner, String runnerUp) {
        this.tournamentId = tournamentId;
        this.winner = winner;
        this.runnerUp = runnerUp;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public String getWinner() {
        return winner;
    }

    public String getRunnerUp() {
        return runnerUp;
    }
}
