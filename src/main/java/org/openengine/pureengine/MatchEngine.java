package org.openengine.pureengine;

import java.util.Random;

public class MatchEngine {

    private static final double SEED_xG = 0.75;
    private static final double MATCH_xG = 1.5;
    private static final int PERIODS = 90;
    private static Random rnd = new Random();
    private Match match = new Match();

    private Team homeTeam;
    private Team awayTeam;

    public MatchEngine(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public static void main(String[] args) {

        MatchEngine matchEngine = new MatchEngine(new Team("Red"), new Team("Blue"));
        matchEngine.play();

    }

    public void play() {
        simulateScoring();
        displayScore();
    }

    private void displayScore() {
        System.out.println(homeTeam.getName() + " - " + awayTeam.getName()
                + " "
                + homeTeam.getGoalsScored() + " - " + awayTeam.getGoalsScored());
    }

    private void simulateScoring() {
        double xG = MATCH_xG / PERIODS;
        for (int i = 0; i < PERIODS; i++) {
            if (rnd.nextDouble() <= xG) {
                homeTeam.score();
                match.addEvent(new MatchEvent(this.homeTeam.getName(), MatchEventType.GOAL, i + 1));
            }
            if (rnd.nextDouble() <= xG) {
                awayTeam.score();
                match.addEvent(new MatchEvent(this.awayTeam.getName(), MatchEventType.GOAL, i + 1));
            }
        }
    }

    private static void simulateScoringProgressiveReduction(Team team) {
        while(rnd.nextDouble() <= SEED_xG / (team.getGoalsScored() + 1)) {
            team.score();
        }
    }
}
