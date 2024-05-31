package org.openengine.mpn;

import org.mpn.Dataset;
import org.mpn.MatchModel;
import org.mpn.Processable;

import java.util.Random;

public class MatchEventDataGeneration {

    private Dataset seedMatchEvents;
    private Dataset generatedMatchEvents;

    private Random rnd = new Random();

    private Team homeTeam;
    private Team awayTeam;

    private Team initialKickOffTeam;
    private Team possessionTeam;

    public MatchEventDataGeneration() {
        this.seedMatchEvents = Processable.loadData();
        MatchModel model = new MatchModel(this.seedMatchEvents, "L", "T");
        homeTeam = new Team("Liverpool", model.getHomeTeamActionChainMappings());
        awayTeam = new Team("Tottenham", model.getAwayTeamActionChainMappings());
    }

    public static void main(String[] args) {
        MatchEventDataGeneration match = new MatchEventDataGeneration();
        match.generate();
        match.out();
    }

    public void out() {
        System.out.println(possessionTeam);
    }

    public void generate() {
        // TODO implement generating of match events
        // refactoring of the start() method of MatchEngine class

        tossCoin();
    }

    private void tossCoin() {
        boolean homeTeamKicksOff = rnd.nextBoolean();
        initialKickOffTeam = homeTeamKicksOff ? homeTeam : awayTeam;
        possessionTeam = initialKickOffTeam;
    }
}
