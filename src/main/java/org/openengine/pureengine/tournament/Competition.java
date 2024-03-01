package org.openengine.pureengine.tournament;

import java.util.ArrayList;
import java.util.List;

public class Competition {

    private String name;
    private List<KnockoutRound> rounds = new ArrayList<>();

    public Competition(String name, List<String> roundNames) {
        this.name = name;
        roundNames.forEach(roundName -> rounds.add(new KnockoutRound(roundName)));
    }

    public void displayName() {
        System.out.println(name);
    }

    public void displayStageNames() {
        System.out.println("Stages: ");
        rounds.forEach(round -> System.out.println(round.getName()));
    }
}
