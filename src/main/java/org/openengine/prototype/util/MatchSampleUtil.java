package org.openengine.prototype.util;

import org.openengine.prototype.domain.Environment;
import org.openengine.prototype.util.analysis.MatchReport;

import java.util.HashMap;
import java.util.Map;

public class MatchSampleUtil {

    public static void main(String [] args) {

        Environment environment = new Environment();

        if (!environment.load()) {
            System.out.println("Error loading environment");
            return;
        }

        MatchReport matchReport = new MatchReport(environment.getSamplesPath() + "/" + args[0]);

        Map<String, Long> formationRatings = new HashMap<>();

        matchReport.getTeams().forEach(teamName ->
                formationRatings.put(teamName, matchReport.getFormationRating(environment.getTeam(teamName))));

        formationRatings.forEach((teamName, teamRating) -> System.out.println(teamName + ": " + teamRating));
    }
}
