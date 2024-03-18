package org.openengine.pureengine.tournament;

import org.openengine.pureengine.*;

import static org.openengine.pureengine.TieBreaker.*;

public class Fixture {

    private MatchEngine matchEngine;
    private boolean played;
    private Team winningTeam;
    private Team losingTeam;
    private static final TieBreaker TIE_BREAKER = EXTRA_TIME_PENALTIES;

    private boolean homeAdvantage;

    public Fixture(Team homeTeam, Team awayTeam) {
        this(homeTeam, awayTeam, false);
    }

    public Fixture(Team homeTeam, Team awayTeam, boolean homeAdvantage) {
        this.matchEngine = new MatchEngine(homeTeam, awayTeam);
        this.homeAdvantage = homeAdvantage;
    }

    public void play() {
        matchEngine.simulateMatch(TIE_BREAKER, this.homeAdvantage);
        this.winningTeam = matchEngine.getMatch().getWinningTeam();
        this.losingTeam = matchEngine.getMatch().getLosingTeam();
        this.played = true;
    }

    public Team getWinningTeam() {
        return winningTeam;
    }

    public Team getLosingTeam() {
        return losingTeam;
    }

    @Override
    public String toString() {
        Match matchDetails = matchEngine.getMatch();

        String teams = matchDetails.getHomeTeam().getName() + " - " + matchDetails.getAwayTeam().getName();
        String venue = homeAdvantage ? "" : " (N)";
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
            } else if (TIE_BREAKER == EXTRA_TIME_PENALTIES && matchDetails.isExtraTimePlayed()) {
                if (matchDetails.isExtraTimePlayed()) {
                    String normalTimeScore = matchDetails.getHomeGoalsScoredNormalTime()
                            + " - "
                            + matchDetails.getAwayGoalsScoredNormalTime();
                    score = normalTimeScore + ", " + score + " (AET)";
                    fullScore = teams + " " + score;
                    if (matchDetails.getHomeGoalsScored() == matchDetails.getAwayGoalsScored()) {
                        fullScore += ", "
                                + matchDetails.getHomePenaltyShootOutGoals()
                                + " - "
                                + matchDetails.getAwayPenaltyShootOutGoals()
                                + " (pens)";
                    }
                }
            }
            return fullScore;
        } else {
            return teams + venue;
        }
    }
}
