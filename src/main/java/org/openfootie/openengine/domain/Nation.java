package org.openfootie.openengine.domain;

public class Nation implements Team {

    private String name;
    private Squad squad = new Squad();

    public Nation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Squad getSquad() {
        return this.squad;
    }
}
