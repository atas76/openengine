package org.openengine.pureengine.tournament;

import org.openengine.pureengine.domain.model.Tournament;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class TournamentAggregator {

    private final static int REPETITIONS = 10_000;

    private Tournament tournament;

    private Map<String, Integer> winners = new HashMap<>();
    private Map<String, Integer> runnerUps = new HashMap<>();

    private Map<String, Integer> finalParticipants = new HashMap<>();

    private Map<String, Integer> historicalCoefficient = new HashMap<>();

    private double averageWinnerSkillRating;

    public TournamentAggregator(Tournament tournament) {
        this.tournament = tournament;
    }

    public void execute() {
        execute(REPETITIONS);
    }

    public void execute(int repetitions) {
        long winnerSkillRatingSum = 0;
        for (int i = 0; i < REPETITIONS; i++) {
            Tournament tournament = new Tournament(this.tournament);
            tournament.play(true);
            winnerSkillRatingSum += tournament.getWinner().getSkill();
            this.winners.merge(tournament.getWinner().getFullName(), 1, Integer::sum);
            this.historicalCoefficient.merge(tournament.getWinner().getFullName(), 2, Integer::sum);
            this.runnerUps.merge(tournament.getRunnerUp().getFullName(), 1, Integer::sum);
            this.historicalCoefficient.merge(tournament.getRunnerUp().getFullName(), 1, Integer::sum);
            this.finalParticipants.merge(tournament.getWinner().getFullName(), 1, Integer::sum);
            this.finalParticipants.merge(tournament.getRunnerUp().getFullName(), 1, Integer::sum);
        }
        this.averageWinnerSkillRating = winnerSkillRatingSum / (double) REPETITIONS;
    }

    public void displayResults() {
        displayRanking(this.winners, "Winners");
        displayRanking(this.runnerUps, "Runner ups");
        displayRanking(this.finalParticipants, "Final participants");
        displayRanking(this.historicalCoefficient, "Historical coefficient");
        System.out.println("Average winner skill rating: " + this.averageWinnerSkillRating);
    }

    private void displayRanking(Map<String, Integer> map, String label) {
        Map<String, Integer> ranking = getSortedMap(map);
        System.out.println(label + ": ");
        ranking.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println();
    }

    private LinkedHashMap<String, Integer> getSortedMap(Map<String, Integer> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
    }
}
