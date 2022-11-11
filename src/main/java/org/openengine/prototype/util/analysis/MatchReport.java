package org.openengine.prototype.util.analysis;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.openengine.prototype.domain.Team;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

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

    public Set<String> getTeams() {
        return this.teamParticipation.keySet();
    }

    public Long getFormationRating(Team team) {
        return calculateParticipationRatings(team).stream().mapToLong(Long::longValue).sum();
    }

    /**
     *
     * Calculates a list of participation ratings per team for the current match report
     *
     * Each player placement in a specific tactical position for X minutes is defined as a 'participation', and it is
     * the data unit in the match report sample. Each such participation corresponds to a 'rating' based on players'
     * ability and 'minutes of participation', and it is aimed as a measure of a player's aggregate expected
     * contribution playing in that particular tactical position for X minutes.
     *
     * @param team the team object retrieved from the environment
     * @return a raw list of participation ratings
     */
    List<Long> calculateParticipationRatings(Team team) {

        List<Long> participationRatings = new ArrayList<>();

        this.teamParticipation.get(team.getName()).forEach(playerParticipation ->
            participationRatings.add(Math.round(team.getSquad()
                    .getPlayerByNumber(playerParticipation.getShirtNo()).getAbility() *
                    playerParticipation.getMinutesPlayed() / (double) this.matchDuration)));

        return participationRatings;
    }
}
