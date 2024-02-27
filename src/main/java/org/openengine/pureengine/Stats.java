package org.openengine.pureengine;

public class Stats {

    private Team homeTeam;
    private Team awayTeam;

    private TeamStats homeTeamStats = new TeamStats();
    private TeamStats awayTeamStats = new TeamStats();

    public Stats(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public void addPossession(boolean isHomeTeam) {
        if (isHomeTeam) {
            homeTeamStats.addPossession();
        } else {
            awayTeamStats.addPossession();
        }
    }

    public void display() {
        System.out.println("Possession:");
        int totalPossession = this.homeTeamStats.getPossession() + awayTeamStats.getPossession();
        double homeTeamPossession = (double) this.homeTeamStats.getPossession() * 100 / totalPossession;
        double awayTeamPossession = (double) this.awayTeamStats.getPossession() * 100 / totalPossession;
        System.out.println(this.homeTeam.getName() + " - " + this.awayTeam.getName() + " "
                + Math.round(homeTeamPossession) + "%" + " - " + Math.round(awayTeamPossession) + "%");
    }
}
