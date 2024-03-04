package org.openengine.pureengine.tournament;

import org.openengine.pureengine.MatchEngine;
import org.openengine.pureengine.Team;

public class Fixture {

    private Team homeTeam;
    private Team awayTeam;
    private boolean played;

    public Fixture(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public void play() {
        new MatchEngine(homeTeam, awayTeam).simulateScoring();
        this.played = true;
    }

    @Override
    public String toString() {
        return this.homeTeam.getName() + " - " + this.awayTeam.getName() + " "
                + (this.played ? this.homeTeam.getGoalsScored() + " - " + this.awayTeam.getGoalsScored() : "");
    }
}
