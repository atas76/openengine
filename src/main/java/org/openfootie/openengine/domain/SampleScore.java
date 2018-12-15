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

    public String toString() {
        return "Home strength: " + this.homeStrength + ", " +
                "Away strength: " + this.awayStrength + ", " +
                "Home score: " + this.homeScore + ", " +
                "Away score: " + this.awayScore + ", " + "Neutral: " + this.neutral;
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

    private double squaredDistance(double a, double f) {
        return Math.pow(a - f, 2);
    }

    private double hypotenuse(double sqrX, double sqrY) {
        return Math.sqrt(sqrX + sqrY);
    }

    public void calculateDistance(double homeStrength, double awayStrength, boolean neutral) {

        double homeSquaredDistance = squaredDistance(homeStrength, this.homeStrength);
        double awaySquaredDistance = squaredDistance(awayStrength, this.awayStrength);

        double totalDistance = hypotenuse(homeSquaredDistance, awaySquaredDistance);

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

        double distance = squaredDistance(strength, filterOnHome ? this.homeStrength : this.awayStrength);

        if (neutral) {

            double reverseDistance = squaredDistance(strength, filterOnHome ? this.awayStrength : this.homeStrength);

            if (reverseDistance < distance) {
                distance = reverseDistance;
            }
        }

        this.distance = distance;
    }

    private double calculateNeutralDistance(double homeStrength, double awayStrength, double totalDistance) {

        double homeToAwayDistance = squaredDistance(homeStrength, this.awayStrength);
        double awayToHomeDistance = squaredDistance(awayStrength, this.homeStrength);

        double reverseDistance = squaredDistance(homeToAwayDistance, awayToHomeDistance);

        if (reverseDistance < totalDistance) {
            totalDistance = reverseDistance;
        }

        return totalDistance;
    }
}
