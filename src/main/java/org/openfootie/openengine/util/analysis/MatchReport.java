package org.openfootie.openengine.util.analysis;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.openfootie.openengine.domain.Team;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchReport {

    private List<PlayerParticipation> matchParticipation = new ArrayList<>();

    private Map<String, List<PlayerParticipation>> teamParticipation = new HashMap<>();

    private final int MATCH_DURATION = 90;

    private int matchDuration = MATCH_DURATION;

    public MatchReport(String matchFile) {
        try {

            Reader reader = new FileReader(matchFile);

            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(reader);

            for (CSVRecord record : records) {

                PlayerParticipation playerParticipation = new PlayerParticipation(
                        record.get(0),
                        record.get(1),
                        Integer.parseInt(record.get(2)),
                        Integer.parseInt(record.get(3)));

                matchParticipation.add(playerParticipation);

                teamParticipation.putIfAbsent(playerParticipation.getTeam(), new ArrayList<>());

                teamParticipation.get(playerParticipation.getTeam()).add(playerParticipation);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot retrieve samples data");
        } catch (IOException ex) {
            System.out.println("Error accessing sample data");
        }
    }

    public List<PlayerParticipation> getMatchParticipation() {
        return this.matchParticipation;
    }

    // TODO untested
    public List<Long> calculateParticipationRatings(Team team) {

        List<Long> participationRatings = new ArrayList<>();

        this.teamParticipation.get(team.getName()).forEach(playerParticipation ->
            participationRatings.add(Math.round(team.getSquad()
                    .getPlayerByNumber(playerParticipation.getShirtNo()).getAbility() *
                    playerParticipation.getMinutesPlayed() / (double) this.matchDuration)));

        return participationRatings;
    }
}
