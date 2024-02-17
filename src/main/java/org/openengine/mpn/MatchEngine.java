package org.openengine.mpn;

import org.mpn.Dataset;
import org.mpn.MatchModel;
import org.mpn.Processable;
import org.mpn.State;
import org.openengine.openfootie.util.Util;

import java.util.Random;
import java.util.Set;

public class MatchEngine {

    private Team homeTeam;
    private Team awayTeam;

    private static final int DURATION = 60 * 45; // duration in seconds

    private int currentTime = 0;
    private Team initialKickOffTeam;
    private Team possessionTeam;

    private Random rnd = new Random();

    public static void main(String[] args) {

        MatchModel model = new MatchModel(Processable.loadData(), "L", "T");

        Team homeTeam = new Team("Liverpool", model.getHomeTeamActionChainMappings());
        Team awayTeam = new Team("Tottenham", model.getAwayTeamActionChainMappings());

        MatchEngine matchEngine = new MatchEngine(homeTeam, awayTeam);
        matchEngine.start();

        System.out.println();
        System.out.println("Final score: " + homeTeam + " - " + awayTeam + " "
                + homeTeam.getGoalsScored() + " - " + awayTeam.getGoalsScored());
    }

    private void start() {

        tossCoin();

        System.out.println();
        System.out.println("*** FIRST HALF ***");

        playTimePeriodClean(DURATION);

        System.out.println("End of first half");

        this.possessionTeam = this.initialKickOffTeam == this.homeTeam ? this.awayTeam : this.homeTeam;

        System.out.println();

        System.out.println("*** SECOND HALF ***");

        currentTime = DURATION; // Reset timer for 2nd half
        playTimePeriodClean(DURATION * 2);
    }

    private void displayMatchInfo(MatchPhaseTransition currentPhaseTransition) {
        displayEvents(currentPhaseTransition);
        System.out.println();
        displayMatchState();
        System.out.println(currentPhaseTransition);
    }

    private void displayEvents(MatchPhaseTransition currentPhaseTransition) {
        if (currentPhaseTransition.getEndState() == State.GOAL
                && currentPhaseTransition.getInitialState() == State.GOAL_ATTEMPT_OUTCOME) {
            displayGoal();
        }
    }

    private void playTimePeriodClean(int duration) {
        MatchPhaseTransition currentTransition = getKickOffPhaseTransition();
        postProcessTransition(currentTransition);
        while (currentTime < duration) {
            currentTransition = processTransition(currentTransition);
            postProcessTransition(currentTransition);
        }
    }

    private void postProcessTransition(MatchPhaseTransition currentTransition) {
        displayMatchInfo(currentTransition);
        updateMatchState(currentTransition);
    }

    private void updateMatchState(MatchPhaseTransition phaseTransition) {
        if (State.GOAL_ATTEMPT_OUTCOME == phaseTransition.getInitialState()
                && State.GOAL == phaseTransition.getEndState()) {
            possessionTeam.score();
        } else {
            currentTime += phaseTransition.getDuration();
        }
        if (phaseTransition.getInitialState() != State.GOAL_ATTEMPT) {
            updatePossession(phaseTransition.isPossessionChanged());
        }
    }

    private MatchPhaseTransition processTransition(MatchPhaseTransition transition) {
        if (Set.of(State.GOAL_ATTEMPT, State.PENALTY, State.GOAL_ATTEMPT_FREEKICK)
                .contains(transition.getInitialState())) {
            if (isGoalAttemptSuccessful(transition.getxG())) {
                return new DynamicTransition( transition.getTeamKey(),
                        State.GOAL_ATTEMPT_OUTCOME, State.GOAL,
                        transition.getDuration(), transition.getGoalAttemptOutcome(), true,
                        transition.getxG(), transition.getDefaultEndState());
            } else {
                State outcomeState = transition.getGoalAttemptOutcome() != null
                        ? transition.getGoalAttemptOutcome()
                        : transition.getEndState();
                if (outcomeState == State.OFF_TARGET) {
                    // TODO copy constructor maybe? OTOH, we want to be tightly-bound to the MPN 'Statement'
                    return new DynamicTransition(
                            transition.getTeamKey(), State.GOAL_ATTEMPT_OUTCOME, State.OFF_TARGET,
                            transition.getDuration(), transition.getGoalAttemptOutcome(), true,
                            transition.getxG(), transition.getDefaultEndState());
                } else if (outcomeState == State.GOAL) {
                    return new DynamicTransition(
                            transition.getTeamKey(), State.GOAL_ATTEMPT_OUTCOME, transition.getDefaultEndState(),
                            transition.getDuration(), transition.getDefaultEndState(), false,
                            transition.getxG(), transition.getDefaultEndState());
                } else {
                    return new DynamicTransition(
                            transition.getTeamKey(), State.GOAL_ATTEMPT_OUTCOME, outcomeState,
                            transition.getDuration(), transition.getGoalAttemptOutcome(),
                            transition.isPossessionChanged(), transition.getxG(), transition.getDefaultEndState());
                }
            }
        }
        if (State.GOAL_ATTEMPT_OUTCOME == transition.getInitialState() && State.GOAL == transition.getEndState()) {
            return getKickOffPhaseTransition();
        }
        return possessionTeam.getActionsByState(mapSetPieces(transition.getEndState())).getAny();
    }

    private State mapSetPieces(State openPlayState) {
        return switch(openPlayState) {
            case OFF_TARGET -> State.GOAL_KICK;
            case FOUL, OFFSIDE -> State.FREEKICK;
            default -> openPlayState;
        };
    }

    private boolean isGoalAttemptSuccessful(Double xG) {
        return rnd.nextDouble() <= xG;
    }

    private void updatePossession(boolean possessionChanged) {
        if (possessionChanged) {
            changePossession();
        }
    }

    private void changePossession() {
        possessionTeam = (possessionTeam == homeTeam) ? awayTeam : homeTeam;
    }

    private void displayGoal() {
        System.out.println(possessionTeam + ": SCORED");
    }

    private void displayScore() {
        System.out.println(this.homeTeam + " - " + this.awayTeam + " "
                + this.homeTeam.getGoalsScored() + " - " + this.awayTeam.getGoalsScored());
    }

    public void displayMatchState() {
        System.out.println(Util.convertForTimer(currentTime) + ": " + possessionTeam);
        displayScore();
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

    public MatchEngine(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }
}
