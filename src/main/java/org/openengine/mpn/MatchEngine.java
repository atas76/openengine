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

        MatchPhaseTransition currentPhaseTransition = getKickOffPhaseTransition();
        System.out.println(currentPhaseTransition);

        while (currentTime < DURATION) {
            assert currentPhaseTransition != null;
            Dataset potentialTransitions =
                    possessionTeam.getActionsByState(mapSetPieces(currentPhaseTransition.getEndState()));
            currentPhaseTransition = potentialTransitions.getAny();
            displayMatchState();
            System.out.println(currentPhaseTransition);
            currentPhaseTransition = updateMatchState(currentPhaseTransition);
        }
    }

    private State mapSetPieces(State openPlayState) {
        return switch(openPlayState) {
            case OFF_TARGET -> State.GOAL_KICK;
            case FOUL, OFFSIDE -> State.FREEKICK;
            default -> openPlayState;
        };
    }

    private MatchPhaseTransition updateMatchState(MatchPhaseTransition phaseTransition) {
        currentTime += phaseTransition.getDuration();
        if (Set.of(State.GOAL_ATTEMPT, State.PENALTY, State.GOAL_ATTEMPT_FREEKICK)
                .contains(phaseTransition.getInitialState())) {
            State goalAttemptOutcome = (phaseTransition.getGoalAttemptOutcome() != null)
                    ? phaseTransition.getGoalAttemptOutcome()
                    : phaseTransition.getEndState(); // TODO replace with dynamic calculation
            if (goalAttemptOutcome == State.GOAL) {
                displayGoal();
                possessionTeam.score();
                changePossession();
                return getKickOffPhaseTransition();
            } else {
                State outcomeState = phaseTransition.getEndState();
                if (phaseTransition.getEndState() == State.OFF_TARGET) {
                    changePossession();
                } else {
                    outcomeState = phaseTransition.getGoalAttemptOutcome();
                }
                updatePossession(phaseTransition.isPossessionChanged());
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
        System.out.println(possessionTeam + ": GOAL");
    }

    public void displayMatchState() {
        System.out.println(Util.convertForTimer(currentTime) + ": " + possessionTeam);
        System.out.println();
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
