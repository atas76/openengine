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

        MatchPhaseTransition kickOffPhaseTransition = getKickOffPhaseTransition();
        displayMatchInfo(kickOffPhaseTransition);

        playTimePeriod(DURATION, kickOffPhaseTransition);

        System.out.println("End of first half");

        this.possessionTeam = this.initialKickOffTeam == this.homeTeam ? this.awayTeam : this.homeTeam;

        System.out.println();

        System.out.println("*** SECOND HALF ***");

        kickOffPhaseTransition = getKickOffPhaseTransition();
        displayMatchInfo(kickOffPhaseTransition);

        currentTime = DURATION; // Reset timer for 2nd half
        playTimePeriod(DURATION * 2, kickOffPhaseTransition);
    }

    private void displayMatchInfo(MatchPhaseTransition currentPhaseTransition) {
        System.out.println();
        displayMatchState();
        System.out.println(currentPhaseTransition);
    }

    private void playTimePeriod(int duration, MatchPhaseTransition currentPhaseTransition) {
        while (currentTime < duration) {
            assert currentPhaseTransition != null;
            Dataset potentialTransitions =
                    possessionTeam.getActionsByState(mapSetPieces(currentPhaseTransition.getEndState()));
            currentPhaseTransition = potentialTransitions.getAny();
            displayMatchInfo(currentPhaseTransition);
            MatchPhaseTransition updatedPhaseTransition = updateMatchState(currentPhaseTransition);
            if (updatedPhaseTransition != currentPhaseTransition) {
                displayMatchInfo(updatedPhaseTransition);
            }
            currentPhaseTransition = updatedPhaseTransition;
        }
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

    private MatchPhaseTransition updateMatchState(MatchPhaseTransition phaseTransition) {
        currentTime += phaseTransition.getDuration();
        if (Set.of(State.GOAL_ATTEMPT, State.PENALTY, State.GOAL_ATTEMPT_FREEKICK)
                .contains(phaseTransition.getInitialState())) {
            if (isGoalAttemptSuccessful(phaseTransition.getxG())) {
                displayGoal();
                possessionTeam.score();
                changePossession();
                return getKickOffPhaseTransition();
            } else {
                State outcomeState = phaseTransition.getGoalAttemptOutcome() != null
                        ? phaseTransition.getGoalAttemptOutcome()
                        : phaseTransition.getEndState();
                if (outcomeState == State.OFF_TARGET) {
                    changePossession();
                } else if (outcomeState == State.GOAL) {
                    // possession does not change for current default state
                    // TODO take into account possession changes in a more sophisticated implementation
                    outcomeState = phaseTransition.getDefaultEndState();
                } else { // 'else' block probably redundant, but good to play it safe, especially for future changes
                    updatePossession(phaseTransition.isPossessionChanged());
                }
                System.out.println("Goal outcome state: " + outcomeState);
                Dataset potentialTransitions =
                        possessionTeam.getActionsByState(mapSetPieces(outcomeState));
                return potentialTransitions.getAny();
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
