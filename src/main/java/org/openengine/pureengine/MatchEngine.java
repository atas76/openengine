package org.openengine.pureengine;

import java.util.Random;

public class MatchEngine {

    private static final double SEED_xG = 0.75;
    private static final double MATCH_xG = 3.0;
    private static final int PERIODS = 90;
    /*
     This is technically the 'home team' 'possession bias', but I didn't want to name it as such explicitly because:
        1. It is a more abstract and generic bias used for mainly for testing, and didn't want to get it confused with
        home team advantage.
        2. We use it currently to determine team 'possession', but its semantics shall evolve in the future
     */
    private static final double TEAM_BIAS = 0.5;
    private static Random rnd = new Random();
    private Match match;

    private Team homeTeam;
    private Team awayTeam;

    public MatchEngine(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.match = new Match(homeTeam, awayTeam);
    }

    public static void main(String[] args) throws InterruptedException {
        MatchEngine matchEngine = new MatchEngine(new Team("Red"), new Team("Blue"));
        matchEngine.play();
    }

    public void play() throws InterruptedException {
        simulateScoring();
        reproduceGame();
        displayScore();
        displayStats();
    }

    public void reproduceGame() throws InterruptedException {
        match.getEvents().forEach(matchEvent -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(matchEvent);
        });
        Thread.sleep(1000);
        System.out.println();
    }

    private void displayScore() {
        System.out.println(homeTeam.getName() + " - " + awayTeam.getName()
                + " "
                + homeTeam.getGoalsScored() + " - " + awayTeam.getGoalsScored());
    }

    private void displayStats() {
        System.out.println();
        match.displayStats();
    }

    private void simulateScoring() {
        double xG = MATCH_xG / PERIODS;
        for (int i = 0; i < PERIODS; i++) {
            if (rnd.nextDouble() <= TEAM_BIAS) {
                match.addPossession(true);
                simulateTeamScoring(homeTeam, i, xG);
            } else {
                match.addPossession(false);
                simulateTeamScoring(awayTeam, i, xG);
            }
        }
    }

    private void simulateTeamScoring(Team team, int i, double xG) {
        if (rnd.nextDouble() <= xG) {
            team.score();
            match.addEvent(new MatchEvent(team.getName(), MatchEventType.GOAL, i + 1));
        }
    }

    private static void simulateScoringProgressiveReduction(Team team) {
        while(rnd.nextDouble() <= SEED_xG / (team.getGoalsScored() + 1)) {
            team.score();
        }
    }
}
