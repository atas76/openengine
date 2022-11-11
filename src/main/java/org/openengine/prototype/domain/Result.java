package org.openengine.prototype.domain;

public class Result {

    private int homeScore;
    private int awayScore;

    public Result(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }
}
