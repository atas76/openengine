package org.openfootie.openengine.util;

import org.openfootie.openengine.domain.Environment;
import org.openfootie.openengine.util.analysis.MatchReport;

public class MatchSampleAnalysis {

    private Environment environment;
    private MatchReport matchReport;

    public MatchSampleAnalysis(Environment environment, MatchReport matchReport) {
        this.environment = environment;
        this.matchReport = matchReport;
    }

    public static void main(String [] args) {

        Environment environment = new Environment();

        if (!environment.load()) {
            System.out.println("Error loading environment");
            return;
        }

        MatchReport matchReport =
                new MatchReport(environment.getSamplesPath() + "/" + args[0]);
    }
}
