package org.openengine.pureengine.domain.model;

public class TournamentReplayHistory {

    private int tournamentId;
    private String teamName;
    private int winsNum;
    private int finalsParticipationsNum;
    private int historialCoefficient;

    public TournamentReplayHistory(int tournamentId, String teamName, int winsNum, int finalsParticipationsNum,
                                   int historialCoefficient) {
        this.tournamentId = tournamentId;
        this.teamName = teamName;
        this.winsNum = winsNum;
        this.finalsParticipationsNum = finalsParticipationsNum;
        this.historialCoefficient = historialCoefficient;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getWinsNum() {
        return winsNum;
    }

    public int getFinalsParticipationsNum() {
        return finalsParticipationsNum;
    }

    public int getHistorialCoefficient() {
        return historialCoefficient;
    }
}
