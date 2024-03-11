package org.openengine.pureengine;

import java.util.*;

public class Match {

    private Team homeTeam;
    private Team awayTeam;

    private Team winningTeam;
    private Team losingTeam;

    private static Random rnd = new Random();

    private List<MatchEvent> events = new ArrayList<>();
    private Stats stats;
    private Map<Team, Integer> goalsScored = new HashMap<>();
    private Map<Team, Integer> goalsScoredNormalTime;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.goalsScored.put(this.homeTeam, 0);
        this.goalsScored.put(this.awayTeam, 0);
        this.stats = new Stats(homeTeam, awayTeam);
    }

    public void score(Team team) {
        this.goalsScored.put(team, this.goalsScored.get(team) + 1);
    }

    public void addPossession(boolean isHomeTeam) {
        this.stats.addPossession(isHomeTeam);
    }

    public void addEvent(MatchEvent matchEvent) {
        events.add(matchEvent);
    }

    public void endNormalTime() {
        this.goalsScoredNormalTime =
                Map.of(this.homeTeam, this.goalsScored.get(this.homeTeam),
                        this.awayTeam, this.goalsScored.get(this.awayTeam));
    }


    public List<MatchEvent> getEvents() {
        return List.copyOf(events);
    }

    public void displayStats() {
        stats.display();
    }

    public void decideWinner() {
        this.winningTeam = rnd.nextBoolean() ? this.homeTeam : this.awayTeam;
        this.losingTeam = this.homeTeam.equals(this.winningTeam) ? this.awayTeam : this.homeTeam;
    }

    public Team getWinningTeam() {
        if (this.goalsScored.get(this.homeTeam) > this.goalsScored.get(this.awayTeam)) {
            this.winningTeam = this.homeTeam;
        } else if (this.goalsScored.get(this.awayTeam) > this.goalsScored.get(this.homeTeam)) {
            this.winningTeam = this.awayTeam;
        }

        return winningTeam;
    }

    public Team getLosingTeam() {
        if (this.goalsScored.get(this.homeTeam) < this.goalsScored.get(this.awayTeam)) {
            this.losingTeam = this.homeTeam;
        } else if (this.goalsScored.get(this.awayTeam) < this.goalsScored.get(this.homeTeam)) {
            this.losingTeam = this.awayTeam;
        }

        return losingTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public int getHomeGoalsScored() {
        return this.goalsScored.get(this.homeTeam);
    }

    public int getAwayGoalsScored() {
        return this.goalsScored.get(this.awayTeam);
    }

    public int getHomeGoalsScoredNormalTime() {
        return this.goalsScoredNormalTime.get(this.homeTeam);
    }

    public int getAwayGoalsScoredNormalTime() {
        return this.goalsScoredNormalTime.get(this.awayTeam);
    }

    public boolean isExtraTimePlayed() {
        return this.goalsScoredNormalTime != null;
    }
}
