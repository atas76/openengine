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
        play(false);
    }

    public void play(boolean silentMode) {

        draw();

        if (!silentMode) {
            System.out.println(participants.size() > 2
                    ? this.competitionRound.getName() + " fixtures:"
                    : this.competitionRound.getName() + ":");
            displayFixtures();
            System.out.println();
        }

        playFixtures();

        if (!silentMode) {
            System.out.println(participants.size() > 2 ? this.competitionRound.getName() + " results: " : "Result:");
            displayFixtures();
        }
    }

    public void draw() {
        List<Team> pool = new ArrayList<>(participants);
        Collections.shuffle(pool);

        for (int i = 0; i < pool.size(); i += 2) {
            this.fixtures.add(new Fixture(pool.get(i), pool.get(i + 1)));
        }
    }

    public void displayFixtures() {
        this.fixtures.forEach(System.out::println);
    }

    public void playFixtures() {
        fixtures.forEach(Fixture::play);
    }

    public List<Team> getQualifiedTeams() {
        return this.fixtures.stream().map(Fixture::getWinningTeam).toList();
    }
 }
