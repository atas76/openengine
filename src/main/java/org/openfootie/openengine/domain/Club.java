package org.openfootie.openengine.domain;

public class Club implements Team {

    private String name;
    private String nation;
    private Double strength;

    public Club(String name, String nation, Double strength) {
        this.name = name;
        this.nation = nation;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public Double getStrength() {
        return strength;
    }

    public String getNation() {
        return this.nation;
    }
}
