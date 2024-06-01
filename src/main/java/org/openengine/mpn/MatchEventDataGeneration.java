package org.openengine.mpn;

import org.mpn.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatchEventDataGeneration {

    private static final int DURATION = 60 * 45; // duration in seconds

    private Dataset seedMatchEvents;
    private Dataset generatedMatchEvents;

    private List<ProcessUnit> matchEventsBuffer = new ArrayList<>();

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
        this.generatedMatchEvents = new Dataset(matchEventsBuffer, true);
        this.generatedMatchEvents.getData().forEach(System.out::println);
    }

    public void generate() {
        // TODO implement generating of match events
        // refactoring of the start() method of MatchEngine class

        tossCoin();
        playTimePeriod(DURATION);
    }

    private void registerTransition(MatchPhaseTransition transition) {
        this.matchEventsBuffer.add((Statement) transition);
    }

    private void playTimePeriod(int duration) {
        MatchPhaseTransition currentTransition = getKickOffPhaseTransition();
        registerTransition(currentTransition);
        // processTransition(currentTransition);
        /*
        while (currentTime < duration) {
            currentTransition = getNextTransition(currentTransition);
            processTransition(currentTransition);
        }
         */
    }

    private MatchPhaseTransition getKickOffPhaseTransition() {
        Dataset potentialTransitions = possessionTeam.getActionsByState(State.KICK_OFF);
        return potentialTransitions.getAny();
    }

    private void tossCoin() {
        boolean homeTeamKicksOff = rnd.nextBoolean();
        initialKickOffTeam = homeTeamKicksOff ? homeTeam : awayTeam;
        possessionTeam = initialKickOffTeam;
    }
}
