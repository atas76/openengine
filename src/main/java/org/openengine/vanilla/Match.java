package org.openengine.vanilla;

import java.util.Random;

public class Match {

    private static final int DURATION = 300; // Number of actions in the match
    private static Random rnd = new Random();

    private int currentTime = 0; // Actions played so far
    private State state = new State();
    private Team homeTeam = new Team("Reds", Tactics._4_4_2);
    private Team awayTeam = new Team("Blues", Tactics._4_4_2);

    private int homeTeamScore;
    private int awayTeamScore;

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
            Action action = state.getPossessionPlayer().decide();
            this.state = state.execute(action);
            updateStats(state);
            ++currentTime;
        }
        displayScore();
    }

    private void displayScore() {
        System.out.println(homeTeam + " - " + awayTeam + " " + this.homeTeamScore + " - " + this.awayTeamScore);
    }

    private void updateStats(State state) {

    }
}
