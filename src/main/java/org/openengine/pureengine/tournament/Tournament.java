package org.openengine.pureengine.tournament;

import org.openengine.pureengine.Team;

import java.util.ArrayList;
import java.util.List;

public class Tournament {

    private long year;
    private List<Team> participants;
    private Competition competition;
    private List<TournamentRound> rounds = new ArrayList<>();

    public Tournament(long year, List<Team> participants, Competition competition) {
        this.year = year;
        this.participants = participants;
        this.competition = competition;
        rounds.add(new TournamentRound(competition.getRounds().get(0), participants));
    }

    public void displayHeader() {
        System.out.println(competition.getName() + ", year: " + year);
    }

    public void displayParticipants() {
        System.out.println("Participants " + "(" + competition.getStartingRoundName() + "): ");
        participants.forEach(participant -> System.out.println(participant.getName()));
    }

    public void play() {
        TournamentRound currentRound = this.rounds.get(0);
        currentRound.play();
        List<Team> qualifiedTeams = currentRound.getQualifiedTeams();
        for (int i = 1; i < competition.getRounds().size(); i++) {
            rounds.add(new TournamentRound(competition.getRounds().get(i), qualifiedTeams));
            currentRound = this.rounds.get(i);
            System.out.println();
            currentRound.play();
            qualifiedTeams = currentRound.getQualifiedTeams();
        }
        System.out.println();
        System.out.println(competition.getName() +  " " + year +  " winner: " + qualifiedTeams.get(0).getName());
    }
}
