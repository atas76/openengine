package org.openengine.mpn;

import org.mpn.Dataset;
import org.mpn.MatchModel;
import org.mpn.Processable;

public class MatchEventDataGeneration {

    private Dataset seedMatchEvents;
    private Dataset generatedMatchEvents;

    private Team homeTeam;
    private Team awayTeam;

    public MatchEventDataGeneration() {
        this.seedMatchEvents = Processable.loadData();
        MatchModel model = new MatchModel(this.seedMatchEvents, "L", "T");
        homeTeam = new Team("Liverpool", model.getHomeTeamActionChainMappings());
        awayTeam = new Team("Tottenham", model.getAwayTeamActionChainMappings());
    }

    public void generate() {
        // TODO implement generating of match events
        // refactoring of the start() method of MatchEngine class
    }
}
