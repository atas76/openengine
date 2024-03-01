package org.openengine.pureengine.tournament;

import org.openengine.pureengine.Team;

import java.util.ArrayList;
import java.util.List;

public class KnockoutRound {

    private String name;

    private List<Team> teams = new ArrayList<>();

    private List<Fixture> fixtures = new ArrayList<>();

    public KnockoutRound(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
