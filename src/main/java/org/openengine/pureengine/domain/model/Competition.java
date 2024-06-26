package org.openengine.pureengine.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Competition {

    private int id;

    private String name;
    private String country;
    private String countryDemonym;

    private List<CompetitionRound> rounds = new ArrayList<>();

    public Competition(int id, String name, String country, String countryDemonym) {
        this(name, country, countryDemonym);
        this.id = id;
    }

    public Competition(String name, String country, String countryDemonym) {
        this.name = name;
        this.country = country;
        this.countryDemonym = countryDemonym;
    }

    public Competition(String name, Collection<CompetitionRound> rounds) {
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

    public void setRoundDetails(List<CompetitionRound> rounds) {
        this.rounds = rounds;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartingRoundName() {
        return rounds.get(0).getName();
    }

    public List<CompetitionRound> getRoundDetails() {
        return rounds;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryDemonym() {
        return countryDemonym;
    }

    @Override
    public String toString() {
        return this.countryDemonym + " " + this.name + " (" + this.country + ")";
    }
}
