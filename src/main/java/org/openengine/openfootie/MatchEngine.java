package org.openengine.openfootie;

import org.openengine.openfootie.util.Util;

import java.util.Random;

import static org.openengine.openfootie.MatchEvent.GOAL;
import static org.openengine.openfootie.Special.MAIN;

public class MatchEngine {

    private static Random rnd = new Random();

    private Team homeTeam;
    private Team awayTeam;

    private static final int DURATION = 60 * 45; // duration in seconds

    private int currentTime = 0;
    private Team initialKickOffTeam;
    private Team possessionTeam;

    public static void main(String [] args) {

        MatchFlowMatrixRepository.load();

        MatchFlowMatrix homeTeamFlowMatrix = MatchFlowMatrixRepository.L_CLF19;
        MatchFlowMatrix awayTeamFlowMatrix = MatchFlowMatrixRepository.T_CLF19;

        Team homeTeam = new Team("Liverpool", homeTeamFlowMatrix);
        Team awayTeam = new Team("Tottenham", awayTeamFlowMatrix);

        MatchEngine matchEngine = new MatchEngine(homeTeam, awayTeam);
        matchEngine.start();

        System.out.println();
        System.out.println("Final score: " + homeTeam + " - " + awayTeam + " "
                + homeTeam.getGoalsScored() + " - " + awayTeam.getGoalsScored());
    }

    public MatchEngine() {}

    public MatchEngine(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    private void tossCoin() {
        boolean homeTeamKicksOff = rnd.nextBoolean();
        initialKickOffTeam = homeTeamKicksOff ? homeTeam : awayTeam;
        possessionTeam = initialKickOffTeam;
    }

    private MatchDataElement getKickOffDataElement() {
        MatchSequence kickOffSequence = possessionTeam.getMatchFlowMatrix().getMatchSequence(MAIN);
        return getNextSequenceElement(kickOffSequence);
    }

    public void start() {

        tossCoin();

        MatchDataElement currentDataElement = getKickOffDataElement();

        while (currentTime < DURATION) {
            if (currentDataElement.type().equals(GOAL)) {
                currentDataElement = getKickOffDataElement();
            }
            MatchSequence currentMatchSequence = possessionTeam.getMatchFlowMatrix().getMatchSequence(currentDataElement.type());
            currentDataElement = getNextSequenceElement(currentMatchSequence);
            System.out.println(currentDataElement);
            currentDataElement = updateMatchState(currentDataElement);
            displayMatchState();
        }
    }

    private MatchDataElement updateMatchState(MatchDataElement currentDataElement) {
        currentTime += currentDataElement.duration();
        if (currentDataElement.type().equals(GOAL)) {
            possessionTeam.score();
            changePossession();
            return getKickOffDataElement();
        }
        updatePossession(currentDataElement.retainPossession());
        return currentDataElement;
    }

    private void updatePossession(boolean retainPossession) {
        if (!retainPossession) {
            changePossession();
        }
    }

    private void changePossession() {
        possessionTeam = (possessionTeam == homeTeam) ? awayTeam : homeTeam;
    }

    public MatchDataElement getNextSequenceElement(MatchSequence sequence) {
        return sequence.matchDataElements()[rnd.nextInt(sequence.matchDataElements().length)];
    }

    public void displayMatchState() {
        System.out.println("Time: " + Util.convertForTimer(currentTime));
        System.out.println(possessionTeam);
        System.out.println();
    }

    /**
     *
     * To be used only for testing or for when we would like a definite outcome for some reason
     *
     * @param sequence
     * @param sequenceIndex
     * @return
     */
    public MatchDataElement getNextSequenceElement(MatchSequence sequence, int sequenceIndex) {
        return sequence.matchDataElements()[sequenceIndex];
    }
}
