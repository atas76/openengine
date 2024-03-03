package org.openengine.pureengine.tournament;

import org.openengine.pureengine.Team;

import java.util.List;

public class Tournament {

    private long year;
    private List<Team> participants;
    private Competition competition;

    public Tournament(long year, List<Team> participants, Competition competition) {
        this.year = year;
        this.participants = participants;
        this.competition = competition;
    }

    public void displayHeader() {
        System.out.println(competition.getName() + ", year: " + year);
    }

    public void displayParticipants() {
        System.out.println("Participants " + "(" + competition.getStartingRoundName() + "): ");
        participants.forEach(participant -> System.out.println(participant.getName()));
    }
}
