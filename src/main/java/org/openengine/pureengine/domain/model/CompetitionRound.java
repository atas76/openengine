package org.openengine.pureengine.domain.model;

import org.openengine.pureengine.TieBreaker;

public class CompetitionRound {

    private String name;
    private boolean homeAdvantage;
    private TieBreaker tieBreaker;

    public CompetitionRound(String name) {
        this.name = name;
    }

    public CompetitionRound(String name, boolean homeAdvantage) {
        this(name);
        this.homeAdvantage = homeAdvantage;
    }

    public CompetitionRound(String name, boolean homeAdvantage, TieBreaker tieBreaker) {
        this(name, homeAdvantage);
        this.tieBreaker = tieBreaker;
    }

    public String getName() {
        return name;
    }

    public boolean hasHomeAdvantage() {
        return homeAdvantage;
    }

    public TieBreaker getTieBreaker() {
        return this.tieBreaker;
    }
}
