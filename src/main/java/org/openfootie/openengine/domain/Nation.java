package org.openfootie.openengine.domain;

public class Nation implements Team {

    private String name;
    private Double strength;

    public Nation(String name, Double strength) {
        this.name = name;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public Double getStrength() {
        return strength;
    }
}
