package org.openengine.pureengine.tournament;

import org.openengine.pureengine.MatchEngine;
import org.openengine.pureengine.Team;
import org.openengine.pureengine.TieBreaker;

import static org.openengine.pureengine.TieBreaker.RANDOM;

public class Fixture {

    private Team homeTeam;
    private Team awayTeam;
    private boolean played;
    private Team winningTeam;
    private static final TieBreaker TIE_BREAKER = RANDOM;

    public Fixture(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public void play() {
        MatchEngine matchEngine = new MatchEngine(homeTeam, awayTeam);
        matchEngine.simulateScoring(TIE_BREAKER);
        this.winningTeam = matchEngine.getMatch().getWinningTeam();
        this.played = true;
    }

    @Override
    public String toString() {
        return this.homeTeam.getName() + " - " + this.awayTeam.getName() + " "
                + (this.played ? this.homeTeam.getGoalsScored() + " - " + this.awayTeam.getGoalsScored() : "")
                + (TIE_BREAKER == RANDOM
                    && this.played
                    && this.homeTeam.getGoalsScored() == this.awayTeam.getGoalsScored()
                        ? ", " +  this.winningTeam.getName() + " wins"
                        : ""
                );
    }
}
