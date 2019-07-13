package org.openfootie.openengine.gameplay;

import org.openfootie.openengine.domain.Environment;
import org.openfootie.openengine.domain.Nation;
import org.openfootie.openengine.domain.TeamImpl;
import org.openfootie.openengine.engine.Match;

public class FriendlyMatch {

    public static void main(String [] args) {

        Environment environment = new Environment();

        if (!environment.load()) {
            System.out.println("Error loading environment");
            return;
        }

        Match match = new Match(new TeamImpl("Team A"), new TeamImpl("Team B"));

        match.play();

        System.out.println(match.getScore());
    }
}
