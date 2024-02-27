package org.openengine.pureengine;

import java.util.ArrayList;
import java.util.List;

public class Match {

    private Team homeTeam;
    private Team awayTeam;

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
}
