package org.openengine.pureengine.tournament;

public class CompetitionRound {

    private String name;
    private boolean homeAdvantage;

    public CompetitionRound(String name) {
        this.name = name;
    }

    public CompetitionRound(String name, boolean homeAdvantage) {
        this(name);
        this.homeAdvantage = homeAdvantage;
    }

    public boolean isHomeAdvantage() {
        return homeAdvantage;
    }

    public String getName() {
        return name;
    }
}
