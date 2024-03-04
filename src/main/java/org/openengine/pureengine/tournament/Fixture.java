package org.openengine.pureengine.tournament;

import org.openengine.pureengine.Team;

public class Fixture {

    private Team homeTeam;
    private Team awayTeam;

    public Fixture(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    @Override
    public String toString() {
        return this.homeTeam.getName() + " - " + this.awayTeam.getName();
    }
}
