package org.openengine.vanilla;

import org.openengine.vanilla.util.Flags;

import java.util.Map;
import java.util.Random;

public class Match {

    private static final int DURATION = 300; // Number of actions in the match
    private static Random rnd = new Random();

    private int currentTime = 0; // Actions played so far
    private State state = new State();
    private Team homeTeam = new Team("Reds", Tactics._4_3_3);
    private Team awayTeam = new Team("Blues", Tactics._4_3_3);

    private Map<Team, TeamStats> stats = Map.of(homeTeam, new TeamStats(), awayTeam, new TeamStats());

    private int homeTeamScore;
    private int awayTeamScore;

    public Match() {
        initializeMarkings();
    }

    public static void main(String[] args) {
        Flags.LOGGING = false;
        new Match().play();
    }

    public void kickOff() {
        state.setPossessionTeam(rnd.nextBoolean() ? homeTeam : awayTeam);
        state.setPossessionPlayer(state.getPossessionTeam().getGoalkeeper()); // keep things simple for now
    }

    public void initializeMarkings() {
        // 4-4-2 vs. 4-4-2
        // init442Markings(homeTeam, awayTeam);
        // init442Markings(awayTeam, homeTeam);

        // 4-3-3 vs. 4-3-3
        init433Markings(homeTeam, awayTeam);
        init433Markings(awayTeam, homeTeam);
    }

    private void init442Markings(Team attackingTeam, Team defendingTeam) {
        attackingTeam.getPlayerByPosition(Position.D_CR).addMarker(defendingTeam.getPlayerByPosition(Position.F_CL));
        attackingTeam.getPlayerByPosition(Position.D_CL).addMarker(defendingTeam.getPlayerByPosition(Position.F_CR));
        attackingTeam.getPlayerByPosition(Position.M_R).addMarker(defendingTeam.getPlayerByPosition(Position.M_L));
        attackingTeam.getPlayerByPosition(Position.M_CR).addMarker(defendingTeam.getPlayerByPosition(Position.M_CL));
        attackingTeam.getPlayerByPosition(Position.M_CL).addMarker(defendingTeam.getPlayerByPosition(Position.M_CR));
        attackingTeam.getPlayerByPosition(Position.M_L).addMarker(defendingTeam.getPlayerByPosition(Position.M_R));
        attackingTeam.getPlayerByPosition(Position.F_CR).addMarker(defendingTeam.getPlayerByPosition(Position.D_CL));
        attackingTeam.getPlayerByPosition(Position.F_CR).addMarker(defendingTeam.getPlayerByPosition(Position.D_L), 0.33);
        attackingTeam.getPlayerByPosition(Position.F_CL).addMarker(defendingTeam.getPlayerByPosition(Position.D_CR));
        attackingTeam.getPlayerByPosition(Position.F_CL).addMarker(defendingTeam.getPlayerByPosition(Position.D_R), 0.33);
    }

    private void init433Markings(Team attackingTeam, Team defendingTeam) {
        attackingTeam.getPlayerByPosition(Position.D_R).addMarker(defendingTeam.getPlayerByPosition(Position.F_LC), 0.5);
        attackingTeam.getPlayerByPosition(Position.D_CR).addMarker(defendingTeam.getPlayerByPosition(Position.F_LC), 0.5);
        attackingTeam.getPlayerByPosition(Position.D_CR).addMarker(defendingTeam.getPlayerByPosition(Position.F_C), 0.5);
        attackingTeam.getPlayerByPosition(Position.D_CL).addMarker(defendingTeam.getPlayerByPosition(Position.F_C), 0.5);
        attackingTeam.getPlayerByPosition(Position.D_CL).addMarker(defendingTeam.getPlayerByPosition(Position.F_RC), 0.5);
        attackingTeam.getPlayerByPosition(Position.D_L).addMarker(defendingTeam.getPlayerByPosition(Position.F_RC), 0.5);
        attackingTeam.getPlayerByPosition(Position.M_RC).addMarker(defendingTeam.getPlayerByPosition(Position.M_LC), 0.5);
        attackingTeam.getPlayerByPosition(Position.M_C).addMarker(defendingTeam.getPlayerByPosition(Position.M_C), 0.5);
        attackingTeam.getPlayerByPosition(Position.M_LC).addMarker(defendingTeam.getPlayerByPosition(Position.M_RC), 0.5);
        attackingTeam.getPlayerByPosition(Position.F_RC).addMarker(defendingTeam.getPlayerByPosition(Position.D_L), 0.5);
        attackingTeam.getPlayerByPosition(Position.F_RC).addMarker(defendingTeam.getPlayerByPosition(Position.D_CL), 0.5);
        attackingTeam.getPlayerByPosition(Position.F_C).addMarker(defendingTeam.getPlayerByPosition(Position.D_CR), 0.5);
        attackingTeam.getPlayerByPosition(Position.F_C).addMarker(defendingTeam.getPlayerByPosition(Position.D_CL), 0.5);
        attackingTeam.getPlayerByPosition(Position.F_LC).addMarker(defendingTeam.getPlayerByPosition(Position.D_CR), 0.5);
        attackingTeam.getPlayerByPosition(Position.F_LC).addMarker(defendingTeam.getPlayerByPosition(Position.D_R), 0.5);
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
        System.out.println();
        displayStats();
    }

    void displayScore() {
        System.out.println(homeTeam + " - " + awayTeam + " " + this.homeTeamScore + " - " + this.awayTeamScore);
    }

    private void displayStats() {
        System.out.println("Shots at goal: " +
                this.getTeamStats(this.homeTeam).getShotsAtGoal() + " - " +
                this.getTeamStats(this.awayTeam).getShotsAtGoal());
    }

    void updateState(ActionOutcome actionOutcome) {
        if (actionOutcome.isPossessionChange()) {
            changePossession();
        }
        if (actionOutcome.getPossessionPlayer() != null) {
            this.state.setPossessionPlayer(actionOutcome.getPossessionPlayer());
        } else {
            this.state.setDefaultPossessionPlayer();
        }
    }

    private void changePossession() {
        this.state.setPossessionTeam(this.state.getPossessionTeam().equals(this.homeTeam) ?
                this.awayTeam : this.homeTeam);
    }

    void updateStats(ActionOutcome actionOutcome) {
        actionOutcome.getEvents().forEach(event -> {
            switch (event.getType()) {
                case GOAL_SCORED -> {
                    if (this.state.getPossessionTeam().equals(this.homeTeam)) {
                        ++homeTeamScore;
                    } else {
                        ++awayTeamScore;
                    }
                }
                case SHOT -> stats.get(this.state.getPossessionTeam()).addShotAtGoal();
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

    public TeamStats getTeamStats(Team team) {
        return this.stats.get(team);
    }
}
