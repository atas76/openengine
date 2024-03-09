package org.openengine.pureengine.tournament;

import org.openengine.pureengine.*;

import static org.openengine.pureengine.TieBreaker.*;

public class Fixture {

    private MatchEngine matchEngine;
    private boolean played;
    private Team winningTeam;
    private static final TieBreaker TIE_BREAKER = EXTRA_TIME;

    public Fixture(Team homeTeam, Team awayTeam) {
        this.matchEngine = new MatchEngine(homeTeam, awayTeam);
    }

    public void play() {
        matchEngine.simulateMatch(TIE_BREAKER);
        this.winningTeam = matchEngine.getMatch().getWinningTeam();
        this.played = true;
    }

    public Team getWinningTeam() {
        return winningTeam;
    }

    @Override
    public String toString() {
        Match matchDetails = matchEngine.getMatch();

        String teams = matchDetails.getHomeTeam().getName() + " - " + matchDetails.getAwayTeam().getName();
        String score = matchDetails.getHomeGoalsScored() + " - " + matchDetails.getAwayGoalsScored();
        String winningTeam = this.winningTeam != null ? ", " +  this.winningTeam.getName() + " wins": "";
        String fullScore = teams + " " + score;

        if (this.played) {
            if (matchDetails.getHomeGoalsScored() == matchDetails.getAwayGoalsScored()
                    && TIE_BREAKER == RANDOM) {
                fullScore += winningTeam;
            } else if (TIE_BREAKER == EXTRA_TIME && matchDetails.isExtraTimePlayed()) {
                String normalTimeScore = matchDetails.getHomeGoalsScoredNormalTime()
                        + " - "
                        + matchDetails.getAwayGoalsScoredNormalTime();
                score = normalTimeScore + ", " + score + " (AET)";
                fullScore = teams + " " + score;
                if (matchDetails.getHomeGoalsScored() == matchDetails.getAwayGoalsScored()) {
                    fullScore += winningTeam;
                }
            }
            return fullScore;
        } else {
            return teams;
        }
    }
}
