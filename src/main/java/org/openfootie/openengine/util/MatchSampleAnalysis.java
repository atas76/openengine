package org.openfootie.openengine.util;

import org.openfootie.openengine.domain.Environment;
import org.openfootie.openengine.util.analysis.MatchSample;

public class MatchSampleAnalysis {

    public static void main(String [] args) {

        Environment environment = new Environment();

        if (!environment.load()) {
            System.out.println("Error loading environment");
            return;
        }

        MatchSample matchParticipation =
                new MatchSample(environment.getSamplesPath() + "/" + args[0]);
    }
}
