package org.openengine.vanilla;

import java.util.Random;

public class Match {

    private static final int DURATION = 300; // Number of actions in the match
    private static Random rnd = new Random();

    private int currentTime = 0; // Actions played so far
    private State state;
    private Team homeTeam;
    private Team awayTeam;

    public static void main(String[] args) {
        new Match().play();
    }

    public void kickOff() {
        state.setPossessionTeam(rnd.nextBoolean() ? homeTeam : awayTeam);
        state.setPossessionPlayer(state.getPossessionTeam().getGoalkeeper()); // keep things simple for now
    }

    public void play() {
        kickOff();
        while (currentTime < DURATION) {
            kickOff();
            Action action = state.getPossessionPlayer().decide();
            ++currentTime;
        }
    }
}
