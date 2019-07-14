package org.openfootie.openengine.engine;

import org.openfootie.openengine.domain.Team;
import org.openfootie.openengine.engine.log.MatchLogRecord;
import org.openfootie.openengine.engine.log.MatchLogger;

import java.util.Random;

import static org.openfootie.openengine.engine.Coordinates.*;

public class Match {

    private Team homeTeam;
    private Team awayTeam;

    private TeamStats homeTeamStats = new TeamStats();
    private TeamStats awayTeamStats = new TeamStats();

    private final static int HT_STEP_DURATION = 96;

    private static Random rnd = new Random();

    private StateActionFrequencyMatrix decisionFM = new StateActionFrequencyMatrix();
    private ActionOutcomeFrequencyMatrix outcomeFM = new ActionOutcomeFrequencyMatrix();

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    private Team coinToss() {
       return rnd.nextBoolean() ? this.homeTeam : this.awayTeam;
    }

    private void incrementScore(Team team) {
        if (team.getName().equals(this.homeTeam.getName())) {
            this.homeTeamStats.incrementScore();
        } else if (team.getName().equals(this.awayTeam.getName())) {
            this.awayTeamStats.incrementScore();
        } else {
            throw new RuntimeException("Unexpected error: team naming mismatch");
        }
    }

    private Team toggle(Team team) {
        if (team.getName().equals(this.homeTeam.getName())) {
            return this.awayTeam;
        } else if (team.getName().equals(this.awayTeam.getName()))  {
            return this.homeTeam;
        } else {
            throw new RuntimeException("Unexpected error: team naming mismatch");
        }
    }

    public void play() {

        MatchLogger matchLogger = new MatchLogger();

        State state = new State(D, coinToss());

        int currentStep = 0;

        while (currentStep < HT_STEP_DURATION) {

            Action action = decisionFM.getAction(state.getBall());

            MatchLogRecord matchLogRecord = new MatchLogRecord(state.getPossession().getName(), state.getBall(), action);

            Outcome outcome = outcomeFM.getOutcome(action, state);
            if (outcome.isGoal()) {
                matchLogRecord.setGoalScored();
                incrementScore(state.getPossession());
            }

            state = new State(outcome.getCoordinates(), outcome.isKeepPossession() ? state.getPossession() : toggle(state.getPossession()));
            ++currentStep;
            matchLogger.info(matchLogRecord);
        }
    }

    public String getScore() {
        return String.format("%s - %s %d - %d",
                this.homeTeam.getName(), this.awayTeam.getName(),
                this.homeTeamStats.getScore(), this.awayTeamStats.getScore());
    }
}
