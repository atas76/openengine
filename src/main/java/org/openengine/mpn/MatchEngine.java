package org.openengine.mpn;

import org.mpn.*;
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

    private PenaltiesDataset penaltiesDataset;

    private Random rnd = new Random();

    public static void main(String[] args) {

        MatchModel model = new MatchModel(Processable.loadData(), "L", "T");

        Team homeTeam = new Team("Liverpool", model.getHomeTeamActionChainMappings());
        Team awayTeam = new Team("Tottenham", model.getAwayTeamActionChainMappings());

        MatchEngine matchEngine = new MatchEngine(homeTeam, awayTeam,
                Processable.loadPenaltiesData("src/main/resources/data/mpn/penalties_incremental.mpn")); // File used in 'dev mode'
        matchEngine.start();

        System.out.println();
        System.out.println("Final score: " + homeTeam + " - " + awayTeam + " "
                + homeTeam.getGoalsScored() + " - " + awayTeam.getGoalsScored());
    }

    private void start() {

        tossCoin();

        System.out.println();
        System.out.println("*** FIRST HALF ***");

        playTimePeriod(DURATION);

        System.out.println("End of first half");

        this.possessionTeam = this.initialKickOffTeam == this.homeTeam ? this.awayTeam : this.homeTeam;

        System.out.println();

        System.out.println("*** SECOND HALF ***");

        currentTime = DURATION; // Reset timer for 2nd half
        playTimePeriod(DURATION * 2);
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

    private void playTimePeriod(int duration) {
        MatchPhaseTransition currentTransition = getKickOffPhaseTransition();
        processTransition(currentTransition);
        while (currentTime < duration) {
            currentTransition = getNextTransition(currentTransition);
            processTransition(currentTransition);
        }
    }

    private void processTransition(MatchPhaseTransition currentTransition) {
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

    private final double PENALTY_AWARD_COEFFICIENT = 0.00525;
    // For testing
    // private final double PENALTY_AWARD_COEFFICIENT = 0.0525;

    // TODO Leaving it as it is for now until injury time implementation
    private final int PENALTY_DURATION = 0;
    private final double PENALTY_XG = 0.79;

    private MatchPhaseTransition getNextTransition(MatchPhaseTransition transition) {
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
        if (Set.of(State.ATTACK, State.COUNTER_ATTACK).contains(transition.getInitialState())) {
            if (rnd.nextDouble() < PENALTY_AWARD_COEFFICIENT) { // penalty awarded
                System.out.println("Penalty awarded");
                Statement penaltyStatement = this.penaltiesDataset.getAny();
                return new DynamicTransition(transition.getTeamKey(), State.PENALTY, penaltyStatement.getEndState(), PENALTY_DURATION,
                        penaltyStatement.getGoalAttemptOutcome(), false, PENALTY_XG,
                        penaltyStatement.getDefaultEndState());
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

    public MatchEngine(Team homeTeam, Team awayTeam, PenaltiesDataset penaltiesDataset) {
        this(homeTeam, awayTeam);
        this.penaltiesDataset = penaltiesDataset;
    }

    public MatchEngine(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }
}
