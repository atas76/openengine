package org.openengine.pureengine.tournament;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;


import java.util.*;

public class TournamentAggregator {

    private final static int REPETITIONS = 10_000;

    private Tournament tournament;

    private Map<String, Integer> winners = new HashMap<>();

    public TournamentAggregator(Tournament tournament) {
        this.tournament = tournament;
    }

    public void execute() {
        execute(REPETITIONS);
    }

    public void execute(int repetitions) {
        for (int i = 0; i < REPETITIONS; i++) {
            Tournament tournament = new Tournament(this.tournament);
            tournament.play(true);
            this.winners.merge(tournament.getWinner().getName(), 1, Integer::sum);
        }
    }

    public void displayResults() {
        Map<String, Integer> winsRanking = this.winners.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
        System.out.println("Wins: ");
        winsRanking.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
