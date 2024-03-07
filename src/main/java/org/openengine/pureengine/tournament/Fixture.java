package org.openengine.pureengine.tournament;

import org.openengine.pureengine.*;

import static org.openengine.pureengine.TieBreaker.RANDOM;

public class Fixture {

    private MatchEngine matchEngine;
    private boolean played;
    private Team winningTeam;
    private static final TieBreaker TIE_BREAKER = RANDOM;

    public Fixture(Team homeTeam, Team awayTeam) {
        this.matchEngine = new MatchEngine(homeTeam, awayTeam);
    }

    public void play() {
        matchEngine.simulateScoring(TIE_BREAKER);
        this.winningTeam = matchEngine.getMatch().getWinningTeam();
        this.played = true;
    }

    public Team getWinningTeam() {
        return winningTeam;
    }

    @Override
    public String toString() {
        Match matchDetails = matchEngine.getMatch();
        return matchDetails.getHomeTeam().getName() + " - " + matchDetails.getAwayTeam().getName() + " "
                + (this.played ? matchDetails.getHomeGoalsScored() + " - " + matchDetails.getAwayGoalsScored() : "")
                + (TIE_BREAKER == RANDOM
                    && this.played
                    && matchDetails.getHomeGoalsScored() == matchDetails.getAwayGoalsScored()
                        ? ", " +  this.winningTeam.getName() + " wins"
                        : ""
                );
    }
}
