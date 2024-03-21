package org.openengine.pureengine.domain;

public class CompetitionRoundDTO {

    private String name;
    private Boolean homeAdvantage;
    private String tieBreaker;

    public CompetitionRoundDTO(String name, Boolean homeAdvantage, String tieBreaker) {
        this.name = name;
        this.homeAdvantage = homeAdvantage;
        this.tieBreaker = tieBreaker;
    }

    public String getName() {
        return name;
    }

    public Boolean getHomeAdvantage() {
        return homeAdvantage;
    }

    public String getTieBreaker() {
        return tieBreaker;
    }
}
