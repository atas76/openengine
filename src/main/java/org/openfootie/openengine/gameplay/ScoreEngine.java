package org.openfootie.openengine.gameplay;

import org.openfootie.openengine.domain.Environment;
import org.openfootie.openengine.domain.Fixture;
import org.openfootie.openengine.domain.Result;
import org.openfootie.openengine.domain.SampleScore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ScoreEngine {

    private Environment environment;

    private static Random rnd = new Random();

    public ScoreEngine(Environment environment) {
        this.environment = environment;
    }

    public Result calculate(Fixture fixture) {

        Double homeStrength = fixture.getHomeTeam().getStrength();
        Double awayStrength = fixture.getAwayTeam().getStrength();

        List<SampleScore> scoreSample = fixture.isNeutralVenue() ?
                environment.getNeutralVenueScores() :
                environment.getHomeAwayVenueScores();

        List<SampleScore> closestPairSample = new ArrayList<>(scoreSample);
        List<SampleScore> closestHomeSample = new ArrayList<>(scoreSample);
        List<SampleScore> closestAwaySample = new ArrayList<>(scoreSample);

        Collections.shuffle(closestPairSample);
        Collections.shuffle(closestHomeSample);
        Collections.shuffle(closestAwaySample);

        SampleScore closestPairScore;
        SampleScore closestHomeScore;
        SampleScore closestAwayScore;

        if (!fixture.isNeutralVenue()) {
            closestPairScore = calculateClosestPair(closestPairSample, homeStrength, awayStrength, false);
            closestHomeScore = calculateClosestHome(closestHomeSample, homeStrength);
            closestAwayScore = calculateClosestAway(closestAwaySample, awayStrength);
        } else {
            closestPairScore = calculateClosestPair(closestPairSample, homeStrength, awayStrength, true);
            closestHomeScore = calculateClosest(closestHomeSample, homeStrength, true);
            closestAwayScore = calculateClosest(closestAwaySample, awayStrength, false);
        }

        return new Result(
                calculateAverage(closestPairScore.getHomeScore(), closestHomeScore.getHomeScore(), closestAwayScore.getHomeScore()),
                calculateAverage(closestPairScore.getAwayScore(), closestHomeScore.getAwayScore(), closestAwayScore.getAwayScore()));
    }

    private int calculateAverage(int... numbers) {

        int sum = 0;

        for (int n: numbers) {
            sum += n;
        }

        return sum / numbers.length;
    }

    private SampleScore calculateClosestPair(List<SampleScore> sample, double homeStrength, double awayStrength, boolean neutral) {

        SampleScore fittestScore = sample.get(rnd.nextInt(sample.size()));

        fittestScore.calculateDistance(homeStrength, awayStrength, neutral);

        for (SampleScore score: sample) {
            score.calculateDistance(homeStrength, awayStrength, neutral);
            if (score.getDistance() < fittestScore.getDistance()) {
                fittestScore = score;
            } else {
                break;
            }
        }

        return fittestScore;
    }

    private SampleScore calculateClosestHome(List<SampleScore> sample, double strength) {

        SampleScore fittestScore = sample.get(rnd.nextInt(sample.size()));

        fittestScore.calculateHomeDistance(strength, false);

        for (SampleScore score: sample) {
            score.calculateHomeDistance(strength, false);
            if (score.getDistance() < fittestScore.getDistance()) {
                fittestScore = score;
            } else {
                break;
            }
        }

        return fittestScore;
    }

    private SampleScore calculateClosestAway(List<SampleScore> sample, double strength) {

        SampleScore fittestScore = sample.get(rnd.nextInt(sample.size()));

        fittestScore.calculateAwayDistance(strength, false);

        for (SampleScore score: sample) {
            score.calculateAwayDistance(strength, false);
            if (score.getDistance() < fittestScore.getDistance()) {
                fittestScore = score;
            } else {
                break;
            }
        }

        return fittestScore;
    }

    private SampleScore calculateClosest(List<SampleScore> sample, double strength, boolean filterOnHome) {

        SampleScore fittestScore = sample.get(rnd.nextInt(sample.size()));

        fittestScore.calculateClosest(strength, filterOnHome);

        for (SampleScore score: sample) {
            score.calculateClosest(strength, filterOnHome);
            if (score.getDistance() < fittestScore.getDistance()) {
                fittestScore = score;
            } else {
                break;
            }
        }

        return fittestScore;
    }
}
