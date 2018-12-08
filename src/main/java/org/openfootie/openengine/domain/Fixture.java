package org.openfootie.openengine.domain;

public class Fixture {

    private Team homeTeam;
    private Team awayTeam;
    private boolean neutralVenue;

    public Fixture(Team homeTeam, Team awayTeam, boolean neutralVenue) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.neutralVenue = neutralVenue;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public boolean isNeutralVenue() {
        return neutralVenue;
    }
}
