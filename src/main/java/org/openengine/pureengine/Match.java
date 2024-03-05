package org.openengine.pureengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Match {

    private Team homeTeam;
    private Team awayTeam;

    private Team winningTeam;

    private static Random rnd = new Random();

    private List<MatchEvent> events = new ArrayList<>();
    private Stats stats;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.stats = new Stats(homeTeam, awayTeam);
    }

    public void addPossession(boolean isHomeTeam) {
        this.stats.addPossession(isHomeTeam);
    }

    public void addEvent(MatchEvent matchEvent) {
        events.add(matchEvent);
    }


    public List<MatchEvent> getEvents() {
        return List.copyOf(events);
    }

    public void displayStats() {
        stats.display();
    }

    public void decideWinner() {
        this.winningTeam = rnd.nextBoolean() ? this.homeTeam : this.awayTeam;
    }

    public Team getWinningTeam() {
        if (this.homeTeam.getGoalsScored() > this.awayTeam.getGoalsScored()) {
            this.winningTeam = this.homeTeam;
        } else if (this.awayTeam.getGoalsScored() > this.homeTeam.getGoalsScored()) {
            this.winningTeam = this.awayTeam;
        }

        return winningTeam;
    }
}
