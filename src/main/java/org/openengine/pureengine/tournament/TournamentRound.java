package org.openengine.pureengine.tournament;

import org.openengine.pureengine.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TournamentRound {

    private CompetitionRound competitionRound;

    private List<Team> participants;
    private List<Fixture> fixtures = new ArrayList<>();

    public TournamentRound(CompetitionRound competitionRound, List<Team> participants) {
        this.competitionRound = competitionRound;
        this.participants = participants;
    }

    public void play() {
        if (participants.isEmpty()) {
            System.out.println("No participants in this round");
        }

        draw();
        displayFixtures();
    }

    public void draw() {
        List<Team> pool = new ArrayList<>(participants);
        Collections.shuffle(pool);

        for (int i = 0; i < pool.size(); i += 2) {
            this.fixtures.add(new Fixture(pool.get(i), pool.get(i + 1)));
        }
    }

    public void displayFixtures() {
        System.out.println(this.competitionRound.getName() + " fixtures:");
        this.fixtures.forEach(System.out::println);
    }
}
