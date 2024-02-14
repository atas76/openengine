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

    private void startOld() {

        tossCoin();

        System.out.println();
        System.out.println("*** FIRST HALF ***");

        MatchPhaseTransition kickOffPhaseTransition = getKickOffPhaseTransition();
        displayMatchInfo(kickOffPhaseTransition);

        playTimePeriod(DURATION, kickOffPhaseTransition);
        // playTimePeriodClean(DURATION);

        System.out.println("End of first half");

        this.possessionTeam = this.initialKickOffTeam == this.homeTeam ? this.awayTeam : this.homeTeam;

        System.out.println();

        System.out.println("*** SECOND HALF ***");

        kickOffPhaseTransition = getKickOffPhaseTransition();
        displayMatchInfo(kickOffPhaseTransition);

        currentTime = DURATION; // Reset timer for 2nd half
        playTimePeriod(DURATION * 2, kickOffPhaseTransition);
        // playTimePeriodClean(DURATION * 2);
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
        if (currentPhaseTransition.isGoal() && currentPhaseTransition.getInitialState() == State.GOAL_ATTEMPT_OUTCOME) {
            displayGoal();
        }
    }

    private void playTimePeriodClean(int duration) {
        MatchPhaseTransition currentTransition = getKickOffPhaseTransition();
        displayMatchInfo(currentTransition);
        updateMatchStateClean(currentTransition);
        while (currentTime < duration) {
            currentTransition = processTransition(currentTransition);
            displayMatchInfo(currentTransition);
            updateMatchStateClean(currentTransition);
        }
    }

    private void updateMatchStateClean(MatchPhaseTransition phaseTransition) {
        if (State.GOAL_ATTEMPT_OUTCOME == phaseTransition.getInitialState() && phaseTransition.isGoal()) {
            possessionTeam.score();
        } else {
            currentTime += phaseTransition.getDuration();
        }
        // TODO Possession is flipped back on goal attempts -> exclude goal attempts from possession updates?
        if (phaseTransition.getInitialState() != State.GOAL_ATTEMPT) {
            updatePossession(phaseTransition.isPossessionChanged());
        }
    }

    private MatchPhaseTransition processTransition(MatchPhaseTransition transition) {
        if (Set.of(State.GOAL_ATTEMPT, State.PENALTY, State.GOAL_ATTEMPT_FREEKICK)
                .contains(transition.getInitialState())) {
            if (isGoalAttemptSuccessful(transition.getxG())) {
                return new DynamicTransition( transition.getTeamKey(),
                        State.GOAL_ATTEMPT_OUTCOME, transition.getEndState(),
                        transition.getDuration(), transition.getGoalAttemptOutcome(), true,
                        transition.getxG(), transition.getDefaultEndState(), true);
            } else {
                State outcomeState = transition.getGoalAttemptOutcome() != null
                        ? transition.getGoalAttemptOutcome()
                        : transition.getEndState();
                if (outcomeState == State.OFF_TARGET) {
                    return new DynamicTransition(
                            transition.getTeamKey(), State.GOAL_ATTEMPT_OUTCOME, State.OFF_TARGET,
                            transition.getDuration(), transition.getGoalAttemptOutcome(), true,
                            transition.getxG(), transition.getDefaultEndState(), false);
                } else if (outcomeState == State.GOAL) {
                    return new DynamicTransition(
                            transition.getTeamKey(), State.GOAL_ATTEMPT_OUTCOME, transition.getDefaultEndState(),
                            transition.getDuration(), transition.getDefaultEndState(), false,
                            transition.getxG(), transition.getDefaultEndState(), false);
                } else {
                    return new DynamicTransition(
                            transition.getTeamKey(), State.GOAL_ATTEMPT_OUTCOME, outcomeState,
                            transition.getDuration(), transition.getGoalAttemptOutcome(),
                            transition.isPossessionChanged(), transition.getxG(), transition.getDefaultEndState(),
                            false);
                }
            }
        }
        if (State.GOAL_ATTEMPT_OUTCOME == transition.getInitialState() && transition.isGoal()) {
            // if (transition.isGoal()) {
                return getKickOffPhaseTransition();
            // } else {
            //    return possessionTeam.getActionsByState(mapSetPieces(transition.getEndState())).getAny();
            // }

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

    private void playTimePeriod(int duration, MatchPhaseTransition currentPhaseTransition) {
        while (currentTime < duration) {
            assert currentPhaseTransition != null;
            // Process transition
            Dataset potentialTransitions =
                    possessionTeam.getActionsByState(mapSetPieces(currentPhaseTransition.getEndState()));
            currentPhaseTransition = potentialTransitions.getAny();
            // Display state
            displayMatchInfo(currentPhaseTransition);
            // Update state
            MatchPhaseTransition updatedPhaseTransition = updateMatchState(currentPhaseTransition);
            // Display state
            if (updatedPhaseTransition != currentPhaseTransition) {
                displayMatchInfo(updatedPhaseTransition);
            }
            currentPhaseTransition = updatedPhaseTransition;
        }
    }

    private boolean isGoalAttemptSuccessful(Double xG) {
        return rnd.nextDouble() <= xG;
    }

    private MatchPhaseTransition updateMatchState(MatchPhaseTransition phaseTransition) {
        currentTime += phaseTransition.getDuration(); // Update state
        if (Set.of(State.GOAL_ATTEMPT, State.PENALTY, State.GOAL_ATTEMPT_FREEKICK)
                .contains(phaseTransition.getInitialState())) {
            if (isGoalAttemptSuccessful(phaseTransition.getxG())) {
                displayGoal(); // Display event
                possessionTeam.score(); // Update state
                changePossession(); // Update state
                return getKickOffPhaseTransition(); // Process transition
            } else {
                // Process transition
                State outcomeState = phaseTransition.getGoalAttemptOutcome() != null
                        ? phaseTransition.getGoalAttemptOutcome()
                        : phaseTransition.getEndState();
                if (outcomeState == State.OFF_TARGET) { // Update state & Process transition
                    changePossession();
                } else if (outcomeState == State.GOAL) { // Update state & Process transition
                    // possession does not change for current default state
                    // TODO take into account possession changes in a more sophisticated implementation
                    outcomeState = phaseTransition.getDefaultEndState();
                } else { // 'else' block probably redundant, but good to play it safe, especially for future changes
                    updatePossession(phaseTransition.isPossessionChanged()); // Update state
                }
                System.out.println("Goal outcome state: " + outcomeState); // Display event
                // Process transition
                Dataset potentialTransitions =
                        possessionTeam.getActionsByState(mapSetPieces(outcomeState));
                // Process transition & Update state
                return updateMatchState(potentialTransitions.getAny());
            }
        }
        updatePossession(phaseTransition.isPossessionChanged());
        return phaseTransition;
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
