package org.openfootie.openengine.util;

import org.openfootie.openengine.domain.Environment;
import org.openfootie.openengine.util.analysis.MatchSampleReport;

public class MatchSampleAnalysis {

    public static void main(String [] args) {

        Environment environment = new Environment();

        if (!environment.load()) {
            System.out.println("Error loading environment");
            return;
        }

        MatchSampleReport matchParticipation =
                new MatchSampleReport(environment.getSamplesPath() + "/" + args[0]);
    }
}
