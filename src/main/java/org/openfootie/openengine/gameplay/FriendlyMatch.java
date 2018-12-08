package org.openfootie.openengine.gameplay;

import org.openfootie.openengine.domain.Environment;
import org.openfootie.openengine.domain.Fixture;
import org.openfootie.openengine.domain.Result;

public class FriendlyMatch {

    public static void main(String [] args) {

       boolean international = true;
       Environment environment = new Environment();

       if (!environment.load()) {
           System.out.println("Error loading environment");
           return;
       }

       if ("c".equals(args[0]) || "club".equals(args[0])) {
           international = false;
       }

       Fixture fixture = environment.getRandomFixture(international);
       ScoreEngine scoreEngine = new ScoreEngine(environment);

       Result result = scoreEngine.calculate(fixture);

       displayMatchResult(fixture, result);
    }

    private static void displayMatchResult(Fixture fixture, Result result) {
        System.out.println(
                fixture.getHomeTeam().getName() + " - " + fixture.getAwayTeam().getName() + " " +
                        result.getHomeScore() + " - " + result.getAwayScore());
    }
}
