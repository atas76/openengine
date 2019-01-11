package org.openfootie.openengine.domain;

public class Club {

    private String name;
    private String nation;

    public Club(String name, String nation) {
        this.name = name;
        this.nation = nation;
    }

    public String getName() {
        return name;
    }

    public String getNation() {
        return this.nation;
    }
}
