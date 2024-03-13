package org.openengine.pureengine;

import java.util.Random;

public class PenaltyShootOut {

    private static final int PENALTIES_NUM = 5;
    private static final double xG = 0.79;
    private static Random rnd = new Random();

    private Team homeTeam;
    private Team awayTeam;

    private int homeGoalsScored;
    private int awayGoalsScored;

    public PenaltyShootOut(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public void execute() {
        execute(true);
    }

    public void execute(boolean silentMode) {
        for (int i = 0; i < PENALTIES_NUM; i++) {
            shootPenalty(this.homeTeam, silentMode);
            shootPenalty(this.awayTeam, silentMode);
        }
        if (!silentMode) {
            System.out.println("Final score:");
            displayScore();
        }
    }

    private void displayScore() {
        System.out.println(homeTeam.getName() + " - " + awayTeam.getName() + " " +
                this.homeGoalsScored + " - " + this.awayGoalsScored);
        System.out.println();
    }

    private void shootPenalty(Team team, boolean silentMode) {

        boolean teamScored = false;

        if (rnd.nextDouble() <= xG) {
            teamScored = true;
            if (team.equals(this.homeTeam)) {
                this.homeGoalsScored++;
            } else {
                this.awayGoalsScored++;
            }
        }
        if (!silentMode) {
            System.out.println(team.getName() + (teamScored ? " score!" : " miss!"));
            displayScore();
        }
    }

    public int getHomeGoalsScored() {
        return homeGoalsScored;
    }

    public int getAwayGoalsScored() {
        return awayGoalsScored;
    }
}
