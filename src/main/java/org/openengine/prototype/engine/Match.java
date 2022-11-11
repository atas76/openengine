package org.openengine.prototype.engine;

import org.openengine.prototype.domain.Team;
import org.openengine.prototype.engine.log.MatchLogRecord;
import org.openengine.prototype.engine.log.MatchLogger;

import java.util.Random;

import static java.util.Objects.isNull;
import static org.openengine.prototype.engine.Coordinates.*;

public class Match {

    private Team homeTeam;
    private Team awayTeam;

    private TeamStats homeTeamStats = new TeamStats();
    private TeamStats awayTeamStats = new TeamStats();

    private Team halfTimeStarter;

    private final static int HT_STEP_DURATION = 96;

    private static Random rnd = new Random();

    private StateActionFrequencyMatrix decisionFM = new StateActionFrequencyMatrix();
    private ActionOutcomeFrequencyMatrix outcomeFM = new ActionOutcomeFrequencyMatrix();

    private MatchLogger matchLogger = new MatchLogger();

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public MatchLogger getMatchLogger() {
        return this.matchLogger;
    }

    private Team getHalfTimeStarter() {
       if (isNull(this.halfTimeStarter)) {
           return rnd.nextBoolean() ? this.homeTeam : this.awayTeam;
       } else {
           return this.halfTimeStarter;
       }
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

    public void playHalfTime() {

        State state = new State(D, getHalfTimeStarter());

        this.halfTimeStarter = toggle(state.getPossession());

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
            matchLogger.report(matchLogRecord);
        }
    }

    public String getScore() {
        return String.format("%s - %s %d - %d",
                this.homeTeam.getName(), this.awayTeam.getName(),
                this.homeTeamStats.getScore(), this.awayTeamStats.getScore());
    }
}
