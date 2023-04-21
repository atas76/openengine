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
            ActionOutcome actionOutcome = state.execute(action);
            updateStats(actionOutcome);
            updateState(actionOutcome);
            ++currentTime;
        }
        displayScore();
    }

    void displayScore() {
        System.out.println(homeTeam + " - " + awayTeam + " " + this.homeTeamScore + " - " + this.awayTeamScore);
    }

    void updateState(ActionOutcome actionOutcome) {
        if (actionOutcome.isPossessionChange()) {
            changePossession();
        }
        if (actionOutcome.getPossessionPlayer() != null) {
            this.state.setPossessionPlayer(actionOutcome.getPossessionPlayer());
        }
    }

    private void changePossession() {
        this.state.setPossessionTeam(this.state.getPossessionTeam().equals(this.homeTeam) ?
                this.awayTeam : this.homeTeam);
    }

    void updateStats(ActionOutcome actionOutcome) {
        actionOutcome.getEvent().ifPresent(event -> {
            if (event.getType().equals(EventType.GOAL_SCORED)) {
                if (this.state.getPossessionTeam().equals(this.homeTeam)) {
                    ++homeTeamScore;
                } else {
                    ++awayTeamScore;
                }
            }
        });
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public State getState() {
        return state;
    }
}

