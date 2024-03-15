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

    private int homeTeamPenaltiesTaken;
    private int awayTeamPenaltiesTaken;

    public PenaltyShootOut(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public void execute() {
        execute(true);
    }

    public void execute(boolean silentMode) {
        for (int i = 0; i < PENALTIES_NUM; i++) {
            if (!isTeamInShootOut(this.homeTeam)) break;
            if (!silentMode) displayPenalty(this.homeTeam);
            shootPenalty(this.homeTeam, silentMode);
            this.homeTeamPenaltiesTaken++;
            if (!isTeamInShootOut(this.homeTeam)) break;

            if (!isTeamInShootOut(this.awayTeam)) break;
            if (!silentMode) displayPenalty(this.awayTeam);
            shootPenalty(this.awayTeam, silentMode);
            this.awayTeamPenaltiesTaken++;
            if (!isTeamInShootOut(this.awayTeam)) break;
        }
        while (homeGoalsScored == awayGoalsScored) {
            executePenaltyShotRound(silentMode);
        }
        if (!silentMode) {
            System.out.println("Final score:");
            displayScore();
        }
    }

    private void executePenaltyShotRound(boolean silentMode) {
        if (!silentMode) displayPenalty(this.homeTeam);
        shootPenalty(this.homeTeam, silentMode);
        this.homeTeamPenaltiesTaken++;
        if (!silentMode) displayPenalty(this.awayTeam);
        shootPenalty(this.awayTeam, silentMode);
        this.awayTeamPenaltiesTaken++;
    }

    private boolean isTeamInShootOut(Team team) {
        int penaltiesTaken = team.equals(this.homeTeam) ? this.homeTeamPenaltiesTaken : this.awayTeamPenaltiesTaken;
        int scoreDifference = team.equals(this.homeTeam) ?
                this.homeGoalsScored - this.awayGoalsScored : this.awayGoalsScored - this.homeGoalsScored;
        return PENALTIES_NUM - penaltiesTaken >= -scoreDifference;
    }

    private String getOrdinalSuffix(int ordinal) {
        return switch (ordinal) {
          case 0 -> "st";
          case 1 -> "nd";
          case 2 -> "rd";
          default -> "th";
        };
    }

    private void displayScore() {
        System.out.println(homeTeam.getName() + " - " + awayTeam.getName() + " " +
                this.homeGoalsScored + " - " + this.awayGoalsScored);
        System.out.println();
    }

    private void displayPenalty(Team team) {
        int penaltiesTaken = team.equals(this.homeTeam) ? this.homeTeamPenaltiesTaken : this.awayTeamPenaltiesTaken;
        System.out.println(team.getName() + " " + (penaltiesTaken + 1) + getOrdinalSuffix(penaltiesTaken) + " penalty");
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
