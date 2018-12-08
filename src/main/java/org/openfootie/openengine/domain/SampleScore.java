package org.openfootie.openengine.domain;

public class SampleScore {

    private double homeStrength;
    private double awayStrength;

    private int homeScore;
    private int awayScore;

    private boolean neutral;

    private double distance;

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

    public double getDistance() {
        return this.distance;
    }

    private double leastSqrt(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) - Math.pow(y, 2));
    }

    public void calculateDistance(double homeStrength, double awayStrength, boolean neutral) {

        double homeDistance = leastSqrt(homeStrength, this.homeStrength);
        double awayDistance = leastSqrt(awayStrength, this.awayStrength);

        double totalDistance = leastSqrt(homeDistance, awayDistance);

        if (neutral) {
            totalDistance = calculateNeutralDistance(homeStrength, awayStrength, totalDistance);
        }

        this.distance = totalDistance;
    }

    public void calculateHomeDistance(double homeStrength, boolean neutral) {
        calculateSingleDistance(homeStrength, neutral, true);
    }

    public void calculateAwayDistance(double awayStrength, boolean neutral) {
        calculateSingleDistance(awayStrength, neutral, false);
    }

    public void calculateClosest(double strength, boolean filterOnHome) {
        calculateSingleDistance(strength, true, filterOnHome);
    }

    public void calculateSingleDistance(double strength, boolean neutral, boolean filterOnHome) {

        double distance = leastSqrt(strength, filterOnHome ? this.homeStrength : this.awayStrength);

        if (neutral) {

            double reverseDistance = leastSqrt(strength, filterOnHome ? this.awayStrength : this.homeStrength);

            if (reverseDistance < distance) {
                distance = reverseDistance;
            }
        }

        this.distance = distance;
    }

    private double calculateNeutralDistance(double homeStrength, double awayStrength, double totalDistance) {

        double homeToAwayDistance = leastSqrt(homeStrength, this.awayStrength);
        double awayToHomeDistance = leastSqrt(awayStrength, this.homeStrength);

        double reverseDistance = leastSqrt(homeToAwayDistance, awayToHomeDistance);

        if (reverseDistance < totalDistance) {
            totalDistance = reverseDistance;
        }

        return totalDistance;
    }
}
