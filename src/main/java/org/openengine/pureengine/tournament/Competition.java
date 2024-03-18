package org.openengine.pureengine.tournament;

import java.util.ArrayList;
import java.util.List;

public class Competition {

    private String name;
    private List<CompetitionRound> rounds = new ArrayList<>();

    public Competition(String name, List<CompetitionRound> rounds) {
        this.name = name;
        this.rounds.addAll(rounds);
    }

    public void displayName() {
        System.out.println(name);
    }

    public void displayStageNames() {
        System.out.println("Stages: ");
        rounds.forEach(round -> System.out.println(round.getName()));
    }

    public String getName() {
        return name;
    }

    public String getStartingRoundName() {
        return rounds.get(0).getName();
    }

    public List<CompetitionRound> getRounds() {
        return rounds;
    }
}
