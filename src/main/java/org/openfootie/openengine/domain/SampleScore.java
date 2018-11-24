package org.openfootie.openengine.domain;

public class SampleScore {

    private double homeStrength;
    private double awayStrength;

    private int homeScore;
    private int awayScore;

    private boolean neutral;

    public SampleScore(double homeStrength, double awayStrength, int homeScore, int awayScore, boolean neutral) {
        this.homeStrength = homeStrength;
        this.awayStrength = awayStrength;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.neutral = neutral;
    }

    public double getHomeStrength() {
        return homeStrength;
    }

    public double getAwayStrength() {
        return awayStrength;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public boolean isNeutral() {
        return neutral;
    }
}
