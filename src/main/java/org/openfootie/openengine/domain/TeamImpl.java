package org.openfootie.openengine.domain;

public class TeamImpl implements Team {

    private String name;
    private Squad squad;

    public TeamImpl(String name) {
        this.name = name;
        this.squad = new Squad();
    }

    public TeamImpl(String name, Squad squad) {
        this.name = name;
        this.squad = squad;
    }

    public String getName() {
        return this.name;
    }

    public Squad getSquad() {
        return this.squad;
    }
}
