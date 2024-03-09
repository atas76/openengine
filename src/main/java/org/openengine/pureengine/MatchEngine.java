package org.openengine.pureengine;

import org.openengine.pureengine.config.Debug;

import java.util.Random;

public class MatchEngine {

    private static final double SEED_xG = 0.75;
    private static final double MATCH_xG = 3.0;
    private static final int PERIODS = 90;
    private static final int EXTRA_TIME_PERIODS = 30;
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

    public void play() throws InterruptedException {
        simulateMatch();
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
                + match.getHomeGoalsScored() + " - " + match.getAwayGoalsScored());
    }

    private void displayStats() {
        System.out.println();
        match.displayStats();
    }

    public void simulateMatch(TieBreaker tieBreaker) {
        double homeMatchxG = MATCH_xG;
        double awayMatchxG = MATCH_xG;
        int skillDifference = homeTeam.getSkill() - awayTeam.getSkill();

        if (skillDifference > 0) {
            homeMatchxG = MATCH_xG + skillDifference * (MATCH_xG / 4);
            awayMatchxG = MATCH_xG - (MATCH_xG / 10) * skillDifference;
        }
        if (skillDifference < 0) {
            awayMatchxG = MATCH_xG - skillDifference * (MATCH_xG / 4);
            homeMatchxG = MATCH_xG + (MATCH_xG / 10) * skillDifference;
        }

        if (Debug.DISPLAY_MATCH_xG) {
            System.out.println("Home match xG: " + homeMatchxG / 2);
            System.out.println("Away match xG: " + awayMatchxG / 2);
            System.out.println();
        }

        simulateMatchFlow(homeMatchxG, awayMatchxG, PERIODS);

        if (match.getHomeGoalsScored() == match.getAwayGoalsScored() && tieBreaker != TieBreaker.NONE) {
            if (tieBreaker == TieBreaker.RANDOM) {
                match.decideWinner();
            } else if (tieBreaker == TieBreaker.EXTRA_TIME) {
                match.endNormalTime();
                final double EXTRA_TIME_RATIO = (double) EXTRA_TIME_PERIODS / PERIODS;
                simulateMatchFlow(homeMatchxG * EXTRA_TIME_RATIO, awayMatchxG * EXTRA_TIME_RATIO,
                        EXTRA_TIME_PERIODS);
                if (match.getHomeGoalsScored() == match.getAwayGoalsScored()) {
                    match.decideWinner();
                }
            }
        }
    }

    private void simulateMatchFlow(double homeMatchxG, double awayMatchxG, int duration) {
        final double intervalHomexG = homeMatchxG / duration;
        final double intervalAwayxG = awayMatchxG / duration;

        for (int i = 0; i < duration; i++) {
            if (rnd.nextDouble() <= TEAM_BIAS) {
                match.addPossession(true);
                simulateTeamScoring(homeTeam, i, intervalHomexG);
            } else {
                match.addPossession(false);
                simulateTeamScoring(awayTeam, i, intervalAwayxG);
            }
        }
    }

    public void simulateMatch() {
        simulateMatch(TieBreaker.NONE);
    }

    private void simulateTeamScoring(Team team, int i, double xG) {
        if (rnd.nextDouble() <= xG) {
            match.score(team);
            match.addEvent(new MatchEvent(team.getName(), MatchEventType.GOAL, i + 1));
        }
    }

    public Match getMatch() {
        return match;
    }
}
